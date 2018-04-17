package hhbkvdhur.Service;

import hhbkvdhur.Model.AllItems;
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

    public AllItems getAll() throws SQLException
    {
        AllItems allItems = new AllItems();

        allItems.setHardwareArrayList(getHardwareList());
        allItems.setDruckerArrayList(getDruckerList());
        allItems.setRechnerArrayList(getRechnerList());

        return allItems;
    }

    public ArrayList<Hardware> getHardwareList() throws SQLException
    {
        ArrayList<Hardware> hardwareArrayList = new ArrayList<>();
        String sql;
        ResultSet resultSet;

        openConnection();

        sql = "SELECT id, typ, seriennummer, inventarnummer, hersteller, modell, status FROM hardware WHERE art = 0;";

        // Statementobjekt erzeugen
        stmt = connection.createStatement();

        // SQL-Statement abschicken
        stmt.execute(sql);

        // Ergebnismenge holen
        resultSet = stmt.getResultSet();

        while (resultSet.next() == true)
        {
            hardwareArrayList.add(new Hardware(
                    resultSet.getInt("id"),
                    resultSet.getString("typ"),
                    resultSet.getString("seriennummer"),
                    resultSet.getString("inventarnummer"),
                    resultSet.getString("hersteller"),
                    resultSet.getString("modell"),
                    resultSet.getInt("status")
            ));
        }

        stmt.close();

        closeConnection();

        return hardwareArrayList;
    }

    public ArrayList<Rechner> getRechnerList() throws SQLException
    {
        ArrayList<Rechner> rechnerArrayList = new ArrayList<>();
        String sql;
        ResultSet resultSet;

        openConnection();

        stmt = connection.createStatement();

        sql = "SELECT h.id, h.typ, h.seriennummer, h.inventarnummer, h.hersteller, h.modell, h.status, r.imagepfad FROM hardware h, rechner r WHERE h.art=1 AND h.id = r.fk_hardware";

        // SQL-Statement abschicken
        stmt.execute(sql);

        // Ergebnismenge holen
        resultSet = stmt.getResultSet();

        while (resultSet.next() == true)
        {
            rechnerArrayList.add(new Rechner(
                    resultSet.getInt("id"),
                    resultSet.getString("typ"),
                    resultSet.getString("seriennummer"),
                    resultSet.getString("inventarnummer"),
                    resultSet.getString("hersteller"),
                    resultSet.getString("modell"),
                    resultSet.getInt("status"),
                    resultSet.getString("imagepfad")
            ));
        }

        closeConnection();

        return rechnerArrayList;
    }

    public ArrayList<Drucker> getDruckerList() throws SQLException
    {
        ArrayList<Drucker> druckerArrayList = new ArrayList<>();
        String sql;
        ResultSet resultSet;

        openConnection();

        stmt = connection.createStatement();

        sql = "SELECT h.id, h.typ, h.seriennummer, h.inventarnummer, h.hersteller, h.modell, h.status, p.betriebsmittel FROM hardware h, drucker p WHERE h.art=2 AND h.id = p.fk_hardware";

        // SQL-Statement abschicken
        stmt.execute(sql);

        // Ergebnismenge holen
        resultSet = stmt.getResultSet();

        while (resultSet.next() == true)
        {
            druckerArrayList.add(new Drucker(
                    resultSet.getInt("id"),
                    resultSet.getString("typ"),
                    resultSet.getString("seriennummer"),
                    resultSet.getString("inventarnummer"),
                    resultSet.getString("hersteller"),
                    resultSet.getString("modell"),
                    resultSet.getInt("status"),
                    resultSet.getString("betriebsmittel")
            ));
        }

        stmt.close();

        closeConnection();

        return druckerArrayList;
    }

    public Hardware getHardware(int id) throws SQLException
    {
        for(Hardware hardware : getAll().hardwareArrayList)
        {
            if(hardware.getId() == id)
            {
                return hardware;
            }
        }
        return null;
    }

    public Rechner getRechner(int id) throws SQLException
    {
        for(Rechner rechner : getAll().rechnerArrayList)
        {
            if(rechner.getId() == id)
            {
                return rechner;
            }
        }
        return null;
    }

    public Hardware getDrucker(int id) throws SQLException
    {
        for(Drucker drucker : getAll().druckerArrayList)
        {
            if(drucker.getId() == id)
            {
                return drucker;
            }
        }
        return null;
    }

    public void insertHardware(Hardware hardware) throws SQLException
    {
        String sql;

        openConnection();

        // TODO: HOW IS ROOM STORED ?
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

    public void insertDrucker(Drucker drucker) throws SQLException
    {
        String sql;
        String sql2;
        int id = 0;

        openConnection();

        sql = "INSERT INTO hardware(typ, seriennummer, inventarnummer, hersteller, modell, status, art) " +
                "VALUES('" + drucker.getTyp() + "','"
                + drucker.getSeriennummer() + "','"
                + drucker.getInventarnummer() + "','"
                + drucker.getHersteller() + "','"
                + drucker.getModell() + "','"
                + drucker.getStatus() + "',"
                + "2)";

        stmt = connection.createStatement();

        stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);

        java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
        if ( generatedKeys.next() ) {
            id = generatedKeys.getInt(1);
        }

        sql2 = "INSERT INTO drucker(fk_hardware, betriebsmittel)"
                + " Values(" + id + ",'" + drucker.getBetriebsmittel() + "')";

        stmt.execute(sql2);

        stmt.close();

        closeConnection();
    }

    public void insertRechner(Rechner rechner) throws SQLException
    {
        String sql;
        String sql2;
        int id = 0;

        openConnection();

        sql = "INSERT INTO hardware(typ, seriennummer, inventarnummer, hersteller, modell, status, art) " +
                "VALUES('" + rechner.getTyp() + "','"
                + rechner.getSeriennummer() + "','"
                + rechner.getInventarnummer() + "','"
                + rechner.getHersteller() + "','"
                + rechner.getModell() + "','"
                + rechner.getStatus() + "',"
                + "1)";

        stmt = connection.createStatement();

        stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);

        java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
        if ( generatedKeys.next() ) {
            id = generatedKeys.getInt(1);
        }

        sql2 = "INSERT INTO rechner(fk_hardware, imagepfad)"
                + " Values(" + id + ",'" + rechner.getImagepfad() + "')";

        stmt.execute(sql2);

        stmt.close();

        closeConnection();
    }

    //TODO: update; delete
}