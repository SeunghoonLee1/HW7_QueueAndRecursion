package edu.miracosta.cs113.printerQueue;

/**
 * PrinterDriver.java : This class is a program to simulate processing 100 print jobs of varying lengths using one, two, or three printers.
 * The system printers are able to print 10 pages per minute. Smaller print jobs are printed before larger print jobs,
 * and print jobs of the same priority are queued up in the order in which they are received.
 * Assume that a print request is made every minute and that the number of pages to print varies from 1 to 50 pages.
 * The output from your program should indicate the order in which the jobs were received, the order in which they were printed,
 * and the time required to process the set of print jobs. If more than one printer is being used, indicate which printer each job was printed on.
 *
 * @author Danny Lee
 * @version 1.0
 */

import java.util.Random;
import java.util.LinkedList;

public class PrinterDriver {
    /*
     *Algorithm
     *Create a number of printers according to the variable, numberOfPrinters.
     *Assign print jobs that varies 1 to 50 pages to the printers.
     *Use for loop to loop through the printing process (MAXIMUM_RUNTIME times.).
     *   A print request is made every iteration of for loop(every minute).
     *   All printers prints if there is a print job in its printer queue.
     *   If a print job is done, print out the printer name and the print job.(Display the pages printed for that print job).
     *
     *Display the total time required to process the set of print jobs.
     *
     */
    public static void main(String[] args) {
        int numberOfPrinters = 3;
        final int MAXIMUM_PRINTJOBS = 100;
        final int MAXIMUM_RUNTIME = 500;    //when all inputs are 50(Worst case).
        int printer1NextPrintJob = 0;
        int printer2NextPrintJob = 0;
        int printer3NextPrintJob = 0;

        boolean isPrinter1JobDone = true;
        boolean isPrinter2JobDone = true;
        boolean isPrinter3JobDone = true;

        Random randomPrintJobInput = new Random();
        int totalNumberOfPrintJobs = 100;

        int[] printJobList = new int[MAXIMUM_PRINTJOBS];
        LinkedList<Integer> printer1JobList = new LinkedList<>();
        LinkedList<Integer> printer2JobList = new LinkedList<>();
        LinkedList<Integer> printer3JobList = new LinkedList<>();

        PrinterQueue<Integer> printer1 = null;
        PrinterQueue<Integer> printer2 = null;
        PrinterQueue<Integer> printer3 = null;

        //Set the lower and upper limits of printers depending on the number of printers made(3,2 or 1)
        if (numberOfPrinters == 3) {
            for (int i = 0; i < totalNumberOfPrintJobs; i++) {
                int printJob = randomPrintJobInput.nextInt(50) + 1;
                System.out.println("Print job " + i + " : " + printJob + "pages");

                printJobList[i] = printJob;

                if (printJob >= 1 && printJob <= 9) {
                    printer1JobList.add(printJob);
                } else if (printJob >= 10 && printJob <= 19) {
                    printer2JobList.add(printJob);
                } else if (printJob >= 20) {
                    printer3JobList.add(printJob);
                }
            }

            printer1 = new PrinterQueue<Integer>(1, 9);
            printer2 = new PrinterQueue<Integer>(10, 19);
            printer3 = new PrinterQueue<Integer>(20, 50);

        }//end of case when the number of printers is 3.
        else if(numberOfPrinters == 2){
            for (int i = 0; i < totalNumberOfPrintJobs; i++) {
                int printJob = randomPrintJobInput.nextInt(50) + 1;
                System.out.println("Print job " + i + " : " + printJob + "pages");

                printJobList[i] = printJob;

                if (printJob >= 1 && printJob <= 9) {
                    printer1JobList.add(printJob);
                } else if (printJob >= 10 && printJob <= 50) {
                    printer2JobList.add(printJob);
                }
            }
            printer1 = new PrinterQueue<Integer>(1, 9);
            printer2 = new PrinterQueue<Integer>(10, 50);
        }//end of case when the number of printers is 2.
        else if(numberOfPrinters == 1){
            for (int i = 0; i < totalNumberOfPrintJobs; i++) {
                int printJob = randomPrintJobInput.nextInt(50) + 1;
                System.out.println("Print job " + i + " : " + printJob + "pages");
                printJobList[i] = printJob;
                printer1JobList.add(printJob);
            }
            printer1 = new PrinterQueue<Integer>(1, 50);
        }//end of case when the number of printers is 1.

        for(int i = 0; i < MAXIMUM_RUNTIME; i++){
            //Print request made every minute.
            if(i < MAXIMUM_PRINTJOBS){
                if(numberOfPrinters == 3){
                    printer1.printerQueue.offer(printJobList[i]);
                    printer2.printerQueue.offer(printJobList[i]);
                    printer3.printerQueue.offer(printJobList[i]);
                }else if(numberOfPrinters == 2){
                    printer1.printerQueue.offer(printJobList[i]);
                    printer2.printerQueue.offer(printJobList[i]);
                }else if(numberOfPrinters == 1){
                    printer1.printerQueue.offer(printJobList[i]);
                }
            }

            //Printing process.
            //Printer A
            if(!(printer1.printerQueue.isEmpty())) {
                if (!(printer1.printerQueue.isEmpty()) && (isPrinter1JobDone)) {
                    printer1NextPrintJob = printer1.printerQueue.peek();
                    isPrinter1JobDone = false;
                }

                printer1NextPrintJob = printer1.print(printer1NextPrintJob);

                if (printer1NextPrintJob <= 0) {
                    isPrinter1JobDone = true;

                    if (isPrinter1JobDone && printer1.printerQueue.peek() != null) {
                        System.out.println("[Printer A]. Printer job done! : " + printer1JobList.poll() + "pages.");

                        printer1.printerQueue.poll();//wrote this instead of line above.
                    }
                }//end of printerA
            }//end of isPrinter1Empty.

            //Printer B
            if(numberOfPrinters != 1) {
                if (!(printer2.printerQueue.isEmpty())) {
                    if (!(printer2.printerQueue.isEmpty()) && (isPrinter2JobDone)) {
                        printer2NextPrintJob = printer2.printerQueue.peek();
                        isPrinter2JobDone = false;
                    }

                    printer2NextPrintJob = printer2.print(printer2NextPrintJob);
                    if (printer2NextPrintJob <= 0) {
                        isPrinter2JobDone = true;

                        if (isPrinter2JobDone && printer2.printerQueue.peek() != null) {
                            System.out.println("[Printer B]. Printer job done! : " + printer2JobList.poll() + "pages.");

                            printer2.printerQueue.poll();//wrote this instead of line above.

                        }

                    }//end of printerB
                }//end of isPrinter2Empty.
            }
            //Printer C
            if(numberOfPrinters == 3) {
                if (!(printer3.printerQueue.isEmpty())) {
                    if (!(printer3.printerQueue.isEmpty()) && (isPrinter3JobDone)) {
                        printer3NextPrintJob = printer3.printerQueue.peek();
                        isPrinter3JobDone = false;
                    }

                    printer3NextPrintJob = printer3.print(printer3NextPrintJob);
                    if (printer3NextPrintJob <= 0) {
                        isPrinter3JobDone = true;

                        if (isPrinter3JobDone && printer3.printerQueue.peek() != null) {
                            System.out.println("[Printer C]. Printer job done! : " + printer3JobList.poll() + "pages.");

                            printer3.printerQueue.poll();
                        }

                    }//end of printerC
                }//end of isPrinter3Empty.
            }
        }//end of for loop.

        //Display the results.
        if(numberOfPrinters == 3){
            System.out.println("\nTotal time spent to print the set of print jobs in printerA : " + printer1.printerQueue.getPrintTime() + " minutes.");
            System.out.println("Total time spent to print the set of print jobs in printerB : " + printer2.printerQueue.getPrintTime() + " minutes.");
            System.out.println("Total time spent to print the set of print jobs in printerC : " + printer3.printerQueue.getPrintTime() + " minutes.");
        }else if(numberOfPrinters == 2){
            System.out.println("\nTotal time spent to print the set of print jobs in printerA : " + printer1.printerQueue.getPrintTime() + " minutes.");
            System.out.println("Total time spent to print the set of print jobs in printerB : " + printer2.printerQueue.getPrintTime() + " minutes.");
        }else if(numberOfPrinters == 1){
            System.out.println("\nTotal time spent to print the set of print jobs in printerA : " + printer1.printerQueue.getPrintTime() + " minutes.");

        }

    }//end of main
}//end of class
