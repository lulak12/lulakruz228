package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class logInController implements Initializable {
@FXML
private Button  butt_exit;
@FXML
private Label label_welcome;
@FXML
private Label label_channel;
@FXML
private Button bt_custumer;
@FXML
private Button bt_devices;
@FXML
private Button bt_repair;
@FXML
private Button Bt_master;
@FXML
private Button bt_matelials;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        butt_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"sample.fxml", "log in!", "null", null);
            }
        });
        bt_custumer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               DBUtils.changeScene(event, "/emploe/cuctumers/custumer.fxml","сотркдники","null",null );
            }
        });
        bt_repair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/emploe/repair/Repair.fxml","ремонт","null",null );
            }
        });
        Bt_master.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/emploe/masters/masters.fxml","мастера","null",null );
            }
        });
        bt_devices.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/emploe/devices/devices.fxml","оборудование","null",null );
            }
        });
        bt_matelials.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "/emploe/materials/materials.fxml","материалы","null",null );
            }
        });
    }
        public void setUserInfo(String username, String channel){
        label_welcome.setText("Добро пожаловать! " + username);
        label_channel.setText("Вы вошли как :" + channel);
        }
}
