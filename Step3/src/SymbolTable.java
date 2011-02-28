
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	public String scopeId;
	public String currentVarType;
	Map<String, Symbol> symbols = new HashMap<String, Symbol>();
	public SymbolTable() {};
	public SymbolTable(String newScopeId) {this.scopeId = newScopeId;}
	public void define(Symbol s) {symbols.put(s.id, s);}
	public Symbol resolve(String id) {return symbols.get(id);}
	public String print(){return scopeId+":"+symbols;}
}
