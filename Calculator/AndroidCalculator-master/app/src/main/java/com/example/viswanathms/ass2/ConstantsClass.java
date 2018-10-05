package com.example.viswanathms.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by viswanathms on 2018-02-17.
 * This class is meant for declaring and using constants in the program.
 * This class cannot be inherited by any class. Made final according to java conventions.
 * Reference: http://www.javapractices.com/topic/TopicAction.do?Id=2
 */

public final class ConstantsClass {
    /**
     * Consists of a list of characters that can be used as a part of operand.
     * */
    public static final HashMap<Object,Character> operandMap= createOperationMap();
    public static HashMap<Object,Character> createOperationMap(){
        HashMap<Object,Character> operatorsMap = new HashMap<>();
        operatorsMap.put(R.id.one,'1');
        operatorsMap.put(R.id.two,'2');
        operatorsMap.put(R.id.three,'3');
        operatorsMap.put(R.id.four,'4');
        operatorsMap.put(R.id.five,'5');
        operatorsMap.put(R.id.six,'6');
        operatorsMap.put(R.id.Seven,'7');
        operatorsMap.put(R.id.Eight,'8');
        operatorsMap.put(R.id.Nine,'9');
        operatorsMap.put(R.id.one,'1');
        operatorsMap.put(R.id.zero,'0');
        operatorsMap.put(R.id.decimal,'.');
        return operatorsMap;
    }
    /**
     * HashMap contains all operators used in calculator
     * */
    public static final HashMap<Object,Character> operatorMap= createOperatorMap();
    public static HashMap<Object,Character> createOperatorMap(){
        HashMap<Object,Character> operMap = new HashMap<>();
        operMap.put(R.id.plus,'+');
        operMap.put(R.id.minus,'-');
        operMap.put(R.id.divide,'\u00F7');
        operMap.put(R.id.multiply,'x');
        operMap.put(R.id.root,'\u221a');
        return operMap;
    }
    //https://stackoverflow.com/questions/21696784/how-to-declare-an-arraylist-with-values
    public static final List operatorSymbols = new ArrayList<>(Arrays.asList("+","-","x","\u00F7","\u221a"));
    public static final String Error = "Error!";
}
