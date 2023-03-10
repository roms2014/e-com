package com.example.e_com.model;

public class TV {
    int id;
    String img, title, price, resolution, background;

    public TV(int id, String img, String title, String price, String resolution, String background) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.price = price;
        this.resolution = resolution;
        this.background = background;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
