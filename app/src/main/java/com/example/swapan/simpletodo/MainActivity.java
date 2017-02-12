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

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        TodoItem todoItem = new TodoItem(itemText, "1");
        itemsAdapter.add(todoItem);
        etNewItem.setText("");
        writeItems();
    }


    @Override
    public void onFinishEditDialog(String editedValue, int editPosition) {
        TodoItem todoItem = new TodoItem(editedValue, "1");
        items.set(editPosition, todoItem);
        itemsAdapter.notifyDataSetChanged();
        writeItems();

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
                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
    }

    private void readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            List<String> strItemsList =  FileUtils.readLines(todoFile);
            int counter = 0;
            items = new ArrayList<TodoItem>();
            for(String item: strItemsList){
                TodoItem todoItem = new TodoItem(item, "1");
                items.add(counter, todoItem);
                counter++;
            }
        }
        catch (IOException e){
            items = new ArrayList<>();
        }
        System.out.println(items);
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
