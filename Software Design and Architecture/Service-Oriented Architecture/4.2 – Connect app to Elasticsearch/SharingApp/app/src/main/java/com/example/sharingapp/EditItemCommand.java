package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command used to edit pre-existing item
 */
public class EditItemCommand extends Command {
    private Item old_item;
    private Item new_item;

    public EditItemCommand(Item old_item, Item new_item) {
        this.old_item = old_item;
        this.new_item = new_item;
    }

    // Delete the old item remotely, save the new item remotely to server
    public void execute() {
        ElasticSearchManager.RemoveItemTask remove_item_task = new ElasticSearchManager.RemoveItemTask();
        remove_item_task.execute(old_item);

        ElasticSearchManager.AddItemTask add_item_task = new ElasticSearchManager.AddItemTask();
        add_item_task.execute(new_item);

        // use get() to access the return of AddItemTask/RemoveItemTask.
        // i.e. AddItemTask/RemoveItemTask returns a Boolean to indicate if the item was successfully
        // deleted/saved to the remote server
        try {
            if(add_item_task.get() && remove_item_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
