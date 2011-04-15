import java.util.*;



public class Translator {
	int reg_index;
	String cmd;
	String tiny;
	String newReg;
	Map<String, String> regMap;
	
	Function currentFunc;
	Map<String, Symbol> currentSymTab;
	
	public Translator() {
		regMap = new LinkedHashMap<String, String>();
		currentFunc = null;
	};
	
	void print_code(String code){
		System.out.println(code);
	}
	String getReg(){
		return "r" + Integer.toString(reg_index++);
	}
	public void Translate(CodeGen code, LinkedList<IrNode> irList){
//		for(String varName: symTab.symbols.keySet())
//			System.out.println(varName);
		currentSymTab = code.globalSymTab;
		Vector v = new Vector(currentSymTab.keySet());
//		Collections.sort(v);
		Iterator it = v.iterator();
		Symbol sym;
		
		/* Variable declare */
		while(it.hasNext()){
			String var = it.next().toString();
			sym = currentSymTab.get(var);
			if(sym.type.equals("INT") || sym.type.equals("FLOAT"))
				print_code("var " + var);
			else if(sym.type.equals("STRING"))
				print_code("str " + var + " " + sym.str);
			else
				System.out.println("unknown var type: "+ sym.type);
		}
		/* initializatin - push stack */
		print_code("push");
		print_code("push r0");
		print_code("push r1");
		print_code("push r2");
		print_code("push r3");
		print_code("jsr main");
		print_code("sys halt");
		
		/* code translation */
		
		for(IrNode node : irList){
			System.out.println(";"+node.irCode+" ");
			String[] part;
			/* o_part[] is exactly the same as part[] here
			 * but since part[] will be changed later, we need to a backup
			 * o_part[], to do some look up work
			 */
			String[] o_part;
			tiny = new String(node.irCode);
			part = tiny.split(" ");
			o_part = tiny.split(" ");
			// CMD Replacement
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
			for(int i=0; i < part.length; i++){
				if(currentFunc == null)
					break;
				int lNum = currentFunc.lNum;
				int pNum = currentFunc.pNum;
				//System.out.println("pNum: "+ Integer.toString(pNum));
				int num_i;
				String num_s;
				if(part[i].startsWith("$T")){
					// No Need to Translate $T
				}
				else if(part[i].startsWith("$L")){
					num_i = Integer.parseInt(part[i].substring(2));
					//System.out.println("num_i: "+ i+ " " + Integer.toString(num_i));
					part[i] = "$"+ Integer.toString(-1*num_i);
				}
				else if(part[i].startsWith("$P")){
					num_i = Integer.parseInt(part[i].substring(2));
					//System.out.println("num_i: "+ i+ " " + Integer.toString(num_i));
					part[i] = "$"+ Integer.toString(6+ pNum - num_i);
				}
				else if(part[i].equals("$R")){
					part[i] = "$"+ Integer.toString(6+ pNum);
				}
			}
			
			/* OP */
			/* FIRST check whether how many temporaries are there in the part[]
			 * 1. 2 VALUE
			 * 		1.1 make newReg and move part[1] into it
			 * 		1.2 add part[2] to newReg
			 * 2. 1 VALUE and 1 TEMP
			 * 	 	2.1 make newReg and move part[1] into it
			 * 		2.2 add part[2] to newReg
			 * 3. 1 TEMP and 1 VALUE
			 *		3.1 add part[2] to part[1]
			 *		3.2 put part[1] into part[3] in MAP  
			 * 4. 2 TEMP
			 * 		4.1 add part[2] to newReg
			 * 		4.2 put part[1] into part[3] in MAP
			 */
			int[][] oprand_pos = {{0, 0}, {0, 0}};	
			if(tiny.startsWith("ADD") || tiny.startsWith("SUB") || tiny.startsWith("MULT") || tiny.startsWith("DIV")){

				if(part[1].startsWith("$T")){
					if(part[2].startsWith("$T")){
						part[1] = regMap.get(part[1]);
						part[2] = regMap.get(part[2]);
						oprand_pos[1][1]++;
					}else{
						part[1] = regMap.get(part[1]);
						oprand_pos[1][0]++;
					}
				}else{
					if(part[2].startsWith("$T")){
						part[2] = regMap.get(part[2]);
						oprand_pos[0][1]++;
					}else
						oprand_pos[0][0]++;
				}
				
//				System.out.println("&&&& PART TEST: " + part[0] + " and " + part[1] + " and " + part[2]);
//				System.out.println("-----position: "+ oprand_pos[0][1] );
			
				if(oprand_pos[0][0] == 1){
					newReg = getReg();
					print_code("move " + part[1] + " " + newReg);
					print_code(cmd + " " + part[2] + " " + newReg);
					regMap.put(part[3],	newReg);
				}else if(oprand_pos[0][1] == 1){
					newReg = getReg();
					print_code("move " + part[1] + " " + newReg);
					print_code(cmd + " " + part[2] + " " + newReg);
					regMap.put(part[3], newReg);
				}else if(oprand_pos[1][0] == 1){
					print_code(cmd + " " + part[2] + " "+ part[1]);
					regMap.put(part[3], part[1]);
				}else if(oprand_pos[1][1] == 1){
					print_code(cmd + " " + part[2] + " " + part[1]);
					regMap.put(part[3], part[1]);
				}
				
	
			/* STORE 
			 * 1. STORE TEM VAR
			 * 		1.1 get REG for TEM 
			 * 		1.2 move REG into VAR
			 * 2. STORE VAR TEM
			 * 		2.1 make REG for TEM
			 * 		2.2 move VAR into REG
			 * 3. STORE VAR VAR - at least on operand has to be a REG
			 * 		3.1 make REG for first VAR
			 * 		3.2 move first VAR into REG
			 * 		3.3 move REG into second VAR
			 * */
			}else if(tiny.startsWith("STORE")){
					if(part[1].startsWith("$T")){
						part[1]= regMap.get(part[1]);
						print_code(cmd + " " + part[1] + " " + part[2]);
					}else if(part[2].startsWith("$T")){
						newReg = getReg();
						regMap.put(part[2], newReg);
						part[2] = newReg;
						print_code(cmd + " " + part[1] + " " + part[2]);
					}else{
						newReg = getReg();
						print_code(cmd + " " + part[1] + " " + newReg);
						print_code(cmd + " " + newReg + " " + part[2]);
					}

			}
			/* CONDITIONAL BRANCH */
			else if(tiny.startsWith("GE") || tiny.startsWith("LE") || tiny.startsWith("NE")){
//				for(int i=0 ; i < part.length ; i++){
//					System.out.println("TEST cond translate: "+part[i]);
//				}
				/* check if the operand has no registers, if so getReg() */
				if(regMap.get(part[2])==null){
					regMap.put(part[2], getReg());
					print_code("move "+part[2]+" " +regMap.get(part[2]));
				}
				/* Here is where we use o_part[] */
				if(currentSymTab.get(o_part[1]).type.equals("INT")){
//					System.out.println("TEST symbol resolve: "+ symTab.resolve(part[1]).getType());
					print_code("cmpi " + part[1] + " " + regMap.get(part[2]));
					print_code(cmd + " " + part[3]);
				}else if(currentSymTab.get(o_part[1]).type.equals("FLOAT")){
					print_code("cmpr " + part[1] + " " + regMap.get(part[2]));
					print_code(cmd + " " + part[3]);
				}
			}else if(tiny.startsWith("JUMP")){
				print_code(cmd + " " +  part[1]);
			}
			/* I/O */
			else if(tiny.startsWith("WRITE") || tiny.startsWith("READ")){
				print_code("sys " + cmd + " " + part[1]);
			}
			/* FUNCTION */
			else if(tiny.startsWith("LABEL")){
				print_code(cmd + " "+ part[1]);
				if(code.funcTab.get(part[1]) != null){
					currentFunc = code.funcTab.get(part[1]);
					currentSymTab = currentFunc.symTab;
					//System.out.println("cur Func : " + part[1]);
				}
			}else if(tiny.startsWith("JSR")){
				print_code("push r0");
				print_code("push r1");
				print_code("push r2");
				print_code("push r3");
				print_code(cmd + " " + part[1]);
				print_code("pop r3");
				print_code("pop r2");
				print_code("pop r1");
				print_code("pop r0");
			}else if(tiny.startsWith("PUSH")){
				if(tiny.equals("PUSH"))
					print_code(cmd);
				else{
					if(regMap.get(part[1]) != null)
						print_code(cmd + " " + regMap.get(part[1]));
					else
						print_code(cmd + "" + part[1]);
				}
			}else if(tiny.startsWith("POP")){
				if(tiny.equals("POP"))
					print_code(cmd);
				else{
					newReg = getReg();
					print_code(cmd + " " + newReg);
					regMap.put(part[1], newReg);
				}
			}else if(tiny.startsWith("LINK")){
				if(currentFunc == null)
					System.out.println("cur Func NULL !\n");
				else{
					print_code(cmd + " "+ Integer.toString(currentFunc.lNum));
				}
			}else if(tiny.startsWith("RET")){
				print_code("unlnk");
				print_code("ret");
			}
		}
		print_code("end");		
	}
	
	
}
