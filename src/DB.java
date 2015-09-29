/**
 * Created by niko on 24/09/15.
 */
import java.sql.SQLException;
import java.util.Scanner;

public class DB {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connect.Connect();
        Scanner scan = new Scanner(System.in);
        System.out.println("Привет, выбери действие: ");
        System.out.println("1.Регистрация");
        System.out.println("2.Авторизация");
        System.out.print("Введи цифру: ");
        String operation = scan.nextLine();
        boolean flag = false;
        switch (operation) {
            case "1":
               flag = InteractiveForUser.Registration(scan);
                break;
            case "2":
                flag = InteractiveForUser.Authorization(scan);
                break;
            default:
                System.out.println("Ты не ввел цифру, олух!");
        }
        if(flag) {
            System.out.println("Выбирете действие: ");
            System.out.println("1.Заказать такси");
            System.out.println("2.Изменить заказы");
            System.out.println("3.Просмотреть заказы");
            System.out.print("Введите цифру:");
            operation = scan.nextLine();
            switch (operation) {
                case "1":
                    InteractiveForUser.CreateOrder(scan);
                    break;
                case "2":
                    InteractiveForUser.UpdateOrder(scan);
                    break;
                case "3":
                    InteractiveForUser.Show();
                    break;
                default:
                    System.out.println("Ты не выбрал, олух!");
            }
        }
        Connect.Close();
    }
}
