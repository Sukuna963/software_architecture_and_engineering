package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity implements Observer{

    private ContactList contact_list = new ContactList();
    private ContactListController contact_list_controller = new ContactListController(contact_list);
    private ContactController contact_controller;
    private Contact contact;
    private EditText email;
    private EditText username;
    private Context context;
    private boolean on_create_update = false;
    private int pos;
    private ArrayAdapter<String> adapter;
    private Spinner borrower_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

       context = getApplicationContext();

       contact_list_controller.addObserver(this);
       contact_list_controller.loadContacts(context);

       on_create_update = true;

       contact_list_controller.addObserver(this);
       contact_list_controller.loadContacts(context);

       on_create_update = false;
    }

    public void saveContact(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contact.getId(); // Reuse the contact id
        Contact update_contact = new Contact(username_str, email_str, id);
        ContactController update_contact_controller = new ContactController(update_contact);
        update_contact_controller.setEmail(email_str);

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contact_list.isUsernameAvailable(username_str) && !(contact.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        boolean success = contact_list_controller.editContact(contact, update_contact, context);
        if(!success) {
            return;
        }

        // End EditContactActivity
        contact_list_controller.removeObserver(this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteContact(View view) {

        boolean success = contact_list_controller.deleteContact(contact, context);
        if(!success){
            return;
        }

        contact_list_controller.removeObserver(this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void update() {
        if (on_create_update){
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    contact_list_controller.getAllUsernames());
            borrower_spinner.setAdapter(adapter);
            contact = contact_list_controller.getContact(pos);
            contact_controller = new ContactController(contact);
            Contact contact = contact_controller.getContact();
            if (contact != null){
                int contact_pos = contact_list_controller.getIndex(contact);
                borrower_spinner.setSelection(contact_pos);
            }
        }
    }
}
