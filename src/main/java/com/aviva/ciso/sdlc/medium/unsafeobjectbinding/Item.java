package com.aviva.ciso.sdlc.medium.unsafeobjectbinding;

public class Item {
	private int id;
	private int order;
	private User buyer;

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public int getOrder() {
		return order;
	}

	public void setOrder(int amount) {
		this.order = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
