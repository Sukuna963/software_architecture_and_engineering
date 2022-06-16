package com.example.sharingapp;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * UserList class
 */
public class UserList extends Observable {

    private static ArrayList<User> users;

    public UserList() {
        users = new ArrayList<User>();
    }

    public void setUsers(ArrayList<User> user_list) {
        users = user_list;
        notifyObservers();
    }

    public ArrayList<User> getUsers() {
        return users;
    }


    public void addUser(User user) {
        users.add(user);
        notifyObservers();
    }

    public void deleteUser(User user) {
        users.remove(user);
        notifyObservers();
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public int getSize() {
        return users.size();
    }

    public User getUserByUsername(String username){
        for (User u : users){
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    public User getUserByUserId(String user_id){
        for (User u : users){
            if (u.getId().equals(user_id)){
                return u;
            }
        }
        return null;
    }

    public String getUsernameByUserId(String user_id){
        for (User u : users){
            if (u.getId().equals(user_id)){
                return u.getUsername();
            }
        }
        return null;
    }

    public String getUserIdByUsername(String username){
        for (User u : users){
            if (u.getUsername().equals(username)){
                return u.getId();
            }
        }
        return null;
    }

    public boolean isUsernameAvailable(String username){
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void getRemoteUsers(){
        ElasticSearchManager.GetUserListTask get_user_list_task = new ElasticSearchManager.GetUserListTask();
        get_user_list_task.execute();

        try {
            users = get_user_list_task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        notifyObservers();
    }
}
