import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niko on 24/09/15.
 */
public class Connect {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void Connect() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:glass.sqlite");
        statement = connection.createStatement();
        System.out.println("База подключена!");
        /*CreateTable.Names(statement);
        CreateTable.LastNames(statement);
        CreateTable.Cars(statement);
        CreateTable.Users(statement);
        CreateTable.Months(statement);
        CreateTable.Orders(statement);*/
    }

    public static boolean Registration(String name, String lastname, String login, String password) throws ClassNotFoundException, SQLException {
        boolean flag = false;
        int id_name = 0;
        int id_lastname = 0;
        resultSet = statement.executeQuery("SELECT id FROM 'namen' WHERE namen = '" + name + "'");
        if (!resultSet.next()) {
            statement.execute("INSERT INTO 'namen' VALUES(null,'" + name + "')");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'lastnames' WHERE lastname = '" + lastname + "'");
        if (!resultSet.next()) {
            statement.execute("INSERT INTO 'lastnames' VALUES(null,'" + lastname + "')");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'namen' WHERE namen='" + name + "'");
        while (resultSet.next()) {
            id_name = resultSet.getInt("id");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'lastnames' WHERE lastname='" + lastname + "'");
        while (resultSet.next()) {
            id_lastname = resultSet.getInt("id");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'users' WHERE login ='" + login + "'");
        if (resultSet.next()) {
            System.out.println("Такой логин уже есть!");
        } else {
            statement.execute("INSERT INTO 'users' VALUES(null," + id_name + "," + id_lastname + ",'" + login + "','" + password + "')");
            flag = true;
        }
        return flag;
    }

    public static boolean Authorization(String login, String password) throws ClassNotFoundException {
        boolean flag = false;
        try {
            resultSet = statement.executeQuery("SELECT id FROM 'users' WHERE login ='" + login + "'");
            while (resultSet.next()) {
                resultSet = statement.executeQuery("SELECT password FROM 'users' WHERE login='" + login + "'");
                while (resultSet.next()) {
                    String passwordToken = resultSet.getString("password");
                    if (password.contains(passwordToken)) {
                        flag = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!flag) System.out.println("Неправильный логин или пароль!");
        return flag;
    }

    public static void Show(String login) throws ClassNotFoundException, SQLException {
        /*String query = "SELECT \'orders.id\' AS \'id\', \'namen.namen\' AS \'name\', \'lastnames.lastname\' AS \'lastname\'," +
                " \'cars.car\' AS \'car\', \'orders.day\' AS \'day\', \'months.month\' AS \'month\', \'orders.time\' AS 'time'" +
                " FROM months INNER JOIN (cars INNER JOIN (orders INNER JOIN" +
                " (namen INNER JOIN (lastnames INNER JOIN users ON \'lastnames.id\' = \'users.id_lastname\')" +
                " ON \'namen.id\' = \'users.id_name\') ON \'users.id\' = \'orders.id_user\')" +
                " ON \'cars.id\' = \'orders.id_car\') ON \'months.id\' = \'orders.id_month\'" +
                " WHERE \'users.login\' = '" + login + "'";
        resultSet = statement.executeQuery(query);
        if (!resultSet.next()) System.out.println("У вас нет заказов");
        else
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String car = resultSet.getString("car");
                String day = resultSet.getString("day");
                String month = resultSet.getString("month");
                String time = resultSet.getString("time");
                System.out.println(id + " " + name + " " + lastname + " " + car + " " + day + " " + month + " " + time);
            }*/
        int id = 0;
        int id_name = 0;
        int id_lastname = 0;
        resultSet = statement.executeQuery("SELECT id, id_name, id_lastname FROM users WHERE login = '"+login+"'");
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            id_lastname =resultSet.getInt("id_lastname");
            id_name = resultSet.getInt("id_name");
        }
        String namen ="";
        String lastname ="";
        String month = "";
        String car = "";
        resultSet = statement.executeQuery("SELECT namen FROM namen WHERE id = "+id_name+"");
        while (resultSet.next()) {
            namen = resultSet.getString("namen");
        }
        resultSet = statement.executeQuery("SELECT lastname FROM lastnames WHERE id = "+id_lastname+"");
        while (resultSet.next()) {
            lastname = resultSet.getString("lastname");
        }
        resultSet = statement.executeQuery("SELECT * FROM orders WHERE id_user ="+id+"");
        while(resultSet.next()) {
            int id_month = resultSet.getInt("id_month");
            int id_car = resultSet.getInt("id_car");
            int id_order = resultSet.getInt("id");
            String day = resultSet.getString("day");
            String time = resultSet.getString("time");
            ResultSet resultSet2;
            Statement statement1 = connection.createStatement();
            resultSet2 = statement1.executeQuery("SELECT months FROM months WHERE id = " + id_month + "");
            while (resultSet2.next()) {
               month = resultSet2.getString("months");
            }
            resultSet2 = statement1.executeQuery("SELECT car FROM cars WHERE id = " + id_car + "");
            while (resultSet2.next()) {
                car = resultSet2.getString("car");
            }
            System.out.println(id_order+" "+namen+" "+lastname+" "+car+" "+day+" "+month+" "+time);
        }
    }

    public static void UpdateOrder(String day, String time, int id) throws SQLException, ClassNotFoundException{
        statement.execute("UPDATE orders SET 'day' = '"+day+"', 'time' = '"+time+"' WHERE id = "+id+"");
        System.out.println("Заказ изменен!");
    }

    public static void CreateOrder(String car, String day, String month, String time, String login) throws ClassNotFoundException, SQLException {
        int id_user = 0;
        int id_car = 0;
        int id_month = 0;
        resultSet = statement.executeQuery("SELECT id FROM 'cars' WHERE car ='" + car + "'");
        if (!resultSet.next()) {
            statement.execute("INSERT INTO 'cars' VALUES(null,'" + car + "')");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'months' WHERE months ='" + month + "'");
        if (!resultSet.next()) {
            statement.execute("INSERT INTO 'months' VALUES(null,'" + month + "')");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'users' WHERE login = '"+login+"'");
        while (resultSet.next()) {
            id_user = resultSet.getInt("id");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'cars' WHERE car = '"+car+"'");
        while (resultSet.next()){
            id_car = resultSet.getInt("id");
        }
        resultSet = statement.executeQuery("SELECT id FROM 'months' WHERE months = '"+month+"'");
        while (resultSet.next()){
            id_month = resultSet.getInt("id");
        }
        statement.execute("INSERT INTO 'orders' VALUES(null,"+id_user+","+id_car+",'"+day+"',"+id_month+",'"+time+"')");
        System.out.println("Запись успешно добавлена!");
    }

    public static void Close() throws ClassNotFoundException, SQLException {
        connection.close();
        statement.close();
        //resultSet.close();
        System.out.println("Соединения закрыты!");
    }
}
