package Linklist;


public class Cookies implements DS {
	private String name;
	
	Cookies(String n) {
		name = n;
	}

	@Override
	public String toString() {
		return "Cookie [name=" + name + "]";
	}
	
	public Cookies deepCopy( )
	   {  
		Cookies clone = new Cookies(name);
	      return clone;
	   }

	@Override
	public int compareTo(Object targetKey) {
		
		 return(name.compareTo((String) targetKey));
	}





	
}
