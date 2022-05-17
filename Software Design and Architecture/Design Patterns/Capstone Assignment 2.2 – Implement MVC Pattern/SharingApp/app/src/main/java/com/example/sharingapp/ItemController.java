package com.example.sharingapp;

import android.graphics.Bitmap;

public class ItemController {

    private Item item;

    public ItemController(Item item){
        this.item = item;
    }

    public String getId(){
        return item.getId();
    }

    public void setId() {
        item.setId();
    }

    public void setTitle(String title) {
        item.setTitle(title);
    }

    public String getTitle() {
        return item.getTitle();
    }

    public void setMaker(String maker) {
        item.setMaker(maker);
    }

    public String getMaker() {
        return item.getMaker();
    }

    public void setDescription(String description) {
        item.setDescription(description);
    }

    public String getDescription() {
        return item.getDescription();
    }

    public void setDimensions(String length, String width, String height) {
        item.setDimensions(length, width, height);
    }

    public String getLength() {
        return item.getLength();
    }

    public String getWidth() {
        return item.getWidth();
    }

    public String getHeight() {
        return item.getHeight();
    }

    public String getStatus() {
        return item.getStatus();
    }

    public void setStatus(String status) {
        item.setStatus(status);
    }

    public Contact getBorrower() {
        return item.getBorrower();
    }

    public void setBorrower(Contact borrower) {
        item.setBorrower(borrower);
    }

    public Bitmap getImage() {
        return item.getImage();
    }

    public void addImage(Bitmap new_image) {
        item.addImage(new_image);
    }

    public Item getItem() {
        return this.item;
    }

    public void addObserver(Observer obs) {
        item.addObserver(obs);
    }

    public void removeObserver(Observer obs) {
        item.removeObserver(obs);
    }
}