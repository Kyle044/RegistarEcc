
public class Dog extends Animal {
	String Type;
	
	Dog( String _name,int _age,String _type){
		
	super(_name,_age);
	this.Type = _type;
	}
	
	
	
	public String toString() {
		
		return  super.toString() + "\n" + "Type : "+this.Type;
	}
}
