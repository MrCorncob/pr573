import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
	public String scopeId;
	public String currentVarType;
	public SymbolTable parentScope;
	Map<String, SymbolTable> tables = new HashMap<String, SymbolTable>();
	public void nestScope(String nestScopeID, SymbolTable nestScope) 
		{tables.put(nestScopeID, nestScope);}
	public SymbolTable getTab(String tabName) { return tables.get(tabName);}
	Map<String, Symbol> symbols = new HashMap<String, Symbol>();
	public Symbol getSym(String symName) {return symbols.get(symName);}
	public SymbolTable() {};
	public SymbolTable(String newScopeId) {this.scopeId = newScopeId;}
	public void define(Symbol s) {symbols.put(s.id, s);}
	public Symbol resolve(String id) {return symbols.get(id);}
	public String print(){return scopeId+":"+symbols;}
}
