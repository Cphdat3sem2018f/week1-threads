/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cos.mavenproject7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bladt
 */
public class MonitorExample {

    /**
     * @param args the command line arguments
     */
    static boolean valueIsSet = false;
    static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (monitor) {
                System.out.println("t1 starts");
                while(!valueIsSet) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("Something bad happened");
                    }
                }
                System.out.println("t1 can now use value, and terminate");
            }
        });
        t1.start();
        Thread.sleep(1500);
        synchronized (monitor) {
            value = 42;
            valueIsSet = true;
            monitor.notify();
        }
    }

}
