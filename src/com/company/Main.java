/**
 * @author Adrianna Gilmore
 * @version 1
 * CS162 - Final Project
 * This program is a simulation of a zombie outbreak.
 * The model will include a group of zombie and a group of humans,
 * when they collide zombies may defeat humans, they may turn humans, or humans may defeat
 * the zombies. The outcomes are based on probability.
 *
 */
package com.company;

import processing.core.PApplet;

public class Main {
    private final static String[] OPTIONS= new String[] {"--present","Sketch"};

    public static void main(String[] args) {
        PApplet.main(OPTIONS);
    }
}
