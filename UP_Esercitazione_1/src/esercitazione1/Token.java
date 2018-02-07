package esercitazione1;

public class Token {

	private String name;
	private String attribute_value;

	public Token(String name) {
		super();
		this.name = name;
		this.attribute_value= null;
	}

	public Token(String name, String attribute_value) {
		super();
		this.name = name;
		this.attribute_value = attribute_value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttribute_value() {
		return attribute_value;
	}

	public void setAttribute_value(String attribute_value) {
		this.attribute_value = attribute_value;
	}

	@Override
	public String toString() {
		if(attribute_value==null) {
			return "<" + name + ">";
		}
		return "<" + name + ", " + attribute_value + ">";
	}
	
}
