package JavaFXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.UserDetailsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
   private static UserDetailsEntity currentUser;
   private static List<UserDetailsEntity> allUsers = new ArrayList<>();

   private static List<UserDetailsEntity> loggedUser = new ArrayList<>();

    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Button Login;
    @FXML
    public Button Register;
    
    @FXML
    public void createLogin(ActionEvent event) {
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
            }
        }
        currentUser = loggedUser.get(loggedUser.size() - 1);

    }
    @FXML
    public void createRegister(ActionEvent event) {
        
        
    }
}
