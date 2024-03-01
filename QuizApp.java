package srija;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class QuizApp {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public QuizApp(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        // Add questions with options and correct answers
        questions.add(new Question("What is the capital of France?",
                List.of("Berlin", "Paris", "Madrid", "Rome"), 1));
        // Add more questions...

        QuizApp quizApp = new QuizApp(questions);
        quizApp.startQuiz();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("Question: " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            startTimer();
            getUserAnswer();
        } else {
            endQuiz();
        }
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                stopTimer();
                displayNextQuestion();
            }
        }, 15000);  // 15 seconds timer for each question
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
        timer = new Timer();
    }

    private void getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer (enter the option number): ");
        int userAnswerIndex = scanner.nextInt() - 1;

        Question currentQuestion = questions.get(currentQuestionIndex);
        if (userAnswerIndex >= 0 && userAnswerIndex < currentQuestion.getOptions().size()) {
            checkAnswer(userAnswerIndex, currentQuestion.getCorrectOptionIndex());
        } else {
            System.out.println("Invalid option. Please choose a valid option.");
            getUserAnswer();
        }
    }

    private void checkAnswer(int userAnswerIndex, int correctAnswerIndex) {
        stopTimer();
        if (userAnswerIndex == correctAnswerIndex) {
            System.out.println("Correct! Well done.");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was option " + (correctAnswerIndex + 1));
        }

        currentQuestionIndex++;
        displayNextQuestion();
    }

    private void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + " out of " + questions.size());
        // Display more details about correct/incorrect answers if needed
    }
}