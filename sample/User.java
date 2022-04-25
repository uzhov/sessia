package sample;

public class User {
    public static String code;
    public static String phone;

    public User(String code, String phone){
        this.code = code;
        this.phone = phone;
    }

    public User() {

    }

    public static String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
