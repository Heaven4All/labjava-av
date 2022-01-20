package com.aviva.ciso.sdlc.medium.unsafeobjectbinding;

public class ItemDTO {
    private int id;
    private int order;
    private String userName;
    private String userId;

    public ItemDTO(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setBuyerName(String userName) {
        this.userName = userName;
    }

    public String getBuyerId() {
        return userId;
    }

    public void setBuyerId(String userId) {
        this.userId = userId;
    }
}
