package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.event.Event;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;



import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class DBUtils {

    public static void changeScene(javafx.event.ActionEvent event, String fxmlFile, String title, String username, String channel){
        Parent root = null;

        if (username != null && channel != null) {
try {
    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
    root = loader.load();
    logInController logInController = loader.getController();
    logInController.setUserInfo(username, channel);
} catch (IOException e) {
    e.printStackTrace();
}
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource (fxmlFile));
            } catch (IOException e) {
              e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node)event.getSource()) .getScene() .getWindow();
        stage.setTitle(title);
stage.setScene(new Scene(root,800, 600 ));
stage.show();

    }

public static void singUpUser (javafx.event.ActionEvent event, String username, String password, String channel) {
    Connection connection = null;
    PreparedStatement psInsert = null;
    PreparedStatement psCheckUserExists = null;
    ResultSet resultSet = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/repair of household appliances?useSSl=false", "root", "12345");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM `repair of household appliances`.users WHERE username = ? ? ");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("user exit");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();
            } else {
        psInsert = connection.prepareStatement("INSERT INTO `repair of household appliances`.users (username, password, channel) VALUE (?,?,?)");
        psInsert.setString(1, username);
        psInsert.setString(2, password);
        psInsert.setString(3, channel);
        psInsert.executeUpdate();

        changeScene(event,"login.fxml", "Добро пожаловать !", username, channel);
                }
        } catch (SQLException e) {
         e.printStackTrace();
        } finally {
            if (resultSet !=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists !=null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert !=null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

   }

public static void logInUser(javafx.event.ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement   preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/repair of household appliances?useSSl=false", "root", "12345");
            preparedStatement = connection.prepareStatement("SELECT password, channel FROM `repair of household appliances`.users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("user not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedChannel = resultSet.getString("channel");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event,"login.fxml","Добро пожаловать !", username, retrievedChannel);
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
              try {
                  resultSet.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}

}
