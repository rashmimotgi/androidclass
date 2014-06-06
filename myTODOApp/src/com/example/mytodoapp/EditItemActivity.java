package com.example.mytodoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
    private EditText etText, etText1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
	    etText = (EditText) findViewById(R.id.etText);
	    String etext = (String) getIntent().getSerializableExtra("value");			
        etText.setText(etext);
	}
	public void editText(View v) {
	//	etNewItem =(EditText) findViewById(R.id.etNewItem);
		etText1 = (EditText) findViewById(R.id.etText);
		Intent data = new Intent();
		data.putExtra("name",etText1.getText().toString());
		setResult(RESULT_OK, data);
		
	//	String itemText =  etNewItem.getText().toString();
	//	todoAdapter.add(itemText);
	//	etNewItem.setText("");
	//	writeItems();
		finish();
	}
}
