package com.kenjohn;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEARS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal ($1K - $1M): ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate (1 - 30): ", 1, 30);
        byte period = (byte) readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterest, period);
        printPaymentSchedule(principal, annualInterest, period);

    }

    public static void printPaymentSchedule(int principal, float annualInterest, byte period) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");

        double balance = 0;

        for(short month = 0; month <= period * MONTHS_IN_YEARS; month++) {
            balance = calculateBalance(principal, annualInterest, period, month);
            System.out.println(convertToCurrency(balance));
        }
    }

    public static void printMortgage(int principal, float annualInterest, byte period) {
        double mortgage = calculateMortgage(principal, annualInterest, period);

        System.out.println("MORTGAGE");
        System.out.println("----------------");
        System.out.println("Monthly Payments: " + convertToCurrency(mortgage));
        System.out.println("");
    }

    public static double readNumber(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while(true){
            System.out.print(prompt);
            value = scanner.nextDouble();

            if(value >= min && value <= max)
                break;

            System.out.println("Enter a number between " + min + " and " + max);
        }

        return value;
    }

    public static double calculateMortgage(int principal, float annualInterest, byte period) {
        float monthlyInterest = (annualInterest / PERCENT) / MONTHS_IN_YEARS;
        int numberOfPayments = period * MONTHS_IN_YEARS;

        double mortgage = principal * (monthlyInterest * Math.pow((1 + monthlyInterest), numberOfPayments)) / ((Math.pow((1 + monthlyInterest), numberOfPayments) -1));

        return mortgage;
    }

    public static double calculateBalance(int principal, float annualInterest, int period, short numberOfPaymentsMade) {
        double balance = 0;

        float monthlyInterest = (annualInterest / PERCENT) / MONTHS_IN_YEARS;
        int numberOfPayments = period * MONTHS_IN_YEARS;

        balance = principal * ((Math.pow((1 + monthlyInterest), numberOfPayments) - Math.pow((1 + monthlyInterest), numberOfPaymentsMade)) / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1));

        return balance;
    }

    public static String convertToCurrency(double number) {
        String formattedNumber = NumberFormat.getCurrencyInstance().format(number);

        return formattedNumber;
    }



}
