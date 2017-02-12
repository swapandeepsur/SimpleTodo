package com.example.swapan.simpletodo;

import java.io.Serializable;

/**
 * Created by swapan on 2/09/17.
 */

public class TodoItem implements Serializable{
	private String todoText;
	private String todoPriority;

	public TodoItem(String todoText, String todoPriority) {
		this.todoText = todoText;
		this.todoPriority = todoPriority;
	}

	public String getTodoText() {
		return todoText;
	}

	public String getTodoPriority() {
		return todoPriority;
	}

	@Override
	public String toString(){

		return todoText;
	}
}
