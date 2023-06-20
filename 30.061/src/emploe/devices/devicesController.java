package emploe.devices;

import emploe.cuctumers.custumers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.DBUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class devicesController implements Initializable {
    @FXML
    private TextField tf_id_r;
    @FXML
    private TextField tf_name_r;
    @FXML
    private TextField tf_description_r;
    @FXML
    private TableView<devices> tv_repatr;
    @FXML
    private TableColumn<devices, Integer> coi_id_r;
    @FXML
    private TableColumn<devices, String> coi_name_r;
    @FXML
    private TableColumn<devices, String> coi_description_r;
    @FXML
    private Button bt_exit;
    @FXML
    private Button bt_isert;
    @FXML
    private Button bt_update;
    @FXML
    private Button bt_delete;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == bt_isert) {
            insertRecord();
        } else if (event.getSource() == bt_update) {
            updateRecord();
        } else if (event.getSource() == bt_delete) {
            deleteButton();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bt_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "login.fxml", "Добро пожаловать !", "", "");
            }
        });
        showDevices();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/repair of household appliances?useSSl=false", "root", "12345");
            return connection;
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
            return null;
        }
    }

    public ObservableList<devices> getDevicesList() {
        ObservableList<devices> devicesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `repair of household appliances`.devices";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            devices devices;
            while (rs.next()) {
                devices = new devices(rs.getInt("id_dev"), rs.getString("name_dev"), rs.getString("description"));
                devicesList.add(devices);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devicesList;
    }

    public void showDevices() {
        ObservableList<devices> list = getDevicesList();
        coi_id_r.setCellValueFactory(new PropertyValueFactory<devices, Integer>("id_dev"));
        coi_name_r.setCellValueFactory(new PropertyValueFactory<devices, String>("name_dev"));
        coi_description_r.setCellValueFactory(new PropertyValueFactory<devices, String>("description"));

        tv_repatr.setItems(list);
    }

    private void insertRecord() {
        String query = "INSERT INTO devices VALUES (" +
                Integer.parseInt(tf_id_r.getText()) + "," +
                "'" + tf_name_r.getText() + "'" + "," +
                "'" + tf_description_r.getText() + "'" + ")";
        executeQuery(query);
        showDevices();
    }

    private void deleteButton() {
        String query = "DELETE FROM devices WHERE id_dev = " + Integer.parseInt(tf_id_r.getText()) + " ";
        executeQuery(query);
        showDevices();
    }

    private void updateRecord() {

        //     String query = "UPDATE customers SET name  = '" + tf_name.getText() + "'," + "surname = '" + tf_surname.getText() + "', contacts = " +
        //              Integer.parseInt(tf_contacts.getText()) + ", sale = " + Integer.parseInt(tf_sale.getText()) + " WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
        //    executeQuery(query);
        //     showCustumers();
    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        devices devices = tv_repatr.getSelectionModel().getSelectedItem();
        tf_id_r.setText("" + devices.getId_dev());
        tf_name_r.setText(devices.getName_dev());
        tf_description_r.setText(devices.getDescription());
    }
}
