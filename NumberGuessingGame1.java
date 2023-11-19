import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame1
{
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	Random random=new Random();
	int lowerBound=1;
	int upperBound=100;
	int maxAttempts=5;
	int totalRounds=3;
	int totalScore=0;
	System.out.println("WELCOME TO THE NUMBER GUESSING GAME!");
 	for (int round = 1; round <= totalRounds; round++){
		int randomNumber=random.nextInt(upperBound-lowerBound+1)+lowerBound;
		int attempts=0;
		System.out.println("\nRound"+round+ ":");
		System.out.println("Try to guess the number between"+lowerBound+"and"+upperBound);
		while(attempts<maxAttempts)
		{
			System.out.println("Enter your Guess:");
			int userGuess= sc.nextInt();
			attempts++;
			if(userGuess==randomNumber)
			{
				System.out.println("Congratualations! You guessed the number in"+attempts+"attempts.");								int roundScore=maxAttempts-attempts+1;
				totalScore+=roundScore;  
				System.out.println("Round Score: " + roundScore + " | Total Score: " + totalScore);
				break;
			}
			else if (userGuess<randomNumber)
			{
				System.out.println("Try again!The number is higher.");
			}
			else
			{
				System.out.println("Try again! The number is lower.");
			}
		}
		if(attempts==maxAttempts)
		{
			System.out.println("Sorry you have reached the maximum number of attempts. The correct number was:"+randomNumber);								
		}
	}
	System.out.println("\n Game over. your Total Score:"+ totalScore);
	
}
}
		
 

	