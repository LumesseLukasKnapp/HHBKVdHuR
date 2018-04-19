package hhbkvdhur.Service;

import hhbkvdhur.Model.Hardware;

import java.sql.*;
import java.util.*;

public class SqlProvider
{
    private Connection connection;
    private Statement stmt;

    public SqlProvider()
    {
        this.connection = null;
        this.stmt = null;
    }

    private void openConnection() throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://localhost:3306/hardwarereperatur";
            this.connection = DriverManager.getConnection(url,"root","");
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void closeConnection()
    {
        if(this.connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Hardware> getAllHardware() throws SQLException
    {
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();
        String sql;
        ResultSet resultSet;

        openConnection();

        sql = "SELECT * FROM hardware";

        stmt = connection.createStatement();

        stmt.execute(sql);

        resultSet = stmt.getResultSet();

        while (resultSet.next() == true) {
            hardwareArrayList.add(new Hardware(
                    resultSet.getInt("id"),
                    resultSet.getString("typ"),
                    resultSet.getString("seriennummer"),
                    resultSet.getString("inventarnummer"),
                    resultSet.getString("hersteller"),
                    resultSet.getString("modell"),
                    resultSet.getInt("status"),
                    resultSet.getString("imagepfad"),
                    resultSet.getString("art"),
                    resultSet.getString("betriebsmittel")
            ));
        }

        stmt.close();

        closeConnection();

        return hardwareArrayList;
    }

    public void insertHardware(Hardware hardware) throws SQLException
    {
        String sql;

        openConnection();

        sql = "INSERT INTO hardware(typ, seriennummer, inventarnummer, hersteller, modell, status, art) " +
                "VALUES('" + hardware.getTyp() + "','"
                + hardware.getSeriennummer() + "','"
                + hardware.getInventarnummer() + "','"
                + hardware.getHersteller() + "','"
                + hardware.getModell() + "','"
                + hardware.getStatus() + "',"
                + "0)";

        // Statementobjekt erzeugen
        stmt = connection.createStatement();

        // SQL-Statement abschicken
        stmt.execute(sql);

        stmt.close();

        closeConnection();
    }
}