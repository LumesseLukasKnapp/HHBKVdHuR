package hhbkvdhur.Service;

import hhbkvdhur.Model.Drucker;
import hhbkvdhur.Model.Hardware;
import hhbkvdhur.Model.Rechner;

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

            String url = "jdbc:mysql://localhost:3306/hardwareraperatur";
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

        while (resultSet.next() == true)
        {
            if(resultSet.getString("betriebsmittel") == null && resultSet.getString("imagepfad") == null)
            {
                hardwareArrayList.add(new Hardware(
                        resultSet.getInt("id"),
                        resultSet.getString("typ"),
                        resultSet.getString("seriennummer"),
                        resultSet.getString("inventarnummer"),
                        resultSet.getString("hersteller"),
                        resultSet.getString("modell"),
                        resultSet.getInt("status"),
                        resultSet.getString("art")
                ));
            }
            else if(resultSet.getString("betriebsmittel") != null && resultSet.getString("imagepfad") == null)
            {
                hardwareArrayList.add(new Drucker(
                        resultSet.getInt("id"),
                        resultSet.getString("typ"),
                        resultSet.getString("seriennummer"),
                        resultSet.getString("inventarnummer"),
                        resultSet.getString("hersteller"),
                        resultSet.getString("modell"),
                        resultSet.getInt("status"),
                        resultSet.getString("betriebsmittel"),
                        resultSet.getString("art")
                ));
            }
            else if(resultSet.getString("betriebsmittel") == null && resultSet.getString("imagepfad") != null)
            {
                hardwareArrayList.add(new Rechner(
                        resultSet.getInt("id"),
                        resultSet.getString("typ"),
                        resultSet.getString("seriennummer"),
                        resultSet.getString("inventarnummer"),
                        resultSet.getString("hersteller"),
                        resultSet.getString("modell"),
                        resultSet.getInt("status"),
                        resultSet.getString("art"),
                        resultSet.getString("imagepfad")
                ));
            }
        }

        stmt.close();

        closeConnection();

        return hardwareArrayList;
    }

    public ArrayList<Hardware> getHardwareById(int id) throws SQLException
    {
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();

        String sql;
        ResultSet resultSet;

        openConnection();

        sql = "SELECT * FROM hardware WHERE fk_raum_id = " + id;

        stmt = connection.createStatement();

        stmt.execute(sql);

        resultSet = stmt.getResultSet();

        while (resultSet.next() == true)
        {
            if(resultSet.getString("betriebsmittel") == null && resultSet.getString("imagepfad") == null)
            {
                hardwareArrayList.add(new Hardware(
                        resultSet.getInt("id"),
                        resultSet.getString("typ"),
                        resultSet.getString("seriennummer"),
                        resultSet.getString("inventarnummer"),
                        resultSet.getString("hersteller"),
                        resultSet.getString("modell"),
                        resultSet.getInt("status"),
                        resultSet.getString("art")
                ));
            }
            else if(resultSet.getString("betriebsmittel") != null && resultSet.getString("imagepfad") == null)
            {
                hardwareArrayList.add(new Drucker(
                        resultSet.getInt("id"),
                        resultSet.getString("typ"),
                        resultSet.getString("seriennummer"),
                        resultSet.getString("inventarnummer"),
                        resultSet.getString("hersteller"),
                        resultSet.getString("modell"),
                        resultSet.getInt("status"),
                        resultSet.getString("betriebsmittel"),
                        resultSet.getString("art")
                ));
            }
            else if(resultSet.getString("betriebsmittel") == null && resultSet.getString("imagepfad") != null)
            {
                hardwareArrayList.add(new Rechner(
                        resultSet.getInt("id"),
                        resultSet.getString("typ"),
                        resultSet.getString("seriennummer"),
                        resultSet.getString("inventarnummer"),
                        resultSet.getString("hersteller"),
                        resultSet.getString("modell"),
                        resultSet.getInt("status"),
                        resultSet.getString("art"),
                        resultSet.getString("imagepfad")
                ));
            }
        }

        stmt.close();

        closeConnection();

        return hardwareArrayList;
    }

    public void insertHardware(Hardware hardware, int raumId) throws SQLException
    {
        String sql;

        openConnection();

        sql = "INSERT INTO hardware(typ, seriennummer, inventarnummer, hersteller, fk_raum_id, modell, status, art) " +
                "VALUES('" + hardware.getTyp() + "','"
                + hardware.getSeriennummer() + "','"
                + hardware.getInventarnummer() + "','"
                + hardware.getHersteller() + "',"
                + raumId + ",'"
                + hardware.getModell() + "','"
                + hardware.getStatus() + "',"
                + hardware.getArt() + ")";

        // Statementobjekt erzeugen
        stmt = connection.createStatement();

        // SQL-Statement abschicken
        stmt.execute(sql);

        stmt.close();

        closeConnection();
    }

    public void insertHardware(Drucker drucker, int raumId) throws SQLException
    {
        String sql;

        openConnection();

        sql = "INSERT INTO hardware(typ, seriennummer, inventarnummer, hersteller, fk_raum_id, modell, status, art, betriebsmittel) " +
                "VALUES('" + drucker.getTyp() + "','"
                + drucker.getSeriennummer() + "','"
                + drucker.getInventarnummer() + "','"
                + drucker.getHersteller() + "',"
                + raumId + ",'"
                + drucker.getModell() + "','"
                + drucker.getStatus() + "','"
                + drucker.getArt() + "','"
                + drucker.getBetriebsmittel() + "')";

        // Statementobjekt erzeugen
        stmt = connection.createStatement();

        // SQL-Statement abschicken
        stmt.execute(sql);

        stmt.close();

        closeConnection();
    }

    public void insertHardware(Rechner rechner, int raumId) throws SQLException
    {
        String sql;

        openConnection();

        sql = "INSERT INTO hardware(typ, seriennummer, inventarnummer, hersteller, fk_raum_id, modell, status, art, imagepfad) " +
                "VALUES('" + rechner.getTyp() + "','"
                + rechner.getSeriennummer() + "','"
                + rechner.getInventarnummer() + "','"
                + rechner.getHersteller() + "',"
                + raumId + ",'"
                + rechner.getModell() + "','"
                + rechner.getStatus() + "','"
                + rechner.getArt() + "','"
                + rechner.getImagepfad() + ")";

        // Statementobjekt erzeugen
        stmt = connection.createStatement();

        // SQL-Statement abschicken
        stmt.execute(sql);

        stmt.close();

        closeConnection();
    }
}