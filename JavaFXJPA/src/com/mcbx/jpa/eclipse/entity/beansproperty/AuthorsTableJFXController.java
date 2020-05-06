package com.mcbx.jpa.eclipse.entity.beansproperty;

import com.mcbx.jpa.eclipse.eclipse;
import com.mcbx.jpa.eclipse.entity.Authors; 
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AuthorsTableJFXController implements Initializable {

    EntityManager em;
    /*SET TABLE LAYOUT*/
    ObservableList<AuthorsJFX> authorsObservableList;

    @FXML
    private TableView<AuthorsJFX> tb1;
    @FXML
    private TableColumn<AuthorsJFX, Number> id;

    @FXML
    private TableColumn<AuthorsJFX, String> firstname;

    @FXML
    private TableColumn<AuthorsJFX, String> lastname;

    @FXML
    private TableColumn<AuthorsJFX, String> contactinformation;

    @FXML
    private TableColumn<AuthorsJFX, String> gender;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        em = eclipse.emf.createEntityManager();
        authorsObservableList = FXCollections.observableArrayList();
//        
//        Query query = em.createQuery("Select a from Authors a");
//        List<Authors> list = query.getResultList();
//        /*COPY DATA FROM JFX BEANS*/
//        for (Authors list1 : list) {
//            System.out.println(list1);
//            authorsObservableList.add(new AuthorsJFX(list1.getIdauthors(), list1.getFirstname(), list1.getLastname(), list1.getContactinformation(), list1.getGender()));
//        }

//        tb1.setItems(authorsObservableList);
//        tb1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
////        
//        id.setCellValueFactory(cell -> cell.getValue().getIdauthorsProperty());
//        firstname.setCellValueFactory(cell -> cell.getValue().getFirstnameProperty());
//        lastname.setCellValueFactory(cell -> cell.getValue().getLastnameProperty());
//        contactinformation.setCellValueFactory(cell -> cell.getValue().getContactinformationProperty());
//        gender.setCellValueFactory(cell -> cell.getValue().getGenderProperty());
    }

}
