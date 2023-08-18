import java.util.Scanner;
import java.util.Random;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
        int min = 1;
        int max = 100;
        int attempts = 5;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I've chosen a number between " + min + " and " + max + ".");
        
        int target = rand.nextInt(max - min + 1) + min;
        boolean correctGuess = false;
        
        for (int attempt = 1; attempt <= attempts; attempt++) {
            System.out.print("Attempt #" + attempt + ": Your guess: ");
            int guess = sc.nextInt();
            
            if (guess == target) {
                correctGuess = true;
                System.out.println("Congratulations! You guessed correctly.");
                break;
            } else if (guess < target) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }
        
        if (!correctGuess) {
            System.out.println("Sorry, you're out of attempts. The correct number was: " + target);
        }
        
        sc.close();
    }
}
