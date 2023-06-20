package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button butt_Sign_Up;
    @FXML
    private Button butt_login;
    @FXML
    private RadioButton rb_mastr;
    @FXML
    private  RadioButton rb_emploe;
    @FXML
    private TextField tf_user;
    @FXML
    private TextField tf_pass;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_emploe.setToggleGroup(toggleGroup);
        rb_mastr.setToggleGroup(toggleGroup);

        rb_emploe.setSelected(true);

        butt_Sign_Up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

if (!tf_user.getText().trim().isEmpty() && !tf_pass.getText().trim().isEmpty()) {
    DBUtils.singUpUser(event, tf_user.getText(), tf_pass.getText(), toggleName);
}   else {
    System.out.println("Please fill in all information");
    Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setContentText("Please fill in all information to sing up");
alert.show();
               }
            }
        });
        butt_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "log in!", null, null);
            }
        });
    }
}
