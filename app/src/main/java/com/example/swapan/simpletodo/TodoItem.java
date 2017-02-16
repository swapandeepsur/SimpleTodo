package com.example.swapan.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by swapan on 2/09/17.
 */

@Table(database = TodoDatabase.class)
public class TodoItem extends BaseModel implements Serializable {

	@PrimaryKey(autoincrement = true)
	@Column
	public int uid;

	@Column
	private String todoText;

	@Column
	private String todoPriority;

	public TodoItem() {
		super();
	}

	public String getTodoText() {
		return todoText;
	}

	public String getTodoPriority() {
		return todoPriority;
	}

	public void setTodoText(String todoText) {
		this.todoText = todoText;
	}

	public void setTodoPriority(String todoPriority) {
		this.todoPriority = todoPriority;
	}

	@Override
	public String toString(){

		StringBuffer sb = new StringBuffer();
		sb.append("uid=");
		sb.append(uid);
		sb.append("*************");
		sb.append("todoText=");
		sb.append(todoText);

		return sb.toString();
	}
}
