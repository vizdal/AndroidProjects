# Simple Android Calculator

## Introduction
The application is a simple android calculator that operates on two operators at a given time. This does not involve DMAS rules because we deal with only two numbers at a time. The application implements basic mathematical operations like addition, subtraction, multiplication, division and square root operation.
The application also supports clear and all clear function. It has a two level view which clearly presents the state of operations. The maximum support is 10 numbers.

## Installation Notes

* Android Studio: version 2.5.0
* JRE: 1.8.0
* Emulator Version: 27.1.10

## Java Classes

### Main Activity

Control of the application starts from the __MainActivity.java__ which associates clicks to all calculator functions.

### Constants Class 

Constants class stores the constant maps and various other constants that is used through out the application at different points in the code.
All variables in this class are public static and final. This is to allow usage without actual instantiation.

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
### Calculator Utility

This class is used to store the value obtained from the user temporarily. These classes have functions to add a character to an operand or event the operator.
Apart from handling all this it also handles operations. When ever an operator is pressing the control immediately is shifted to Operations Class.

### Operations Class

The operations class makes used of an __Array Dequeue__ to strore the operations and the respective operand. When ever the string length reached three
all the elements in the deque are popped out an the result of the calculation is stored in the deque again.
