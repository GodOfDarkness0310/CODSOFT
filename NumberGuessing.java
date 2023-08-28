import java.util.Random;
import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int target = random.nextInt(max - min + 1) + min;
        int attempts = 5;
        int remainingAttempts = attempts;
        int hintsUsed = 0;

        System.out.println("************************************");
        System.out.println("*   Welcome to the Guessing Game!  *");
        System.out.println("************************************");
        System.out.println("I've chosen a number between " + min + " and " + max + ".");
        System.out.println("You have " + attempts + " attempts.");
        System.out.println("Let's begin!\n");

        while (remainingAttempts > 0) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == target) {
                System.out.println("\nCongratulations! You're a mind reader! (^o^)/");
                System.out.println("You guessed the secret number " + target + ".");
                break;
            } else if (guess < target) {
                System.out.println("\nYour guess is too low. Aim higher! ┗( T_T )┛");
            } else {
                System.out.println("\nYour guess is too high. Bring it down! ㄟ( ▔__,▔ )ㄏ");
            }

            remainingAttempts--;
            if (remainingAttempts > 0) {
                System.out.println("You have " + remainingAttempts + " attempts left.");
                if (hintsUsed < 2 && random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        System.out.println("Here's a hint: The secret number ends with " + target % 10 + " (+_+)?");
                    } else {
                        int divisor = random.nextInt(3) + 2;
                        System.out.println("Hint: The secret number is divisible by " + divisor + " ☆*: .｡. o(≧▽≦)o .｡.:*☆");
                    }
                    hintsUsed++;
                }
                System.out.println();
            } else {
                System.out.println("Oops! You're out of attempts. Don't worry, try again! (^o^)/");
                System.out.println("The secret number was: " + target);
            }
        }

        scanner.close();
    }
}
