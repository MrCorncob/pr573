import java.util.LinkedList;

import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;

public class CodeGenerator {
	CodeGenerator(SymbolTable tab) {
		temp_index = 1;
		label_index = 1;
		globalSymTab = tab;
		currentSymTab = globalSymTab;
		irList = new LinkedList();
	}
	int temp_index;
	int label_index;
	SymbolTable globalSymTab;
	SymbolTable currentSymTab;
	LinkedList<IRnode> irList;
	void link(String code) {  // Link code into irList
		irList.add(new IRnode(code));
		//System.out.println(code);
	}
	CommonTree getCh(BaseTree tr, int i) {
		return  (CommonTree)tr.getChild(i);
	}
	String getChToken(BaseTree tr, int i) {
		return getCh(tr,i).getToken().getText();
	}
	String getRegNum(){
		return Integer.toString(temp_index++);
	}
	String getReg(){
		return "$T"+Integer.toString(temp_index++);
	}
	public void gen(CommonTree tr) {
		if(tr.getToken() == null){ 
			for(int i = 0; i < tr.getChildren().size(); i ++)
				gen( getCh(tr, i) );
			System.out.println("\n");
		}
		else
			;//System.out.println("node: "+tr.getToken().getText()+" ");
		switch(tr.getToken().getType()) {
		    case MicroParser.K_PROGRAM :     program(tr);        break;
			case MicroParser.FUNC_DECL_LIST: func_decl_list(tr); break;
			case MicroParser.K_FUNCTION:     function(tr);       break;
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
			default: System.out.println("gen: unmatched token"+tr.getToken().getText());
		}
	}
	public void op_gen(CommonTree tr, String[] res_type) {
		/* res_type[0] will hold the register storing the result
		 * res_type[1] will hold the operation type "int" or "float"
		 * 1. call op_gen() on operator, 
		 * 2. operator recursively call op_gen() on its children
		 *    if child is terminal  =>  set result as its name
		 *    else                  =>  set result as  the register name
		 */
	     // if tr is terminal operand
		//System.out.println("op_node: "+tr.getToken().getText()+" "+tr.getToken().getType());
		if(res_type == null)
			res_type = new String[3];
		if(tr.getToken().getType()==MicroParser.IDENTIFIER) {
			res_type[0] = tr.getToken().getText();
			Symbol sym = globalSymTab.getSym(res_type[0]);
			res_type[1] = sym.type;
			res_type[2] = res_type[0];
			// Allocate Reg if necessary
			/*if( (sym = globalSymTab.getSym(res_type[0])).reg == null ) {
				res_type[2] = getReg();
				sym.reg = res_type[2];
			}
			else
				res_type[2] = sym.reg;*/
		}
		else if(tr.getToken().getType()==MicroParser.INTLITERAL) {
			res_type[0] = tr.getToken().getText();
			res_type[1] = "INT";
			res_type[2] = getReg();
			link("STOREI "+res_type[0]+" "+res_type[2]);
			res_type[0] = res_type[2];
		}
		else if(tr.getToken().getType()==MicroParser.FLOATLITERAL) {
			res_type[0] = tr.getToken().getText();
			res_type[1] = "FLOAT";
			res_type[2] = getReg();
			link("STOREF "+res_type[0]+" "+res_type[2]);
			res_type[0] = res_type[2];
		}
		else {  // tr is operator
			String[] left_res_type  = {null, null, null};
			String[] right_res_type = {null, null, null};
			String op = null;
			String type = null;
			String reg = null;
			String code = null;
			op_gen(getCh(tr, 0), left_res_type);
			op_gen(getCh(tr, 1), right_res_type);
			if(left_res_type[1] == "INT" || right_res_type[1] == "INT") {
				res_type[1] = "INT";
			    type = "I ";
			}
			else if(left_res_type[1] == "FLOAT" || right_res_type[1] == "FLOAT") {
				res_type[1] = "FLOAT";
			    type = "F ";
			}
			switch(tr.getToken().getType()) {
				case(MicroParser.ASSIGN): 
					op = "STORE"+type;
					code = op+right_res_type[2]+" "+left_res_type[0];
					left_res_type[1] = right_res_type[1];
					break;
				case(MicroParser.ADD):    op = "ADD"+type;  break;
				case(MicroParser.SUB):    op = "SUB"+type;  break;
				case(MicroParser.MULT):   op = "MULT"+type;  break;
				case(MicroParser.DIV):    op = "DIV"+type;  break;
			}
			if(tr.getToken().getType()!=MicroParser.ASSIGN){
				res_type[2] = getReg();
				res_type[0] = res_type[2];
				code = op+left_res_type[0]+" "+right_res_type[0]+" "+res_type[2];
			}
			link( code );
		}
	}
	public void cond_gen(CommonTree tr, int[] label_num) {
		/* Generate Code for opposite condition
		 */
		String[] left_res_type = {null, null, null};
		String[] right_res_type = {null, null, null};
		String cmd = null;
		String code = null;
		label_num[0] = label_index ++;
		op_gen( getCh(tr, 0), left_res_type );
		op_gen( getCh(tr, 1), right_res_type );
		if(tr.getToken().getType()==MicroParser.GT) // Greater Than >
			cmd = "LE ";
		else if(tr.getToken().getType()==MicroParser.LT)
			cmd = "GE ";
		else if(tr.getToken().getType()==MicroParser.EQ)
			cmd = "NE ";
		else
			cmd = tr.getToken().getText();
		code = cmd + left_res_type[2] + " " + right_res_type[2] + 
		       " label" + Integer.toString(label_num[0]);
		link(code);
	}
	
	void program(CommonTree tr) {
		for(int i = 0; i < tr.getChildren().size(); i++) {
			gen( getCh(tr, i) );
		}
	}
	void func_decl_list(CommonTree tr) {
		for(int i = 0; i < tr.getChildren().size(); i++)
			gen( getCh(tr, i) );
	}
	void function(CommonTree tr) {
		/* Function Code Structure
		 * 1. LABEL function_name
		 * 2. FUNCTION_BODY
		 */
		// LABEL
		link( "LABEL "+ getChToken(tr, 1) );
		// Change currentSymTab
		//currentSymTab = currentSymTab.getTab(getChToken(tr, 1));
		// STMT_LIST
		gen( getCh(tr,2) );
	}
	void stmt_list(CommonTree tr) {
		for(int i = 0; i < tr.getChildren().size(); i++)
			gen( getCh(tr, i) );
	}
	void call_expr(CommonTree tr) { 
		// Leave Blank
	}
	void expr_list(CommonTree tr) {
		for(int i = 0; i < tr.getChildren().size(); i++)
			gen( getCh(tr, i) );
	}
	String identifier(CommonTree tr) {  
		// Don't link, it doesn't form instruction
		return tr.getToken().getText();
	}
	String stringliteral(CommonTree tr) {
		return tr.getToken().getText();
	}
	String floatliteral(CommonTree tr) {
		return tr.getToken().getText();
	}
	void operator(CommonTree tr) {
		String[] res_type = {null, null};
		op_gen(tr, res_type);
	}
	void read_write(CommonTree tr, char rw) {
		String type = null;
		String op = null;
		if(rw == 'r')
			op = "READ";
		else
			op = "WRITE";
		tr = (CommonTree)tr.getChild(0);
		for(int i = 0; i < tr.getChildren().size(); i ++) {
			type = globalSymTab.getSym( getChToken(tr, i) ).getType();
			if(type == "INT")
				link(op+"I "+getChToken(tr, i));
			else if(type == "FLOAT")
				link(op+"F "+getChToken(tr, i));
		}
	}
	void if_stmt(CommonTree tr) {
		/*  We need to check whether there is "elsepart" or not
		 *  1. With "elsepart"
		 *     Code from Cond
		 *     [JUMP TO LABEL1 (if reverse Cond true => Cond false)]
		 *     Code from Stmt_list
		 *     JUMP TO LABEL2
		 *     LABEL1
		 *     Code from "elsepart"
		 *     LABEL2
		 *  2. Without "elsepart"
		 *     Code from Cond
		 *     [JUMP TO LABEL1 (Cond false)]
		 *     Code from Stmt_list
		 *     LABEL1
		 */
		int[] label1 = {-1};
		int   label2;
		cond_gen( (CommonTree)tr.getChild(0), label1 );  // Code from Cond
		gen( (CommonTree)tr.getChild(1) );  // Code from Stmt_list
		if(tr.getChildren().size() == 3) { // With "elsepart"
			label2 = label_index ++;
			link( "JUMP label"+Integer.toString(label2) );
			link( "LABEL label"+Integer.toString(label1[0]));
			gen( (CommonTree)tr.getChild(2) );
			link(  "LABEL label"+Integer.toString(label2) );
		}
		else { // Without "elsepart"
			link( "LABEL label"+Integer.toString(label1[0]) );
		}
	}
	void for_stmt(CommonTree tr) {
		/*  Assign_expr, Cond, Assign_expr are all Optional
		 *  Code Structure
		 *   Code from Initial Assign_expr
		 *   LABEL label1
		 *   Code from Cond [JUMP TO LABEL2 if Cond false]
		 *   Code from stmt_list
		 *   Code from Second Assign_expr
		 *   JUMP label1
		 *   LABEL label2
		 */
		int semicolon = 43;
		int label1 = label_index ++;
		int[] label2 = {-1};
		int[] semicolon_pos = new int[2];
		int pos_index = 0;
		for(int i=0; i < tr.getChildren().size(); i++){
			//System.out.println(getChToken(tr, i));
			if(getCh(tr, i).getToken().getType() == semicolon)
				semicolon_pos[pos_index++] = i;
		}
		// Whether Initial Assign_expr exists?
		if( semicolon_pos[0] != 0 )
			gen(getCh(tr, semicolon_pos[0]-1));
		link( "LABEL label"+Integer.toString(label1) ); // LABEL1
		// Whether Cond exists?
		if( semicolon_pos[0] + 1 != semicolon_pos[1] )
			cond_gen( getCh(tr, semicolon_pos[1]-1), label2 );
		// Whether Second Assign_expr exists?
		if( getChToken( tr, semicolon_pos[1]+1 ) != "STMT_LIST" ) { // Yes
			gen( getCh(tr, semicolon_pos[1] + 2) );  // Code from stmt_list
			gen( getCh(tr, semicolon_pos[1] + 1) );      // Code from Second Assign_expr
		}
		else  // No
			gen( getCh(tr, semicolon_pos[1] + 1) );  // Code from stmt_list
		// JUMP label1
		link( "JUMP label"+Integer.toString(label1) );
		// LABEL label2
		link( "LABEL label"+Integer.toString(label2[0]));
	}
}
