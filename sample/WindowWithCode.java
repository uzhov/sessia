package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;

public class WindowWithCode {
    @FXML
    public TextField codeForAuthto;
    @FXML
    public Button come;
    @FXML
    public void initialize() {
        String simCode = "qwertyuiopasdfghjkzxcvbnmQWERTYUOASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        char sim;
        String codee = "";
        int index;
        for (int i = 0; i < 8; i++){
            index = random.nextInt(simCode.length());
            sim = simCode.charAt(index);
            codee += sim;

            codeForAuthto.setText(codee);

            come.setOnAction(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("sample.fxml"));

                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            });
        }

    }
}