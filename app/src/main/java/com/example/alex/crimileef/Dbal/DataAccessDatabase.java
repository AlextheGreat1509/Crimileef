package com.example.alex.crimileef.Dbal;
import java.sql.*;
import java.util.ArrayList;

public class DataAccessDatabase {

        // Create a variable for the connection string.
        private String connectionUrl = "jdbc:jtds:sqlserver://192.168.1.207:1433;" +
                "databaseName=Crimileef;user=App;password=App123";

        // Declare the JDBC objects.
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Establish the connection.
        public Connection getCon(){
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                con = DriverManager.getConnection(connectionUrl);
            }catch(Exception e){
                e.printStackTrace();
            }
            return con;
        }

    public ArrayList<String> getCities(){
        ArrayList<String> cities = new ArrayList<>();
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "Select Distinct City from Crimileef; ";

            stmt = con.prepareStatement(SQL);
            rs = stmt.executeQuery();

            while(rs.next()){
                cities.add(rs.getString("City"));
                System.out.println(cities.toString());
            }
            return cities;
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
        return cities;
    }

    public static void main(String... args) {
        DataAccessDatabase database = new DataAccessDatabase();
        database.getCities();
    }
}
