package bean;

import java.util.Date;

public class Cart {
	private int cartId;
	private int bookId;
	private String bookImgSrc;
	private String bookName;
	private int bookMount;
	private int resMount;
	private float bookPrice;
	private int userId;
	private Date addTime;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookMount() {
		return bookMount;
	}

	public void setBookMount(int bookMount) {
		this.bookMount = bookMount;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getBookImgSrc() {
		return bookImgSrc;
	}

	public void setBookImgSrc(String bookImgSrc) {
		this.bookImgSrc = bookImgSrc;
	}

	public int getResMount() {
		return resMount;
	}

	public void setResMount(int resMount) {
		this.resMount = resMount;
	}

	
}
