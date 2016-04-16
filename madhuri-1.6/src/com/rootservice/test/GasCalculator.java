package com.rootservice.test;

import java.util.Scanner;
public class GasCalculator {

    public static void main (String args[]) {

    	GasCalculator gc = new GasCalculator();
    	System.out.println(gc.getDurationString(11));
    }
    private String getDurationString(int n) {
    	double i = n/10;
    	return ""+i;
    }

}
