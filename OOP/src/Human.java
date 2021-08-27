
public class Human {
	//declaring an instance of GUIClass
	GUIClass gui = new GUIClass();
	//a static variable
	static int numOfHumans;
	String name;
	int age;
	double weight;
	boolean isReady;
	//Global Variables are declared outside the method but within a class
	//Local variables are inside only the method and can only access by that method
	Human(String _name,int _age,double _weight,boolean _isReady){
	this.name=_name;
	this.age=_age;
	this.weight=_weight;
	this.isReady=_isReady;
	// incrementing the variable for every instance
	numOfHumans++;
	HumanSaysHello();
	}
	//represent the data
	public String toString() {
		
		return "Name : "+name+"\n"+"Age : "+age+"\n"+"Weight : "+weight+"\n"+"Relationship : "+isReady+"\n";
	}
	public void EatHuman() {
		//using the display method of GUI Class
		gui.Display(this.name+" is Eating Something");
	}
	
	public void HumanSaysHello() {
		//Human Saying Hello
		gui.Display("My name is " + this.name +" And i have been Created Hello!");
	}
	//object as argument
	public void HumanTalk(Human who) {
		
		
		if(who.isReady) {
			gui.Display("My name is " + this.name +" And i would like you to invite on a date");
		}
		else {
			gui.Display(who.name+" is not yet ready for a relationship");
		}
		
		
		
	}

}
