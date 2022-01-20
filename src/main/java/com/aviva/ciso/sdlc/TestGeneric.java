package com.aviva.ciso.sdlc;

import java.io.Serializable;

public abstract class TestGeneric<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 6861748234305042705L;
	private T inject;

	public TestGeneric(T inject) {
		this.inject = inject;
	}

	public T getStored() {
		return inject;
	}

	public void setStored(T inject) {
		this.inject = inject;
	}
}