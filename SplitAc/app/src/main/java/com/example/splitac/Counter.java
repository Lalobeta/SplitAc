package com.example.splitac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Counter extends AppCompatActivity {

    public static final String  user="names";
private TextView counterTxt;
private Button plusBoton;
private Button resetboton;
    private TextView counterTxt2;
    private Button plusBoton2;
    private Button resetboton2;

    public EditText pant;
    public EditText pant2;
    public double ope1,ope2;
    public float ope;

private int counter,counter2;

    private View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.plusBtn:
                    plusCounter();
                    total();
                    break;
                case R.id.resetBtn:
                    initCounter();
                    total();
                    break;
                case R.id.plusBtn2:
                    plusCounter2();
                    total();
                    break;
                case R.id.resetBtn2:
                    initCounter2();
                    total();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        pant= (EditText) findViewById(R.id.Precio);
        pant2= (EditText) findViewById(R.id.Precio2);

        counterTxt = (TextView) findViewById(R.id.counterTxt);
        plusBoton =(Button)findViewById(R.id.plusBtn);
        plusBoton.setOnClickListener(clickListener);
        resetboton=(Button)findViewById(R.id.resetBtn);
        resetboton.setOnClickListener(clickListener);

        counterTxt2 = (TextView) findViewById(R.id.counterTxt2);
        plusBoton2 =(Button)findViewById(R.id.plusBtn2);
        plusBoton2.setOnClickListener(clickListener);
        resetboton2=(Button)findViewById(R.id.resetBtn2);
        resetboton2.setOnClickListener(clickListener);

        initCounter();
    }

    private void initCounter(){

        counter=0;
        counterTxt.setText(counter + "");




    }

    private void plusCounter(){
        counter++;
        counterTxt.setText(counter + "");

    }

    private void initCounter2(){
        counter2=0;
        counterTxt2.setText(counter2 + "");

    }

    private void plusCounter2(){
        counter2++;
        counterTxt2.setText(counter2 + "");

    }



    private void total(){
        float pre1, pre2;
        DecimalFormat formato1 = new DecimalFormat("#.00");
        pre1 = Float.parseFloat(pant.getText().toString());
        pre2 = Float.parseFloat(pant2.getText().toString());

        ope= (pre1 * counter) + (pre2 * counter2);

        EditText total = (EditText)findViewById(R.id.total);
        total.setText(formato1.format(ope) + " Pesos");
    }

}
