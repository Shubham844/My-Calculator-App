package com.example.mycalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonAC,R.id.button_all_clear);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_c);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDot,R.id.button_decimal);
        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);
        assignId(buttonEquals,R.id.button_equal_to);


    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
       String dataToCalculate = solutionTv.getText().toString();

       if(buttonText.equals("AC")){
           solutionTv.setText("");
           resultTv.setText("0");
           return;
       }

       if(buttonText.equals("=")){
           solutionTv.setText(resultTv.getText());
           return;
       }
       if(buttonText.equals("C")){
           dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
       }else{
           dataToCalculate = dataToCalculate+buttonText;
       }

       solutionTv.setText(dataToCalculate);

       String finalResult = getResult(dataToCalculate);
       if(!finalResult.equals("Err")){
           resultTv.setText(finalResult);
       }

    }
    String getResult(String data){
        try{
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
           }
           return finalResult;
        }catch (Exception e){
            return "Err";
        }


    }
}