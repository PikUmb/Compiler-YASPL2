package Util;

import java.util.ArrayList;
import java.util.Hashtable;

/*
*Classe usata per salvare i tipi in input e in output di ogni funzione
*/

public class FunctionTable extends Hashtable<String, EntryFunzione>{
	
	private String nomeFunzione;
	
	public FunctionTable(String nomeFunzione) {
		super();
		this.nomeFunzione= nomeFunzione;
	}
	
	public void addFunzione(String nomeF) {
		this.put(nomeF, new EntryFunzione());
	}
	
	public void addParamInFunction(String nomeFunzioneTabella, String paramIn) {
		if(this.containsKey(nomeFunzioneTabella)) {
			this.get(nomeFunzioneTabella).addParametriIn(paramIn);
		}else {
			this.addFunzione(nomeFunzioneTabella);
			this.get(nomeFunzioneTabella).addParametriIn(paramIn);
		}
	}
	
	public void addParamOutFunction(String nomeFunzioneTabella, String paramOut) {
		if(this.containsKey(nomeFunzioneTabella)) {
			this.get(nomeFunzioneTabella).addParametriOut(paramOut);
		}else {
			this.addFunzione(nomeFunzioneTabella);
			this.get(nomeFunzioneTabella).addParametriOut(paramOut);
		}
	}
	
	public ArrayList<String> getParamIn(String nomeF){
		return this.get(nomeF).getParametriIn();
	}
	
	public ArrayList<String> getParamOut(String nomeF){
		return this.get(nomeF).getParametriOut();
	}
	
	public String getNomeFunzione() {
		return nomeFunzione;
	}

	public void setNomeFunzione(String nomeFunzione) {
		this.nomeFunzione = nomeFunzione;
	}

	public String toString() {
		return "[Tabella Funzioni]" +super.toString().toUpperCase();
	}
	

}
