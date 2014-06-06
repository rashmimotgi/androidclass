package com.example.mytodoapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class TodoActivity extends Activity {
    private static final int REQUEST_CODE = 20;
	private ArrayList<String> todoItems;
    private ArrayAdapter<String> todoAdapter;
    private ListView lvItem;
    private EditText etNewItem, etNewItems;
    private static int textPos;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		lvItem = (ListView) findViewById(R.id.lvItem);
        readItems();
        todoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
	    lvItem.setAdapter(todoAdapter);
	    setuplistViewListener();
	    setupEditViewListener();
	}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      // REQUEST_CODE is defined above
      if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
         // Extract name value from result extras
         String name = data.getExtras().getString("name");
         // Toast the name to display temporarily on screen
         Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
			todoItems.set(textPos,name);
			todoAdapter.notifyDataSetChanged();
		    writeItems();

      }
    } 
	
	private void setuplistViewListener() {
		lvItem.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View item,
					int pos, long id) {
				// TODO Auto-generated method stub
			    todoItems.remove(pos);
			    todoAdapter.notifyDataSetChanged();
			    writeItems();
				return true;
			}
			
			});
	}
	public void launchEditView(String itemText, int position) {
		  // first parameter is the context, second is the class of the activity to launch
		  Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
		  lvItem =(ListView) findViewById(R.id.lvItem);
		  i.putExtra("value", itemText);
		  startActivityForResult(i, REQUEST_CODE);
// brings up the second activity
		}
	
	private void setupEditViewListener() {
		lvItem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View item, int pos,
					long id) {
				textPos = pos;
				String itemText=todoAdapter.getItem(pos);
				launchEditView(itemText,pos);

				// TODO Auto-generated method stub
			}	
			});
	}
	public void addTodoItem(View v) {
		etNewItem =(EditText) findViewById(R.id.etNewItem);
		String itemText =  etNewItem.getText().toString();
		todoAdapter.add(itemText);
		etNewItem.setText("");
		writeItems();
	}
	
	public void readItems() {
		File filesDir =getFilesDir();
		File todoFile= new File(filesDir, "todo.txt");
		try {
			todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
		
		}catch (IOException e){
			todoItems =new ArrayList<String>();
		}
				
	}
	public void writeItems() {
		File filesDir =getFilesDir();
		File todoFile= new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, todoItems);
		
		}catch (IOException e){
			e.printStackTrace();
		}
				
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
