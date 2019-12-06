package JavaFXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AdminController {
    final static long organizerID = 2L;
    final static long distributorID = 3L;
    final static long adminID = 1L;

    @FXML
    public TextField fullname;
    @FXML
    public PasswordField password;
    @FXML
    public TextField email;
    @FXML
    public TextField username;
    @FXML
    public TextField phone;
    @FXML
    public Button button;
    @FXML
    public ComboBox combobox;
    @FXML
    public TextField company;
    @FXML
    public TextField salary;
    @FXML
    public TextField rating;
    @FXML
    public Pane visiblePane;


    @FXML
    private void initialize() {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );

        EntityManager entitymanager = emfactory.createEntityManager( );

        combobox.getItems().addAll("Admin", "Organizer", "Distributor");

    }
    public void setRole(){
        String chosenUser = combobox.getSelectionModel().getSelectedItem().toString();
        if(chosenUser.equals("Organizer")){
            company.setVisible(true);
            salary.setVisible(true);
            visiblePane.setVisible(true);
        }else if (chosenUser.equals("Distributor")){
            company.setVisible(true);
            salary.setVisible(true);
            visiblePane.setVisible(true);
        }else{
            company.setVisible(false);
            salary.setVisible(false);
            visiblePane.setVisible(false);
        }
    }
    public void add(ActionEvent event) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

        entitymanager.persist(userDetailsEntity);


        String chosenUser = combobox.getSelectionModel().getSelectedItem().toString();

        String fullname1 = fullname.getText();
        String user = username.getText();
        String pass = password.getText();
        String email1 = email.getText();
        String phoneNumber = phone.getText();

        if(chosenUser.equals("Organizer")){
            String companyOrganizer = company.getText();
            String salaryString = salary.getText();
            setRole();
            double salaryOrganizer = Double.parseDouble(salaryString);
            OrganizerEntity organizerEntity = new OrganizerEntity();
            organizerEntity.setOrganizerCompany(companyOrganizer);
            organizerEntity.setOrganizerSalary(salaryOrganizer);
            userDetailsEntity.setUserFullname(fullname1);
            userDetailsEntity.setUserUsername(user);
            userDetailsEntity.setUserPassword(pass);
            userDetailsEntity.setUserEmail(email1);
            userDetailsEntity.setUserPhone(phoneNumber);
            entitymanager.persist(userDetailsEntity);
            organizerEntity.setOrganizerId(userDetailsEntity.getUserId());
            userDetailsEntity.setOrganizerEntity(organizerEntity);
            UserTypeEntity userTypeEntity = entitymanager.find(UserTypeEntity.class, organizerID);
            userDetailsEntity.setUserTypeEntity(userTypeEntity);

        }else if(chosenUser.equals("Distributor")){
            String companyDistributor = company.getText();
            String salaryString = salary.getText();
            setRole();
            double salaryDistributor = Double.parseDouble(salaryString);

            DistributorEntity distributorEntity = new DistributorEntity();
            distributorEntity.setDistributorCompany(companyDistributor);
            distributorEntity.setDistributorSalary(salaryDistributor);

            distributorEntity.setDistributorRating(0.0);
            userDetailsEntity.setUserFullname(fullname1);
            userDetailsEntity.setUserUsername(user);
            userDetailsEntity.setUserPassword(pass);
            userDetailsEntity.setUserEmail(email1);
            userDetailsEntity.setUserPhone(phoneNumber);

            entitymanager.persist(userDetailsEntity);

            distributorEntity.setDistributorId(userDetailsEntity.getUserId());
            userDetailsEntity.setDistributorEntity(distributorEntity);
            UserTypeEntity userTypeEntity = entitymanager.find(UserTypeEntity.class, distributorID);
            userDetailsEntity.setUserTypeEntity(userTypeEntity);
        }else{
            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setAdminId(userDetailsEntity.getUserId());
            userDetailsEntity.setAdminEntity(adminEntity);
            userDetailsEntity.setUserFullname(fullname1);
            userDetailsEntity.setUserUsername(user);
            userDetailsEntity.setUserPassword(pass);
            userDetailsEntity.setUserEmail(email1);
            userDetailsEntity.setUserPhone(phoneNumber);

            entitymanager.persist(userDetailsEntity);

            adminEntity.setAdminId(userDetailsEntity.getUserId());
            userDetailsEntity.setAdminEntity(adminEntity);
            UserTypeEntity userTypeEntity = entitymanager.find(UserTypeEntity.class, adminID);
            userDetailsEntity.setUserTypeEntity(userTypeEntity);
        }
        entitymanager.persist(userDetailsEntity);
        entitymanager.getTransaction().commit();
    }
}
