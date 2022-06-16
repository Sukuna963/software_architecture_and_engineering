package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command used to edit pre-existing user
 */
public class EditUserCommand extends Command {

    private User old_user;
    private User new_user;

    public EditUserCommand (User old_user, User new_user){
        this.old_user = old_user;
        this.new_user = new_user;
    }

    // Delete the old user remotely, save the new user remotely to server
    public void execute() {
        ElasticSearchManager.RemoveUserTask remove_user_task = new ElasticSearchManager.RemoveUserTask();
        remove_user_task.execute(old_user);

        ElasticSearchManager.AddUserTask add_user_task = new ElasticSearchManager.AddUserTask();
        add_user_task.execute(new_user);

        // use get() to access the return of AddUserTask/RemoveUserTask.
        // i.e. AddUserTask/RemoveUserTask returns a Boolean to indicate if the user was successfully
        // deleted/saved to the remote server
        try {
            if(add_user_task.get() && remove_user_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
