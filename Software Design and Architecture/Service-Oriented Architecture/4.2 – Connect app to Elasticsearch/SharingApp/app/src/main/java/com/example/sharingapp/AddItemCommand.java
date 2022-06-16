package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command to add an item
 */
public class AddItemCommand extends Command{

    private Item item;

    public AddItemCommand(Item item) {
        this.item = item;
    }

    // Save the item remotely to server
    public void execute(){
        ElasticSearchManager.AddItemTask add_item_task = new ElasticSearchManager.AddItemTask();
        add_item_task.execute(item);

        // use get() to access the return of AddItemTask. i.e. AddItemTask returns a Boolean to
        // indicate if the item was successfully saved to the remote server
        try {
            if(add_item_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
