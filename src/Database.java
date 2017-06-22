import java.sql.*;

public class Database {

    public Database(String DBName, String user, String password) {
        this.DBName = DBName;
        this.user = user;
        this.password = password;
    }
    private String DBName, user, password;
    Connection con;

    /**
     * TODO: Implement basic functions
     * connect, select, insert, update, delete, create, drop
     * QUESTIONS:
     * - What is the scope of the select function? Should I store a current table/arg?
     *      or does it do this on its own?
     * - Should I establish a connection every time? If so, then don't need to store 'con'
     * - I don't know if the try/catch format is cool.
     * - repeated code.
     */

     /**
     * Connect to a database
     */
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/" + DBName, user, password); // Whole URL string, instead?
            this.con = con;

            // Perhaps this should be on a different level, for error messages.
            System.out.println("Connected to database " + DBName + " successfully.");

            /*
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("select * from cats"); // Specify in action.
            while(res.next())
                System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getString(3) + " " +res.getString(4));
            con.close();
            */
        } catch (Exception e) { System.out.println(e); }
    }

    public void disconnect() {
        try {
            con.close();
        } catch (Exception e) { System.out.println(e); }
    }

    public void createTable(String tableName, String tableContents) { // Should this include parens?
        try {
            // Our SQL create (table) query
            String query = "CREATE TABLE " + tableName + "(" + tableContents + ");";

            // Create java statement
            PreparedStatement statement = con.prepareStatement(query);

            // execute statement
            statement.execute();

        } catch (Exception e) { System.out.println(e); }
    }

    /**
     * Select a working table
     * @param arg
     * @param table
     */
    public void select(String arg, String table) { // Check what these should be called.
        try {

            // Our SQL select query
            String query = "SELECT " + arg + " FROM " + table;

            // Create java statement
            Statement statement = con.createStatement();

            // Execute query and get ResultSet
            ResultSet result = statement.executeQuery(query);

            /* doing stuff here:
            while (result.next()) {
                int id = result.getInt("id");
                System.out.println("ID #" + id); // Print out ID numbers for each table entry
            }
            */

            statement.close();
            System.out.println("Successfully disconnected from " + DBName);
            this.DBName = "";

        } catch (Exception e) { System.out.println(e); }
    }

    public void insert(String format, String values, String table) {
        try {

            // Our SQL insert query
            String query = "INSERT INTO " + table + " " + format + " VALUES " + values;

            // Create java statement
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // Execute query
            preparedStatement.execute();

        } catch (Exception e) { System.out.println(e);}

    }

    public void delete(String arg, String table) { // example arg: "name='muffin' "
        try {

            // Our SQL delete query
            String query = "DELETE from " + table + " where " + arg;

            // Create java statement
            PreparedStatement preparedStatement = con.prepareStatement(query); // maybe just statement?

            // Execute statement
            preparedStatement.execute();

        } catch (Exception e) { System.out.println(e); }
    }

    public void drop() {

    }

    public static void main(String[] args) {
        Database db = new Database("pets", "root", "");
        db.connect();
        db.select("*", "cats");
        db.delete("name='lena'", "cats");
        db.insert("(name, owner, birth)","('lena', 'muffin iii', '2015-01-03')", "cats");
        db.createTable("dogs", "id INT unsigned NOT NULL AUTO_INCREMENT, name VARCHAR(150) NOT " +
                "NULL, PRIMARY KEY (id)");
        db.disconnect();
        db.select("*", "cats"); // Test to make sure connection is closed.
    }
}
