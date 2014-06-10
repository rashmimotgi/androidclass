package com.example.tipcalculator;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etText;
	private TextView tvTip; 
	private double value;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void onTenPercent(View v) {
		//Toast.makeText(this, "10 % tip ", Toast.LENGTH_SHORT).show();
		etText=(EditText) findViewById(R.id.etTotal);
		float val = Integer.parseInt(etText.getText().toString());
		 value =  val*10/100;
		 tvTip = (TextView) findViewById(R.id.tvTip);
		 tvTip.setText("Tip is: $ "+value);

	}
	public void onFifteenPercent(View v) {
	//	Toast.makeText(this, "15 % tip ", Toast.LENGTH_SHORT).show();
		etText=(EditText) findViewById(R.id.etTotal);
		float val = Integer.parseInt(etText.getText().toString());
		 value =  val*15/100;
		 tvTip = (TextView) findViewById(R.id.tvTip);
		 tvTip.setText("Tip is: $ "+value);

		}
	public void onTwentyPercent(View v) {
		//Toast.makeText(this, "20 % tip ", Toast.LENGTH_SHORT).show();
		etText=(EditText) findViewById(R.id.etTotal);
		float val = Integer.parseInt(etText.getText().toString());
		 value =  val*20/100;
		 tvTip = (TextView) findViewById(R.id.tvTip);
		 tvTip.setText("Tip is: $ "+value);

		}
}
