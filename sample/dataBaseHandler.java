package sample;

import java.sql.*;

public class dataBaseHandler extends config{
    Connection dbConnection;
    Statement statement;
    ResultSet result = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ResultSet getUSer (){
        ResultSet resultSet = null;

        String select = "SELECT * FROM sessia.client " + constants.TABLE + " WHERE " + constants.PHONE +
                "=? AND " + constants.CODE + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, User.getPhone());
            preparedStatement.setString(2, User.getCode());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }



    public ResultSet querry(String querry) {
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = preparedStatement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet abonents(String fio, String number, String licsh, String yslugi){
        ResultSet resultSet = null;

        String selected = "INSERT INTO abonents (fio, number, licsh, yslugi)VALUES(?,?,?,?)";
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(selected);
            preparedStatement.setString(1, abonents.getFio());
            preparedStatement.setString(2, abonents.getNumber());
            preparedStatement.setString(3, abonents.getLicsh());
            preparedStatement.setString(4, abonents.getYslugi());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet users(String fio){
        ResultSet resultSet = null;

        String selected = "INSERT INTO abonents (fio)VALUES(?)";
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(selected);
            preparedStatement.setString(1, abonents.getFio());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
