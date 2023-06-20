package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

@FXML
private Button butt_login;
@FXML
private Button butt_Sign_Up;
@FXML
private Button bt_pereiti;
@FXML
private TextField tf_user;
@FXML
private TextField tf_pass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        butt_login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, tf_user.getText(), tf_pass.getText());
            }
        });

        bt_pereiti.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/Client/Repair.fxml", "Ремонт", null, null);
            }
        });

butt_Sign_Up.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        DBUtils.changeScene(event,"signUp.fxml","Sign  Up!", null, null );
    }
});
    }
}
