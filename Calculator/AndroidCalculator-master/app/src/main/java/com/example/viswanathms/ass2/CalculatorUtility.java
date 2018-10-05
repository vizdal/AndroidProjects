package com.example.viswanathms.ass2;

/**
 * Created by viswanathms on 2018-02-11.
 * This class will consist of all methods that are required to support the calculator
 * from the mathematics side. Will have all methods with regards to calculation.
 */

public class CalculatorUtility {
    /**
     * We store input as StringBuffer till user confirms.
     * i.e., he has not clicked on equals or any other operator.
     * Once the input is finalized the number will be pushed into a stack.
     * */
    private static StringBuffer tempNumber= new StringBuffer("");
    /**
     * Set to true when the last operator clicked is equals.
     * */
    private static boolean isEqualsLast;
    /**
     * Value of isDivide is set to true when user clicks on division. Zero is disabled to prevent the user
     * from entering zero. Once user enters an operand 0 is enabled again.
     * */
    private static Boolean isDivide = false;
    /**
     * If square root operation is performed +/- is disable and this value is set.
     * */
    private static Boolean isSqrt = false;
    /**
     * Method to get tempNumber value
     * @return  StringBuffer the value in tempNumber
     * */
    public StringBuffer getTempNumber() {
        return tempNumber;
    }
    /**
     * Method to set a given value to tempNumber attribute.
     * @param  tempNumber StringBuffer contains the value to be assigned.
     * */
    public void setTempNumber(StringBuffer tempNumber) {
        this.tempNumber = tempNumber;
    }
    /**
     * returns the value of isEqualsLast
     * @return boolean returns true/false depending upon the value set.
     * */
    public static boolean getIsEqualsLast() {
        return isEqualsLast;
    }
    /**
     * sets the value of isEqualsLast to the given value
     * @param isEqualsLast boolean isEqualsLast true if equals is the last key to be clicked
     * */
    public static void setIsEqualsLast(boolean isEqualsLast) {
        CalculatorUtility.isEqualsLast = isEqualsLast;
    }
    /**
     * Everytime a digit is clicked adds it at the end of the number.
     * @param digit int gives the digit clicked.
     * */
    public void addDigit(char digit){
        if(tempNumber.toString().equals("0") && digit=='0'){//Do not add multiple unwanted zeros to the input e.g., 00000000 should be avoided.
            return;
        }
        if(getIsEqualsLast()){// Digit entered after equals, queue should be cleared.
            OperationsClass operClass = new OperationsClass();
            operClass.clearQueue();
            this.clearCurrent();
            setIsEqualsLast(false);
        }
        if(isDivide){//Zero was disabled to avoid error. User has given a first digit now we can enable zero.
            isDivide = false;
            MainActivity.zero.setEnabled(true);
        }
        if(tempNumber == null) {
            tempNumber = new StringBuffer();
        }
        if(digit=='.'){
            if(tempNumber.indexOf(".")>=0){ // Allows only one decimal point
                return;
            } else if(tempNumber==null || tempNumber.length() ==0){
                tempNumber.append("0");
            }
        }
        if(tempNumber.length()<10 ){
            tempNumber.append(digit);
        }
    }
    /**
     * Clears the string buffer there by making it empty again.
     * Used to clear screen
     * */
    public void clearCurrent(){
        if(tempNumber!=null) {
            tempNumber.delete(0, tempNumber.length());
        }
        OperationsClass operClass = new OperationsClass();
        if(operClass.getOperationQueue()!=null && !operClass.getOperationQueue().isEmpty()){//If the operator we are working on is divide.
            if(operClass.getOperationQueue().getLast().equals("\u00F7")) {
                isDivide = true;
                MainActivity.zero.setEnabled(false);
            }
            else if (operClass.getOperationQueue().getLast().equals("\u221a")){
                isSqrt = true;
                MainActivity.plusMinus.setEnabled(false);
            }
        }

    }
    /**
     * Toggles the sign of an operand when clicked.
     * @return void
     * */
    public void toggleSigns(){
        if(tempNumber != null && tempNumber.length()!=0){
            if(tempNumber.charAt(0)=='-'){
                tempNumber.delete(0,1);
            } else{
                tempNumber.insert(0,'-');
            }
        }
    }
    /**
     * Adds the given operand and the operator to the queue when a operator appears.
     * @param  operator String represents the operator to be added to the queue
     * @return String returns a string to be set in the operations pane
     * */
    public String addOperator(String operator){
        if(getIsEqualsLast()){// is equals initialized to false to prevent the queue from getting cleared
            setIsEqualsLast(false);
        }
        //To prevent Divide by zero
        if(operator.equals("\u00F7")){//Division operation disable zero
            isDivide = true;
            MainActivity.zero.setEnabled(false);
        } else if(isDivide == true){
            isDivide = false;
            MainActivity.zero.setEnabled(true);
        }
        //To handle negative square root
        if(operator.equals(String.valueOf('\u221a'))){//Division operation disable zero
            isSqrt = true;
            MainActivity.plusMinus.setEnabled(false);
        } else if(isSqrt == true){
            isSqrt = false;
            MainActivity.plusMinus.setEnabled(true);
        }
        OperationsClass operClass = new OperationsClass();
        if(!getTempNumber().toString().isEmpty()) {
            operClass.pushOperand(getTempNumber().toString());
        }else if(operClass.getOperationQueue()!=null && operClass.getOperationQueue().isEmpty() && !operator.equals("\u221a")){
            operClass.pushOperand("0");
        }
        clearCurrent();
        return operClass.pushOperand(operator);
    }
    public void handleEquals(){
        OperationsClass operClass = new OperationsClass();
        operClass.pushOperand(getTempNumber().toString());
        String operResult =operClass.getResult();
        MainActivity.zero.setEnabled(true);
        MainActivity.plusMinus.setEnabled(true);
        if(operResult!=null){
            setTempNumber(new StringBuffer(operResult));
        }
        setIsEqualsLast(true);//Equals is the last value to be added
    }
    public String handleAllClear(){
        OperationsClass operationsClass = new OperationsClass();
        operationsClass.clearQueue();
        this.clearCurrent();
        MainActivity.zero.setEnabled(true);
        MainActivity.plusMinus.setEnabled(true);

        return operationsClass.getString();
    }
}