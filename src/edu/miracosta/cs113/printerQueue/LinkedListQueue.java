package edu.miracosta.cs113.printerQueue;

/**
 *LinkedListQueue.java : This class implements the built in Queue interface with a LinkedList instantiation to accomplish
 * the project.
 * @author Danny Lee
 * @version 1.0
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

public class LinkedListQueue implements Queue<Integer> {

    private Node<Integer> head;
    private Node<Integer> tail;
    private int size;
    private int lowerLimit;
    private int upperLimit;
    private String printerName;
    private int printTime;

    private LinkedList<Integer> printJobList = null;

    /**
     * Full Constructor, specifying data of a Queue.
     * @param lowerLimit smallest amount of pages the printer can print.
     * @param upperLimit biggest amount of pages the printer can print.
     */
    public LinkedListQueue(int lowerLimit, int upperLimit){
        printJobList = new LinkedList();
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        size = 0;
        printTime = 0;
        head = null;
        tail = null;
        if(lowerLimit == 1){
            printerName = "printerA";
        }else if(lowerLimit == 10){
            printerName = "printerB";
        }else if(lowerLimit == 20){
            printerName = "printerC";
        }
    }

    /**
     * Default Constructor
     */
    public LinkedListQueue(){
        printJobList = new LinkedList<Integer>();
        this.lowerLimit = 0;
        this.upperLimit = 0;
        size = 0;
        printTime = 0;
        head= null;
        tail = null;
        printerName = "Unknown";
    }

    /**
     * getter method
     * @return printTIme returns printTIme instance variable.
     */
    public int getPrintTime(){
        return printTime;
    }

    /**
     * setter method
     * increments printTIme and sets it as a new printTime value.
     */
    public void addPrintTime(){
        printTime++;
    }



    /**
     * Inserts the specified element into this queue if it is possible to do so immediately without violating capacity
     * restrictions. Returning true upon success and throwing an IllegalStateException if no space is currently available.
     * @param printJobPages number of pages to add to printer queue.
     * @return true if add is successful.
     */
    @Override
    public boolean add(Integer printJobPages) {
        if(offer(printJobPages)){
            return true;
        }
        else{
            throw new IllegalStateException("Failed to add.");
        }
    }

    /**
     * Returns the entry at the front of the queue without removing it. If the queue is empty, throws a NoSuchElementException.
     * @return nextPrintJob : the first entry of the queue.
     */
    @Override
    public Integer element() {
        if(size == 0){
            throw new NoSuchElementException();
        }
        int nextPrintJob = head.numberOfPages;
        return nextPrintJob;
    }

    /**
     * Inserts item at the rear of the queue. Returns true if successful; returns false if the item could not be inserted.
     * @param printJobPages number of pages to insert at the rear of the queue.
     * @return true if inserting to the queue is successful.
     */
    @Override
    public boolean offer(Integer printJobPages) {
        if(printJobPages >= lowerLimit && printJobPages <= upperLimit) {
            if(head == null){//check for empty queue.
                tail = new Node<Integer>(printJobPages);
                head = tail;
            }else{
                //Allocate a new node at end, store item in it, and link it to old end of queue.
                tail.next = new Node<Integer>(printJobPages);
                tail = tail.next;
            }
            size++;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the entry at the front of the queue without removing it; returns null if the queue is empty.
     * @return nextPrintJob : the entry at the front of the queue.
     */
    @Override
    public Integer peek() {
        if(size == 0){
            return null;
        }
        int nextPrintJob = head.numberOfPages;
        return nextPrintJob;
    }

    /**
     * Removes the entry at the front of the queue and returns it; returns null if the queue is empty.
     * @return nextPrintJob : the first entry of the queue.
     */
    @Override
    public Integer poll() {
        if(head == null){
            return null;
        }else{
            int nextPrintJob = head.numberOfPages;
            head = head.next;
            size --;
            return nextPrintJob;
        }
    }

    /**
     * Removes the entry at the front of the queue and returns it if the queue is not empty.
     * If the queue is empty, throws a NoSuchElementException.
     * @return removedPrintJob : the first entry of the queue.
     */
    @Override
    public Integer remove() {
        int removedPrintJob = 0;
        if(isEmpty()){
            throw new NoSuchElementException();
        }else{
            removedPrintJob = head.numberOfPages;
            head = head.next;
            size--;
        }
        // head.next.previous = head;
        return removedPrintJob;
    }

    //*******************************************************************
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
    /**
     *Inner class Node
     */
    private static class Node<Integer>{
        private int numberOfPages;
        private int printTime;
        private Node<Integer> next;
        private boolean isPrinted;

        /**
         * Default constructor
         */
        public Node(){
            numberOfPages = 0;
            next = null;
            isPrinted = false;
        }

        /**
         * Constructor with one parameter
         * @param numberOfPages
         */
        public Node(int numberOfPages){

            this.numberOfPages = numberOfPages;

            printTime = 0;
            this.next = null;
            isPrinted = false;
        }


        /**
         * full constructor
         * @param numberOfPages
         * @param next
         */
        public Node(int numberOfPages, Node<Integer> next){
            this.numberOfPages = numberOfPages;
            this.next = next;
        }

        /**
         * Returns the data of the node.
         * @return string value of the data
         */
        public String toString(){
            if(numberOfPages == 0){
                return "no data";
            }else{
                return(numberOfPages + "");
            }
        }

        /**
         * Compares the specified object with this Node for equality.
         * @param anObject
         * @return
         */
        public boolean equals(Object anObject){
            if(anObject == null || getClass() != anObject.getClass()){
                return false;
            }
            Node aNode = (Node)anObject;
            return (numberOfPages == aNode.numberOfPages);
        }
    }//end of inner class Node

}