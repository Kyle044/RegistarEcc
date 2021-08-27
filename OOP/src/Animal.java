
public abstract class Animal {
	String name;
	int age;
	
	Animal(String _name,int _age){
		
		
		this.name=_name;
		this.age=_age;
		
	}
	abstract void go();
	public String toString() {
		return "Name : " +this.name+ "\n" +"Age : "+ this.age;  
	}
}
