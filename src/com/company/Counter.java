package com.company;

public class Counter {
    int startNum;

    public Counter() {
        startNum = 0;
    }

    public Counter(int startNum) {
        this.startNum = startNum;
    }

    public int increaseByOne() {
        return startNum += 1;
    }

    public int decreaseByOne() {
        return startNum -= 1;
    }

    //work on actually displaying this in draw screen
    public String display(String displayText) {
        return displayText +": " + startNum;
    }
}
