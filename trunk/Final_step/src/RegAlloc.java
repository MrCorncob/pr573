import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

public class RegAlloc {
	LinkedList<IrNode> irList;
	IrNode node;
	CodeGen codeGen;

	Map<String, String>  varReg;  // <var, reg> hash map
	Map<String, String>  regVar;  // <reg, var> hash map
	Map<String, Integer> dirty;   // <string, int>
	int regNum;
	
	Function currentFunc;
	Map<String, Symbol> currentSymTab;
	Map<String, String> varname;
	
	public RegAlloc(int regnb, CodeGen code){
		codeGen = code;
		irList = code.irList;
		
		varReg = new LinkedHashMap<String, String>();
		regVar = new LinkedHashMap<String, String>();
		dirty  = new LinkedHashMap<String, Integer>();
		regNum = regnb;
		
		currentFunc = null;
	}
	
	private void print(String code){
		System.out.println(code);
	}
	
	public void findNeighbor(){  // find pred and succ for each node
		Map<String, IrNode> jmper = new LinkedHashMap<String, IrNode>();
		Map<String, IrNode> jmpee = new LinkedHashMap<String, IrNode>();
		String[] part;
		IrNode pre_node = null;
		IrNode jump;
		for(IrNode node : irList){
			/* 1. when pre_node = RET, don't link
			 * 2. when pre_node = JUMP, don't link
			 */
			if(pre_node!= null && !pre_node.irCode.startsWith("RET") && !pre_node.irCode.startsWith("JUMP")){
				pre_node.succ = node;
				node.pred = pre_node;
			}
			if(node.irCode.startsWith("LABEL")){
				// 1. Look up for jmper
				part = node.irCode.split(" ");
				if((jump = jmper.get(part[1])) != null){
					jump.succ2 = node;
					node.pred2 = jump;
				}
				else
					jmpee.put(part[1], node);
			}
			else if(node.irCode.startsWith("JUMP")){
				part = node.irCode.split(" ");
				if((jump = jmpee.get(part[1])) != null){
					jump.pred2 = node;
					node.succ2 = jump;
				}
				else
					jmper.put(part[1], node);
			}
			else if(node.irCode.startsWith("GE")  ||
			        node.irCode.startsWith("LE")  ||
			        node.irCode.startsWith("NE")){
				part = node.irCode.split(" ");
				if((jump = jmpee.get(part[3])) != null){
					jump.pred2 = node;
					node.succ2 = jump;
				}
				else
					jmper.put(part[3], node);
			}
			pre_node = node;
		}
		
		/* traverse and print */
		for(IrNode node : irList){
			String toPrint;
			String predCode = " {PRED nodes:"; 
			String succCode = " {SUCC nodes:";
			if(node.pred2 != null) predCode += " "+node.pred2.irCode+" ";
			if(node.pred  != null) predCode += " "+node.pred.irCode+" ";
			predCode += "}";
			if(node.succ2 != null) succCode += " "+node.succ2.irCode+" ";
			if(node.succ  != null) succCode += " "+node.succ.irCode+" ";
			succCode += "}";
			toPrint = "; "+node.irCode+predCode+succCode;
			System.out.println(toPrint);
		}
	}
	
	public void kill_gen(){
		IrNode last_node = irList.getLast();
		IrNode node;
		String code;
		String[] part;
		ListIterator<IrNode> it= irList.listIterator();
		while(it.hasNext())  // point to the end
			it.next();
		while(it.hasPrevious()){
			node = it.previous();
			node.kills = 0;
			node.gens = 0;
			code = node.irCode;
			part = code.split(" ");
			if(code.startsWith("ADD") || code.startsWith("SUB") || code.startsWith("MULT") || code.startsWith("DIV")){
				/*if(part[1].startsWith("$T")){
					node.gen[node.gens++] = part[1];
				}
				if(part[2].startsWith("$T")){
					node.gen[node.gens++] = part[2];
				}
				if(part[3].startsWith("$T")){
					node.kill = part[3];
					node.kills ++;
				}*/
				node.gen[node.gens++] = part[1];
				node.gen[node.gens++] = part[2];
				node.kill = part[3];
				node.kills ++;
			}
			else if(code.startsWith("STORE")){
				/*if(part[1].startsWith("$T")){
					node.gen[node.gens++] = part[1];
				}*/
				if(part[1].startsWith("$T") || part[1].startsWith("$L") || !Character.isDigit(part[1].charAt(0))){
					node.gen[node.gens++] = part[1];
				}
				node.kill = part[2];
				node.kills ++;
			}
			else if(code.startsWith("LOAD")){
				/*if(part[2].startsWith("$T")){
					node.kill = part[2];
					node.kills ++;
				}*/
				node.kill = part[2];
				node.kills ++;
			}
			if(code.startsWith("GE") || code.startsWith("LE") || code.startsWith("NE")){
				/*if(part[1].startsWith("$T")){
					node.gen[node.gens++] = part[1];
				}
				if(part[2].startsWith("$T")){
					node.gen[node.gens++] = part[2];
				}*/
				node.gen[node.gens++] = part[1];
				node.gen[node.gens++] = part[2];
			}
			if(code.startsWith("READ") || code.startsWith("WRITE")){
				/*if(part[1].startsWith("$T")){
					node.gen[node.gens++] = part[1];
				}*/
				node.gen[node.gens++] = part[1];
			}
		}
	}
	
	public void liveAnal(){
		int i;
		IrNode node;
		ListIterator<IrNode> it= irList.listIterator();
		while(it.hasNext()) 
			it.next();
		while(it.hasPrevious()){
			node = it.previous();
			//if(node.pred2 != null || node.succ2 != null)
				//continue;
			if(node.pred == null && node.pred2 == null)
				continue;
			Map<String, String> tmmap = new LinkedHashMap<String, String>();
			if(!node.liveVar.isEmpty()){
				tmmap.putAll(node.liveVar);
			}
			if(node.kills >0){
				tmmap.remove(node.kill);
			}
			for(i=0; i < node.gens; i++){
				if(!tmmap.containsKey(node.gen[i]))
					tmmap.put(node.gen[i], node.gen[i]);
			}
			if(node.pred != null)
				node.pred.liveVar.putAll(tmmap);
			if(node.pred2 != null)
				node.pred2.liveVar.putAll(tmmap);
		}
	}
	
	private String allocate(String var){
		/* get a free reg
		 * if there is free r
		 *     return r
		 * else
		 *     get a farthest var(in r) 
		 *     free(r)
		 *     return r
		 */
		int i;
		String reg = null;
		// if there exists free r, return it
		for(i=regNum-1; i >=0; i--){
			reg = "r"+Integer.toString(i);
			if(!regVar.containsKey(reg)){
				regVar.put(reg, var);
				varReg.put(var, reg);
				return reg;
			}
		}
		// get farthest var
		IrNode nd;
		String[] varsInReg = new String[regNum];
		int remainVars = regNum;
		for(i=0; i< regNum; i++){
			varsInReg[i] = regVar.get("r"+Integer.toString(i));
		}
		for(nd=node; nd != null; nd= nd.succ){
			// check for basic block boundry
			// minus gens from varsInReg
			for(int j=0; j <nd.gens; j++){
				// minus every generated from varsInReg
				// until only one left
				for(int k=0; k < regNum; k++){
					if(nd.gen[j].equals(null))
						break;
					if(nd.gen[j].equals(varsInReg[k])){
						varsInReg[k] = null;
						remainVars --;
						break;
					}
				}
				if(remainVars == 1)
					break;
			}
			if(remainVars == 1)
				break;
		}
		// free the farthest var's r
		for(i=0; i <regNum; i++){
			if(varsInReg[i] != null){
				reg = "r"+Integer.toString(i);
				break;
			}
		}
		if(reg == null){
			print("allocate: no free reg found");
			return "ERROR";
		}
		
		// spill reg
		String s = regVar.get(reg);
		if(s.startsWith("$"))
			print("move "+reg+" "+varname.get(s));
		else
			print("move "+reg+" "+regVar.get(reg));
		free(reg);
		// associate freed reg with var
		varReg.put(var, reg);
		regVar.put(reg, var);
		return reg;
	}
	
	private String ensure(String var, String printName){
		/* ensure a variable
		 * if there is already a mapping r in varReg
		 *     return r
		 * else
		 *     allocate a new reg r for var
		 *     load var r
		 *     return r
		 */
		String reg;
		// if there exists r, return it
		if(varReg.containsKey(var)){
			return varReg.get(var);
		}
		// allocate a new reg r
		reg = allocate(var);
		//print("In ensure:");
		varReg.put(var, reg);
		//print("varReg.put("+var+","+reg+");");
		regVar.put(reg, var);
		//print("regVar.put("+reg+","+var+");");
		//print("{ r0->"+ regVar.get("r0")+" r1->"+ regVar.get("r1")+ " r2->"+ regVar.get("r2")+ " r3->"+ regVar.get("r3")+ " }");
		if(printName != null){
			if(printName.startsWith("$T"))
				print("move "+ varname.get(printName)+ " "+ reg);
			else
				print("move "+ printName+ " "+reg);
		}
		return reg;
	}
	
	private String desure(String var, IrNode node){
		/* counter-ensure
		 * if var is dead, free var, return its reg
		 * else return null
		 */
		if(var== null)
			return null;
		if(node.liveVar.containsKey(var))
			return null;
		String r = varReg.get(var);
		/*if(r != null)
			print(r);
		else
			print("r==null");*/
		free(r);
		return r;
	}
	
	private void free(String reg){
		String var;
		if(reg == null)
			return;
		if(dirty.containsKey(reg)){
			if(dirty.get(reg) == 1){
			// generate store operation
				print("; dirty");
				print("move "+ reg+ " "+ regVar.get(reg));
			}
		}
		var = regVar.get(reg);
		regVar.remove(reg);
		varReg.remove(var);
	}
	
	private String switchReg(String fromVar, String toVar){
		// switch fromVar's Reg to toVar
		String reg = varReg.get(fromVar);
		// remove old map
		varReg.remove(fromVar);
		regVar.remove(reg);
		// add new map
		varReg.put(toVar, reg);
		regVar.put(reg, toVar);
		return reg;
	}
	
	public void allocReg(){
		/* For every instruction
		 * 1. ensure all gens
		 * 2. switch first operand's reg to result
		 */
		int i;
		String[] part, o_part;
		String code;
		String cmd = null;
		String newReg;
		String rx, ry, rz;
		String op1, op2, label;
		varname = new LinkedHashMap<String, String>();
		
		currentSymTab = codeGen.globalSymTab;
		Vector v = new Vector(currentSymTab.keySet());
//		Collections.sort(v);
		Iterator it = v.iterator();
		Symbol sym;
		/* Variable declare */
		while(it.hasNext()){
			String var = it.next().toString();
			sym = currentSymTab.get(var);
			if(sym.type.equals("INT") || sym.type.equals("FLOAT"))
				print("var " + var);
			else if(sym.type.equals("STRING"))
				print("str " + var + " " + sym.str);
			else
				System.out.println("unknown var type: "+ sym.type);
		}
		/* initializatin - push stack */
		print("push");
		print("push r0");
		print("push r1");
		print("push r2");
		print("push r3");
		print("jsr main");
		print("sys halt");
		
		ListIterator<IrNode> lit= irList.listIterator();
		while(lit.hasNext()){
			node = lit.next();
			op2 = null;
			code   = node.irCode;
//			print("{ r0->"+ regVar.get("r0")+" r1->"+ regVar.get("r1")+ " r2->"+ regVar.get("r2")+ " r3->"+ regVar.get("r3")+ " }");
			print("; "+code);
/*			print(" LiveVar");
			Vector lv = new Vector(node.liveVar.keySet());
			Iterator lvit = lv.iterator();
			while(lvit.hasNext()){
				print("  "+ lvit.next().toString());
			}
*/			part   = code.split(" ");
			o_part = code.split(" ");
			// CMD Replacement
			/* OPERATOR */
			if(part[0].compareTo("ADDI")==0) cmd = "addi";
			else if(part[0].compareTo("ADDF")==0) cmd = "addr";
			else if(part[0].compareTo("SUBI")==0) cmd = "subi";
			else if(part[0].compareTo("SUBF")==0) cmd = "subr";
			else if(part[0].compareTo("MULTI")==0) cmd = "muli";
			else if(part[0].compareTo("MULTF")==0) cmd = "mulr";
			else if(part[0].compareTo("DIVI")==0) cmd = "divi";
			else if(part[0].compareTo("DIVF")==0) cmd = "divr";
			/* STORE */
			else if(part[0].compareTo("STOREI")==0) cmd = "move";
			else if(part[0].compareTo("STOREF")==0) cmd = "move";
			/* COND */
			else if(part[0].compareTo("GE")==0) cmd = "jge";
			else if(part[0].compareTo("LE")==0) cmd = "jle";
			else if(part[0].compareTo("NE")==0) cmd = "jne";
			else if(part[0].compareTo("JUMP")==0) cmd = "jmp";
			/* I/O */
			else if(part[0].compareTo("READI")==0) cmd = "readi";
			else if(part[0].compareTo("READF")==0) cmd = "readr";
			else if(part[0].compareTo("READS")==0) cmd = "reads";
			else if(part[0].compareTo("WRITEI")==0) cmd = "writei";
			else if(part[0].compareTo("WRITEF")==0) cmd = "writer";
			else if(part[0].compareTo("WRITES")==0) cmd = "writes";
			/* FUNCTION */
			else if(part[0].compareTo("LABEL")==0) cmd = "label";
			else if(part[0].compareTo("JSR")==0) cmd = "jsr";
			else if(part[0].compareTo("PUSH")==0) cmd = "push";
			else if(part[0].compareTo("POP")==0) cmd = "pop";
			else if(part[0].compareTo("LINK")==0) cmd = "link";
			else if(part[0].compareTo("RET")==0) cmd = "ret";
			
			/* Register Replacement
			 *  $Tx    -> $(-lNum-x)
			 *  $Lx    -> $(-x)
			 *  RetAddr-> $0
			 *  StPtr  -> $1
			 *  Rx     -> $(5-x)
			 *  $Px    -> $(6+pNum-x)
			 *  $R     -> $(6+pNum)
			 */
			for(i=0; i < part.length; i++){
				if(currentFunc == null)
					break;
				int lNum = currentFunc.lNum;
				int pNum = currentFunc.pNum;
				//System.out.println("pNum: "+ Integer.toString(pNum));
				int num_i;
				String num_s;
				if(part[i].startsWith("$T")){
					// No Need to Translate $T
					num_i = Integer.parseInt(part[i].substring(2));
					varname.put(o_part[i], "$"+Integer.toString(-1*num_i-lNum));
				}
				else if(part[i].startsWith("$L")){
					num_i = Integer.parseInt(part[i].substring(2));
					//System.out.println("num_i: "+ i+ " " + Integer.toString(num_i));
					part[i] = "$"+ Integer.toString(-1*num_i);
					varname.put(o_part[i], part[i]);
				}
				else if(part[i].startsWith("$P")){
					num_i = Integer.parseInt(part[i].substring(2));
					//System.out.println("num_i: "+ i+ " " + Integer.toString(num_i));
					part[i] = "$"+ Integer.toString(6+ pNum - num_i);
					varname.put(o_part[i], part[i]);
				}
				else if(part[i].equals("$R")){
					part[i] = "$"+ Integer.toString(6+ pNum);
					varname.put(o_part[i], part[i]);
				}
			}
			
			/* OP */
			if(code.startsWith("ADD") || code.startsWith("MULT")){
				rx = ensure(o_part[1], part[1]);
				//print("move "+ part[1]+ " "+ rx);
				ry = ensure(o_part[2], part[2]);
				//print("move "+ part[2]+ " "+ rx);
				desure(o_part[1], node);
				desure(o_part[2], node);
				rz = ensure(o_part[3], null);
				if(rz.equals(rx)){
					print(cmd+ " "+ ry+ " "+ rz);
				}
				else if(rz.equals(ry)){
					print(cmd+ " "+ rx+ " "+ rz);
				}
				else{
					print("move "+ rx+ " "+ rz);
					print(cmd+ " "+ ry+ " "+ rz);
				}
			}else if(code.startsWith("SUB") || code.startsWith("DIV")){
				rx = ensure(o_part[1], part[1]);
				//print("move "+ part[1]+ " "+ rx);
				ry = ensure(o_part[2], part[2]);
				//print("move "+ part[2]+ " "+ rx);
				rz = desure(o_part[1], node);
				desure(o_part[2], node);
				if(rx.equals(rz)){
					// assign rx to node.kill
					varReg.put(o_part[3], rx);
					regVar.put(rx, o_part[3]);
				}else{
					// spill node.gen[0] and assign rz to node.kill
					if(node.liveVar.containsKey(o_part[1])){
						print("move "+ rx+" "+ part[1]);
					}
					varReg.put(o_part[3], rx);
					regVar.put(rx, o_part[3]);
				}
				print(cmd+ " "+ ry+ " "+ rx);
			}else if(code.startsWith("STORE")){
				/* STORE xx yy
				 * 1. if xx is $T, ensure(xx); if xx is dead, free(xx)
				 * 2. ensure(yy)
				 */
				if(node.gens == 1){    // if xx is $T
					rx = ensure(o_part[1], null);
					desure(o_part[1], node);
					ry = ensure(o_part[2], null);
					desure(o_part[2], node);
					if(!rx.equals(ry))
						print("move "+ rx+ " "+ ry);
					if(!node.liveVar.containsKey(o_part[2]) || o_part[2].equals("$R"))
						print("move "+ ry+ " "+ part[2]);
				}
				else{  // STORE 1, %Tx
					ry = ensure(o_part[2], null);
					desure(o_part[2], node);
					print("move "+ part[1]+ " "+ ry);
					if(o_part[2].equals("$R"))
						print("move "+ ry+ " "+ part[2]);
				}
			}
			/* CONDITIONAL BRANCH */
			else if(code.startsWith("GE") || code.startsWith("LE") || code.startsWith("NE")){
				op1 = ensure(o_part[1], part[1]);
				op2 = ensure(o_part[2], part[2]);
				label = part[3];
				Symbol s;
				if((s=currentSymTab.get(o_part[1])) == null){
					if((s=codeGen.globalSymTab.get(o_part[1])) == null)
						System.out.println("error: cannot find symbol in symTab\n");
				}
				if(s.type.equals("INT")){
//					System.out.println("TEST symbol resolve: "+ symTab.resolve(part[1]).getType());
					print("cmpi " + op1 + " " + op2);
					//print(cmd + " " + label);  -> put after SPILL ALL
				}else if(s.type.equals("FLOAT")){
					print("cmpr " + op1 + " " + op2);
					//print(cmd + " " + label);  -> put after SPILL ALL
				}
			}/*else if(code.startsWith("JUMP")){
				label = part[1];
				print(cmd + " " +  label);
			}*/
			/* I/O */
			else if(code.startsWith("READ")){
				/*if(varReg.containsKey(o_part[1])){
					op1 = varReg.get(o_part[1]);
					desure(o_part[1], node);
				}
				else
					op1 = o_part[1];*/
				// distinguish between string and var
				// if string, no ensure, otherwise ensure
				if(codeGen.globalSymTab.containsKey(o_part[1])){
					Symbol s = codeGen.globalSymTab.get(o_part[1]);
					if(s.type.equals("STRING"))
						print("sys "+ cmd+ " "+ o_part[1]);
					else{
						op1 = ensure(o_part[1], part[1]);
						desure(o_part[1], node);
						print("sys " + cmd + " " + op1);
					}
				}else if(!o_part[1].equals(part[1])){
					print("sys " + cmd + " " + part[1]);
				}else{
					op1 = ensure(o_part[1], part[1]);
					desure(o_part[1], node);
					print("sys " + cmd + " " + op1);
				}
			}
			else if(code.startsWith("WRITE")){
				/*if(varReg.containsKey(o_part[1])){
					op1 = varReg.get(o_part[1]);
					desure(o_part[1], node);
				}
				else
					op1 = o_part[1];*/
				// distinguish between string and var
				// if string, no ensure, otherwise ensure
				if(codeGen.globalSymTab.containsKey(o_part[1])){
					Symbol s = codeGen.globalSymTab.get(o_part[1]);
					if(s.type.equals("STRING"))
						print("sys "+ cmd+ " "+ o_part[1]);
					else{
						op1 = ensure(o_part[1], part[1]);
						desure(o_part[1], node);
						print("sys " + cmd + " " + op1);
					}
				}else{
					op1 = ensure(o_part[1], part[1]);
					desure(o_part[1], node);
					print("sys " + cmd + " " + op1);
				}
			}
			/* FUNCTION or LABEL */
			else if(code.startsWith("LABEL")){
				label = part[1];
				if(codeGen.funcTab.get(label) != null){
					currentFunc = codeGen.funcTab.get(label);
					currentSymTab = currentFunc.symTab;
					print(cmd + " "+ label);
					varname.clear();
					//System.out.println("cur Func : " + part[1]);
				}
			}else if(code.startsWith("PUSH")){
				if(code.equals("PUSH"))
					print(cmd);
				else{
					/*if(varReg.containsKey(o_part[1]))
						print(cmd + " " + varReg.get(o_part[1]));
					else
						print(cmd + " " + o_part[1]);*/
					op1 = ensure(o_part[1], part[1]);
					desure(o_part[1], node);
					print(cmd +" "+ op1);
				}
			}else if(code.startsWith("POP")){
				if(code.equals("POP"))
					print(cmd);
				else{
					op1 = ensure(o_part[1], null);
					print(cmd + " " + op1);
				}
			}else if(code.startsWith("LINK")){
				if(currentFunc == null)
					System.out.println("cur Func NULL !\n");
				else{
					print(cmd + " "+ Integer.toString(currentFunc.lNum+currentFunc.temp_index-1));
				}
			}else if(code.startsWith("RET")){
				print("unlnk");
				print("ret");
			}
			
			// spill all, if at end of basic block
			if(node.succ2 != null || node.pred2 != null || node.irCode.startsWith("JSR")){
				// for now, save all reg, don't consider dirty
				String s, os;
//				print("; SPILL ALL");
				for(i=0; i< regNum; i++){
					s = regVar.get("r"+Integer.toString(i));
//					print("r"+ Integer.toString(i)+"- "+s);
					if(varname.containsKey(s)){
						//if(node.liveVar.containsKey(s))
						print("move "+ "r"+Integer.toString(i)+" "+ varname.get(s));						
					}
					else if((currentSymTab.containsKey(s) || codeGen.globalSymTab.containsKey(s)) && !s.startsWith("$")){
						// variable like a, b, c
						print("move "+ " r"+ Integer.toString(i)+ " "+ s);
					}
					/*else if(s != null && currentFunc != null){
						if(s.startsWith("$T")){
							int num_i = Integer.parseInt(s.substring(2));
							int lNum = currentFunc.lNum;
							print("move "+ " r"+ Integer.toString(i)+ " $"+ Integer.toString(-1*num_i-lNum));
						}
					}*/
					else if(node.liveVar.containsKey(s)){
						print("move "+ "r"+Integer.toString(i)+" "+ s);
					}
				}
				varReg.clear();
				regVar.clear();
			}
			
			if(code.startsWith("GE") || code.startsWith("LE") || code.startsWith("NE")){
				label = part[3];
				print(cmd + " " + label);
			}else if(code.startsWith("JUMP")){
				label = part[1];
				print(cmd + " " +  label);
			}else if(code.startsWith("LABEL")){
				label = part[1];
				if(codeGen.funcTab.get(label) == null){
					print(cmd + " "+ label);
				}
			}else if(code.startsWith("JSR")){
				label = part[1];
				print("push r0");
				print("push r1");
				print("push r2");
				print("push r3");
				print(cmd + " " + label);
				print("pop r3");
				print("pop r2");
				print("pop r1");
				print("pop r0");
			}
		}
		print("end");
	}
	
	public void translate(){
		findNeighbor();
		kill_gen();
		liveAnal();
		allocReg();
	}
}
