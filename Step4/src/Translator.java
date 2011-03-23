import java.util.*;

public class Translator {
	int reg_index;
	void link(String code) {
		System.out.println(code);
	}
	String getReg() {
		return "r"+Integer.toString(reg_index++);
	}
	public void translate(SymbolTable symTab, LinkedList<IRnode> irList){
		for(IRnode node : irList)
			link( ";"+node.ircode );
		// 1. Symbol Table Translation
		/*for(String varName : symTab.symbols.keySet()) {
			System.out.println("var "+varName);
		}*/
		Vector v = new Vector(symTab.symbols.keySet());
		Collections.sort(v);
		Iterator it = v.iterator();
		while(it.hasNext())
			System.out.println("var "+(String)it.next());
		// 2. Pushes
		System.out.println("push");
		System.out.println("push r0");
		System.out.println("push r1");
		System.out.println("push r2");
		System.out.println("push r3");
		System.out.println("jsr main");
		System.out.println("sys halt");
		// 3. Translate
		Map<String, String> regMap = new HashMap<String, String>();
		for(IRnode node : irList){
			String cmd = null;
			String[] parts;
			String tiny = new String(node.ircode);
			// Operators
			if(tiny.startsWith("ADDI"))      cmd = "addi";
			else if(tiny.startsWith("ADDF")) cmd = "addr";
			else if(tiny.startsWith("SUBI")) cmd = "subi";
			else if(tiny.startsWith("SUBF")) cmd = "subr";
			else if(tiny.startsWith("MULTI"))cmd = "muli";
			else if(tiny.startsWith("MULTF"))cmd = "mulr";
			else if(tiny.startsWith("DIVI")) cmd = "divi";
			else if(tiny.startsWith("DIVF")) cmd = "divr";
			// Stores
			else if(tiny.startsWith("STOREI"))cmd = "move";
			else if(tiny.startsWith("STOREF"))cmd = "move";
			// Comps
			else if(tiny.startsWith("GE"))   cmd = "jge";
			else if(tiny.startsWith("LE"))   cmd = "jle";
			else if(tiny.startsWith("NE"))   cmd = "jne";
			// direct translate
			else if(tiny.startsWith("JUMP")) cmd = "jmp";
			else if(tiny.startsWith("LABEL"))cmd = "label";
			// Read and Write
			else if(tiny.startsWith("READI"))cmd = "sys readi";
			else if(tiny.startsWith("READF"))cmd = "sys readr";
			else if(tiny.startsWith("WRITEI"))cmd = "sys writei";
			else if(tiny.startsWith("WRITEF"))cmd = "sys writer";
			//System.out.println(tiny);
			String newReg = null;
			parts = tiny.split(" ");
			if(tiny.startsWith("ADD") || tiny.startsWith("SUB") || tiny.startsWith("MULT") || tiny.startsWith("DIV")) {
				/*  1. Replace $T in first 2 with allocated regs
				 *  2. If first 2 are all regs, 
				 *     1) one instruction
				 *     2) add mapping
				 *  3. If first 2 are 1 reg with 1 var, 
				 *     1) move var to newReg,
				 *     2) addx reg newReg
				 *  4. If frst 2 are all var
				 *     1) move first 1 to newReg
				 *     2) addx 2nd_var newReg
				 *  3,4 ==> move first var to newReg, 
				 *          add second whatever to newReg
				 *  
				 *  First Replace first 2, and count var_num
				 *  If 0 =>...
				 *  If 1 and 2  => allocate newReg for 1st_Var
				 *                 add another whatever to newReg
				 */
				int var_num = 0;
				int first_var = -1;
				for(int i=1; i <= 2; i++){
					if(!parts[i].startsWith("$T")){
						if(first_var == -1)
							first_var = i;
						var_num ++;
					}
					else
						parts[i] = regMap.get(parts[i]);
				}
				//System.out.println(tiny+Integer.toString(parts.length)+"\n"+Integer.toString(var_num)+" "+Integer.toString(first_var));
				if(var_num == 0) {
					link(cmd+" "+parts[2]+" "+parts[1]);
					regMap.put(parts[3], parts[1]);
				}
				else if(var_num == 1) {
					if(first_var == 1){
						newReg = getReg();
						link("move "+parts[1]+" "+newReg);
						link(cmd+" "+parts[2]+" "+newReg);
						regMap.put(parts[3], newReg);
					}
					else{
						link(cmd+" "+parts[first_var]+" "+parts[3-first_var]);
						regMap.put(parts[3], parts[3-first_var]);
					}
				}
				else {
					newReg = getReg();
					link("move "+parts[first_var]+" "+newReg);
					link(cmd+" "+parts[3-first_var]+" "+newReg);
					regMap.put(parts[3], newReg);
				}
			}
			else if(tiny.startsWith("STORE")){
				// Check whether first guy start with $T
				if(parts[1].startsWith("$T"))
					parts[1] = regMap.get(parts[1]);
				if(parts[2].startsWith("$T")){
					newReg = getReg();
					regMap.put(parts[2], newReg);
					parts[2] = newReg;
				}
				link(cmd+" "+parts[1]+" "+parts[2]);
			}
			else if(tiny.startsWith("GE") || tiny.startsWith("LE")
					|| tiny.startsWith("NE")){
				if(symTab.getSym(parts[1]).getType()=="INT")
					link("cmpi "+parts[1]+" "+regMap.get(parts[2]));
				else if(symTab.getSym(parts[1]).getType()=="FLOAT")
					link("cmpr "+parts[1]+" "+regMap.get(parts[2]));
				link(cmd+" "+parts[3]);
			}
			else if(tiny.startsWith("JUMP"))
				link(cmd+" "+parts[1]);
			else if(tiny.startsWith("LABEL")){
				link(cmd+" "+parts[1]);
				if(parts[1].equals("main"))
					link( "link 0" );
			}
			else if(tiny.startsWith("READ") || tiny.startsWith("WRITE")){
				link(cmd+" "+parts[1]);
			}
		}
		link("unlnk");
		link("ret");
		link("end");
	}
}
