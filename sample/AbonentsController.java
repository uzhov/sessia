package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class AbonentsController {
    @FXML
    private TableView<ObservableList> table;

    public void initialize() {
        try {
            Main.fill("SELECT * FROM abonents", table);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
}}
