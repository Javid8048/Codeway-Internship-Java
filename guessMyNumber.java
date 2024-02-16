import java.util.Scanner;

public class guessMyNumber {
    static void guessNumberStart() {
        Scanner scan = new Scanner(System.in);
        int randomNumber = (int)Math.floor(Math.random()*101);
        int totalAttemps = 10;
        System.out.println("Guess a number between 1 to 100");
        try {
            boolean notGuessed = true;
            while (notGuessed) {
                int userInp = Integer.parseInt(scan.nextLine());
                if(totalAttemps == 0) {
                    System.out.println("Your lose... Gameover");
                    notGuessed = false;
                }
                else if(userInp < 1 || userInp > 100) System.out.println("Please enter a number between 0 to 100");
                else if(userInp == randomNumber) {
                    System.out.println("Hurry, you guessed the number :) \n");
                        System.out.println("_______________________");
                    System.out.printf("| Your final score %d | \n", totalAttemps);
                        System.out.println("```````````````````````");
                    notGuessed = false;
                }
                else {
                    String outputMessage = userInp < randomNumber ? "Input number is less than guess number" : "Input number is greater than guess number";
                    System.out.println(outputMessage);
                        System.out.println("____________________________");
                    System.out.printf("| Total attempts remain %d | \n ", totalAttemps--);
                        System.out.println("````````````````````````````");
                }
            }
        }       
        catch(Exception err) {
            System.err.println("Please enter an Integer");
        }

        finally {
            System.out.println("Game over\n");
            System.out.println("If you want to play again then enter option : 1");
            System.out.println("If you want to end the game enter option : 2");
            int userDecision = scan.nextInt();
            switch (userDecision) {
                case 1:
                    guessNumberStart();
                    break;
                case 2:
                    System.out.println("Game completed");
                    break;
                default:
                    System.out.println("Invalid option, game ended");
                    break;
            }
            scan.close();
        }
    }
    public static void main(String args[]) {
        guessNumberStart();
    }
}