package com.trueagain.swingtodolist;

import java.io.Serializable;

public class ToDoItem implements Serializable{
	private String name;
	
	public ToDoItem(String _name){
		this.name = _name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
