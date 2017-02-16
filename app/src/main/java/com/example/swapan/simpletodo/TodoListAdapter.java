package com.example.swapan.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ctsuser1.simpletodo.R;

import java.util.ArrayList;

/**
 * Created by swapan on 2/10/17.
 */
public class TodoListAdapter extends ArrayAdapter<TodoItem> {
    public TodoListAdapter(Context context, int resource, ArrayList<TodoItem> todoItems) {
        super(context, resource, todoItems);
     }

    private ArrayList<TodoItem> dataSet;
    Context mContext;

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoItem todoItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }
         TextView todoView = (TextView) convertView.findViewById(R.id.tvTodoItem);
         todoView.setText(todoItem.getTodoText());
         return convertView;
    }
}
