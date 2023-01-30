package validators;

import java.util.ArrayList;

public abstract class Validator {
	protected ArrayList<String> results = new ArrayList<String>();
	
	public ArrayList<String> getResult() {
		return this.results;
	}
}
