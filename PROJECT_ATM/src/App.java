import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static Scanner scan = new Scanner(System.in);
    static int totalAmount() {
        int totalAmt = 0;
        if(list.size() == 0) return 0;
        for(int amt:list) {
            totalAmt+=amt;
        }
        return totalAmt;
    }
    static String depositAmount() {
        System.out.print("Enter the Amount : ");
        int amount = scan.nextInt();
        list.add(amount);
        return  amount+" Amount deposit successfully";
    }
    static String withdrawAmount() {
        if(totalAmount() < 0) return "___________________\n| Sorry, you have insufficient balance |\n```````````````\n";
        System.out.print("Enter the Amount : ");
        int amount = scan.nextInt();
        if(amount > totalAmount()) return "___________________\n| Sorry, you have insufficient balance |\n```````````````\n";
        list.add(-amount);
        return  amount+" Amount withdrawed successfully";
    }
    public static void main(String[] args) throws Exception {
        list.add(1000);
        System.out.println("Welcome");
        try {
            boolean needService = true;
            while(needService) {
                System.out.println("What you want to do");
                System.out.println("a : Deposit amount");
                System.out.println("b : Withdraw amount");
                System.out.println("c : Check Balance");
                System.out.println("d : Account Details");
                System.out.println("e : Exit \n");
                char opt = scan.next().charAt(0);
                switch (opt) {
                    case 'a':
                        System.out.println(depositAmount());
                        break;
                    case 'b':
                        System.out.println(withdrawAmount()+"\n");
                        break;
                    case 'c':
                        System.out.println("You have "+totalAmount()+" in your bank"+"\n");
                        break;
                    case 'd':
                        System.out.println("This account is belong to Mr. JAVID ASHWAQ F \nAccount No. 2113171058048"+"\n");
                        break;
                    case 'e':
                        System.out.println("Thank you, we hope you like our service"+"\n");
                        needService = false;
                        break;
                    default:
                        System.out.println("Invalid option"+"\n");
                        break;
                    }
            }
        }catch( Exception err) {
            System.out.println(err);
        }finally {
            System.out.println();
            scan.close();
        }
    }
}