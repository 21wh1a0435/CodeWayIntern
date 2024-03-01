package srija;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lowerLimit = 1;
        int upperLimit = 100;
        int attemptsLimit = 10;
        int roundsPlayed = 0;
        int totalAttempts = 0;

        while (true) {
            // Generate a random number within the specified range
            int secretNumber = (int) (Math.random() * (upperLimit - lowerLimit + 1)) + lowerLimit;
            int attemptsRemaining = attemptsLimit;

            System.out.println("\nRound " + (roundsPlayed + 1));
            System.out.println("Guess the number between " + lowerLimit + " and " + upperLimit);

            while (attemptsRemaining > 0) {
                System.out.print("Attempts left: " + attemptsRemaining + ". Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + secretNumber + "!");
                    totalAttempts += (attemptsLimit - attemptsRemaining) + 1;
                    break;
                } else if (userGuess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attemptsRemaining--;
            }

            if (attemptsRemaining == 0) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + secretNumber + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            } else {
                roundsPlayed++;
            }
        }

        System.out.println("\nGame over! Rounds played: " + roundsPlayed + ", Total attempts: " + totalAttempts);
    }
}