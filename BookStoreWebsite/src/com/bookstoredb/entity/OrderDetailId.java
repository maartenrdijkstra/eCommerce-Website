package com.bookstoredb.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetailId implements java.io.Serializable {

	private Integer orderId;
	private Integer bookId;
	private int quantity;
	private float subtotal;

	public OrderDetailId() {
	}

	public OrderDetailId(int quantity, float subtotal) {
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public OrderDetailId(Integer orderId, Integer bookId, int quantity, float subtotal) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = true)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = true)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return this.subtotal;
	}

	@Column(name = "subtotal", nullable = false, precision = 12, scale = 0)
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderDetailId))
			return false;
		OrderDetailId castOther = (OrderDetailId) other;

		return ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
				&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getBookId() == castOther.getBookId()) || (this.getBookId() != null
						&& castOther.getBookId() != null && this.getBookId().equals(castOther.getBookId())))
				&& (this.getQuantity() == castOther.getQuantity()) && (this.getSubtotal() == castOther.getSubtotal());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result + (getBookId() == null ? 0 : this.getBookId().hashCode());
		result = 37 * result + this.getQuantity();
		result = 37 * result + (int) this.getSubtotal();
		return result;
	}

}
