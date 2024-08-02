package com.example.wrmusic_project;

public class CartItem {
    private final int imageResId;
    private final String title;
    private final int quantity;
    private final double price;

    public CartItem(int imageResId, String title, int quantity, double price) {
        this.imageResId = imageResId;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
