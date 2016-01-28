package com.example.khanhnguyen.inclass2a;
/*
Assignment: 2b
File Name: 800580208_InClass01.zip
Full Name: Khanh Nguyen
* */
import android.app.Activity;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText input;
    TextView resultDisplay;
    String resultString;
    double result, inputAmnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.inputView);
        resultDisplay = (TextView) findViewById(R.id.resultDisp);


        //buttons listeners
        findViewById(R.id.inBtn).setOnClickListener(this);
        findViewById(R.id.ftBtn).setOnClickListener(this);
        findViewById(R.id.mlBtn).setOnClickListener(this);
        findViewById(R.id.clrAllBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        InputMethodManager inputMethodManager = (InputMethodManager)  this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        if(input.getText().toString() == null || input.getText().toString().length() ==0 ){
            if(v.getId() != R.id.clrAllBtn){
                Log.d("Error", "user failed to input shit");
                Toast.makeText(MainActivity.this, "No Input Found!", Toast.LENGTH_SHORT).show();
            }
        }else{
            String tempHolder = input.getText().toString();
            boolean parsable = true;
            try{
                result = Double.parseDouble(tempHolder);
            }catch(NumberFormatException e){
                Toast.makeText(MainActivity.this, "Invalid Input!", Toast.LENGTH_SHORT).show();
                parsable = false;
            }

            if(parsable){
                switch (v.getId()){
                    case R.id.inBtn:
                        result *= 39.3701;
                        break;
                    case R.id.ftBtn:
                        result *= 3.28;
                        break;
                    case R.id.mlBtn:
                        result *= 0.0006;
                        break;
                }

                resultDisplay.setText(result + "");
                resultDisplay.setVisibility(View.VISIBLE);
            }

            if(v.getId() == R.id.clrAllBtn)
                clearALL();

        }
    }


    public void clearALL(){
        result = 0;
        Log.d("Test", "Now it's clean");
        input.setText("");
        resultDisplay.setText("");
    }
}
