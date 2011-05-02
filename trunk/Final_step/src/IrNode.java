
import java.util.LinkedHashMap;
import java.util.Map;


public class IrNode {
	public IrNode pred;
	public IrNode pred2;
	public IrNode succ;
	public IrNode succ2;
	
	public String kill;
	public String[] gen;
	int kills, gens;
	Map<String, String> liveVar;
	
	public String irCode;
	public String[] tinycode;

	public IrNode() {
		pred = null; pred2 = null;
		succ = null; succ2 = null;
		gen = new String[2];
		liveVar = new LinkedHashMap<String, String>();
	};
	public IrNode(String ir){
		pred = null; pred2 = null;
		succ = null; succ2 = null;
		gen = new String[2];
		liveVar = new LinkedHashMap<String, String>();
		irCode = ir;
	}
}
