import java.util.ArrayList;
import java.util.Scanner;

public class findStudentGrade {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of subjects ");
        int noOfSubject = scan.nextInt();
        int totalMarks = 0;
        ArrayList<Integer> SubjectMarks = new ArrayList<Integer>();
        for(int i=0; i<noOfSubject; i++) {
            System.out.printf("Enter mark %d : ", i+1);
            int mark = scan.nextInt(); 
            if(mark > 100) {
                System.out.println("Enter the correct mark, maximum mark is 100");
                continue;
            }
            SubjectMarks.add(mark);
            totalMarks+=mark;
        }
        System.out.println();
        System.out.println("Your Total Marks "+totalMarks);
        int totalPercentage = (int)Math.round(totalMarks / noOfSubject);
        System.out.println("Your Percentage "+totalPercentage+"%");
        scan.close();
        boolean failed = false;
        for (int i : SubjectMarks) {
            if(i < 35) {
                failed = true;
                break;
            }
        }
        if(failed) System.out.println("Sorry you Failed, better luck next time"); 
        else if(totalPercentage > 60) System.out.println("First Division : A grade");
        else if(totalPercentage > 50) System.out.println("Second Division : B grade");
        else if(totalPercentage > 30) System.out.println("Third Division : C grade");
        else System.out.println("Pass");
    }
}