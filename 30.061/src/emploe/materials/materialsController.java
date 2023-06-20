package emploe.materials;

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

public class materialsController implements Initializable {
    @FXML
    private TextField tfidm;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tftipe;
    @FXML
    private TextField tfamount;
    @FXML
    private TableView<materials> tvmaterials;
    @FXML
    private TableColumn<materials, Integer> tcidm;
    @FXML
    private TableColumn<materials, String> tctitle;
    @FXML
    private TableColumn<materials, String> tcprice;
    @FXML
    private TableColumn<materials, String> tctipe;
    @FXML
    private TableColumn<materials, String> tcamount;
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
        showMaterials();
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
    public ObservableList<materials> getMaterialsList(){
        ObservableList<materials> materialsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM `repair of household appliances`.materials";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            materials materials;
            while (rs.next()){
                materials = new materials(rs.getInt("id_materials"), rs.getString("title"), rs.getString("price"), rs.getString("tipe"), rs.getString("amount"));
                materialsList.add(materials);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materialsList;
    }
    public void showMaterials(){
        ObservableList<materials> list = getMaterialsList();
        tcidm.setCellValueFactory(new PropertyValueFactory<materials, Integer>("id_materials"));
        tctitle.setCellValueFactory(new PropertyValueFactory<materials, String>("title"));
        tcprice.setCellValueFactory(new PropertyValueFactory<materials, String>("price"));
        tctipe.setCellValueFactory(new PropertyValueFactory<materials, String>("tipe"));
        tcamount.setCellValueFactory(new PropertyValueFactory<materials, String>("amount"));

        tvmaterials.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO materials VALUES (" +
                Integer.parseInt(tfidm.getText()) + "," +
                "'" + tftitle.getText() + "'" + "," +
                "'" + tfprice.getText() + "'" + "," +
                "'" + tftipe.getText() + "'" + "," +
                "'" + tfamount.getText() + "'" + ")";
        executeQuery(query);
        showMaterials();
    }
    private void deleteButton(){
        String query = "DELETE FROM materials WHERE id_materials = " + Integer.parseInt(tfidm.getText()) + " ";
        executeQuery(query);
        showMaterials();
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
        materials materials = tvmaterials.getSelectionModel().getSelectedItem();
        tfidm.setText("" + materials.getId_materials());
        tftitle.setText(materials.getTitle());
        tfprice.setText(materials.getPrice());
        tftipe.setText(materials.getTipe());
        tfamount.setText(materials.getAmount());
    }
}
