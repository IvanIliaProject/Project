package JavaFXControllers;

import JavaFXControllers.DistributorController;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.EventEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class CustomerController implements Serializable {
    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Persistence  ");
    static EntityManager entityManager = emfactory.createEntityManager();
    public List<EventEntity> events = entityManager.createQuery("from EventEntity ").getResultList();

    @FXML
    private TableView<EventEntity> tableView = new TableView<>();

    @FXML
    public TableColumn<EventEntity, String> name;

    @FXML
    public TableColumn<EventEntity,Double> price;

    @FXML
    public TableColumn<EventEntity, java.util.Date> date;

    @FXML
    public TableColumn<EventEntity, String> time;

    @FXML
    public TableColumn<EventEntity,String> place;



    @FXML
    public void initialize()  {
        ObservableList<EventEntity> data =
                FXCollections.observableArrayList(events);

        name.setCellValueFactory(
                new PropertyValueFactory<EventEntity, String>("eventName"));

        price.setCellValueFactory(
                new PropertyValueFactory<EventEntity,Double>("eventPrice"));

        place.setCellValueFactory(
                new PropertyValueFactory<EventEntity, String>("eventPlace"));

        date.setCellValueFactory(
                new PropertyValueFactory<EventEntity, java.util.Date>("eventDate"));

        time.setCellValueFactory(
                new PropertyValueFactory<EventEntity, String>("eventTime"));

        tableView.getItems().addAll(data);


    }
}