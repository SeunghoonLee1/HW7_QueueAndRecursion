package edu.miracosta.cs113.change;

/**
 * ChangeDriver.java : This class is a program to simulate dispensing change for a given amount of money.
 * It dispenses the highest coin first.(Quarters, then dimes, then nickels, then pennies).
 *
 * @author Danny Lee
 * @version 1.0
 */

public class ChangeDriver{
    /*
     *Algorithm
     *Create variables that would store number of each coins and an object of ChangeCalculator.
     *Display initial amount of coins by dispensing the highest coins first.(quarters, then dimes, then nickels, then pennies).
     *Display the total number of combinations possible.
     *Display total combinations of coins.
     *
     */
    public static void main(String[] args){

        //Case of dispensing 75 cents.
        int moneyAmount = 75;
        int moneyLeft = 0;
        int initialQuarterNumber = 0;
        int initialDimeNumber = 0;
        int initialNickelNumber = 0;
        int initialPennyNumber = 0;
        ChangeCalculator changeCalculator = new ChangeCalculator(moneyAmount);

        //Assume moneyAmount is greater than zero!
        System.out.println("Initial moneyAmount : " + moneyAmount + " cents.");

        initialQuarterNumber = moneyAmount / 25;
        moneyLeft = moneyAmount - initialQuarterNumber * 25;
        initialDimeNumber = moneyLeft / 10;
        moneyLeft = moneyLeft - initialDimeNumber * 10;
        initialNickelNumber = moneyLeft / 5;
        moneyLeft = moneyLeft - initialNickelNumber * 5;
        initialPennyNumber = moneyLeft;

        System.out.println("Initial Quarter number : " + initialQuarterNumber);
        System.out.println("Initial Dime number : " + initialDimeNumber);
        System.out.println("Initial Nickel number : " + initialNickelNumber);
        System.out.println("Initial Penny number : " + initialPennyNumber);

        changeCalculator.dispenseChange(moneyAmount, changeCalculator, initialQuarterNumber, initialDimeNumber,initialNickelNumber,initialPennyNumber);

        System.out.println("The total possible number of combinations : " + changeCalculator.getChangeCombinations().size());
        System.out.println("All possible combinations : ");

        for(int[] array : changeCalculator.getChangeCombinations()){
            System.out.println(array[0] + " Quarters. " + array[1] + " Dimes. " + array[2] + " Nickels. " + array[3] + " Pennies.");
        }

    }//end of main

}//end of class