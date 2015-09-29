import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by niko on 24/09/15.
 */
public class InteractiveForUser {
    public static String login;
    public static boolean Authorization(Scanner scan) throws ClassNotFoundException, SQLException {
        System.out.print("Введите логин: ");
        login = scan.nextLine();
        System.out.print("Введите пароль: ");
        String password = scan.nextLine();
        boolean flag = Connect.Authorization(login,password);
        return flag;
    }

    public static boolean Registration(Scanner scan) throws SQLException, ClassNotFoundException {
        System.out.print("Введи имя: ");
        String name = scan.nextLine();
        System.out.print("Введи фамилию: ");
        String lastName = scan.nextLine();
        System.out.print("Введите логин: ");
        login = scan.nextLine();
        System.out.print("Введите пароль: ");
        String password = scan.nextLine();
        boolean flag = Connect.Registration(name,lastName,login,password);
        return flag;
    }

    public static void CreateOrder(Scanner scan) throws SQLException, ClassNotFoundException{
        System.out.print("Какую машину вы хотите: ");
        String car = scan.nextLine();
        System.out.print("Укажите день: ");
        String day = scan.nextLine();
        System.out.print("Укажите месяц: ");
        String month = scan.nextLine();
        System.out.println("Укажите время: ");
        String time = scan.nextLine();
        Connect.CreateOrder(car,day,month,time,login);
    }

    public static void UpdateOrder(Scanner scan) throws SQLException, ClassNotFoundException {
        System.out.print("Укажите id заказа");
        int id = scan.nextInt();
        System.out.print("Укажите день: ");
        String day = scan.nextLine();
        System.out.print("Укажите время: ");
        String time = scan.nextLine();
        Connect.UpdateOrder(day,time,id);
    }

    public static void Show() throws SQLException,ClassNotFoundException {
        Connect.Show(login);
    }
}
