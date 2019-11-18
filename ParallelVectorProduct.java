package com.company.matrixes;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParallelVectorProduct {

    private int flows;
    private Product[] threads;
    private static Logger logger = Logger.getLogger(ParallelVectorProduct.class.getName());

    public static class Product extends Thread {

        private ArrayList<Integer> a;
        private IMatrix b;
        private IMatrix answer;
        private int start;
        private int finish;

        Product(ArrayList<Integer> a, IMatrix b, int start, int finish) {

            this.a = new ArrayList<>(0);
            this.b = new UsualMatrix(0, 0);
            this.answer = new UsualMatrix(0, 0);
            this.a = a;
            this.b = b;
            this.start = start;
            this.finish = finish;

        }

        @Override
        public void run() {

            UsualMatrix answer1 = new UsualMatrix(1, b.getColumns());
            for (int y = 0; y < b.getColumns(); y++) {
                for (int i = this.start; i < this.finish; i++) {
                    answer1.setElement(0, y, answer1.getElement(0, y) + a.get(i) * b.getElement(i, y));
                }
            }
            this.answer = answer1;
        }

        private IMatrix getResult() {

            return this.answer;

        }

    }

    public ParallelVectorProduct(int flows) {

        this.flows = flows;
        this.threads = new Product[this.flows];

    }

    public IMatrix prod(ArrayList<Integer> a, IMatrix b) {

        int step = a.size() / this.flows;
        int start = 0;
        int finish = step;
        int trueFlows = this.flows;
        IMatrix tmp;
        IMatrix answer = new UsualMatrix(1, b.getColumns());
        if (step == 0) {
            finish = 1;
            step = 1;
            trueFlows = a.size();
        }
        for (int i = 0; i < trueFlows; i++) {
            this.threads[i] = new Product(a, b, start, finish);
            this.threads[i].start();
            start += step;
            if (finish + step >= a.size() - 1) {
                finish = a.size();
            }
            else {
                finish += step;
            }
        }
        try {
            for (int i = 0; i < trueFlows; i++) {
                threads[i].join();
            }
        }
        catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
        for (int i = 0; i < trueFlows; i++) {
            tmp = threads[i].getResult();
            for (int q = 0; q < answer.getColumns(); q++) {
                answer.setElement(0, q, answer.getElement(0, q) + tmp.getElement(0, q));
            }
        }
        return answer;

    }

}
