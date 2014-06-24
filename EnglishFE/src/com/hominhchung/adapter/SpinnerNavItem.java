package com.hominhchung.adapter;
//Từ phiên Android lever 11 trở đi (Android 3.0 trở đi) thì Android có hỗ trợ đối tượng ActionBar.
//Model để lưu dữ liệu cho mỗi row trên DropDown 
public class SpinnerNavItem {

	private String title;
	private int icon;
	
	public SpinnerNavItem(String title, int icon){
		this.title = title;
		this.icon = icon;
	}
	
	public String getTitle(){
		return this.title;		
	}
	
	public int getIcon(){
		return this.icon;
	}
}
