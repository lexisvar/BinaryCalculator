package com.example.finalandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BinaryCalculator extends Activity implements View.OnClickListener {

	 EditText val1;
	 EditText val2;
	 EditText result;
	 
	 TextView operation;

	 Button add;
	 Button deduct;
	 Button multiply;
	 Button divide;
	 Button focus;
	 
	 Button one;
	 Button cero;
	 
	 Button operate;
	 Button reset;

	 String oper = ""; 
	 
	 String num1 = "";
	 String num2 = "";
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		val1 = (EditText) findViewById(R.id.val1);
		val2 = (EditText) findViewById(R.id.val2);
		result = (EditText) findViewById(R.id.result);
			
		operation = (TextView) findViewById(R.id.operation);
		
		add = (Button) findViewById(R.id.add);
		deduct = (Button) findViewById(R.id.deduct);
		multiply = (Button) findViewById(R.id.multiply);
		divide = (Button) findViewById(R.id.divide);
		focus = (Button) findViewById(R.id.focus);
		
		one = (Button) findViewById(R.id.one);
		cero = (Button) findViewById(R.id.cero);	
		
		operate = (Button) findViewById(R.id.operate);
		reset = (Button) findViewById(R.id.reset);

	    // set a listener
		add.setOnClickListener(this);
		deduct.setOnClickListener(this);
		multiply.setOnClickListener(this);
		divide.setOnClickListener(this);
		focus.setOnClickListener(this);
		
		one.setOnClickListener(this);
		cero.setOnClickListener(this);
		
		operate.setOnClickListener(this);
		reset.setOnClickListener(this);
		
		val1.requestFocus();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	

	public void onClick(View v) {  
	       
	    switch (v.getId()) { 
		    case R.id.add:
		    	oper = "+";
		    	operation.setText(oper);
		    	break;
		    case R.id.deduct:
		    	oper = "-";
		    	operation.setText(oper);
		    	break;
		    case R.id.multiply:
		    	oper = "*";
		    	operation.setText(oper);
		    	break;
		    case R.id.divide:
		    	oper = "/";
		    	operation.setText(oper);
		    	break;
		    case R.id.one:
		    	if(getCurrentFocus()==val1){
		    		val1.append("1");	
	    		}else{
	    			val2.append("1");
	    		}
		    	break;
		    case R.id.cero:		      
				if(getCurrentFocus()==val1){
					val1.append("0");	
				}else{
					val2.append("0");
				}		     
				break;	    
		    case R.id.focus:	    	
	    		if(getCurrentFocus()==val1){
	    			val2.requestFocus();
	    		}else{
	    			val1.requestFocus();
	    		}	    		
		    	break;	
		    case R.id.operate:
		    	if(oper=="+"){
		    		num1 = val1.getText().toString(); 
		    		num2 = val2.getText().toString();
		    		String results = binaryAdder(num1 , num2);
		    		result.setText(results);		    		
		    	}else if(oper=="-"){
		    		num1 = val1.getText().toString(); 
		    		num2 = val2.getText().toString();
		    		String results = binarySubstraction(num1 , num2);
		    		result.setText(results);
		    	}else if(oper=="*"){
		    		num1 = val1.getText().toString(); 
		    		num2 = val2.getText().toString();
		    		String results = binaryMultiplication(num1 , num2);
		    		result.setText(results);
		    	}else if(oper=="/"){
		    		num1 = val1.getText().toString(); 
		    		num2 = val2.getText().toString();
		    		String results = binaryDivision(num1 , num2);
		    		result.setText(results);
		    	}
		    	break;
		    	
		    case R.id.reset:
		    	val1.setText("");
		    	val2.setText("");
		    	result.setText("");
		    	val1.requestFocus();
			    break;
		    
		    default:
		      break;
	    }	    
	}
	public static String binaryAdder(String a, String b) {
	    int avalue = Integer.parseInt(a, 2);
	    int bvalue = Integer.parseInt(b, 2);
	    return Integer.toBinaryString(avalue + bvalue);
	}
	public static String binarySubstraction(String a, String b) {
	    int avalue = Integer.parseInt(a, 2);
	    int bvalue = Integer.parseInt(b, 2);
	    if(avalue - bvalue < 0){
	    	return "-"+Integer.toBinaryString((avalue - bvalue)*-1);
	    }else{
	    	return Integer.toBinaryString(avalue - bvalue);
	    }
	}
	public static String binaryMultiplication(String a, String b) {
	    int avalue = Integer.parseInt(a, 2);
	    int bvalue = Integer.parseInt(b, 2);
	    return Integer.toBinaryString(avalue * bvalue);
	}
	public static String binaryDivision(String a, String b) {
	    int avalue = Integer.parseInt(a, 2);
	    int bvalue = Integer.parseInt(b, 2);
	    double avalue1 = avalue;
	    double bvalue2 = bvalue;	    
	    return floatToBinaryString((avalue1/bvalue2));
	}
	private static String floatToBinaryString( double n ) {
	    String val = "0.";    // Setting up string for result
	    while ( n > 0 ) {     // While the fraction is greater than zero (not equal or less than zero)
	        double r = n * 2;   // Multiply current fraction (n) by 2
	        if( r >= 1 ) {      // If the ones-place digit >= 1
	            val += "1";       // Concat a "1" to the end of the result string (val)
	            n = r - 1;        // Remove the 1 from the current fraction (n)
	        }else{              // If the ones-place digit == 0
	            val += "0";       // Concat a "0" to the end of the result string (val)
	            n = r;            // Set the current fraction (n) to the new fraction
	        }
	    }
	    return val;          // return the string result with all appended binary values
	}
}