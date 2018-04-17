package hhbkvdhur.Model;

import java.util.List;

import hhbkvdhur.Service.SqlProvider;

public class HHBKVdHuRModel {

    private static SqlProvider sqlProvider;

    public static void init() {
        sqlProvider = new SqlProvider();
    }

    /*public static List<Hardware> getHardware() {
        return sqlProvider.getAllHardware();
    }

    public static List<Hardware> getHardware(Raum r) {
        return sqlProvider.getAllHardwareByRoomId(r.getRaumid());
    }

    public static List<Raum> getRaeume() {
        return sqlProvider.getAllRooms();
    }*/

    public static void addRoom(Raum r) {
        //sqlProvider.insertRoom(r);
    }

    public static void addHardware(Hardware h) {
        //sqlProvider.insertHardware(h);
    }

    public static void updateRoom(Raum r) {
        //sqlProvider.updateRoom(r);
    }

    public static void updateHardware(Hardware h) {
        //sqlProvider.updateHardware(h);
    }
/* ================================================================================================================== */
//    public static void deleteRaum(Raum r) {
//        sqlProvider.deleteRaum(r);
//    }
//
//    public static void deleteHardware(Hardware h) {
//        sqlProvider.deleteRaum(h);
//    }

}
