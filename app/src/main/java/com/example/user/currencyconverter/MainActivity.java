package com.example.user.currencyconverter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double input,output=0;
    double curConvert=1/1.34;
    double curMYRToSGD=1.4;
    char symbol='$';
    RadioGroup rgFrom, rgTo;
    private EditText et;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
        Button btnConvert = (Button)findViewById(R.id.buttonConvert);
        tv = (TextView)findViewById(R.id.textViewOutput);
        tv.setText(" ");
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = et.getText().toString().trim();
                if(a.equals(".") || a.equals("")){
                    Log.d("Demo",a);
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid input";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Log.d("demo",a);
                    input=Double.parseDouble(a);
                    output=input*curConvert*curMYRToSGD;
                    Log.d("demo","Input="+input+"Output="+output+"");
                    double temp=output*1000;
                    long t=(long)temp;
                    output=(double)t/1000.0;
                    tv.setText(symbol+" "+output+"");
                }
            }
        });
        rgFrom = (RadioGroup)findViewById(R.id.radioGroupFrom);
        rgTo = (RadioGroup)findViewById(R.id.radioGroupTo);
        rgFrom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rbFrom = (RadioButton)findViewById(checkedId);
                String curFrom = rbFrom.getText().toString().trim();
                if(curFrom.matches("AUD")){
                    curConvert=3.38/1;
                    Log.d("demo",curConvert+"");
                }else if(curFrom.matches("NZD")){
                    curConvert=3.12/1;
                    Log.d("demo",curConvert+"");
                }else if(curFrom.matches("USD")){
                    curConvert=4.45/1;
                    Log.d("demo",curConvert+"");
                }else{
                    tv.setText("Invalid selection");
                }

            }
        });

        rgTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rbTo = (RadioButton)findViewById(checkedId);
                String curTo = rbTo.getText().toString().trim();
                if(curTo.matches("MYR")){
                    curMYRToSGD=1;
                    symbol='$';
                    Log.d("demo","MYR selected"+curMYRToSGD+"");
                }else if(curTo.matches("SGD")){
                    curMYRToSGD=0.32;
                    symbol='$';
                    Log.d("demo","SGD selected"+curMYRToSGD+"");
                }else{
                    tv.setText("Invalid selection");
                }

            }
        });
    }

    public void clear(View view){
        et = (EditText)findViewById(R.id.editText);
        tv = (TextView)findViewById(R.id.textViewOutput);
        tv.setText(" ");
        et.setText(" ");
        output=0;
        input=0;
        RadioButton rb = (RadioButton)findViewById(R.id.radioButtonAUD);
        rb.setChecked(true);
        rb = (RadioButton)findViewById(R.id.radioButtonMYR);
        rb.setChecked(true);
    }
}
