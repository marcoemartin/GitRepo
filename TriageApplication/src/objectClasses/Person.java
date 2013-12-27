package objectClasses;

import java.io.Serializable;

public abstract class Person implements Serializable{

	/**
	 * Serialized class and it's subclass can be saved into file.
	 */
	private static final long serialVersionUID = 1255064273977169666L;
	protected String first;
	protected String last;
	

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
