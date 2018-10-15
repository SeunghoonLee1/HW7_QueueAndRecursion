package edu.miracosta.cs113.printerQueue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PrinterQueueTester {

    private PrinterQueue<Integer> queue;

    /*HELPER METHOD*/
    public void setLowerUpperLimits(int lowerLimit, int upperLimit){
        queue = new PrinterQueue<Integer>(lowerLimit, upperLimit);
    }

    @Before
    public void setup(){
        queue = new PrinterQueue<Integer>();
    }

    @Test
    public void testAddToQueue(){
        setLowerUpperLimits(1,9);
        assertTrue("Before adding, it should be empty.", queue.printerQueue.isEmpty());
        queue.printerQueue.add(5);
        assertEquals("First element of the queue should be 5.",5L, (long)queue.printerQueue.peek());
        queue.printerQueue.add(6);
        assertEquals("First element of the queue should still be 5.",5L, (long)queue.printerQueue.peek());
        assertEquals("The size of the queue should be 2.", 2, queue.printerQueue.size());
        try{
           queue.printerQueue.add(15);
           fail("Adding a number that is out of range(not in between the lower limit and upper limit) should have thrown IllegalStateException.");
        }catch(IllegalStateException e){
        }
    }

    @Test
    public void testElementFromAnEmptyQueue(){
        setLowerUpperLimits(1,9);
        int nextPrintJob = 0;
        try{
            nextPrintJob = queue.printerQueue.element();
            queue.print(nextPrintJob);
            fail("It's an empty queue. Element should have thrown NoSuchElementException!");
        }catch(NoSuchElementException e){
        }
    }

    @Test
    public void testElementFromNotEmptyQueue(){
        setLowerUpperLimits(1,9);
        queue.printerQueue.add(7);
        queue.printerQueue.add(8);
        int numberAtFront = queue.printerQueue.element();
        assertEquals("First element of the queue should be 7", 7,numberAtFront);
    }


    @Test
    public void testOfferToQueue(){
        setLowerUpperLimits(1,9);
        boolean isOfferSuccessful = false;

        assertTrue("Before adding, it should be empty.", queue.printerQueue.isEmpty());
        queue.printerQueue.offer(5);
        assertEquals("First element of the queue should be 5.",5L, (long)queue.printerQueue.peek());
        queue.printerQueue.offer(6);
        assertEquals("First element of the queue should still be 5.",5L, (long)queue.printerQueue.peek());
        assertEquals("The size of the queue should be 2.", 2, queue.printerQueue.size());

         isOfferSuccessful = queue.printerQueue.offer(15);
         assertFalse("Cannot offer a number that is out of range(not in between the lower limit and upper limit)", isOfferSuccessful);
    }

    @Test
    public void testPeekInQueue(){
        setLowerUpperLimits(1,9);
        assertEquals("Peek should have returned null because the printer queue is empty.", null, queue.printerQueue.peek());
        queue.printerQueue.add(1);
        queue.printerQueue.add(2);
        assertEquals("Peek should have returned the first element in the queue: 1.", 1L, (long)queue.printerQueue.peek());
    }

    @Test
    public void testPollFromQueue(){
        setLowerUpperLimits(1,9);
        assertEquals("Cannot poll from an empty queue: null", null, queue.printerQueue.poll());
        queue.printerQueue.add(1);
        queue.printerQueue.add(2);
        assertEquals("The first element polled should be 1.", 1L, (long)queue.printerQueue.poll());
        assertEquals("The first element polled should be 2.", 2L, (long)queue.printerQueue.poll());
    }

    @Test
    public void testRemoveFromEmptyQueue(){
        setLowerUpperLimits(1,9);
        try{
            queue.printerQueue.remove();
            fail("Remove from an empty stack should have thrown NoSuchElementException.");
        }catch(NoSuchElementException e){
        }
    }

    @Test
    public void testRemoveFromNotEmptyQueue(){
        setLowerUpperLimits(1,9);
        queue.printerQueue.add(1);
        queue.printerQueue.add(2);
        assertEquals("The first element removed should be 1.", 1L, (long)queue.printerQueue.remove());
        assertEquals("The first element removed should be 2.", 2L, (long)queue.printerQueue.remove());

    }

//***********************************************************************

    @Test
    public void testPrintEmptyQueue(){
        setLowerUpperLimits(1,9);
        int nextPrintJob = 0;
        try{
            nextPrintJob = queue.printerQueue.element();
            queue.print(nextPrintJob);
            fail("Element should have thrown NoSuchElementException!");
        }catch(NoSuchElementException e){
        }
    }

    @Test
    public void testPrintWithPrintJobLessThan10Pages(){
        setLowerUpperLimits(1,9);
        queue.printerQueue.offer(7);
        int printJobLeft = queue.print(queue.printerQueue.peek());
        assertTrue("Should return 0 or negative number.", printJobLeft <= 0);
    }

    @Test
    public void testPrintWithPrintJobBetween10To19Pages(){
        setLowerUpperLimits(10,19);
        queue.printerQueue.offer(15);
        int printJobLeft = queue.print(queue.printerQueue.peek());
        assertTrue("Should return 15 - 10 = 5 pages.", printJobLeft == 5);
    }

    @Test
    public void testPrintWithPrintJobBetween20To50Pages(){
        setLowerUpperLimits(20,50);
        queue.printerQueue.offer(37);
        int printJobLeft = queue.print(queue.printerQueue.peek());
        assertTrue("Should return 37 - 10 = 27 pages.", printJobLeft == 27);
    }

    @Test
    public void testPrintTimeWithPrintJobLessThan10Pages(){
        setLowerUpperLimits(1,9);
        queue.printerQueue.offer(7);
        int printJobLeft = queue.printerQueue.peek();
        while(printJobLeft > 0){
            printJobLeft = queue.print(printJobLeft);
        }
        assertEquals("Expected and actual print time don't match.", 1, queue.printerQueue.getPrintTime());
    }

    @Test
    public void testPrintTimeWithPrintJobBetween10To19Pages(){
        setLowerUpperLimits(10,19);
        queue.printerQueue.offer(17);
        int printJobLeft = queue.printerQueue.peek();
        while(printJobLeft > 0){
            printJobLeft = queue.print(printJobLeft);
        }
        assertEquals("Expected and actual print time don't match.", 2, queue.printerQueue.getPrintTime());
    }

    @Test
    public void testPrintTimeWithPrintJobBetween20To50Pages(){
        setLowerUpperLimits(20,50);
        queue.printerQueue.offer(37);
        int printJobLeft = queue.printerQueue.peek();
        while(printJobLeft > 0){
            printJobLeft = queue.print(printJobLeft);
        }
        assertEquals("Expected and actual print time don't match.", 4, queue.printerQueue.getPrintTime());
    }


}
