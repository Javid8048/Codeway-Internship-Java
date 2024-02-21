import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class javaQuiz {
    static Scanner scan = new Scanner(System.in);
    static Timer timer = new Timer();
    static boolean timeIsUp = false;

    public static void main(String[] args) throws InterruptedException {
        char[] correctAnswers = {'a', 'c', 'b', 'a', 'd'};
        String[][] answrOpt = {
                                    {"   (a) James Gosling", "   (b) Bjarne Stroustrup", "   (c) Guido van Rossum", "   (d) Brendan Eich"},
                                    {"   (a) 1994","   (b) 1992", "   (c) 1995", "   (d) 1996" },
                                    {"   (a) 5","   (b) 4","   (c) 3","   (d) 6"},
                                    {"   (a) Angular", "   (b) Bootstrap", "   (c) SpringBoot", "   (d) GWT"},
                                    {"   (a) Multi-platform", "   (b) Object-oriented", "   (c) Network-centric", "   (d) All of the above"},
                              };
        int totalPoints = 0;

        System.out.println("Welcome to Java Quiz");
        System.out.print("Enter Your name : ");
        String name = scan.nextLine();
        System.out.print("Enter Your Roll Number : ");
        String rollNo = scan.nextLine();
        System.out.println("\n\nLet's start the Quiz");

        totalPoints += askQuestion("1. Who created Java ?", answrOpt[0] , correctAnswers[0]);
        totalPoints += askQuestion("2. When was Java released ?", answrOpt[1], correctAnswers[1]);
        totalPoints += askQuestion("3. How many OOPs concepts are there in Java ?", answrOpt[2], correctAnswers[2]);
        totalPoints += askQuestion("4. Which one is a Java Framework ?", answrOpt[3], correctAnswers[3]);
        totalPoints += askQuestion("5. What is the basic concept of Java ?", answrOpt[4], correctAnswers[4]);

        System.out.println("Quiz Completed\n");


       System.out.println("---------------------------------------------------------------");
        System.out.printf("|                   CONGRATULATIONS                           |          \n|%s Mr/Mrs. %s|\n          |          YOU HAD SUCCESSFULLY COMPLETED THE QUIZ            |\n|              You Scored %d/%d                                 |\n", rollNo, name, totalPoints, correctAnswers.length);
       System.out.println("---------------------------------------------------------------");

        scan.close();
        timer.cancel(); //CLEAR THE TIMMER
    }

    static int askQuestion(String question,String[] opt, char correctAnswer) throws InterruptedException {

        timeIsUp = false;

        System.out.println(question);
        for(int i=0; i<opt.length; i++) {
            System.out.println(opt[i]);
        }

        MyTimerTask timerTask = new MyTimerTask();
        timer.schedule(timerTask, 10_000); // 10SEC

        char userAnswer = scan.next().charAt(0);
        timerTask.cancel(); // CANCEL WHEN SCAN.NEXT ENTERED

        if (timeIsUp) {
            System.out.println("Time's up!");
            return 0;
        } else {
            if (userAnswer == correctAnswer) {
                System.out.println("Correct!");
                return 1;
            } else {
                System.out.println("Incorrect!");
                return 0;
            }
        }
    }

    static class MyTimerTask extends TimerTask {
        public void run() {
            timeIsUp = true;
        }
    }
}





// import java.util.LinkedList;
// import java.util.Scanner;
// import java.util.concurrent.ExecutionException;

// public class javaQuiz {

//     static Scanner scan = new Scanner(System.in);

//     public static void main(String[] args) throws InterruptedException, ExecutionException {

//         char[] correctAnswers = {'a', 'c', 'b', 'a', 'd'};
//         LinkedList<Character> userAnswers = new LinkedList<Character>();
//         int totalPoints = 0;
        
  
//         System.out.println("Welcome to Java Quiz");
//         System.out.print("Enter Your name : "); 
//         String name = scan.nextLine();
//         System.out.print("Enter Your Roll Number : ");
//         int rollNo = scan.nextInt();
//         System.out.println("\n\nLet's start the Quiz");
        
//         System.out.println("1. Who created Java ?");
//         System.out.println("   (a) James Gosling");
//         System.out.println("   (b) Bjarne Stroustrup");
//         System.out.println("   (c) Guido van Rossum");
//         System.out.println("   (d) Brendan Eich");
//         userAnswers.add(scan.next().charAt(0));

//         System.out.println("2. When does Java released ?");
//         System.out.println("   (a) 1994");
//         System.out.println("   (b) 1992");
//         System.out.println("   (c) 1995");
//         System.out.println("   (d) 1996");
//         userAnswers.add(scan.next().charAt(0));

//         System.out.println("3. How many OOPs concept are there in Java ?");
//         System.out.println("   (a) 5");
//         System.out.println("   (b) 4");
//         System.out.println("   (c) 3");
//         System.out.println("   (d) 6");
//         userAnswers.add(scan.next().charAt(0));

//         System.out.println("4. Which one is Java Framework ?");
//         System.out.println("   (a) Angular");
//         System.out.println("   (b) Bootstrap");
//         System.out.println("   (c) SpringBoot");
//         System.out.println("   (d) GWT");
//         userAnswers.add(scan.next().charAt(0));

//         System.out.println("5. What is the basic concept of Java ?");
//         System.out.println("   (a) Multi-platform");
//         System.out.println("   (b) Object-oriented");
//         System.out.println("   (c) Network-centric");
//         System.out.println("   (d) All of the above");
//         userAnswers.add(scan.next().charAt(0));

//         for(int i = 0; i<correctAnswers.length; i++) {
//             if(correctAnswers[i] == userAnswers.get(i)) totalPoints++;
//         }

//         System.out.println("Quiz Completed\n");
//         System.out.println("--------------------------------------------");
//         System.out.printf("|          Congratulations \n|   %d Mr/Mrs. %s \n|You had successfully completed the quiz\n|         You Scored %d/%d\n",rollNo, name, totalPoints, correctAnswers.length);
//         System.out.println("--------------------------------------------");
//         scan.close();
//     }
// }