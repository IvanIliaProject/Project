package JavaFXControllers;

import com.jfoenix.controls.JFXListView;
import com.mysql.jdbc.log.Log;
import javafx.beans.binding.ObjectExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.DistributorEntity;
import models.EventEntity;
import models.UserDetailsEntity;
import JavaFXControllers.LoginController;
import oracle.jdbc.util.Login;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DistributorController {
    @FXML
    public ComboBox comboBox;
    @FXML
    public ListView OrdersList;
    @FXML
    public TextField TicketPriceBox;
    @FXML
    public TextField TotalBox;
    @FXML
    public javafx.scene.control.Button Button;
    @FXML
    public ComboBox CountTicketsCombo;
    @FXML
    public Label isSuccess;
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
    EntityManager entitymanager = emfactory.createEntityManager( );

    List<EventEntity> eventEntities = entitymanager.createQuery("from EventEntity ").getResultList();

    @FXML
    private void initialize() {

        for (EventEntity eventEntity : eventEntities) {
            comboBox.getItems().add(eventEntity.getEventName());
        }
        CountTicketsCombo.getItems().addAll(1, 2 , 3, 5, 6);

    }
    @FXML
    private double getEventData(ActionEvent event){
        String chosenEvent = comboBox.getSelectionModel().getSelectedItem().toString();
        EventEntity eventEntity = new EventEntity();
        for (EventEntity entity : eventEntities) {
            if (entity.getEventName().equals(chosenEvent)){
                eventEntity = entity;
            }
        }
        Double ticketPrice = eventEntity.getEventPrice();
        TicketPriceBox.setText(ticketPrice.toString());
        return ticketPrice;
    }
    @FXML
    public void totalSum(ActionEvent event) {

        Double totalTicketPrice = getEventData(event);

        Integer chosenCount = (Integer) CountTicketsCombo.getSelectionModel().getSelectedItem();

        Double totalSum = totalTicketPrice*chosenCount;

        TotalBox.setText(totalSum.toString());

        OrdersList.getItems().setAll(
                "Event name is: " + comboBox.getSelectionModel().getSelectedItem().toString() + "\n"+
                        "Count of tickets is: " + chosenCount + "\n"+
                        "Total Price is: " + totalSum + "\n" + "\n"+
                        "If you want to proceed ... Press the button!");
    }
    @FXML
    public void ReserveTicket(ActionEvent event) {

        Integer chosenCount = (Integer) CountTicketsCombo.getSelectionModel().getSelectedItem();
        entitymanager.getTransaction( ).begin( );
        String chosenEvent = comboBox.getSelectionModel().getSelectedItem().toString();

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        DistributorEntity distributorEntity = new DistributorEntity();
        userDetailsEntity = LoginController.currentUser;


        EventEntity eventEntity = new EventEntity();
        for (EventEntity entity : eventEntities) {
            if (entity.getEventName().equals(chosenEvent)){
                eventEntity  = entity;
            }
        }
        Integer quantity = eventEntity.getEventQuantity();

        if (chosenCount<=quantity){
            eventEntity.setEventQuantity(quantity-chosenCount);
            userDetailsEntity.getDistributorEntity().setDistributorSold(
                    (userDetailsEntity.getDistributorEntity().getDistributorSold() + chosenCount));
            entitymanager.merge(userDetailsEntity);

            isSuccess.setVisible(true);
            isSuccess.setText("Successful");
            entitymanager.persist(eventEntity);
            entitymanager.getTransaction().commit();
        }else{
            isSuccess.setVisible(true);
            isSuccess.setText("No more tickets");
        }
    }
}
