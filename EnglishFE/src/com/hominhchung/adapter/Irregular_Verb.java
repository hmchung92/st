package com.hominhchung.adapter;
/**
 * Irregular Verb
 */
public class Irregular_Verb {

	String id = null;
	String infinitive = null;
	String past_simple = null;
	String past_participle = null;
	String vicontent = null;
	String example = null;

	public Irregular_Verb(String id, String infinitive, String past_simple,
			String past_participle, String vicontent, String example) {
		super();
		this.id = id;
		this.infinitive = infinitive;
		this.past_simple = past_simple;
		this.past_participle = past_participle;
		this.vicontent = vicontent;
		this.example = example;
	}
	public Irregular_Verb(String infinitive, String past_simple,
			String past_participle, String vicontent, String example) {
		super();
		this.infinitive = infinitive;
		this.past_simple = past_simple;
		this.past_participle = past_participle;
		this.vicontent = vicontent;
		this.example = example;
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
	 * truy vấn từ vưng
	 
	public String getName() {
		return infinitive;
	}

	public void setName(String name) {
		this.infinitive = infinitive;
	}
*/
	/**
	 * truy vấn từ vựng Infinitive
	 */
	public String getInfinitive() {
		return infinitive;
	}

	public void setInfinitive(String infinitive) {
		this.infinitive = infinitive;
	}

	/**
	 * truy vấn từ vựng Simple past
	 */
	public String getPast_simple() {
		return past_simple;
	}

	public void setPast_simple(String past_simple) {
		this.past_simple = past_simple;
	}

	/**
	 * truy vấn từ vựng Participle past 
	 */
	public String getPast_participle() {
		return past_participle;
	}

	public void setVPast_participle(String vicontent) {
		this.past_participle = past_participle;
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
	 * truy vấn ví dụ của từ vựng
	 */
	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
	
	/**
	 * xuất nội dung
	 */
	@Override
	public String toString() {
		return id + " " + infinitive + " " + past_simple+ " " + past_participle + " " + vicontent;
	}

}