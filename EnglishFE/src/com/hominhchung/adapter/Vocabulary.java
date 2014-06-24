package com.hominhchung.adapter;
/**
 * Học 3500 từ vựng (3500 Vocabulary)
 */
public class Vocabulary {

	String id = null;
	String name = null;
	String category = null;
	String local_text = null;
	String read = null;
	String vicontent = null;

	public Vocabulary(String id, String name, String category, String read, String vicontent) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.read = read;
		this.vicontent = vicontent;
	}

	/**
	 * truy vấn số thứ tự
	 */
	public String getId() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	/**
	 * truy vấn từ vưng tiếng anh
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * truy vấn từ loại tiếng anh
	 */
	public String getCatagory() {
		return category;
	}

	public void setCatagory(String local_text) {
		this.category = category;
	}

	/**
	 * truy vấn cách đọc từ vựng
	 */
	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}
	
	/**
	 * truy vấn cách nghĩa từ vựng
	 */
	public String getVicontent() {
		return vicontent;
	}

	public void setVicontent(String vicontent) {
		this.vicontent = vicontent;
	}
	/**
	 * xuất nội dung
	 */
	@Override
	public String toString() {
		return id + " " + name + " " + category + " " + local_text + " " + read + " " + vicontent;
	}

}