package test;


import java.util.LinkedList;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;

public class CodeGen {
	int temp_index;
	int label_index;
	SymbolTable globalSymTab;
	SymbolTable currentSymTab;
	LinkedList<IrNode> irList; 

	public CodeGen(SymbolTable Sym){
		temp_index = 1;
		label_index = 1;
		globalSymTab = Sym;
		currentSymTab = globalSymTab;
		irList = new LinkedList<IrNode>();
	}

	/* Link code into irList */
	void link(String code) {  
		irList.add(new IrNode(code));
//		System.out.println("--- IRNode: "+code);
		System.out.println(code+" ");

	}

	/* get child tree */
	CommonTree getCh(BaseTree tr, int i) {
		return (CommonTree)tr.getChild(i);
	}
	
	/* get child token */
	String getChToken(BaseTree tr, int i) {
		return getCh(tr,i).getText().toString();
	}
	
	/* get register number */
	String getRegNum(){
		return Integer.toString(temp_index++);
	}
	
	/* get register String */
	String getReg(){
		return "$T" + Integer.toString(temp_index++);
	}
	
	/* Code generator calling each functions */
	public void gen(CommonTree tr){
//        System.out.println("Null TEST: "+tr.getText());
        if(tr.getToken() == null){
        	for(int i=0; i<tr.getChildren().size(); i++)
        		gen(getCh(tr,i));
        }

		
		switch(tr.getType()) {
	    case TestParser.K_PROGRAM :     program(tr);        break;
		case TestParser.FUNC_DECL_LIST: func_decl_list(tr); break;
		case TestParser.K_FUNCTION:     function(tr);       break;
		case TestParser.STMT_LIST :     stmt_list(tr);      break;
		case TestParser.CALL_EXPR :     call_expr(tr);      break;
		case TestParser.EXPR_LIST :     expr_list(tr);      break;
		case TestParser.IDENTIFIER:     identifier(tr);     break;
		case TestParser.STRINGLITERAL:  stringliteral(tr);  break;
		case TestParser.FLOATLITERAL :  floatliteral(tr);   break;
		case TestParser.ADD :
		case TestParser.SUB :
		case TestParser.MULT:
		case TestParser.DIV :           operator(tr);       break;
		case TestParser.K_READ:         read_write(tr,'r'); break;
		case TestParser.K_WRITE:        read_write(tr,'w'); break;
		case TestParser.K_IF:           if_stmt(tr);        break;
		case TestParser.K_FOR:          for_stmt(tr);       break;
		case TestParser.ASSIGN:         op_gen(tr, null);   break;
//		case TestParser.K_END:			link("RET");		break;
		default: System.out.println("gen: unmatched token"+tr.getToken().getText());
		}
		
	}

	/* operator generator */
	/* res_type[0] will hold the resluts of Tokens
	 * res_type[1] will hold the types of Tokens 
	 * 
	 * 
	 */
	private void op_gen(CommonTree tr, String[] res_type) {
//		System.out.println("op_node: " + tr.getToken().getText());
		if(res_type == null)
			res_type = new String[3];
		
		if(tr.getToken().getType() == TestParser.IDENTIFIER ){
			res_type[0] = tr.getToken().getText();
			Symbol sym = globalSymTab.resolve(res_type[0]);
			res_type[1] = sym.type;
			res_type[2] = res_type[0];
			
		}else if(tr.getToken().getType() == TestParser.INTLITERAL) {
			res_type[0] = tr.getToken().getText();
			res_type[1] = "INT";
			res_type[2] = getReg();
			link("STOREI "+res_type[0]+" "+res_type[2]);
//			res_type[0] = res_type[2];
		}else if(tr.getToken().getType() == TestParser.FLOATLITERAL){
			res_type[0] = tr.getToken().getText();
			res_type[1] = "FLOAT";
			res_type[2]	= getReg();
			link("STOREF " + res_type[0] + " " + res_type[2]);
//			res_type[0] = res_type[2];
		}else{
			String[] left_res_type = { null, null, null };
			String[] right_res_type = { null, null, null };
			String op = null;
			String type = null;
			String code = null;
			op_gen(getCh(tr,0), left_res_type);
			op_gen(getCh(tr,1), right_res_type);
			
			if(left_res_type[1] == "INT" || right_res_type[1] == "INT"){
				res_type[1] = "INT";
				type = "I ";
			}else if(left_res_type[1] == "FLOAT" || right_res_type[1] == "FLOAT"){
				res_type[1] = "FLOAT";
				type = "F ";
			}
				
			switch(tr.getToken().getType()){
			case(TestParser.ASSIGN):
				op = "STORE" + type;
				code = op + right_res_type[2] + " " + left_res_type[0];
				left_res_type[1] = right_res_type[1];
				break;
			case(TestParser.ADD): 
				op = "ADD" + type;
				break;
			case(TestParser.SUB):
				op = "SUB" + type;
				break;
			case(TestParser.MULT):				
				op = "MULT" + type;
				break;
			case(TestParser.DIV):				
				op = "DIV" + type;
				break;
			}
			if(tr.getToken().getType() != TestParser.ASSIGN){
				res_type[2] = getReg();
				res_type[0] = res_type[2];
				code = op + left_res_type[2] + " " + right_res_type[2] + " " + res_type[2];
			}
			link( code );
		}
		
	}

	private void program(CommonTree tr) {
		for(int i=0; i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}
	}

	private void func_decl_list(CommonTree tr) {
		for(int i=0; i<tr.getChildren().size(); i++){
			gen(getCh(tr, i));
		}		
	}

	private void function(CommonTree tr) {
		link("LABEL " + getChToken(tr, 1));
		link("LINK ");
		gen(getCh(tr,2));
		link("RET");
//		for(int i=0; i<tr.getChildren().size(); i++){
//			gen(getCh(tr,i));
//		}
	}

	private void stmt_list(CommonTree tr) {
		for(int i=0; i<tr.getChildren().size();i++){
			gen(getCh(tr, i));
		}
	}

	private void call_expr(CommonTree tr) {
		// TODO Auto-generated method stub
		
	}

	private void expr_list(CommonTree tr) {
		for(int i=0; i<tr.getChildren().size();i++)
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
		if(c=='r')
			op = "READ";
		else
			op= "WRITE";
		for(int i=0; i < tr.getChildren().size(); i++){
			type = globalSymTab.resolve(getChToken(tr, i)).getType();
			if(type == "INT")
				link(op + "I " + getChToken(tr, i));
			else if(type == "FLOAT"){
				link(op + "F " + getChToken(tr, i));
			}
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
		if(tr.getType() == TestParser.GT)
			cond = "LE ";
		else if(tr.getType() == TestParser.LT)
			cond = "GE ";
		else if(tr.getType() == TestParser.EQ)
			cond = "NE ";
		else
			cond = tr.getToken().getText();
		code = cond + left_res_type[0] + " " + right_res_type[2] + " label" + Integer.toString(label_num[0]);
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
		if(tr.getChildren().size() == 2){
			cond_gen((CommonTree)tr.getChild(0), label);
			gen((CommonTree)tr.getChild(1));
			link("JUMP label" + Integer.toString(label[0]+1));
			link("LABEL label" + Integer.toString(label[0]));
			link("LABEL label" + Integer.toString(label[0]+1));
//		System.out.println("TEST if_stmt 1: " + tr.toString());
//		System.out.println("TEST if_stmt 2: " + tr.getChildren().toString());
		}else if(tr.getChildren().size() == 3){
//			System.out.println("TEST if_stmt 1: " + tr.toString());
//			System.out.println("TEST if_stmt 2: " + tr.getChildren().toString());
			cond_gen((CommonTree)tr.getChild(0), label);
			label_br=label[0];
			label[0]=label_index++;
			gen((CommonTree)tr.getChild(1));
			link("JUMP label" + Integer.toString(label_br+1));
			link("LABEL label" + Integer.toString(label_br));
			label[0]=label_index++;
			gen((CommonTree)tr.getChild(2));
			link("LABEL label" + Integer.toString(label_br+1));
		}

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
			if( tr.getChild(i).getType() == TestParser.SEMICOL )
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
		if( getCh(tr, semi_pos[1]+1).getType() != TestParser.STMT_LIST ){
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
