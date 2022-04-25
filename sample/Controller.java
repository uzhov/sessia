package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller extends WindowWithCode{
    @FXML
    public Label getError;
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;
    @FXML
    public TextField codeAutho;
    @FXML
    public Button exit;
    @FXML
    public Button enter;
    @FXML
    public Button update;
    @FXML
    public void initialize(){
        enter.setOnAction(event -> {
            String nomer = login.getText().trim();
            String pass = password.getText().trim();
            String code = codeAutho.getText().trim();
            if (!nomer.equals("") && !pass.equals("") && !code.equals("")) {
                try {
                    loginUser(nomer, pass);
                } catch (Exception e) {
                    getError.setText("Введите данные в поля");
                }
            }
        });

        update.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("windowWithCode.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });
}

    private void loginUser(String nomer, String pass) {
        dataBaseHandler dataBaseHandler = new dataBaseHandler();

        User user = new User();
        user.setCode(pass);
        user.setPhone(nomer);
        ResultSet resultSet = dataBaseHandler.getUSer();
        int count = 0;

        try{
            while(resultSet.next()){
                count ++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if((count>=1)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userScreen.fxml"));
            try{
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }

        if(count==0){
            getError.setText("Неверные данные");
        } else {
            getError.setText("Успешно");
        }
    }
}
