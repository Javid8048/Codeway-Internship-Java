import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    static Scanner scan = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Connection connection = null;
    static Statement st = null;
    static PreparedStatement ps = null;
    static  ResultSet rs; 

    static char whatYouWantToDo() {
        System.out.println("a) New Registration");
        System.out.println("b) Get course details");
        System.out.println("c) Change course");
        System.out.println("d) Change Name");
        System.out.println("e) Change Roll Number");
        System.out.println("f) Remove from a course");
        System.out.println("g) Get my course details");
        System.out.println("h) exit");
        System.out.print("\nEnter your option : ");
        char c = scan.next().charAt(0) ;
        return c;
    }

    static int getNoOfCourses() throws SQLException {
       int index = 1;
        rs = st.executeQuery("Select title from course");
        while (rs.next()) {
            String data = rs.getString(1);
            System.out.println(index+") "+data);
            index++;
        };
       return index;    
    }

    static void getCoursesDetails() throws SQLException, IOException {
       getNoOfCourses();
       System.out.print("select an option : ");
       int code = scan.nextInt();
        st.execute("Select * from course where code = "+code);
        rs = st.getResultSet();
        while (rs.next()) {
            String data = rs.getString("description");
            System.out.println("\n--------------------------------------------------------------\n"+data+"\n--------------------------------------------------------------");
        };   
        startApp();
    }

    static void registerNew() throws SQLException, IOException {
        System.out.print("Please enter your name : ");        String getName = reader.readLine();
        System.out.print("Please enter your roll number : "); int getRollNo = scan.nextInt();
        System.out.println("Enter the option for course");

        rs = st.executeQuery("Select title from course");
        int getCourseLength = getNoOfCourses();
        String opt;
        int courseChoosenOpt = 0;
        while( courseChoosenOpt == 0) {
            System.out.print("\nEnter the option : ");
            opt = scan.next();
            courseChoosenOpt = Integer.parseInt(opt);
            if(courseChoosenOpt > getCourseLength) {
                System.out.println("Sorry invalid option, please check and type the option again");
                courseChoosenOpt = 0;
            };
        }
        st.execute("select title from course where code = "+courseChoosenOpt);
        rs =st.getResultSet();
        rs.next();
        String courseTitle = rs.getString(1);
        st.executeUpdate("insert into  student values("+getRollNo+", '"+getName+"', '"+courseTitle+"');");
        System.out.println("Registered successfully");
    }
    
    static void getMyCourseDetails() throws NumberFormatException, SQLException, IOException {
            System.out.print("Enter your roll number : ");
            int getRollNo = scan.nextInt();
            st.execute("SELECT * FROM student WHERE stdId='"+getRollNo+"';");
            rs = st.getResultSet();
            if (rs.next()) { // Process result set
                String stdName = rs.getString("stdName");
                String course = rs.getString("registeredCourse");
                System.out.println("\n----------------------------------------------------------\n| Mr/Mrs. \""+stdName+"\" your course is \""+course+"\". |\n----------------------------------------------------------\n");
            } else {                  
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n| No data found, please register a new one |\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            }
            startApp();
    }
    
    static void changeCourse() throws IOException, SQLException {
        System.out.print("Please enter your roll number : ");
        long rollNo = scan.nextLong();    
        st.execute("SELECT * FROM student WHERE stdId="+rollNo);
        rs = st.getResultSet();
        if(rs.next()) {
            System.out.println("Mr/Mrs. \""+rs.getString("stdName")+"\" your course is \""+rs.getString("registeredCourse")+"\" are you sure want to change your course");
            System.out.print("(y/n) ");
            char inp = scan.next().charAt(0);
            if(inp == 'y') {
                getNoOfCourses();
                System.out.print("\nEnter the option : ");
                int opt = scan.nextInt();
                st.execute("select * from course where code = "+opt);
                rs = st.getResultSet();
                rs.next();
                String courseTitle = rs.getString("title");
                st.executeUpdate("update student set registeredCourse = '"+courseTitle+"' where stdId = "+rollNo+";");
                System.out.println( "\n+++++++++++++++++++++++++++++++++++++++++++++\n  Your course had been changed successfully\n+++++++++++++++++++++++++++++++++++++++++++++\n");
            } else if(inp == 'n') {
                startApp();
            } else {
                System.out.println("Invalid option");
            }   
        }else {                  
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n| No data found, please register a new one |\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        }
        startApp();
    }

    static void changeName() throws SQLException, IOException {
        System.out.print("Please enter your roll number : ");
        int rollNo = scan.nextInt();
        st.execute("SELECT * FROM student WHERE stdId="+rollNo);
        rs = st.getResultSet();
        if(rs.next()) {
            System.out.println("Your Previous name is \""+rs.getString("stdName")+"\" are you sure want to change your name");
            System.out.print("(y/n) ");
            char inp = scan.next().charAt(0);
            if(inp == 'y') {
                System.out.print("Enter the name : ");
                String newName = reader.readLine();
                st.executeUpdate("update student set stdName='"+newName+"' where stdId="+rollNo+";");
                System.out.println( "\n+++++++++++++++++++++++++++++++++++++++++++\n  Your name had been changed successfully\n+++++++++++++++++++++++++++++++++++++++++++\n");
            } else if(inp == 'n') {
                startApp();
            } else {
                System.out.println("Invalid option");
            }
        } else {
            System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n  Roll number is not registered, register a new one \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
            startApp();
        }
    }
    static void changeRollNo() throws SQLException, IOException {
        System.out.print("Enter your roll number : ");
        long rollNo = scan.nextInt();
        st.execute("SELECT * FROM student WHERE stdId="+rollNo);
        rs = st.getResultSet();
        if(rs.next()) {
            System.out.println("Mr/Mrs. \""+rs.getString("stdName")+"\" are you sure want to change your roll number");
            System.out.print("(y/n) ");
            char inp = scan.next().charAt(0);
            if(inp == 'y') {
                System.out.print("Enter new roll number : ");
                int newRollNo = scan.nextInt();
                st.executeUpdate("update student set stdId="+newRollNo+" where stdId="+rollNo);
                System.out.println( "\n++++++++++++++++++++++++++++++++++++++++++++++++++\n  Your Roll Number had been changed successfully\n++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            } else if(inp == 'n') {
                startApp();
            } else {
                System.out.println("Invalid option");
            }
        } else {
            System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n  Roll number is not registered, register a new one \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
            startApp();
        }
    }
    static void removeFromCourse() throws SQLException, IOException {
        System.out.print("Enter your roll number : ");
        long rollNo = scan.nextInt();
        st.execute("SELECT * FROM student WHERE stdId="+rollNo);
        rs = st.getResultSet();
        if(rs.next()) {
            System.out.println("Mr/Mrs. \""+rs.getString("stdName")+"\" are you sure want to exit from a course \""+rs.getString("registeredCourse")+"\"");
            System.out.print("(y/n) ");
            char inp = scan.next().charAt(0);
            if(inp == 'y') {
                System.out.print("Enter new roll number again : ");
                int getRollNo = scan.nextInt();
                st.execute("delete from student where stdId="+getRollNo);
                System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n Your data has been successfully deleted from our system \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
            } else if(inp == 'n') {
                startApp();
            } else {
                System.out.println("Invalid option");
            }
        } else {
            System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n  Roll number is not registered, register a new one \n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
            startApp();
        }
    }

    static void startApp() throws SQLException, IOException {
        System.out.println("--------------------------------------------------------");
        System.out.println("  Welcome to the course section, what you want to do ?");
        System.out.println("--------------------------------------------------------");
        char userOpt = whatYouWantToDo();
            switch (userOpt) {
                case 'a': registerNew(); break;
                case 'b': getCoursesDetails(); break;
                case 'c': changeCourse(); break;
                case 'd': changeName(); break;
                case 'e': changeRollNo(); break;
                case 'f': removeFromCourse(); break;
                case 'g': getMyCourseDetails(); break;
                case 'h': System.out.println("Exit with status code 200"); break;
            
                default:
                    System.out.println("Invalid option; Please try again with correct option");
                    break;
            }
    }

    public static void main(String[] args) throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5400/collegeDB","postgres", "postgres");
            if(connection == null)  throw new Error("Connection failed, Please try after some time");
            st = connection.createStatement();
            startApp();
        
        } catch(Exception err) {
            System.out.println(err.getMessage());
        } finally {
            System.out.println("Thank you, All the best for the course");
            st.close();
            connection.close();
            scan.close();
        }
    }
}
