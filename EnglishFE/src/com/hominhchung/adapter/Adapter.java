package com.hominhchung.adapter;
/**
 * 100 chủ đề nói và nghe
 */
public class Adapter {

	String id = null;
	String en_text = null;
	String local_text = null;

	public Adapter(String id, String en_text, String local_text) {
		super();
		this.id = id;
		this.en_text = en_text;
		this.local_text = local_text;
	}
	
	public Adapter(String en_text, String local_text) {
		super();
		this.en_text = en_text;
		this.local_text = local_text;
	}

	// truy vấn số thứ tự
	public String getId() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	// truy vấn từ vưng tiếng anh
	public String getEn_text() {
		return en_text;
	}

	public void setEn_text(String en_text) {
		this.en_text = en_text;
	}

	// truy vấn từ vựng tiếng việt
	public String getLocal_text() {
		return local_text;
	}

	public void setLocal_text(String local_text) {
		this.local_text = local_text;
	}

	@Override
	public String toString() {
		return id + " " + en_text + " " + local_text;
	}

}