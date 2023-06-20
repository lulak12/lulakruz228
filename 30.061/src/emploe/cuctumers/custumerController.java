package emploe.cuctumers;

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

public class custumerController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_contacts;
    @FXML
    private TextField tf_sale;
    @FXML
    private TableView<custumers> tv_custumer;
    @FXML
    private TableColumn<custumers, Integer> coi_id;
    @FXML
    private TableColumn<custumers, String> coi_name;
    @FXML
    private TableColumn<custumers, String> coi_surname;
    @FXML
    private TableColumn<custumers, Integer> coi_contacts;
    @FXML
    private TableColumn<custumers, Integer> coi_sale;
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
        if(event.getSource() == bt_isert){
            insertRecord();
        }else if (event.getSource() == bt_update){
            updateRecord();
        }else if(event.getSource() == bt_delete){
            deleteButton();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bt_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"login.fxml", "Добро пожаловать !", "", "");
            }
        });
showCustumers();
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
public ObservableList<custumers> getCustumersList(){
        ObservableList<custumers> custumersList = FXCollections.observableArrayList();
        Connection conn = getConnection();
    String query = "SELECT * FROM `repair of household appliances`.customers";
    Statement st;
    ResultSet rs;
    try {
        st = conn.createStatement();
        rs = st.executeQuery(query);
        custumers custumers;
        while (rs.next()){
            custumers = new custumers(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getInt("contacts"), rs.getInt("sale"));
            custumersList.add(custumers);
        }
    } catch (Exception e) {
       e.printStackTrace();
    }
    return custumersList;
}
public void showCustumers(){
        ObservableList<custumers> list = getCustumersList();
        coi_id.setCellValueFactory(new PropertyValueFactory<custumers, Integer>("id"));
        coi_name.setCellValueFactory(new PropertyValueFactory<custumers, String>("name"));
        coi_surname.setCellValueFactory(new PropertyValueFactory<custumers, String>("surname"));
        coi_contacts.setCellValueFactory(new PropertyValueFactory<custumers, Integer>("contacts"));
        coi_sale.setCellValueFactory(new PropertyValueFactory<custumers, Integer>("sale"));

    tv_custumer.setItems(list);
}
private void insertRecord(){
    String query = "INSERT INTO customers VALUES (" +
            Integer.parseInt(tf_id.getText()) + "," +
            "'" + tf_name.getText() + "'" + "," +
            "'" + tf_surname.getText() + "'" + "," +
            Integer.parseInt(tf_contacts.getText()) + "," +
            Integer.parseInt(tf_sale.getText()) + ")";
    executeQuery(query);
    showCustumers();
}

    private void deleteButton(){
        String query = "DELETE FROM customers WHERE id = " + Integer.parseInt(tf_id.getText()) + " ";
        executeQuery(query);
        showCustumers();
    }
    private void updateRecord(){

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
    custumers custumers = tv_custumer.getSelectionModel().getSelectedItem();
tf_id.setText("" + custumers.getId());
tf_name.setText(custumers.getName());
tf_surname.setText(custumers.getSurname());
tf_contacts.setText("" + custumers.getContacts());
tf_sale.setText("" + custumers.getSale());

}
}
