
public class Symbol {
	String id;
	String type;
	public int    int_value;
	public float  float_value;
	public String str_value;
	public String reg;
	public Symbol(String id) {this.id = id; this.reg = null;}
	public Symbol(String id, String type) 
	{this.id = id; this.type = type; this.reg = null;}
	public Symbol(String id, String type, String str)
	{this.id = id; this.type = type; this.str_value = str;}
	public String getId()   {return id;}
	public String getType() {return type;}
	public void print()     {System.out.println("name: "+id+" type "+type);}
	public void printStr()  {System.out.println("name: "+id+" type "+type+" value "+str_value);}
}
