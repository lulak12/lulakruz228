package emploe.masters;

import emploe.masters.masters;
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

public class mastersController implements Initializable {
    @FXML
    private TextField tfid_mas;
    @FXML
    private TextField tfname_mas;
    @FXML
    private TextField tfsur_mas;
    @FXML
    private TextField tfposit_mas;
    @FXML
    private TextField tfphon_mas;
    @FXML
    private TableView<masters> tvmaster;
    @FXML
    private TableColumn<masters, Integer> tcidm;
    @FXML
    private TableColumn<masters, String> tcnamem;
    @FXML
    private TableColumn<masters, String> tcsurm;
    @FXML
    private TableColumn<masters, String> tcpositm;
    @FXML
    private TableColumn<masters, String> tcphonem;
    @FXML
    private Button btexit;
    @FXML
    private Button btinsert;
    @FXML
    private Button btupdate;
    @FXML
    private Button btdelete;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btinsert){
            insertRecord();
        }else if (event.getSource() == btupdate){
            updateRecord();
        }else if(event.getSource() == btdelete){
            deleteButton();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btexit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"login.fxml", "Добро пожаловать !", "", "");
            }
        });
        showMasters();
    }
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/repair of household appliances?useSSl=false", "root", "12345");
            return connection;
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
            return null;
        }
    }

    public ObservableList<masters> getMastersList(){
        ObservableList<masters> mastersList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `repair of household appliances`.masters";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            masters masters;
            while (rs.next()){
                masters = new masters(rs.getInt("id_masters"), rs.getString("name_master"), rs.getString("surname_master"), rs.getString("position"), rs.getString("phone"));
                mastersList.add(masters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mastersList;
    }
    public void showMasters(){
        ObservableList<masters> list = getMastersList();
        tcidm.setCellValueFactory(new PropertyValueFactory<masters, Integer>("id_masters"));
        tcnamem.setCellValueFactory(new PropertyValueFactory<masters, String>("name_master"));
        tcsurm.setCellValueFactory(new PropertyValueFactory<masters, String>("surname_master"));
        tcpositm.setCellValueFactory(new PropertyValueFactory<masters, String>("position"));
        tcphonem.setCellValueFactory(new PropertyValueFactory<masters, String>("phone"));

        tvmaster.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO masters VALUES (" +
                Integer.parseInt(tfid_mas.getText()) + "," +
                "'" + tfname_mas.getText() + "'" + "," +
                "'" + tfsur_mas.getText() + "'" + "," +
                "'" + tfposit_mas.getText() + "'" + "," +
                "'" + tfphon_mas.getText() + "'" + ")";
        executeQuery(query);
        showMasters();
    }

    private void deleteButton(){
        String query = "DELETE FROM masters WHERE id_masters = " + Integer.parseInt(tfid_mas.getText()) + " ";
        executeQuery(query);
        showMasters();
    }
    private void updateRecord(){

        //     String query = "UPDATE masters SET name  = '" + tf_name.getText() + "'," + "surname = '" + tf_surname.getText() + "', contacts = " +
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
        masters masters = tvmaster.getSelectionModel().getSelectedItem();
        tfid_mas.setText("" + masters.getId_masters());
        tfname_mas.setText(masters.getName_master());
        tfsur_mas.setText(masters.getSurname_master());
        tfposit_mas.setText(masters.getPosition());
        tfphon_mas.setText(masters.getPhone());

    }

}
