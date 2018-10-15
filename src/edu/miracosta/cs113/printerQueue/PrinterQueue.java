package edu.miracosta.cs113.printerQueue;

/**
 *PrinterQueue.java : This class creates printerQueue object which is a queue built with a linked list. It has a print method
 * that prints 10 pages from the print job and returns the pages left to print out. It increments print time by one in it.
 *
 * @author Danny Lee
 * @version 1.0
 */

public class PrinterQueue<Integer> {

    LinkedListQueue printerQueue;

    /**
     * Full Constructor, specifying the lowerLimit and upperLimit of a printer.
     * @param lowerLimit smallest amount of pages the printer can print.
     * @param upperLimit biggest amount of pages the printer can print.
     */
    public PrinterQueue(int lowerLimit, int upperLimit){
        printerQueue = new LinkedListQueue(lowerLimit, upperLimit);
    }

    /**
     * Default constructor, instantiates printerQueue object.
     */
    public PrinterQueue(){
        printerQueue = new LinkedListQueue(0, 0);
    }


    /**
     * Prints out 10 pages from the numberOfPages given through parameter.
     * @param numberOfPages represents the number of pages left to print.
     * @return (numberOfPages - 10) number of pages left to print. If it is negative, it means the print job is done.
     * If there was nothing to print, return 0.
     */
    public int print(int numberOfPages){

        if(numberOfPages > 0){//when the printerQueue is not empty.
            printerQueue.addPrintTime();
            if(numberOfPages <= 10){//finish that printing job
                return numberOfPages - 10;
            }else{
                return numberOfPages - 10;
            }
        }
        return 0;//if there is nothing to print.
    }
}