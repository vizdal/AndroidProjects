package com.example.viswanathms.ass2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener {
    /**
     * Represents the box where the values are inserted
     * */
    private static EditText calcBox;
    /**
     * Represents the box where queue status is shown.
     * One at the top
     * */
    private static EditText queueStatus;
    static Button zero; // Zero button reference is required for division operation. Kept in default scope to make it visible in package
    static Button plusMinus; // plusMinus is required in sqrt operation
    /**
     * Value in the CalcBox is intialized to the value passed
     * @param  value String
     * @return void
     * */
    public static void setCalcBox(String value){
        calcBox.setText(value);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcBox = findViewById(R.id.inputOutputField);
        queueStatus = findViewById(R.id.currentStatus);
        zero = findViewById(R.id.zero);
        (findViewById(R.id.Seven)).setOnClickListener(this);
        (findViewById(R.id.Eight)).setOnClickListener(this);
        (findViewById(R.id.Nine)).setOnClickListener(this);
        (findViewById(R.id.six)).setOnClickListener(this);
        (findViewById(R.id.five)).setOnClickListener(this);
        (findViewById(R.id.four)).setOnClickListener(this);
        (findViewById(R.id.three)).setOnClickListener(this);
        (findViewById(R.id.two)).setOnClickListener(this);
        (findViewById(R.id.one)).setOnClickListener(this);
        zero.setOnClickListener(this);
        (findViewById(R.id.decimal)).setOnClickListener(this);
        (findViewById(R.id.clear)).setOnClickListener(this);
        plusMinus = findViewById(R.id.plusminus);
        plusMinus.setOnClickListener(this);
        (findViewById(R.id.plus)).setOnClickListener(this);
        (findViewById(R.id.minus)).setOnClickListener(this);
        (findViewById(R.id.multiply)).setOnClickListener(this);
        (findViewById(R.id.root)).setOnClickListener(this);
        (findViewById(R.id.divide)).setOnClickListener(this);
        (findViewById(R.id.equals)).setOnClickListener(this);
        (findViewById(R.id.allClear)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CalculatorUtility calcUtil = new CalculatorUtility();
        int input = v.getId();
        if (ConstantsClass.operandMap.containsKey(input)) {
            calcUtil.addDigit(ConstantsClass.operandMap.get(input));
        } else if (ConstantsClass.operatorMap.containsKey(input)) {
            queueStatus.setText(calcUtil.addOperator(ConstantsClass.operatorMap.get(input).toString()));
        } else if (input == R.id.clear) {
            calcUtil.clearCurrent();
        } else if (input == R.id.plusminus) {
            calcUtil.toggleSigns();
        } else if (input == R.id.equals) {
            queueStatus.setText("");
            calcUtil.handleEquals();
        } else if (input == R.id.allClear) {
            queueStatus.setText(calcUtil.handleAllClear());
        }
        setCalcBox(calcUtil.getTempNumber().toString());
    }
    //https://stackoverflow.com/questions/17969925/how-to-highlight-a-button-when-is-pressed
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (ConstantsClass.operatorMap.containsKey(v.getId())){
                findViewById(v.getId()).setBackgroundColor(getResources().getColor(R.color.colorBlack));
            }
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            findViewById(v.getId()).setBackgroundColor(getResources().getColor(R.color.colorWhite));
        }
        return true;
    }
}