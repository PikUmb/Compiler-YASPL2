package Util;

/*
 * Classe usata per salvare le variabili in input e output con i rispettivi tipi
 */

public class EntryDefinizioneFunzione {

	private String nomeVar;
	private String tipo;
	
	public EntryDefinizioneFunzione(String nomeVar, String tipo) {
		this.nomeVar= nomeVar;
		this.tipo= tipo;
	}

	public String getNomeVar() {
		return nomeVar;
	}

	public void setNomeVar(String nomeVar) {
		this.nomeVar = nomeVar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return "[Nome: " + getNomeVar() + ", Tipo: " + getTipo() + "]"; 
	}
	
	
}
