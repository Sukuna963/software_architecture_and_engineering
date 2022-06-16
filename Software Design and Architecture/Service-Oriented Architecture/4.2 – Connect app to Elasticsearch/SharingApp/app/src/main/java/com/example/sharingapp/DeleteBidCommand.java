package com.example.sharingapp;

import java.util.concurrent.ExecutionException;

/**
 * Command to delete a bid
 */
public class DeleteBidCommand extends Command {

    private Bid bid;

    public DeleteBidCommand(Bid bid) { this.bid = bid; }

    // Delete bid locally
    public void execute(){
        ElasticSearchManager.RemoveBidTask remove_bid_task = new ElasticSearchManager.RemoveBidTask();
        remove_bid_task.execute(bid);

        // use get() to access the return of RemoveBidTask. i.e. RemoveBidTask returns a Boolean to
        // indicate if the item was successfully deleted from the remote server
        try {
            if(remove_bid_task.get()) {
                super.setIsExecuted(true);
            }
        }  catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}