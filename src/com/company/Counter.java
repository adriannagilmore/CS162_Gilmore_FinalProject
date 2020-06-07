package com.company;

import processing.core.PApplet;
import processing.core.PConstants;

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

    public void display(String displayText, int count, int x, int y, PApplet p) {
        p.fill(0);
        p.textSize(40);
        p.textAlign(PConstants.CENTER);
        p.text(displayText +": " + count, x, y);
    }
}
