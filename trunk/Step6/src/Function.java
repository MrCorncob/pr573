import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Function {
	public String funcName;
	public int lNum;			/* number of local variables */
	public int pNum;			/* number of parameters */
	public int temp_index;
	public LinkedList<IrNode> irList = new LinkedList<IrNode>();
	public Map<String, Symbol> symTab = new LinkedHashMap<String, Symbol>();		/* function symbol table */
	public Function(){
		funcName = null;
		lNum = 0;
		pNum = 0;
		temp_index = 1;
	}
	public Function(String name){
		funcName = name;
		lNum = 0;
		pNum = 0;
		temp_index = 1;
	}
}
