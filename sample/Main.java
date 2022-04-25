package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javafx.util.Callback;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Telecom");
        primaryStage.setScene(new Scene(root, 670, 480));
        primaryStage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(args);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/sessia";
        String nameUser = "root";
        String passwordUser = "2345678Qwert";
        Connection connection = DriverManager.getConnection(url, nameUser, passwordUser);
        if (connection != null){
            System.out.println("The database is connected");
        }
        else {
            System.out.println("he database is not connected");
        }
    }

    public static void fill(String querry, TableView<ObservableList> table) throws SQLException {
        table.getColumns().clear();
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        dataBaseHandler dbHandler = new dataBaseHandler();
        ResultSet resultSet = dbHandler.querry(querry);
        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(resultSet.getMetaData().getColumnLabel(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            table.getColumns().addAll(col);
        }
        while (resultSet.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.add(resultSet.getString(i));
            }
            data.add(row);
        }
        table.setItems(data);
    }

}
