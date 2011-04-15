import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;

public class CodeGen {
	int label_index;
	Map<String, Symbol> currentSymTab;
	Function currentFunc;
	
	Map<String, Symbol> globalSymTab;
	Map<String, Function> funcTab;
	LinkedList<IrNode> irList; 

	public CodeGen(){
		label_index = 1;
		irList = new LinkedList<IrNode>();
		globalSymTab = new LinkedHashMap<String, Symbol>();
		funcTab = new LinkedHashMap<String, Function>();
		
		currentSymTab = globalSymTab;
		currentFunc = null;
	}

	/* Link code into irList */
	void link(String code) {  
		irList.add(new IrNode(code));
//		System.out.println("--- IRNode: "+code);
		//System.out.println("; "+code+" ");
	}

	/* get child tree */
	CommonTree getCh(BaseTree tr, int i) {
		return (CommonTree)tr.getChild(i);
	}
	
	/* get child token */
	String getChToken(BaseTree tr, int i) {
		return getCh(tr,i).getText().toString();
	}
	
	/* search Symbol Tables
	 * First search currentSymTab, 
	 * if null, search globalSymTab
	 * if null, return null
	 */
	Symbol getSym(String index){
		Symbol result;
		if((result = currentSymTab.get(index)) != null)
			return result;
		return globalSymTab.get(index);
	}
	
	String checkName(Symbol sym){
		if(sym.irname != null)
			return sym.irname;
		return sym.id;
	}
	
	/* get register number */
	String getRegNum(){
		return Integer.toString(currentFunc.temp_index++);
	}
	
	/* get register String */
	String getReg(){
		return "$T" + Integer.toString(currentFunc.temp_index++);
	}
	
	/* Code generator calling each functions */
	public void gen(CommonTree tr){
        //System.out.println("Text: "+tr.getText()+"  Type: "+tr.getType());
        if(tr.getToken() == null){
        	for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++)
        		gen(getCh(tr,i));
        }		
		switch(tr.getType()) {
	    case MicroParser.K_PROGRAM :     program(tr);        break;
	    case MicroParser.PGM_BODY:       pgm_body(tr);       break;
	    case MicroParser.DECL:           decl(tr);           break;
	    case MicroParser.STRING_DECL:    string_decl(tr);    break;
	    case MicroParser.VAR_DECL:       var_decl(tr);       break;
		case MicroParser.FUNC_DECL_LIST: func_decl_list(tr); break;
		case MicroParser.K_FUNCTION:     function(tr);       break;
		case MicroParser.PARAM_DECL_LIST:param_decl_list(tr);break;
		case MicroParser.FUNC_BODY:      func_body(tr);      break;
		case MicroParser.STMT_LIST :     stmt_list(tr);      break;
		case MicroParser.CALL_EXPR :     call_expr(tr);      break;
		case MicroParser.EXPR_LIST :     expr_list(tr);      break;
		case MicroParser.IDENTIFIER:     identifier(tr);     break;
		case MicroParser.STRINGLITERAL:  stringliteral(tr);  break;
		case MicroParser.FLOATLITERAL :  floatliteral(tr);   break;
		case MicroParser.ADD :
		case MicroParser.SUB :
		case MicroParser.MULT:
		case MicroParser.DIV :           operator(tr);       break;
		case MicroParser.K_READ:         read_write(tr,'r'); break;
		case MicroParser.K_WRITE:        read_write(tr,'w'); break;
		case MicroParser.K_IF:           if_stmt(tr);        break;
		case MicroParser.K_FOR:          for_stmt(tr);       break;
		case MicroParser.ASSIGN:         op_gen(tr, null);   break;
//		case MicroParser.K_END:			link("RET");		break;
		case MicroParser.K_RETURN:		ret_stmt(tr);		 break;
		default: System.out.println("gen: unmatched token"+tr.getToken().getText());
		}
		
	}

	private void ret_stmt(CommonTree tr) {
		/* Two Possible Cases:
		 *  1. Return Single Variable
		 *     STORE Variable R
		 *     RET
		 *  2. Return expr
		 *     Call op_gen
		 *     STORE res_type[2] R
		 *     RET
		 */
		//System.out.println("Text:"+getChToken(tr, i)+" Type:"+Integer.toString(getCh(tr, i).getType()));
		/*String ret_type = null;
		if(getCh(tr, 0).getType() == MicroParser.IDENTIFIER){
			// Return Single Variable Case
			Symbol sym;
			sym = getSym(getCh(tr, 0).getText());
			if(sym.type.equals("INT"))
				ret_type = "I ";
			else if(sym.type.equals("FLOAT"))
				ret_type = "F ";
			link("STORE"+ret_type+sym.irname+" $R");
			link("RET");
		}
		else{
			// Return expr
			String res_type[] = new String[3];
			op_gen(getCh(tr, 0), res_type);
			if(res_type[1].equals("INT"))
				ret_type = "I ";
			else if(res_type[1].equals("FLOAT"))
				ret_type = "F ";
			else
				System.out.println("Return unrecognized type: "+ret_type);
			link("STORE"+ ret_type+ res_type[2]+ " $R");
			link("RET");
		}*/
		String ret_type = null;
		String res_type[] = new String[3];
		op_gen(getCh(tr, 0), res_type);
		if(res_type[1].equals("INT"))
			ret_type = "I ";
		else if(res_type[1].equals("FLOAT"))
			ret_type = "F ";
		else
			System.out.println("Return unrecognized type: "+ret_type);
		link("STORE"+ ret_type + res_type[2]+ " $R");
		link("RET");
	}

	/* operator generator */
	private void op_gen(CommonTree tr, String[] res_type) {
		// res_type[0] - var name
		// res_type[1] - var type
		// res_type[2] - var reg
//		System.out.println("op_node: " + tr.getToken().getText());
		if(res_type == null)
			res_type = new String[3];
		
		if(tr.getToken().getType() == MicroParser.IDENTIFIER ){
			res_type[0] = tr.getToken().getText();
			Symbol sym = getSym(res_type[0]);
			res_type[1] = sym.type;
			res_type[2] = checkName(sym);
			//System.out.println("op_gen ID:"+res_type[0]+" Type:"+res_type[1]+" IRname:"+res_type[2]);
		}else if(tr.getToken().getType() == MicroParser.INTLITERAL) {
			res_type[0] = tr.getToken().getText();
			res_type[1] = "INT";
			res_type[2] = getReg();
			link("STOREI "+res_type[0]+" "+res_type[2]);
//			res_type[0] = res_type[2];
		}else if(tr.getToken().getType() == MicroParser.FLOATLITERAL){
			res_type[0] = tr.getToken().getText();
			res_type[1] = "FLOAT";
			res_type[2]	= getReg();
			link("STOREF " + res_type[0] + " " + res_type[2]);
//			res_type[0] = res_type[2];
		}else if(tr.getToken().getType() == MicroParser.CALL_EXPR){
			res_type[1] = "CALL";
			res_type[2] = call_expr(tr);
		}else{
			//System.out.println("op_gen Type:"+Integer.toString(tr.getToken().getType()));
			String[] left_res_type = { null, null, null };
			String[] right_res_type = { null, null, null };
			String op = null;
			String type = null;
			String code = null;
			op_gen(getCh(tr,0), left_res_type);
			op_gen(getCh(tr,1), right_res_type);
			
			if(left_res_type[1].equals("INT") || right_res_type[1].equals("INT")){
				res_type[1] = "INT";
				type = "I ";
			}else if(left_res_type[1].equals("FLOAT") || right_res_type[1].equals("FLOAT")){
				res_type[1] = "FLOAT";
				type = "F ";
			}else{
				//System.out.println("left_res: "+left_res_type[0]+" x"+left_res_type[1]+"x");
				//System.out.println("right_res: "+right_res_type[0]+" x"+right_res_type[1]+"x");
			}
				
			switch(tr.getToken().getType()){
			case(MicroParser.ASSIGN):
				op = "STORE" + type;
				//code = op + right_res_type[2] + " " + left_res_type[0];
				code = op + right_res_type[2] + " " + left_res_type[2];
				left_res_type[1] = right_res_type[1];
				break;
			case(MicroParser.ADD): 
				op = "ADD" + type;
				break;
			case(MicroParser.SUB):
				op = "SUB" + type;
				break;
			case(MicroParser.MULT):				
				op = "MULT" + type;
				break;
			case(MicroParser.DIV):				
				op = "DIV" + type;
				break;
			}
			if(tr.getToken().getType() != MicroParser.ASSIGN){
				res_type[2] = getReg();
				res_type[0] = res_type[2];
				code = op + left_res_type[2] + " " + right_res_type[2] + " " + res_type[2];
			}
			link( code );
		}		
	}

	private void program(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}
	}
	
	private void pgm_body(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}		
	}
	
	private void decl(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}
	}
	
	private void string_decl(CommonTree tr) {
		Symbol sym = new Symbol(getChToken(tr, 0), "STRING", getChToken(tr, 1));
		currentSymTab.put(getChToken(tr, 0), sym);
	}
	
	private void var_decl(CommonTree tr) {
		// var_decl   :  VAR_DECL var_type var_id_list
		// var_id_list:  var_id (COMMA var_id)*
		// Child_0 store var_type, others store var_id_list
		if(tr.getChildren() == null)
			return;
		String type = getChToken(tr, 0);
		int var_num = tr.getChildren().size() - 1;
		for(int i=1; i< tr.getChildren().size(); i++){
			Symbol sym = new Symbol(getChToken(tr, i), type);
			if(currentFunc != null)
				sym.irname = "$L"+Integer.toString(currentFunc.lNum + i);
			currentSymTab.put(getChToken(tr, i), sym);
			currentSymTab.put(sym.irname, sym);  // Also put this so we can use $Lx to search Symbol
		}
		if(currentFunc != null)
			currentFunc.lNum += var_num;
		//System.out.println(Integer.toString(tr.getChildren().size()-1)+" var in VAR_DECL, type:"+type+"\n");
	}

	private void func_decl_list(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}		
	}
	

	private void function(CommonTree tr) {
		/*  K_FUNCTION
		 *   0- any_type
		 *   1- id
		 *   2- param_decl_list
		 *   3- func_body
		 * 
		 *  1. Generate ANTLOR Data Structures
		 *    1) Fill Function Class
		 *    2) <Function Name - Function Class> Hash
		 *  2. Generate IR fo Function
		 */
		// 1. Generate ANTLOR Data Structure
		//   1) Fill Function Class
		Function func = new Function(getChToken(tr, 1));
		currentSymTab = func.symTab;
		currentFunc = func;
		//   2) <Function Name - Function Class> Hash
		funcTab.put( getChToken(tr, 1), func );
		//   3) param_decl_list, if any
		if(getChToken(tr, 2).equals("PARAM_DECL_LIST")){
			// Yes, param_decl_list exists
			gen(getCh(tr, 2));
			link("LABEL " + getChToken(tr, 1));
			link("LINK ");
			// 4) func_body
			gen(getCh(tr, 3));
		}else{
			// 4) func_body
			link("LABEL " + getChToken(tr, 1));
			link("LINK ");
			gen(getCh(tr, 2));
		}
		// 5) check whether last node is RET, if not add not
		if( !irList.getLast().irCode.equals("RET") )
			link("RET");
		// 6) when done, restore currentSymTab and currentFunc
		currentSymTab = globalSymTab;
		currentFunc = null;
			
		// 2. Generate IR for Function
//		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
//			gen(getCh(tr,i));
//		}
	}
	
	private void param_decl_list(CommonTree tr) {
		if(tr.getChildren() == null)
			return;
		currentFunc.pNum = tr.getChildren().size()/2;
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i+=2){
			// Child_i stores Type, Child_i+1 stores Id
			Symbol sym = new Symbol(getChToken(tr, i+1), getChToken(tr, i));
			sym.irname = "$P"+Integer.toString(i/2+1);
			//System.out.println("Param: ID:"+getChToken(tr,i+1)+" Type:"+getChToken(tr,i)+" Parm:"+sym.irname);
			currentSymTab.put(getChToken(tr, i+1), sym);
			currentSymTab.put(sym.irname, sym);  // Also put this so we can use $Px to search Symbol
		}
	}
	
	private void func_body(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}
	}

	private void stmt_list(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}
	}

	private String call_expr(CommonTree tr) {
		/* Generate Code for Function Call
		 *  0. parameter calc:  op_gen
		 *  1. push ret value:  PUSH
		 *  2. push parameters: PUSH $L1; PUSH $L2; ..
		 *  3. jsr function:    JSR func
		 *  4. pop parameters:  POP; POP; ..
		 *  5. pop ret value:   POP $T1;
		 * Return $T1
		 */
		// 0. parameters calc
		int param_num = getCh(tr, 1).getChildren().size();
		String params[] = new String[param_num];
		String res_type[] = new String[3];
		// getCh(tr, 1) is the EXPR_LIST
		for(int i=0; i < param_num; i++){
			// getCh(getCh(tr, 1), i) is i-th parameter
			op_gen( getCh(getCh(tr, 1), i), res_type );
			params[i] = res_type[2];
		}
		// 1. push ret value
		link("PUSH");
		// 2. push parameters
		for(int i = 0; i < param_num; i++){
			link("PUSH "+ params[i]);
		}
		// 3. jsr function
		link("JSR "+ getChToken(tr, 0));
		// 4. pop parameters
		for(int i=0; i < param_num; i++){
			link("POP");
		}
		// 5. pop ret value
		String ret_reg = getReg();
		link("POP "+ ret_reg);
		return ret_reg;
	}

	private void expr_list(CommonTree tr) {
		for(int i=0; tr.getChildren()!=null && i<tr.getChildren().size(); i++)
			gen(getCh(tr, i));		
	}

	private String identifier(CommonTree tr) {
//		System.out.println("TEST3 "+tr.getText());
		return tr.getToken().getText();
	}

	private String stringliteral(CommonTree tr) {
		return tr.getToken().getText();
		
	}

	private String floatliteral(CommonTree tr) {
		return tr.getToken().getText();
		
	}

	private void operator(CommonTree tr) {
		String[] res_type = { null, null };
		op_gen(tr, res_type);
	}

	private void read_write(CommonTree tr, char c) {
		String op = null;
		String type = null;
		String irname = null;
		Symbol sym;
		if(c=='r')
			op = "READ";
		else
			op= "WRITE";
		for(int i=0; i < tr.getChildren().size(); i++){
			sym = getSym(getChToken(tr, i));
			type = sym.getType();
			irname = checkName(sym);
			if( type.equals("INT") )
				link(op + "I " + irname);
			else if( type.equals("FLOAT") ){
				link(op + "F " + irname);
			}
			else if( type.equals("STRING") ){
				link(op + "S " + irname);
			}
			else
				System.out.println("unrecognized type:"+type);
		}
		
	}
	
	private void cond_gen(CommonTree tr, int[] label_num){
		String cond = null;
		String code = null;
		String[] left_res_type = {null, null, null};
		String[] right_res_type = {null, null, null};
		label_num[0] = label_index++;
		
//		System.out.println("TEST cond_gen 1: " + getCh(tr,0) + " "+getCh(tr,1));
		
		op_gen(getCh(tr, 0), left_res_type);
		op_gen(getCh(tr, 1), right_res_type);
		if(tr.getType() == MicroParser.GT)
			cond = "LE ";
		else if(tr.getType() == MicroParser.LT)
			cond = "GE ";
		else if(tr.getType() == MicroParser.EQ)
			cond = "NE ";
		else
			cond = tr.getToken().getText();
		code = cond + left_res_type[2] + " " + right_res_type[2] + " label" + Integer.toString(label_num[0]);
		link( code );
//		System.out.println( "TEST cond gen 2: " + code );
	
		

	}

	/* if(cond) 
	 * then 
	 * 		statement 
	 * else 
	 * 		statement 
	 * endif
	 * 
	 * 1. cond stmt
	 * 		1.1 cond_gen -> if not jump to end(label[0] + 1)
	 * 		1.2 gen() -> stmt done, then next is end(label[0] + 1)
	 *   
	 * 2. cond stmt else
	 * 		2.1 cond_gen -> if not jump to else(label[0] + 1)
	 * 		2.2 gen() -> stmt done, then jump to end(label[0] + 1 + 1)
	 * 		2.3 else ->  stmt done, then next is end(label[0] + 1 + 1)
	 * */
		private void if_stmt(CommonTree tr) {
			int[] label = {-1};
			int label_br = -1;
			
			cond_gen((CommonTree)tr.getChild(0), label);
			label_br = label[0];
			label[0]=label_index++;
			gen((CommonTree)tr.getChild(1));
			link("JUMP label" + Integer.toString(label_br+1));
			link("LABEL label" + Integer.toString(label_br));
			
			if(tr.getChildren().size() == 3){
				label[0]=label_index;
				gen((CommonTree)tr.getChild(2));
			}
			
			link("LABEL label" + Integer.toString(label_br+1));

	}


	private void for_stmt(CommonTree tr) {
		/* for(init(opt) ; cond(opt) ; inc(opt) )
		 * 		statements;
		 * 
		 * 1. part[0] - init
		 * 2. part[1] - (LABEL 1) check cond
		 *		2.1 if true - statements
		 *		2.2 if false - OUT(LABEL 2)
		 * 3. part[2] - increment or decrement
		 * 4. loop - goto cond(LABEL 1)
		 * 5. LABEL 2
		 * 
		 * 
		 */
		int label[] = {0};
		int[] semi_pos = {-1, -1};
		int semi_index = 0;
		int label_link = label_index++;
		
		/* check for the optional - whether there are init, cond, and inc or not */
		for(int i=0 ; i< tr.getChildren().size() ; i++){
//			System.out.println("TEST FOR_STM: " + getChToken(tr, i));
			if( tr.getChild(i).getType() == MicroParser.SEMICOL )
				semi_pos[semi_index++] = i;
		}
		/*	first semicolon - init	*/
		if( semi_pos[0] == 1 ){
			gen(getCh(tr, semi_pos[0] - 1));
		}
		link("LABEL label" + label_link);
		/* second semicolon - cond */
		if( semi_pos[1] == 3){
			cond_gen(getCh(tr, semi_pos[1] - 1), label);
		}
		/*	check the inc and genrate statment w/ or w/o increment	*/
		if( getCh(tr, semi_pos[1]+1).getType() != MicroParser.STMT_LIST ){
			gen(getCh(tr, semi_pos[1]+2));	
			gen(getCh(tr, semi_pos[1]+1));
//			System.out.println("TEST FOR_STM 3: " +getCh(tr, semi_pos[1]+1));
		}else{
			gen(getCh(tr, semi_pos[1]+1));	
//			System.out.println("TEST FOR_STM 2: " +getCh(tr, semi_pos[1]+1));
		}

		link("JUMP label" + label_link);	
//		System.out.println("TEST label: "+ label_link);
		link("LABEL label" + label[0]);		
	}
	
	
}
