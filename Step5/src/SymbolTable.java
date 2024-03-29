// package grammar;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
	public String scopeId;
	public String currentVarType;
	Map<String, SymbolTable> symTab = new LinkedHashMap<String, SymbolTable>();
	
	public SymbolTable getTab(String TabName){
		return symTab.get(TabName);
	}
	public void nestScope(String ScopeId, SymbolTable nestScope){
		symTab.put(ScopeId, nestScope);
	}
	Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();
	public SymbolTable() {};
	public SymbolTable(String scopeId){
		this.scopeId = scopeId;
	}
	public void define(Symbol s){
		symbols.put(s.id, s);
	}
	public Symbol resolve(String id){
		return symbols.get(id);
	}
	public String print(){
		return scopeId+":"+symbols;
	}
}
