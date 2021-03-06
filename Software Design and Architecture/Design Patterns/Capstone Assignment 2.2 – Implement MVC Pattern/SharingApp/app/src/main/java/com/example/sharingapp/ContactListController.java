package com.example.sharingapp;

import android.content.Context;

import java.util.ArrayList;

public class ContactListController {

    private ContactList contact_list;

    public ContactListController(ContactList contact_list) {
        this.contact_list = contact_list;
    }

    public void setContacts(ArrayList<Contact> contact_list) {
        this.contact_list.setContacts(contact_list);
    }

    public ArrayList<Contact> getContacts() {
        return contact_list.getContacts();
    }

    public boolean addContact(Contact contact, Context context) {
        AddContactCommand add_contact_command = new AddContactCommand(contact_list, contact, context);
        add_contact_command.execute();
        return add_contact_command.isExecuted();
    }

    public boolean deleteContact(Contact contact, Context context) {
        DeleteContactCommand delete_contact_command = new DeleteContactCommand(contact_list, contact, context);
        delete_contact_command.execute();
        return delete_contact_command.isExecuted();
    }

    public boolean editContact(Contact contact, Contact update_contact, Context context) {
        EditContactCommand edit_contact_command = new EditContactCommand(contact_list,contact, update_contact, context);
        edit_contact_command.execute();
        return edit_contact_command.isExecuted();
    }

    public Contact getContact(int index) {
        return contact_list.getContact(index);
    }

    public int getIndex(Contact contact) {
        return contact_list.getIndex(contact);
    }

    public int getSize() {
        return contact_list.getSize();
    }

    public void loadContacts(Context context) {
        contact_list.loadContacts(context);
    }

    public boolean hasContact(Contact contact) {
        return contact_list.hasContact(contact);
    }

    public Contact getContactByUsername(String username) {
        return contact_list.getContactByUsername(username);
    }

    public ArrayList<String> getAllUsernames(){
        ArrayList<String> user = new ArrayList<String>();
        for (String u : contact_list.getAllUsernames()){
            user.add(u);
        }
        return user;
    }

    public void addObserver(Observer obs) {
        contact_list.addObserver(obs);
    }

    public void removeObserver(Observer obs) {
        contact_list.removeObserver(obs);
    }
}