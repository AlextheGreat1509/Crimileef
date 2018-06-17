package com.example.alex.crimileef.Dbal;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

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

    public ArrayList<PieEntry> getSportData(String city){
        ArrayList<PieEntry> sportEntries = new ArrayList<>();
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT District, Value from Crimileef Where City = ? AND Category = 'Sport'; ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, city);
            rs = stmt.executeQuery();

            while(rs.next()){
                sportEntries.add(new PieEntry(rs.getInt("Value"), rs.getString("District")));
            }
            return sportEntries;
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
        return sportEntries;
    }

    public ArrayList<PieEntry> getCrimeData(String city){
        ArrayList<PieEntry> crimeEntries = new ArrayList<>();
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT District, Value from Crimileef Where City = ? AND Category = 'Crime'; ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, city);
            rs = stmt.executeQuery();

            while(rs.next()){
                crimeEntries.add(new PieEntry(rs.getFloat("Value"), rs.getString("District")));
            }
            return crimeEntries;
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
        return crimeEntries;
    }

    public ArrayList<BarEntry> getComparisonSportData(String city){
        ArrayList<BarEntry> comparisonSportEntries = new ArrayList<>();
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT District, Value from Crimileef Where City = ? AND Category = 'Sport'; ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, city);
            rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()){
                comparisonSportEntries.add(new BarEntry(i,rs.getFloat("Value"), rs.getString("District")));
                i++;
            }
            return comparisonSportEntries;
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
        return comparisonSportEntries;
    }

    public ArrayList<BarEntry> getComparisonCrimeData(String city){
        ArrayList<BarEntry> comparisonCrimeEntries = new ArrayList<>();
        try {
            getCon();
            // Create and execute an SQL statement that returns some data.
            String SQL = "SELECT District, Value from Crimileef Where City = ? AND Category = 'Crime'; ";

            stmt = con.prepareStatement(SQL);
            stmt.setString(1, city);
            rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()){
                comparisonCrimeEntries.add(i, new BarEntry(i, rs.getFloat("Value"), rs.getString("District")));
                i++;
            }
            return comparisonCrimeEntries;
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
        return comparisonCrimeEntries;
    }
}
