package edu.miracosta.cs113.change;

/**
 *ChangeCalculator.java : This class uses ArrayList that has arrays in it to dispense money and store the combinations.
 *
 * @author Danny Lee
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Arrays;


public class ChangeCalculator{

    int moneyAmount;
    int moneyLeft = 0;
    int initialQuarterNumber = 0;
    int initialDimeNumber = 0;
    int initialNickelNumber = 0;
    int initialPennyNumber = 0;
    private static ArrayList<int[]> changeCombinations = new ArrayList<int[]>();

    /**
     * Full constructor
     * @param moneyAmount amount of money that the user wants to dispense.
     */
    public ChangeCalculator(int moneyAmount){
        this.initialQuarterNumber = moneyAmount / 25;
        moneyLeft = moneyAmount - initialQuarterNumber * 25;
        this.initialDimeNumber = moneyLeft / 10;
        moneyLeft = moneyLeft - initialDimeNumber * 10;
        this.initialNickelNumber = moneyLeft / 5;
        moneyLeft = moneyLeft - initialNickelNumber * 5;
        this.initialPennyNumber = moneyLeft;
    }

    /**
     * getter method
     * @return changeCombination : an ArrayList<int[]> that stores the combinations of coins.
     */
    public ArrayList<int[]> getChangeCombinations(){
        return changeCombinations;
    }

    /**
     * A recursive method that finds unique combinations of coins
     * @param moneyAmount amount of money left to dispense
     * @param changeCalculator an object of ChangeCalculator
     * @param numberOfQuarters number of quarters that has been dispensed previously
     * @param numberOfDimes number of dimes that has been dispensed previously.
     * @param numberOfNickels number of nickels that has been dispensed previously.
     * @param numberOfPennies number of pennies that has been dispensed previously.
     */
    public void dispenseChange(int moneyAmount, ChangeCalculator changeCalculator, int numberOfQuarters, int numberOfDimes, int numberOfNickels, int numberOfPennies){

        //Store combination
        storeCombination(numberOfQuarters, numberOfDimes, numberOfNickels, numberOfPennies);

        //Base case
        if((numberOfNickels == 0 && numberOfPennies >= 5) ||
                numberOfQuarters * 25 + numberOfDimes * 10 + numberOfNickels * 5 + numberOfPennies > moneyAmount){//how about if numberOfQuarters,Dimes,Nickels == 0 and Pennies != 0??
            return;
        }

        //Recursive cases
        if(numberOfNickels > 0){//Break nickels into pennies
            dispenseChange(moneyAmount, changeCalculator, numberOfQuarters, numberOfDimes, numberOfNickels - 1, numberOfPennies + 5);
        }

        if(numberOfPennies >= 5){
            return;
        }

        if(numberOfDimes > 0){//Break dime into nickels
            dispenseChange(moneyAmount, changeCalculator, numberOfQuarters, numberOfDimes - 1, numberOfNickels + 2, numberOfPennies);
        }

        if(numberOfQuarters > 0){
            if(numberOfNickels >= 1){
                dispenseChange(moneyAmount, changeCalculator, numberOfQuarters - 1, numberOfDimes + 3, numberOfNickels - 1, numberOfPennies);
            }else{
                dispenseChange(moneyAmount, changeCalculator, numberOfQuarters - 1, numberOfDimes + 2, numberOfNickels + 1, numberOfPennies);
            }
        }
    }//end of dispenseChange().


    /**
     * stores combinations of the coins into an ArrayList. If the combination already exists, do add into the ArrayList.
     * @param quarters number of quarters in the unique combination.
     * @param dimes number of dimes in the unique combination.
     * @param nickels number of nickels in the unique combination.
     * @param pennies number of pennies in the unique combination.
     */
    public void storeCombination(int quarters, int dimes, int nickels, int pennies){
        int[] changeCombination = {quarters, dimes, nickels, pennies};

        for(int[] combination : changeCombinations){
            if(Arrays.equals(changeCombination, combination)){
                return;
            }
        }
        changeCombinations.add(changeCombination);
    }

}//end of class