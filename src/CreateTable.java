import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by niko on 24/09/15.
 */
public class CreateTable {

    public static void Names(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE 'namen' (" +
                "'id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," +
                " 'namen' CHAR NOT NULL  UNIQUE " +
                ")");
        System.out.println("Таблица Names создана!");

    }

    public static void LastNames(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE 'lastnames' (" +
                "'id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ," +
                "'lastname' CHAR NOT NULL  UNIQUE " +
                ")");
        System.out.println("Таблица lastnames создана!");
    }

    public static void Cars(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE 'cars' (" +
                "'id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ," +
                "'car' CHAR  NOT NULL  UNIQUE " +
                ")");
        System.out.println("Таблица cars создана!");
    }

    public static void Users(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE 'users' (" +
                "'id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE ," +
                "'id_name' INTEGER  NOT NULL, "+
                "'id_lastname' INTEGER NOT NULL, "+
                "'login' CHAR NOT NULL UNIQUE , "+
                "'password' CHAR NOT NULL,"+
                "FOREIGN KEY('id_name') REFERENCES 'namen'('id')," +
                "FOREIGN KEY('id_lastname') REFERENCES 'lastnames'('id')" +
                ")");
        System.out.println("Таблица users создана!");
    }

    public static void Months(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE 'months' (" +
                "'id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," +
                " 'months' CHAR NOT NULL  UNIQUE " +
                ")");
        System.out.println("Таблица months создана!");
    }

    public static void Orders(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE 'orders' (" +
                "'id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE," +
                "'id_user' INTEGER NOT NULL ," +
                "'id_car' INTEGER NOT NULL," +
                "'day' CHAR NOT NULL," +
                "'id_month' INTEGER NOT NULL," +
                "'time' CHAR NOT NULL ," +
                "FOREIGN KEY('id_user') REFERENCES 'users'('id')," +
                "FOREIGN KEY('id_car') REFERENCES 'cars'('id')," +
                "FOREIGN KEY('id_month') REFERENCES 'months'('id')" +
                ")");
        System.out.println("Таблица orders создана!");
    }
}
