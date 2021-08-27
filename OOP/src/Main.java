
public class Main {

	public static void main(String[] args) {
		//declare an instance
		//GUIClass gui = new GUIClass();
		//call the function within the instance of a class
		//gui.whatName();
		//creating an instance of Human Class
		Human Kyle = new Human("Kayl",65,70,true);
		Human Toto = new Human("Toto",65,70,true);
		Kyle.HumanTalk(Toto);
		
		//Method overide to String method
		System.out.println(Kyle);
		
		//super
		
		Dog dog = new Dog("teddy", 2,"toy-foodle");
		
		System.out.println(dog);
				
		
		
		
	}

}
 