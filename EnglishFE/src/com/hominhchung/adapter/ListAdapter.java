package com.hominhchung.adapter;

public class ListAdapter {

	String id = null;
	int _id = 0;
	String en_text = null;
	String local_text = null;

	public ListAdapter(String id, String en_text, String local_text) {
		super();
		this.id = id;
		this.en_text = en_text;
		this.local_text = local_text;
	}
	public ListAdapter(int _id, String en_text, String local_text) {
		super();
		this._id = _id;
		this.en_text = en_text;
		this.local_text = local_text;
	}
	/**
	 * truy vấn số thứ tự
	 */
	public int get_Id() {
		return _id;
	}

	public void set_id(int id) {
		this._id = _id;
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
	public String getEn_text() {
		return en_text;
	}

	public void setEn_text(String en_text) {
		this.en_text = en_text;
	}

	/**
	 * truy vấn từ vựng tiếng việt
	 */
	public String getLocal_text() {
		return local_text;
	}

	public void setLocal_text(String local_text) {
		this.local_text = local_text;
	}

	/**
	 * xuất nội dung
	 */
	@Override
	public String toString() {
		return _id + "" + id + " " + en_text + " " + local_text;
	}

}