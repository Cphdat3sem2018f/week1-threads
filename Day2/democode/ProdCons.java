/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cos.mavenproject7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import sun.security.provider.NativePRNG;

/**
 *
 * @author bladt
 */
public class ProdCons {

    static BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<Integer>(5);

    static Runnable producer = () -> {

        Random rnd = new Random();
        try {
            while (true) {
                int rndnum = rnd.nextInt();
                sharedQueue.put(rndnum);
            }
        } catch (Exception e) {
            System.out.println("nope");
        }
    };

    static Runnable consumer = () -> {
        try {
            while (true) {
                int num = sharedQueue.take();

                System.out.println(Math.sqrt(num));
            }
        } catch (Exception e) {
            System.out.println("Nope, men fra consumer");
        }

    };

    public static void main(String[] args) throws InterruptedException {
        int size = 3;
        Thread[] consumers = new Thread[size];
        Thread[] producers = new Thread[size];

        for (int i = 0; i < size; i++) {
            consumers[i] = new Thread(consumer);
            producers[i] = new Thread(producer);

        }

        for (Thread t : consumers) {
            t.start();
        }

        for (Thread t : producers) {
            t.start();

        }

    }

}
