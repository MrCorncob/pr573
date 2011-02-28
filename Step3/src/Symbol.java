
public class Symbol {
	String id;
	String type;
	String str;
	public Symbol(String id) {this.id = id;}
	public Symbol(String id, String type) 
	{this.id = id; this.type = type;}
	public Symbol(String id, String type, String str)
	{this.id = id; this.type = type; this.str = str;}
	public String getId()   {return id;}
	public String getType() {return type;}
	public void print()     {System.out.println("name: "+id+" type "+type);}
	public void printStr()  {System.out.println("name: "+id+" type "+type+" value: "+str);}
}
