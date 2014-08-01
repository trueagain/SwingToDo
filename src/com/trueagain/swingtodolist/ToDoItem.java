package com.trueagain.swingtodolist;

public class ToDoItem {
	private String name;
	
	public ToDoItem(String _name){
		this.name = _name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
