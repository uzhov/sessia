package sample;

public class abonents {
    public static String fio;
    public static String number;
    public static String licsh;
    public static String yslugi;

    public static String getFio() {
        return fio;
    }

    public static void setFio(String fio) {
        abonents.fio = fio;
    }

    public static String getNumber() {
        return number;
    }

    public static void setNumber(String number) {
        abonents.number = number;
    }

    public static String getLicsh() {
        return licsh;
    }

    public static void setLicsh(String licsh) {
        abonents.licsh = licsh;
    }

    public static String getYslugi() {
        return yslugi;
    }

    public static void setYslugi(String yslugi) {
        abonents.yslugi = yslugi;
    }

    public abonents(String fio, String number, String licsh, String yslugi){
        abonents.fio = fio;
        abonents.number = number;
        abonents.licsh = licsh;
        abonents.yslugi = yslugi;
    }

}
