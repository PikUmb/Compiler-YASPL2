package Util;

import java.util.ArrayList;

public class EntryFunzione {
	
	private ArrayList<String> parametriIn;
	private ArrayList<String> parametriOut;
	
	public EntryFunzione() {
		this.parametriIn= new ArrayList<String>();
		this.parametriOut= new ArrayList<String>();
	}
	
	public void addParametriIn(String paramIn) {
		this.parametriIn.add(paramIn);
	}
	
	public void addParametriOut(String paramOut) {
		this.parametriOut.add(paramOut);
	}

	public ArrayList<String> getParametriIn() {
		return parametriIn;
	}

	public void setParametriIn(ArrayList<String> parametriIn) {
		this.parametriIn = parametriIn;
	}

	public ArrayList<String> getParametriOut() {
		return parametriOut;
	}

	public void setParametriOut(ArrayList<String> parametriOut) {
		this.parametriOut = parametriOut;
	}
	
	public String toString() {
		return "(In: "+parametriIn.toString()+", Out: "+parametriOut.toString()+")";
	}
	
}
