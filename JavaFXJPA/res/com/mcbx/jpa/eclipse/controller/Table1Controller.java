package com.mcbx.jpa.eclipse.controller;

import com.mcbx.jpa.eclipse.eclipse;
import com.mcbx.jpa.eclipse.eclipse;
import com.mcbx.jpa.eclipse.eclipse;
import com.mcbx.jpa.eclipse.entity.data.Person;
import com.mcbx.jpa.eclipse.entity.data.AuthorsData;
import com.mcbx.jpa.eclipse.entity.Authors;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.Query;

public class Table1Controller implements Initializable {

    private TextFieldTableCell tftc;

    @FXML
    private Button CLEAR;

    @FXML
    private Button RELOAD;

    @FXML
    private Button UPDATE;

    @FXML
    private Button RESTORE;

    @FXML
    private Button DELETE;

    @FXML
    private TextField TFID;

    @FXML
    private TextField TFNAME;

    @FXML
    private Button ADDENTRY;

    ObservableList<Authors> authorsObservableList;                                      // <> -> SPECIFY THE <CLASSFILE> FOR YOUR OBSERVABLE_LIST
    public TableView<Authors> table = new TableView<>();                                // <> -> SPECIFY THE <CLASSFILE>
    /*SET COLUMN LAYOUT*/
    public TableColumn<Authors, String> column1 = new TableColumn<>("FIRST NAME");      // "" -> Column Header Name     <,> -> SPECIFY THE <CLASSFILE, DATA TYPE> OF THE TABLE COLUMN
    public TableColumn<Authors, String> column2 = new TableColumn<>("LAST NAME");       // "" -> Column Header Name     <,> -> SPECIFY THE <CLASSFILE, DATA TYPE> OF THE TABLE COLUMN
    public TableColumn<Authors, String> column3 = new TableColumn<>("CONTACT");         // "" -> Column Header Name     <,> -> SPECIFY THE <CLASSFILE, DATA TYPE> OF THE TABLE COLUMN
    public TableColumn<Authors, String> column4 = new TableColumn<>("GENDER");          // "" -> Column Header Name     <,> -> SPECIFY THE <CLASSFILE, DATA TYPE> OF THE TABLE COLUMN
    public TableColumn<Authors, Date> column5 = new TableColumn<>("DATE");
    public TableColumn<Authors, Integer> column6 = new TableColumn<>("INTEGER");
    public TableColumn<Authors, Double> column7 = new TableColumn<>("DOUBLE");
    public TableColumn<Authors, String> column_1_2 = new TableColumn<>("NAME");         // MAKE NEW CONTAINER FOR OTHER COLUMN
    public TableColumn<Authors, String> column_3_4 = new TableColumn<>("DETAILS");      // MAKE NEW CONTAINER FOR OTHER COLUMN
    public TableColumn<Authors, ?> column_6_7 = new TableColumn<>("NUMBER");            // MAKE NEW CONTAINER FOR OTHER COLUMN

    public Table1Controller() {
        this.util = Persistence.getPersistenceUtil();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addListenerForTable();

        /*INITIALIZE COLUMN CLASS.FIELDNAME*/
        column1.setCellValueFactory(new PropertyValueFactory<>("firstname"));           // "" -> Class.fieldname [Case Sensitive] CAREFUL IF NO DISPLAY IN THE TABLEVIEW
        column2.setCellValueFactory(new PropertyValueFactory<>("lastname"));            // "" -> Class.fieldname [Case Sensitive] CAREFUL IF NO DISPLAY IN THE TABLEVIEW
        column3.setCellValueFactory(new PropertyValueFactory<>("contactinformation"));  // "" -> Class.fieldname [Case Sensitive] CAREFUL IF NO DISPLAY IN THE TABLEVIEW
        column4.setCellValueFactory(new PropertyValueFactory<>("gender"));              // "" -> Class.fieldname [Case Sensitive] CAREFUL IF NO DISPLAY IN THE TABLEVIEW
        column5.setCellValueFactory(new PropertyValueFactory<>("dateval"));
        column5.setMinWidth(220);
        column5.setMaxWidth(220);
        column6.setCellValueFactory(new PropertyValueFactory<>("integerval"));
        column7.setCellValueFactory(new PropertyValueFactory<>("doubleval"));

        /*COLUMN EDITABLE*/
        column1.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column3.setCellFactory(TextFieldTableCell.forTableColumn());
        setTableColumnEditable();

        /*SET COLLUMN COMBOBOX*/
        column4.setCellFactory(ComboBoxTableCell.forTableColumn("Male", "Female", "Undefined"));
        /*SET COLUMN DATE*/
        setTableColumnEditableGender();
        /*COLUMN STYLE*/
        column4.setId("my-column-number");
        column5.setId("my-column-number");
        column6.setId("my-column-number");
        column7.setId("my-column-number");


        /*SET COLUMN TABLE EDITABLE*/
        table.setEditable(true);
        /*SET TABLE CONSTRAINED*/
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        /*TABLE STYLE*/
        table.setId("my-table");


        /*COMBINED COLUMNS*/
        column_3_4.getColumns().setAll(column3, column4);
        column_1_2.getColumns().setAll(column1, column2);
        column_6_7.getColumns().setAll(column6, column7);

        /*ADD COLUMN TO THE TABLE*/
        table.getColumns().setAll(column_1_2, column_3_4, column_6_7, column5);          // ADD ALL YOUR INITIALIZED COLUMN TO THE TABLEMODEL
        /*SET TABLE SELECTION MODE*/
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);               // ADDITIONAL PROPERTY SETTINGS TO YOUR TABLE

        /*PUT OBSERVABLE_LIST DATA TO THE TABLE VIEW*/
        table.setItems(getAuthors());                                                   // RETRIEVE THAT DATA YOU WANT TO STORE TO THE TABLE VIEW

        /**/
//        setObservableListListener();

        /**/
 /**/
    }

//------------------------------------------------------------------------------ THIS IS SAMPLE FOR TABLE DATA, NOT ON DATABASE DATA
//    public ObservableList<Person> getPersons() {
//        /*LOCAL DECLARATION OF OBSERVABLE_LIST*/
//        ObservableList<Person> personsList = FXCollections.observableArrayList();
//        /*CREATE A NEW OBJECTS OF A CLASS AND ADD TO THE OBSERVABLE_LIST*/
//        personsList.add(new Person("Jacob", "Smith", "jacob.smith@example.com"));
//        personsList.add(new Person("Jacob", "Smith", "jacob.smith@example.com"));
//        personsList.add(new Person("Isabella", "Johnson", "isabella.johnson@example.com"));
//        personsList.add(new Person("Ethan", "Williams", "ethan.williams@example.com"));
//        personsList.add(new Person("Emma", "Jones", "emma.jones@example.com"));
//        personsList.add(new Person("Michael", "Brown", "michael.brown@example.com"));
//
//        /*RETURN OBJECTS WHICH IS NOW AN OBSERVABLE_LIST IN YOUR TABLE VIEW*/
//        return personsList;
//    }
//------------------------------------------------------------------------------ THIS IS SAMPLE FOR COLLECTING DATA, FROM YOUR DATABASE SERVER
//    public ObservableList<AuthorsData> getAuthorsData() {
//        /*LOCAL DECLARATION OF OBSERVABLE_LIST*/
//        ObservableList<AuthorsData> authorsList = FXCollections.observableArrayList();
//        /*CREATE A QUERY*/
//        Query query = em.createQuery("Select a from Authors a");
//        /*COLLECTED DATA WILL BE STORE TO THE LIST AS AN OBJECT OF A CLASS TYPE*/
//        List<Authors> list = query.getResultList();
//        /*MANIMUPLATE/READ EACH COLLECTED DATA*/
//        list.forEach((e) -> {
//            /*TO ADD NEW OBJECT FROM THE CLASS [CREATE CONSTRUCTOR FIRST(@POJO) WITH SPECIFIC FIELDS] ON TOP GENERATED JPA CLASS*/ /*NEW DATA WILL BE ADDED ALSO TO THE OBSERVABLE_LIST*/
//            authorsList.add(new AuthorsData(e.getIdauthors(), e.getFirstname(), e.getLastname(), e.getContactinformation()));
//        });
//        /*RETURN OBJECTS WHICH IS NOW AN OBSERVABLE_LIST IN YOUR TABLE VIEW*/
//        return authorsList;
//    }
//------------------------------------------------------------------------------ GET DATA FROM DATABASE AND STORE TO THE OBSERVABLE_LIST, AND INSERT IT TO THE TABLEVIEW
    public ObservableList<Authors> getAuthors() {
        /*ObservableList<Authors> authorsObservableList = FXCollections.observableArrayList();*/ //THIS IS LOCAL DECLARATION -> I MAKE IT GLOBAL, SO THAT [OBSERVABLE_LIST] WILL BE MANIPULATED ALSO OUTSIDE, U CAN ALSO STORE ANOTHER ENTRY/OBJECT TO IT
        authorsObservableList = FXCollections.observableArrayList();
        beginConnection();
        /*RETRIEVE INFORMATION FROM DATABASE*/
        Query query = em.createQuery("Select a from Authors a");

        /*COLLECTED DATA WILL BE STORE TO THE LIST AS AN OBJECT OF A CLASS TYPE*/
        List<Authors> list = query.getResultList();

        /*MANIMUPALTE/READ EACH COLLECTED DATA*/
        for(Authors e: list){
            /*TO ADD NEW OBJECT FROM THE CLASS [CREATE CONSTRUCTOR FIRST(@POJO) WITH SPECIFIC FIELDS] ON TOP GENERATED JPA CLASS*/ /*NEW DATA WILL BE ADDED ALSO TO THE OBSERVABLE_LIST*/
            authorsObservableList.add(new Authors(e.getIdauthors(), e.getFirstname(), e.getLastname(), e.getContactinformation(), e.getGender(), e.getDateval(), e.getIntegerval(), e.getDoubleval()));
        }

        closeConnection();
        /*RETURN OBJECTS WHICH IS NOW AN OBSERVABLE_LIST IN YOUR TABLE VIEW*/
        return authorsObservableList;
    }

//    
    @FXML
    void restoreDataThatIsDeleted(ActionEvent event) {
        if (deleted != null) {
                beginConnection();
            deleted.forEach((e) -> {

//               authorsObservableList.add(new Authors(e.getFirstname(), e.getLastname(), e.getContactinformation(), e.getGender(), e.getDateval(), e.getIntegerval(), e.getDoubleval()));
                authorsObservableList.add(e);
                em.persist(e);
            });
        }
                commitConnection();
        deleted = null;
    }

//    public void restorDeletedData(List<Authors> list) {
//        list.forEach((e) -> {
//            authorsObservableList.add(new Authors(e.getIdauthors(), e.getFirstname(), e.getLastname(), e.getContactinformation(), e.getGender(), e.getDateval(), e.getIntegerval(), e.getDoubleval()));
//        });
//
//    }
//------------------------------------------------------------------------------ CLEAR OBSERVABLE LIST
    @FXML
    void obslistCLEAR(ActionEvent event) {
        authorsObservableList.clear();
    }

//------------------------------------------------------------------------------ RELAOD DATA
    @FXML
    void obslistRELOAD(ActionEvent event) {
        authorsObservableList.clear();
        table.setItems(getAuthors());
    }

//------------------------------------------------------------------------------ UPDATE THE ID
    @FXML
    void updateEntry(ActionEvent event) {
        try {

            int entityID = Integer.parseInt(TFID.getText());
            String newValue = TFNAME.getText();
            beginConnection();
            /*CHECK IF CLASS ID EXIST IN THE CLASS*/
            boolean isExist = isEntityIDExist(Authors.class, entityID);
            if (isExist) {
                System.out.println("Enter HERE...");
                Authors a = em.find(Authors.class, entityID);
                a.setFirstname(newValue);
                commitConnection();
//                TableView<Authors> v = table.getSelectionModel().getTableView();
//                TableView<Authors> table = new TableView<>();
//          ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())) 

Authors aa = (Authors) column1.getTableView().getItems();
 



//                Authors tableRow = table.getItems().getClass();
                        
//                System.out.println("" + v);
                 

//                table.get
//                   t.getTableView().getItems().get(t.getTablePosition().getRow()))
//                authorsObservableList.
            } else {
                System.err.println(entityID + " ENTITY ID DOESN'T EXIST...");
                closeConnection();
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage() + "...");
        }
    }

//------------------------------------------------------------------------------ DELETE SELECTED    
    List<Authors> deleted = new ArrayList<>();

    @FXML
    void deleteEntry(ActionEvent event) {
        try {
            beginConnection();
            List<Authors> entry = table.getSelectionModel().getSelectedItems();
            for (Authors list : entry) {
                Authors a = em.find(Authors.class, list.getIdauthors());
                deleted.add(a);
                em.remove(a);
                System.out.println("DELETED= " + list);
            }
            commitConnection();
            authorsObservableList.removeAll(entry);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage() + "...");
        } finally {

        }
    }

//------------------------------------------------------------------------------ ADD DATA TO DATABASE AND OBSERVABLE_LIST
    @FXML
    void addEntry(ActionEvent event) {
        try {

            beginConnection();
            /*CREATE AN OBJECT*/
            Authors a = new Authors();
            a.setFirstname("");
            a.setLastname("");
            a.setContactinformation("");
            a.setGender("Undefined");
            /*MAKE IT TO PERSISTENT STATE*/ /*NOT YET PUSH TO DATABASE*/
            em.persist(a);
            commitConnection();
            /*CHECK IF NEW OBJECT IS LOADED*/
            boolean isObjectLoaded = util.isLoaded(a);
            if (isObjectLoaded) {
                authorsObservableList.add(a);
                System.out.println(a + " WAS LOADED...");
            } else {
                System.err.println(a + " WAS NOT LOADED...");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage() + "...");
        }
    }
//------------------------------------------------------------------------------ SET OBSERVABLE_LIST LISTENER

    private void setObservableListListener() {
        try {
            authorsObservableList.addListener((ListChangeListener) (c -> {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        System.out.println("Was permuated" + c.wasPermutated());
                    } else if (c.wasUpdated()) {
                        System.out.println("Was updated " + c.wasUpdated());
                    } else {
                        System.out.println("Nothing here");
                    }
                }
            }));
        } catch (Exception e) {
            System.err.println(e.getMessage() + "...");
        }
    }

//------------------------------------------------------------------------------ SET TABLE EDITABLE 
    private void setTableColumnEditable() {
        try {

            /*SET FIRSTNAME*/
            column1.setOnEditCommit((TableColumn.CellEditEvent<Authors, String> t) -> {
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstname(t.getNewValue());
                int idAuthors = ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIdauthors();
                beginConnection();
                /*CHECK IF CLASS ID EXIST IN THE CLASS*/
                boolean isExist = isEntityIDExist(Authors.class, idAuthors);
                if (isExist) {
                    Authors a = em.find(Authors.class, idAuthors);
                    a.setFirstname(t.getNewValue());
                    commitConnection();
                } else {
                    System.err.println(idAuthors + " ENTITY ID DOESN'T EXIST...");
                    closeConnection();
                }
            });

            /*SET LASTNAME*/
            column2.setOnEditCommit((TableColumn.CellEditEvent<Authors, String> t) -> {
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastname(t.getNewValue());
                int idAuthors = ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIdauthors();
                beginConnection();
                /*CHECK IF CLASS ID EXIST IN THE CLASS*/
                boolean isExist = isEntityIDExist(Authors.class, idAuthors);
                if (isExist) {
                    Authors a = em.find(Authors.class, idAuthors);
                    a.setLastname(t.getNewValue());
                    commitConnection();
                } else {
                    System.err.println(idAuthors + " ENTITY ID DOESN'T EXIST...");
                    closeConnection();
                }
            });

            /*SET CONTACT INFORMATION*/
            column3.setOnEditCommit((TableColumn.CellEditEvent<Authors, String> t) -> {
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setContactinformation(t.getNewValue());
                int idAuthors = ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIdauthors();
                beginConnection();
                /*CHECK IF CLASS ID EXIST IN THE CLASS*/
                boolean isExist = isEntityIDExist(Authors.class, idAuthors);
                if (isExist) {
                    Authors a = em.find(Authors.class, idAuthors);
                    a.setContactinformation(t.getNewValue());
                    commitConnection();
                } else {
                    System.err.println(idAuthors + " ENTITY ID DOESN'T EXIST...");
                    closeConnection();
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void setTableColumnEditableGender() {
        try {
            /*SET GENDER*/
            column4.setOnEditCommit((TableColumn.CellEditEvent<Authors, String> t) -> {
                ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGender(t.getNewValue());
                int idAuthors = ((Authors) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIdauthors();
                beginConnection();
                /*CHECK IF CLASS ID EXIST IN THE CLASS*/
                boolean isExist = isEntityIDExist(Authors.class, idAuthors);
                if (isExist) {
                    Authors a = em.find(Authors.class, idAuthors);
                    a.setGender(t.getNewValue());
                    commitConnection();
                } else {
                    System.out.println(idAuthors + " ENTITY ID DOESN'T EXIST...");
                    closeConnection();
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

//------------------------------------------------------------------------------ OPEN AND CLOSE CONNECTIONS
    private EntityManager em;
    private final PersistenceUtil util;

    private void beginConnection() {
        em = eclipse.emf.createEntityManager();
        em.getTransaction().begin();
    }

    private void commitConnection() {
        em.getTransaction().commit();
        em.close();
    }

    private void closeConnection() {
        em.close();
    }

//------------------------------------------------------------------------------ CHECK IF ENTITY ID IS EXIST
    public boolean isEntityIDExist(Class<?> entityClass, int classID) {
        return em.find(entityClass, classID) != null;
    }

//------------------------------------------------------------------------------ TABLE SELECTION LISTENER
    private void addListenerForTable() {
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                DELETE.setDisable(false);
//        tableview2.getSelectionModel().clearSelection();
            } else {
                DELETE.setDisable(true);
            }
        });
    }

}
