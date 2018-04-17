package hhbkvdhur.Model;

import java.util.List;

import hhbkvdhur.Service.SqlProvider;

public class HHBKVdHuRModel {

    private static SqlProvider sqlProvider;

    public static void init() {
        sqlProvider = new SqlProvider();
    }

    public static List<Hardware> getHardware(Hardware h) {
        return sqlProvider.getAll(h);
    }

    public static List<Hardware> getHardware(Raum r) {
        return sqlProvider.getAll(Hardware.class, r);
    }

    public static List<Raum> getRaeume(Raum r) {
        return sqlProvider.getAll(r);
    }

    public static void addRaum(Raum r) {
        sqlProvider.insert(r);
        sqlProvider.getRaeume();
    }

    public static void addHardware(Hardware h) {
        sqlProvider.insert(h);
        sqlProvider.getHardware();
    }

    public static void updateRaum(Raum r) {
        sqlProvider.update(r);
        sqlProvider.getRaeume();
    }

    public static void updateHardware(Hardware h) {
        sqlProvider.update(h);
        sqlProvider.getHardware();
    }

    public static void deleteRaum(Raum r) {
        sqlProvider.delete(r);
        sqlProvider.getRaeume();
    }

    public static void deleteHardware(Hardware h) {
        sqlProvider.delete(h);
        sqlProvider.getHardware();
    }

}
