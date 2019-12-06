package JavaFXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.UserDetailsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
   public static UserDetailsEntity currentUser;
   public static List<UserDetailsEntity> allUsers = new ArrayList<>();

   public static List<UserDetailsEntity> loggedUser = new ArrayList<>();

    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Button Login;
    @FXML
    public Button Register;
    @FXML
    public Label invalidUser;

    @FXML
    public void createLogin(ActionEvent event) throws IOException {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        String usernameField = username.getText();
        String passwordField = password.getText();

        allUsers = (List<UserDetailsEntity>) entitymanager.createQuery("from UserDetailsEntity ").getResultList();

        for (UserDetailsEntity users : allUsers) {
            if (users.getUserUsername().equals(usernameField) && users.getUserPassword().equals(passwordField)){
                if(!loggedUser.contains(users))
                loggedUser.add(users);
            }else{
                invalidUser.setVisible(true);
            }
        }
        currentUser = loggedUser.get(loggedUser.size() - 1);
        if (currentUser.getUserTypeEntity().getUserTypeId() == 3){
        Login.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/DistributorForm.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        }else if (currentUser.getUserTypeEntity().getUserTypeId() == 2){
            Login.getScene().getWindow().hide();
            Stage login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/OrganizerForm.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
        }else if (currentUser.getUserTypeEntity().getUserTypeId() == 1){
            Login.getScene().getWindow().hide();
            Stage login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/AdminForm.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
        }
    }
    @FXML
    public void createRegister(ActionEvent event) throws IOException{
        Register.getScene().getWindow().hide();
        Stage register = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/Register.fxml"));
        Scene scene = new Scene(root);
        register.setScene(scene);
        register.show();
        
    }

}
