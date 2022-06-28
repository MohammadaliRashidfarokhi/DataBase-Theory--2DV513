import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        DatabaseInitializer.run(args);
        while (true){
            System.out.println("Please Enter 1 To Register");
            System.out.println("Please Enter 2 To See All Existing Users");
            System.out.println("Please Enter 3 To See All Existing Users Who Has More Than Two Friends");
            System.out.println("Please Enter 4 To See User's Friends Of Friends");
            System.out.println("Please Enter 5 To See The Most Popular User");
            System.out.println("Please Enter 6 To  See People You May Know");
            System.out.println("Please Enter 7 To  See People You May Know (According to their hobby)");
            System.out.println("Please Enter 0 To Exit The Program");

            UsersArea.switcher(scanner);
        }
    }
}
