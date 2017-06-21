import java.sql.*;

public class Database {

    public Database(String DBName, String user, String password) {
        this.DBName = DBName;
        this.user = user;
        this.password = password;
    }
    private String DBName, user, password;

    /**
     * TODO: Implement basic functions
     * connect, select, insert, update, delete, create, drop
     * /
RRR
    /**
     * Connect to a database
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/" + DBName, user, password);
            // Perhaps this should be on a different level, for error messages.
            System.out.println("Connected to database " + DBName + " successfully.");

            // Stuff after this line is for different functions.
            /*
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("select * from cats"); // Specify in action.
            while(res.next())
                System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getString(3) + " " +res.getString(4));
            con.close();
            */
        } catch (Exception e) { System.out.println(e); }
    }

    public static void main(String[] args) {
        Database db = new Database("pets", "root", "");
        db.connect();
    }
}
