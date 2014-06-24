package com.hominhchung.speaking.general_debates;

public class AdapterGeneralDebates {

	String en_text = null;
	String en_hint = null;
	String local_question = null;
	int id;

	public AdapterGeneralDebates(String en_text, String local_question) {
		super();
		this.en_text = en_text;
		this.local_question = local_question;
	}

	public AdapterGeneralDebates(int id, String en_text, String local_question, String en_hint) {
		super();
		this.id = id;
		this.en_text = en_text;
		this.local_question = local_question;
		this.en_hint = en_hint;
	}

	// truy vấn Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// En_topic
	public String getEn_topic() {
		return en_text;
	}

	public void setEn_topic(String en_text) {
		this.en_text = en_text;
	}

	// En_hint
	public String getEn_hint() {
		return en_hint;
	}

	public void setEn_hint(String en_hint) {
		this.en_hint = en_hint;
	}

	// Local_question
	public String getLocal_question() {
		return local_question;
	}

	public void setLocal_question(String local_question) {
		this.local_question = local_question;
	}
 
	
	@Override
	public String toString() {
		return en_text + " " + local_question;
	}

}