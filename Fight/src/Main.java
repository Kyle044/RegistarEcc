import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Main {

	static String[] choices = { "ROCK", "PAPER", "SCISSORS" };

	public static void main(String[] args) {
		God Loki = new God("Loki God Of Mischief", "Staff of Death", "Hypnotized", 50, 100, 500);

		God Thor = new God("Thor God Of Thunder", "ThunderBolt", "Thundrada", 40, 120, 700);

		Scanner scan = new Scanner(System.in);
		String winner = null;

		System.out.println("Play Rock Paper Scissors to Know who will gonna be first");
		String input = scan.nextLine();
		String result = playRock(input);
		while (result == "draw") {
			System.out.println("Play Rock Paper Scissors to Know who will gonna be first");
			input = scan.nextLine();
			result = playRock(input);
		}
		if (result == "won") {

			System.out.println("Choose whos gonna attack first [ME] OR [ENEMY] : ");
			winner = scan.nextLine();
		} else if (result == "lose") {
			winner = "ENEMY";
		}
		
		
		if(winner.equals("ME")) {
			System.out.println("You will go first to attack");
			System.out.println("Your GOD is "+ Thor.name);
			while(Thor.HP>0 && Loki.HP>0 ) {
				System.out.println("ATTACK WITH YOUR " + "[" + Thor.NormalMove+"]" + " Or " + "[" + Thor.SpecialMove +"]");
				String attack = scan.nextLine();
				try
				{
				    Thread.sleep(3000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				if(attack.equals(Thor.NormalMove)) {
					Loki.getDamaged(Thor.doNormalMove());
					System.out.println("Loki HP : " + Loki.HP);
				}
				else if(attack.equals(Thor.SpecialMove)) {
					Loki.getDamaged(Thor.doSpecialMove());
					System.out.println("Loki HP : " + Loki.HP);
				}
				try
				{
				    Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				
				System.out.println("Loki Does his Special move : " + Loki.SpecialMove);
				try
				{
				    Thread.sleep(3000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				Thor.getDamaged(Loki.doSpecialMove());
				System.out.println("Thor HP : " + Thor.HP);
			}
			try
			{
			    Thread.sleep(3000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			if(Thor.HP<0) {
				System.out.println("Loki Won!");
			}
			else if (Loki.HP<0) {
				System.out.println("Thor Won!");
			}
		}
		else if(winner.equals("ENEMY")) {
			System.out.println("The Enemy will Attack First");
			System.out.println("Your GOD is "+ Thor.name);
			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			while(Thor.HP>0 && Loki.HP>0 ) {
				System.out.println("Loki Does his Special move : " + Loki.SpecialMove);
				try
				{
				    Thread.sleep(3000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				Thor.getDamaged(Loki.doSpecialMove());
				System.out.println("Thor HP : " + Thor.HP);
				try
				{
				    Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				System.out.println("ATTACK WITH YOUR " + "[" + Thor.NormalMove+"]" + " Or " + "[" + Thor.SpecialMove +"]");
				String attack = scan.nextLine();
				try
				{
				    Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
				    Thread.currentThread().interrupt();
				}
				if(attack.equals(Thor.NormalMove)) {
					Loki.getDamaged(Thor.doNormalMove());
					System.out.println("Loki HP : " + Loki.HP);
				}
				else if(attack.equals(Thor.SpecialMove)) {
					Loki.getDamaged(Thor.doSpecialMove());
					System.out.println("Loki HP : " + Loki.HP);
				}
				
			
			}
			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			if(Thor.HP<0) {
				System.out.println("Loki Won!");
			}
			else if (Loki.HP<0) {
				System.out.println("Thor Won!");
			}
		}
		
		

	}

	public static String playRock(String choice) {
		Random random = new Random();
		int randNumber = random.nextInt(3);

		String Computer = choices[randNumber];

		if (choice.equals("ROCK")) { // to test if the object is equal refer to this
										// https://stackoverflow.com/questions/8661559/reading-and-checking-strings-from-user-input

			if (Computer == "SCISSORS") {
				System.out.println("YOU HAVE WON THE COMPUTER PICKED SCISSORS");
				return "won";
			} else if (Computer == "PAPER") {
				System.out.println("YOU LOSE THE COMPUTER PICKED PAPER");
				return "lose";
			} else {
				System.out.println("ITS A DRAW");
				return "draw";
			}

		} else if (choice.equals("PAPER")) { // to test if the object is equal refer to this
												// https://stackoverflow.com/questions/8661559/reading-and-checking-strings-from-user-input

			if (Computer == "SCISSORS") {
				System.out.println("YOU LOSE THE COMPUTER PICKED SCISSORS");
				return "lose";
			} else if (Computer == "ROCK") {
				System.out.println("YOU WON THE COMPUTER PICKED ROCK ");
				return "won";
			} else {
				System.out.println("ITS A DRAW");
				return "draw";
			}

		} else if (choice.equals("SCISSORS")) { // to test if the object is equal refer to this
												// https://stackoverflow.com/questions/8661559/reading-and-checking-strings-from-user-input

			if (Computer == "PAPER") {
				System.out.println("YOU WON THE COMPUTER PICKED PAPER");
				return "won";
			} else if (Computer == "ROCK") {
				System.out.println("YOU LOSE THE COMPUTER PICKED ROCK");
				return "lose";
			} else {
				System.out.println("ITS A DRAW");
				return "draw";
			}

		} else {
			System.out.println("SOMETHING WENT WRONG");
			return "wrong";
		}

	}
}