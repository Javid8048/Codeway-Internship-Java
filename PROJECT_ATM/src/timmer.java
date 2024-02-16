import java.util.Scanner;

public class timmer {
    private static final int TIMEOUT_DURATION = 10000; // Timeout duration in milliseconds (10 seconds)
    private static boolean answered = false;

    public static void main(String[] args) {
        Thread timerThread = new Thread(() -> {
            try {
                Thread.sleep(TIMEOUT_DURATION);
                if (!answered) {
                    System.out.println("\nTime's up! Moving to the next question.");
                    // Close scanner or perform any other necessary actions
                    System.exit(0); // Exit the program or handle next question logic here
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();

        System.out.println("Question 1: What is the capital of France?");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        answered = true; // User has entered an answer
        // Process user answer and continue with the quiz
    }
}
