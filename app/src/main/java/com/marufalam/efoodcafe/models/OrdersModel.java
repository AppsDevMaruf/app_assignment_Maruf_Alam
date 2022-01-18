package com.marufalam.efoodcafe.models;

public class OrdersModel {
    private int id;
    private String orderImage,currentDate,soldItemName,orderNumber,price;

    public OrdersModel() {
    }

    public OrdersModel(int id, String orderImage, String currentDate, String soldItemName, String orderNumber, String price) {
        this.id = id;
        this.orderImage = orderImage;
        this.currentDate = currentDate;
        this.soldItemName = soldItemName;
        this.orderNumber = orderNumber;
        this.price = price;
    }

    public OrdersModel(String orderImage, String currentDate, String soldItemName, String price) {
        this.orderImage = orderImage;
        this.currentDate = currentDate;
        this.soldItemName = soldItemName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrdersModel{" +
                "id=" + id +
                ", orderImage='" + orderImage + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", soldItemName='" + soldItemName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
