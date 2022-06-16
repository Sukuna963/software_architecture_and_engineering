package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command to delete an item
 */
public class DeleteItemCommand extends Command {

    private Item item;

    public DeleteItemCommand(Item item) {
        this.item = item;
    }

    // Delete the item remotely from server
    public void execute() {
        ElasticSearchManager.RemoveItemTask remove_item_task = new ElasticSearchManager.RemoveItemTask();
        remove_item_task.execute(item);

        // use get() to access the return of RemoveItemTask. i.e. RemoveItemTask returns a Boolean to
        // indicate if the item was successfully deleted from the remote server
        try {
            if(remove_item_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
