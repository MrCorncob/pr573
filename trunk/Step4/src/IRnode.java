public class IRnode {
	IRnode() {};
	IRnode(String ir) {ircode = ir;}
	public String ircode;
	public String[] tinycode;
	public IRnode jumpto;
}
