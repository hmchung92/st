package com.hominhchung.writing.description_pictures;

public class Adapter {
	int id = 0;
	String en_topic = null;
	String en_hint = null;

	public Adapter(int id, String en_topic, String en_hint) {
		super();
		this.id = id;
		this.en_topic = en_topic;
		this.en_hint = en_hint;
	}

	// ID
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	// En_topic
	public String getEn_topic() {
		return en_topic;
	}

	public void setEn_topic(String en_topic) {
		this.en_topic = en_topic;
	}

	// En_hint
	public String getEn_hint() {
		return en_hint;
	}

	public void setEn_hint(String en_hint) {
		this.en_hint = en_hint;
	}

	@Override
	public String toString() {
		return en_topic + " " + en_hint;
	}

}