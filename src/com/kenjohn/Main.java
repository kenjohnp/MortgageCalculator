package com.kenjohn;

public class Main {

    public static void main(String[] args) {
         int principal = (int)Console.readNumber("Principal ($1K - $1M): ", 1000, 1_000_000);
         float annualInterest = (float)Console.readNumber("Annual Interest (1 - 30): ", 1, 30);
         byte period = (byte)Console.readNumber("Period (Years): ", 1, 30);

         var calculator = new MortgageCalculator(principal, annualInterest, period);
         var report = new MortgageReport(calculator);
         report.printMortgage();
         report.printPaymentSchedule();
    }

}
