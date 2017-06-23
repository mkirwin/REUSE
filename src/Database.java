package wyvern.stdlib.support;

import java.sql.*;

public class Database {

    public Database() {
    }

    public Database(String connectionStr, String user, String password) {
        this.connectionStr = connectionStr;
        this.user = user;
        this.password = password;
    }

    private String connectionStr, user, password;

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

    // should this return a connection?
    public Connection connect(String connectionStr, String user, String password) {
        try {
            // Establish connection with DB
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(connectionStr, user, password);

            // Print success message
            System.out.println("Connected to database successfully."); // no longer has DB specific message

            // Return established connection
            return con;

        } catch (Exception e) {
            System.out.println(e);
            return null; // Should a failed connect return something else?
        }
    }

    /**
     * Disconnect from the database.
     */
    public void disconnect(Connection con) {
        try {
            // Close connection
            con.close();

            // Print success message
            System.out.println("Disconnected from database successfully.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Create a new table in the database.
     * TODO: Use an array for table contents and table name
     *
     * @param tableName
     * @param tableContents
     */
    public void createTable(Connection con, String tableName, String tableContents) {
        try {
            // Our SQL create (table) query
            String query = "CREATE TABLE " + tableName + "(" + tableContents + ");";

            // Create java statement
            PreparedStatement statement = con.prepareStatement(query);

            // Execute statement
            statement.execute();

            // Close statement
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Select a working table.
     * TODO: Use prepared statement; Don't use string for the table
     *
     * @param arg
     * @param table
     */
    public void select(Connection con, String arg, String table) {
        try {
            // Our SQL select query
            String query = "SELECT " + arg + " FROM " + table;

            // Create java statement
            PreparedStatement statement = con.prepareStatement(query);

            // Execute query and get ResultSet
            ResultSet result = statement.executeQuery(query);

            // Close statement
            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Insert an entry into a table.
     * TODO: Don't use strings for arguments
     * @param format
     * @param values
     * @param table
     */
    public void insert(Connection con, String format, String values, String table) {
        try {
            // Our SQL insert query
            String query = "INSERT INTO " + table + " " + format + " VALUES " + values;

            // Create java statement
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // Execute query
            preparedStatement.execute();

            // Close statement
            preparedStatement.close();

        } catch (Exception e) { System.out.println(e);}

    }

    public void update(Connection con, String table, String contents) {

    }

    /**
     * Delete a specified column in a table.
     * TODO: Don't use strings for the arguments
     * @param arg
     * @param table
     */
    public void delete(Connection con, String arg, String table) { // example arg: "name='muffin' "
        try {

            // Our SQL delete query
            String query = "DELETE from " + table + " where " + arg;

            // Create java statement
            PreparedStatement preparedStatement = con.prepareStatement(query); // maybe just statement?

            // Execute statement
            preparedStatement.execute();

            // Close statement
            preparedStatement.close();

        } catch (Exception e) { System.out.println(e); }
    }

    /**
     * BAD NAME: Drop a column in the table (can make drop table function).
     * TODO: Don't use strings for the arguments
     * @param col
     * @param table
     */
    public void dropTable(Connection con, String col, String table) {
        try {

            // Our SQL drop query
            String query = "ALTER TABLE " + table + " DROP " + col;

            // Create java statement
            PreparedStatement preparedStatement = con.prepareStatement(query); // maybe just statement?

            // Execute statement
            preparedStatement.execute();

            // Close statement
            preparedStatement.close();

        } catch (Exception e) { System.out.println(e); }
    }

    public static void main(String[] args) {
        Database db = new Database();
        Connection con = db.connect("jdbc:mysql://localhost:3306/pets", "root", "");
        db.createTable(con, "doggies", "name VARCHAR(50)");
        db.disconnect(con);
        /*
        db.select("*", "cats");
        db.delete("name='lena'", "cats");
        db.insert("(name, owner, birth)","('lena', 'muffin iii', '2015-01-03')", "cats");
        db.createTable("dogs", "id INT unsigned NOT NULL AUTO_INCREMENT, name VARCHAR(150) NOT " +
                "NULL, PRIMARY KEY (id)");
        db.dropTable("name", "dogs");
        db.disconnect();
        db.select("*", "cats"); // Test to make sure connection is closed.
        */
    }
}