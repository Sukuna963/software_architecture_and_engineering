package com.example.sharingapp;

import java.util.UUID;

public class ContactController {

    private Contact contact;

    public ContactController(Contact contact) {
        this.contact = contact;
    }

    public String getId(){
        return contact.getId();
    }

    public void setId() {
        contact.setId();
    }

    public String getUsername() {
        return contact.getUsername();
    }

    public void setUsername(String username) {
        contact.setUsername(username);
    }

    public String getEmail() {
        return contact.getEmail();
    }

    public void setEmail(String email) {
        contact.setEmail(email);
    }

    public Contact getContact() {
        return this.contact;
    }

    public void addObserver(Observer obs) {
        contact.addObserver(obs);
    }

    public void removeObserver(Observer obs) {
        contact.removeObserver(obs);
    }
}
