package com.example.viswanathms.ass2;

import java.text.DecimalFormat;
import java.util.ArrayDeque;

/**
 * Created by viswanathms on 2018-02-14.
 * This class consists of a Queue implementation to store the operations to be performed.
 * The queue is allowed to store up to 2 operands and an operator.
 * When there is a 2nd operand we compute the result and push again.
 */
public class OperationsClass {
    /**
     * Array Dequeue Data Structure is used for convenience of adding and removing
     * from the front and rear end of the queues.
     * */
    private static ArrayDeque<String> operationQueue;
    /***
     * Returns the current value of operation queue.
     * @return ArrayDequeue<String> reference to operationQueue
     */

    public ArrayDeque<String> getOperationQueue(){
        return operationQueue;
    }
    /**
     * gets the current value in the queue as a string
     * StringBuffer is used to reduce the heap load.
     * @return  String contents of the ArrayDeque operationQueue
     * */
    public String getString(){
        StringBuffer sb = new StringBuffer();
        if(operationQueue!=null) {
            for (String s : operationQueue) {
                sb.append(s);
            }
        }
        return sb.toString();
    }
    /**
     * Pushes operand to the operation queue.
     * @param operand String the value of operand / operator to be pushed in the queue
     * @return String
     * */
    public String pushOperand(String operand){
        if(operationQueue!=null && !operationQueue.isEmpty() && ConstantsClass.operatorSymbols.contains(operationQueue.getLast()) && ConstantsClass.operatorSymbols.contains(operand)) {
            // To remove the last operand if there is an operand immediately after that.
            operationQueue.removeLast();
            operationQueue.add(operand);
        } else if(operationQueue!=null && operationQueue.size()==3){
            if(operationQueue.getLast().equals("\u221a")){
                operand = String.valueOf(Math.sqrt(new Double(operand)));
                operationQueue.pollLast();
                operationQueue.add(operand);
            } else{
                operationQueue.add(getResult());
                operationQueue.add(operand);
            }
        } else if(operationQueue!=null && operationQueue.size()==2 && operationQueue.getFirst().equals("\u221a")){
            operationQueue.add(getResult());
            operationQueue.add(operand);
        } else if(operationQueue == null){
            operationQueue = new ArrayDeque<>();
            operationQueue.add(operand);
        } else{
            operationQueue.add(operand);
        }
        return getString();
    }
    /**
     * Method performs all the operations and returns the result of those operation specified
     * @return String result of the operation performed.
     * */
    public String getResult(){
        try{
            Double result = null;
            if(operationQueue!=null) {
                if (operationQueue.size() == 3) {
                    result = 0.0D;
                    Double operand1 = new Double(operationQueue.poll());
                    String operator = operationQueue.poll();
                    String value2 = operationQueue.poll();
                    if(value2.isEmpty()){
                        value2 ="0"; // empty second operand case handled
                    }
                    Double operand2 = new Double(value2);
                    switch (operator) {
                        case "+":
                            result = operand1 + operand2;
                            break;
                        case "-":
                            result = operand1 - operand2;
                            break;
                        case "x":
                            result = operand1 * operand2;
                            break;
                        case "\u00F7":
                            result = operand1 / operand2;
                            break;
                        case "\u221a":
                            result = operand1*Math.sqrt(operand2);
                            break;
                    }
                }
                else if(operationQueue.size()==2 && operationQueue.getFirst().equals("\u221a")){//If square root is the first
                    operationQueue.pollFirst();//Remove square root
                    Double operand=new Double(operationQueue.pollFirst());
                    result = Math.sqrt(operand);
                }
            }
            //https://stackoverflow.com/questions/14204905/java-how-to-remove-trailing-zeros-from-a-double
            DecimalFormat format = new DecimalFormat("0.########");//Formats and removes unwanted decimals
            return format.format(result);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Method clears all the elements in the queue.
     */
    public void clearQueue(){
        if(operationQueue!=null){
            operationQueue.clear();
        }
    }
}