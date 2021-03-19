package com.example.zein_calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);

        /* Listens to clicking in the  display */

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())) {

                    display.setText("");
                }

            }
        });
    }

    /* This method allows the user to edit text inside the operation based on the cursor position */

    public void updateText(String strToAdd){

        String string1 = display.getText().toString();
        int position = display.getSelectionStart();
        String left_side = string1.substring(0,position);
        String rigt_side = string1.substring(position);

        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(position + 1 );
        }
        else {
            display.setText(String.format("%s%s%s", left_side, strToAdd, rigt_side));
            display.setSelection(position + 1);
        }
    }

    public void zeroBtn(View view){
        updateText("0");

    }

    public void oneBtn(View view){
        updateText("1");

    }

    public void twoBtn(View view){

        updateText("2");

    }

    public void threeBtn(View view){

        updateText("3");

    }

    public void fourBtn(View view){

        updateText("4");

    }

    public void fiveBtn(View view){

        updateText("5");

    }

    public void sixBtn(View view){

        updateText("6");

    }

    public void sevenBtn(View view){

        updateText("7");

    }

    public void eightBtn(View view){

        updateText("8");

    }

    public void nineBtn(View view){
        updateText("9");

    }

    public void multpilyBtn(View view){
        updateText("×");
    }

    public void addBtn(View view){

        updateText("+");
    }

    public void divideBtn(View view){

        updateText("÷");
    }

    public void subtractBtn(View view){

        updateText("-");
    }


    public void clearBtn(View view){

        display.setText("");
    }

    /* We've got to track whether a parenthesis has been opened or closed */

    public void parBtn(View view){

        int position = display.getSelectionStart();

        int open =0;
        int close =0;
        int length =display.getText().length();


        for ( int i =0; i < position; i++) {

            if (display.getText().toString().substring(i, i+1).equals("(")) {

                open += 1;
            }

            if ( display.getText().toString().substring(i, i+1).equals(")")) {

                close+= 1;
            }
        }

        /* I won't close the parenthesis unless something is added in it */


        if (open == close || display.getText().toString().substring(length -1, length).equals("(")) {

            updateText("(");
        }

        else if (close < open)
        {
            if (display.getText().toString().substring(length -1, length).equals("×") || display.getText().toString().substring(length -1, length).equals("+") || display.getText().toString().substring(length -1, length).equals("÷") || display.getText().toString().substring(length -1, length).equals("+") || display.getText().toString().substring(length -1, length).equals("-")) {
                updateText("(");
            }

            else if(!display.getText().toString().substring(length -1, length).equals(")"))
            {
                updateText(")");
            }

            else {
                updateText(")");
                Toast toast=Toast.makeText(getApplicationContext(),"Ayyyyyy",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }

        display.setSelection(position + 1);
    }

    public void expBtn(View view){

    }

    public void plusMinusBtn(View view){

    }

    /* Making sure there are no redundant decimal dots */

    public void decimalBtn(View view){

        int len = display.getSelectionStart();
        boolean dec = true;

        for ( int i =0; i < len; i++) {

            if (display.getText().toString().substring(i, i+1).equals(".")) {

                dec = dec && false;
            }

            else if (display.getText().toString().substring(i, i+1).equals("+") || display.getText().toString().substring(i, i+1).equals("÷") || display.getText().toString().substring(i, i+1).equals("×") || display.getText().toString().substring(i, i+1).equals("-")){

                dec = true;
            }
        }

        if ( dec == true ) {

            updateText(".");
        }
    }

    public void equalBtn(View view){


        String input = display.getText().toString();

        input= input.replaceAll("÷", "/");
        input = input.replaceAll("×", "*");

        DecimalFormat fmt1 = new DecimalFormat("#.####");
        Expression exp = new Expression(input);

        String disp = String.valueOf(exp.calculate());
        double disp1 = exp.calculate();


        if ( disp1 == (int) disp1) {

            disp = String.valueOf((int)disp1);
        }

        else if(disp.split(".",2)[1].length() > 4) {

            disp = String.valueOf(fmt1.format(disp1));
        }

        else {

            disp = String.valueOf(disp1);
        }

        display.setText(disp);
        display.setSelection(disp.length()); //update cursor
    }

    public void backBtn(View view){

        int position = display.getSelectionStart(); /* Grab cursor's current position */
        int length = display.getText().length();  /* Since we're going to push back the cursor then if we have an empty display it will cause a null pointer error so we must keep track of the length */

        if ( position !=0 && length !=0) {

            SpannableStringBuilder build = (SpannableStringBuilder) display.getText();
            build.replace(position-1, position, ""); /*Capture parameter behind character and at the position of the cursor */
            display.setText(build);
            display.setSelection(position - 1); /* bringing the cursor back behind the character that we just deleted */
        }
    }
}