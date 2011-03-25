package test;

import java.util.*;



public class Translator {
	int reg_index;
	String cmd;
	String tiny;
	String newReg;
	
	public void Translator() {};
	
	void print_code(String code){
		System.out.println(code);
	}
	String getReg(){
		return "r" + Integer.toString(reg_index++);
	}
	public void Translator(SymbolTable symTab, LinkedList<IrNode> irList){
//		for(String varName: symTab.symbols.keySet())
//			System.out.println(varName);
		
		Map<String, String> regMap = new LinkedHashMap<String, String>();
		Vector v = new Vector(symTab.symbols.keySet());
//		Collections.sort(v);
		Iterator it = v.iterator();
		
		
		/* Variable declare */
		while(it.hasNext())
			print_code("var " + it.next().toString());
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
			String[] part;
			tiny = new String(node.irCode);
			part = tiny.split(" ");
//			System.out.println("COME : " +tiny);
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
			else if(part[0].compareTo("LABEL")==0) cmd = "label";
			else if(part[0].compareTo("READI")==0) cmd = "readi";
			else if(part[0].compareTo("READF")==0) cmd = "readr";
			else if(part[0].compareTo("WRITEI")==0) cmd = "writei";
			else if(part[0].compareTo("WRITEF")==0) cmd = "writer";

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
			 *  
			 * 
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
				/* check if the operand has no registers if so getReg() */
				if(regMap.get(part[2])==null){
					regMap.put(part[2], getReg());
					print_code("move "+part[2]+" " +regMap.get(part[2]));
				}
				if(symTab.resolve(part[1]).getType() == "INT"){
//					System.out.println("TEST symbol resolve: "+ symTab.resolve(part[1]).getType());
					print_code("cmpi " + part[1] + " " + regMap.get(part[2]));
					print_code(cmd + " " + part[3]);
				}else if(symTab.resolve(part[1]).getType() == "FLOAT"){
					print_code("cmpr " + part[1] + " " + regMap.get(part[2]));
					print_code(cmd + " " + part[3]);
				}
			}else if(tiny.startsWith("JUMP")){
				print_code(cmd + " " +  part[1]);
			}
			/* I/O */
			else if(tiny.startsWith("WRITE") || tiny.startsWith("READ")){
				print_code("sys " + cmd + " " + part[1]);
			}else if(tiny.startsWith("LABEL")){
				print_code(cmd + " "+ part[1]);
				if(part[1].startsWith("main"))
					print_code("link 0");
			}
		}
		print_code("unlnk ");
		print_code("ret ");
		print_code("end");

		
	}
	
	
}
