package com.company.matrixes;


import java.util.logging.Logger;

import static java.util.logging.Logger.*;

public class ParallelMatrixProduct {

    private int flows;
    private Product[] threads;
    private static Logger log;

    static {
        log = getLogger(ParallelVectorProduct.class.getName());
    }

    public class Product extends Thread {

        private IMatrix a;
        private IMatrix b;
        private IMatrix answer;
        private int start;
        private int finish;

        private Product(IMatrix a, IMatrix b, int start, int finish) {

            this.a = new UsualMatrix(0, 0);
            this.b = new UsualMatrix(0, 0);
            this.answer = new UsualMatrix(0, 0);
            this.a = a;
            this.b = b;
            this.start = start;
            this.finish = finish;

        }
        @Override
        public void run() {

            UsualMatrix answer1 = new UsualMatrix(a.getRows(), b.getColumns());
            for (int i = this.start; i < this.finish; i++) {
                for (int j = 0; j < b.getColumns(); j++) {
                    for (int y = 0; y < b.getRows(); y++) {
                        answer1.setElement(i, j, answer1.getElement(i, j) + a.getElement(i, y) * b.getElement(y, j));
                    }
                }
            }
            this.answer = answer1;

        }

        private IMatrix getResult() {

            return this.answer;

        }

        private int getStart() {

            return this.start;

        }

        private int getFinish() {

            return this.finish;

        }

    }


    public ParallelMatrixProduct(int flows) {

        this.flows = flows;
        this.threads = new Product[this.flows];

    }

    public IMatrix prod(IMatrix a, IMatrix b) {

        int step = a.getRows() / this.flows;
        int start = 0;
        int finish = step;
        int trueFlows = this.flows;
        IMatrix tmp;
        IMatrix answer = new UsualMatrix(a.getRows(), b.getColumns());
        if (step == 0) {
            finish = 1;
            step = 1;
            trueFlows = a.getRows();
        }
        for (int i = 0; i < trueFlows; i++) {
            this.threads[i] = new Product(a, b, start, finish);
            this.threads[i].start();
            start += step;
            if (finish + step >= a.getRows() - 1) {
                finish = a.getRows();
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
            log.info("Threads fail");
        }
        for (int i = 0; i < trueFlows; i++) {
            tmp = threads[i].getResult();
            for (int j = 0; j < (threads[i].getFinish() - threads[i].getStart()); j++) {
                for (int q = 0; q < answer.getColumns(); q++) {
                    answer.setElement(j + threads[i].getStart(), q, tmp.getElement(j + threads[i].getStart(), q));
                }
            }
        }
        return answer;

    }

}

