/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcbx.jpa.eclipse.entity.beansproperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AuthorsJFX {

    private SimpleIntegerProperty idauthors;
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty contactinformation;
    private SimpleStringProperty gender;

    public AuthorsJFX() {
    }

    public AuthorsJFX(Integer idauthors) {
        this.idauthors = new SimpleIntegerProperty(idauthors);
    }

    public AuthorsJFX(Integer idauthors, String firstname, String lastname, String contactinformation, String gender) {
        this.idauthors = new SimpleIntegerProperty(idauthors);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.contactinformation = new SimpleStringProperty(contactinformation);
        this.gender = new SimpleStringProperty(gender);
    }

    public Integer getIdauthors() {
        return idauthors.get();
    }

    public SimpleIntegerProperty getIdauthorsProperty() {
        return idauthors;
    }

    public void setIdauthors(Integer idauthors) {
        this.idauthors = new SimpleIntegerProperty(idauthors);
    }

//    
    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty getFirstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }
//

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty getLastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = new SimpleStringProperty(lastname);
    }
//

    public String getContactinformation() {
        return contactinformation.get();
    }

    public SimpleStringProperty getContactinformationProperty() {
        return contactinformation;
    }

    public void setContactinformation(String contactinformation) {
        this.contactinformation = new SimpleStringProperty(contactinformation);
    }
//

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty getGenderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = new SimpleStringProperty(gender);
    }
//
}
