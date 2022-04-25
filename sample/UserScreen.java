package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserScreen extends config {
    @FXML
    public Button abonentsBtn;
    @FXML
    public Button equipmentManagementBtn;
    @FXML
    public Button activsBtn;
    @FXML
    public Button billingBtn;
    @FXML
    public Button userSupportBtn;
    @FXML
    public Button crmBtn;

    @FXML
    public MenuButton userMenu;



    private ObservableList<ObservableList> data;
    @FXML
    private TableView<ObservableList> table;


    public void initialize() {
        try {
            Main.fill("SELECT `ФИО`, `Номер абонента`, `Личный счет`, `Услуги` FROM abonents", table);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        setMenuButton();

        FXMLLoader loader = new FXMLLoader();
        abonentsBtn.setOnAction(event -> {
            loader.setLocation(getClass().getResource("abonents.fxml"));
            abonentsBtn.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            assert root != null;
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
    private void setMenuButton() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sessia",
                    "root", "2345678Qwert")) {
                Statement statement = conn.createStatement();
                statement = conn.prepareStatement("SELECT `ФИО` FROM abonents");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM abonents");
            while(resultSet.next()) {
                String name = resultSet.getString("ФИО");
                MenuButton menuItem = new MenuButton(name);

                //EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
                    //public void handle(ActionEvent e) {
                  //      data.getItems().add(menuItem);}
                //};
            }
        } catch (SQLException ex) {
            Logger.getLogger(String.valueOf(ex));
        }
    } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}