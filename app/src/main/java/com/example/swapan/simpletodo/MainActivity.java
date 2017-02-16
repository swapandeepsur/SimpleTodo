package com.example.swapan.simpletodo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ctsuser1.simpletodo.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

/**
 * Created by swapan on 2/08/17.
 */

public class MainActivity extends AppCompatActivity implements EditTodoDialogFragment.EditTodoDialogListener {

    //private static final int EDIT_REQUEST_CODE = 100;
    private ArrayList<TodoItem> items;
    private ArrayAdapter<TodoItem> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new TodoListAdapter(this, R.layout.todo_item, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        setupListEditListener();
    }

    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        TodoItem todoItem = new TodoItem();
        todoItem.setTodoText(itemText);
        todoItem.setTodoPriority("1");
        itemsAdapter.add(todoItem);
        etNewItem.setText("");
        todoItem.save();
    }


    @Override
    public void onFinishEditDialog(String editedValue, int editPosition) {
        TodoItem todoItem = items.get(editPosition);
        todoItem.setTodoText(editedValue);
        todoItem.setTodoPriority("1");

        items.set(editPosition, todoItem);
        itemsAdapter.notifyDataSetChanged();
        todoItem.save();

    }


    private void setupListEditListener(){
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id){

                String editItem = items.get(pos).getTodoText().toString();
                FragmentManager fm = getSupportFragmentManager();
                EditTodoDialogFragment editTodoDialogFragment = EditTodoDialogFragment.newInstance(editItem, pos);
                editTodoDialogFragment.show(fm, "fragment_edit_item");
            }
        });
    }



    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id){
                TodoItem todoItem = items.get(pos);
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                todoItem.delete();
                return true;
            }
        });
    }

    private void readItems(){
        items = (ArrayList<TodoItem>)SQLite.select().from(TodoItem.class).queryList();
    }


}
