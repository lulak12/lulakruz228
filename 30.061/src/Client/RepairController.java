package Client;

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

public class RepairController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tfcustumer;
    @FXML
    private TextField tfmaster;
    @FXML
    private TextField tfdevice;
    @FXML
    private TextField tfmaterial;
    @FXML
    private TextField tfdop_info;
    @FXML
    private TableView<Repair> tvrepair;
    @FXML
    private TableColumn<Repair, Integer> tcid;
    @FXML
    private TableColumn<Repair, String> tcdate;
    @FXML
    private TableColumn<Repair, String> tccustomer;
    @FXML
    private TableColumn<Repair, String> tcmaster;
    @FXML
    private TableColumn<Repair, String> tcdecice;
    @FXML
    private TableColumn<Repair, String> tcmaterial;
    @FXML
    private TableColumn<Repair, String> tcdop_in;
    @FXML
    private Button btinsert;
    @FXML
    private Button btupdate;
    @FXML
    private Button btdelete;
    @FXML
    private Button btexit;
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
        showRepair();
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

    public ObservableList<Repair> getRepairList(){
        ObservableList<Repair> RepairList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `repair of household appliances`.repair";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Repair Repair;
            while (rs.next()){
                Repair = new Repair(rs.getInt("id_repair"), rs.getString("date"), rs.getString("rcustumer"),
                        rs.getString("master"), rs.getString("rdevice") , rs.getString("material"), rs.getString("rdop_info"));
                RepairList.add(Repair);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RepairList;
    }

    public void showRepair(){
        ObservableList<Repair> list = getRepairList();
        tcid.setCellValueFactory(new PropertyValueFactory<Repair, Integer>("id_repair"));
        tcdate.setCellValueFactory(new PropertyValueFactory<Repair, String>("date"));
        tccustomer.setCellValueFactory(new PropertyValueFactory<Repair, String>("rcustumer"));
        tcmaster.setCellValueFactory(new PropertyValueFactory<Repair, String>("master"));
        tcdecice.setCellValueFactory(new PropertyValueFactory<Repair, String>("rdevice"));
        tcmaterial.setCellValueFactory(new PropertyValueFactory<Repair, String>("material"));
        tcdop_in.setCellValueFactory(new PropertyValueFactory<Repair, String>("rdop_info"));

        tvrepair.setItems(list);
    }

    private void insertRecord(){
        String query = "INSERT INTO repair VALUES (" +
                Integer.parseInt(tfid.getText()) + "," +
                "'" + tfdate.getText() + "'" + "," +
                "'" + tfcustumer.getText() + "'" + "," +
                "'" + tfmaster.getText() + "'" + "," +
                "'" + tfdevice.getText() + "'" + "," +
                "'" + tfmaterial.getText() + "'" + "," +
                "'" + tfdop_info.getText() + "'" + ")";
        executeQuery(query);
        showRepair();
    }

    private void deleteButton(){
        String query = "DELETE FROM repair WHERE id = " + Integer.parseInt(tfid.getText()) + " ";
        executeQuery(query);
        showRepair();
    }
    private void updateRecord(){

      //  String query = "UPDATE repair SET date = '" + tfdate.getText() + "'," + "custumer = '" + tfcustumer.getText() + "', master = " +
     //           tfmaster.getText() + ", device = " + tfdevice.getText() + " WHERE id = " + Integer.parseInt(tfid.getText()) + " ";
     //   executeQuery(query);
     //   showRepair();
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
        Repair Repair = tvrepair.getSelectionModel().getSelectedItem();
        tfid.setText("" + Repair.getId_repair());
        tfdate.setText(Repair.getDate());
        tfcustumer.setText(Repair.getRcustumer());
        tfmaster.setText(Repair.getMaster());
        tfdevice.setText(Repair.getRdevice());
        tfmaterial.setText(Repair.getMaterial());
        tfdop_info.setText(Repair.getRdop_info());

    }

}
