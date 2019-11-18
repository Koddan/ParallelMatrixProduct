package com.company;

import com.company.matrixes.*;

import java.util.ArrayList;

import static java.lang.System.*;


public class Main {

    public static void main(String[] args) {

        /*IMatrix a = new UsualMatrix(3, 3);
        IMatrix b = new UsualMatrix(3, 3);
        IMatrix tmp = new UsualMatrix(3, 3);
        ParallelMatrixProduct c = new ParallelMatrixProduct(2);

        a.setElement(0,0,3);
        a.setElement(0,1,1);
        a.setElement(2,2,15);
        a.setElement(1,0,6);
        a.setElement(1,1,2);
        System.out.println("Матрица а:\n"+a);

        b.setElement(0,0,10);
        b.setElement(0,1,1);
        b.setElement(2,2,4);
        b.setElement(1,1,3);
        System.out.println("Матрица b:\n"+b);

        tmp = c.prod(a, b);
        System.out.println("Многопоточное умножение:\n"+tmp);
        a = a.product(b);
        System.out.println("Обычное умножение:\n"+a);

        IMatrix a1 = new UsualMatrix(1000, 1000);
        IMatrix b1 = new UsualMatrix(1000, 1000);
        IMatrix tmp1 = new UsualMatrix(1000, 1000);
        IMatrix tmp2 = new UsualMatrix(1000, 1000);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                a1.setElement(i, j, (int)Math.random() * 10);
            }
        }

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                b1.setElement(i, j, (int)Math.random() * 10);
            }
        }
        out.println("Сравнение многопоточного и обычного умножения матриц 1000х1000");
        for (int i = 1; i < 6; i++) {
            ParallelMatrixProduct c1 = new ParallelMatrixProduct(i);
            System.out.println("\nЧисло потоков - "+i);
            long start = System.currentTimeMillis();
            tmp1 = c1.prod(a1, b1);
            long finish = System.currentTimeMillis();
            System.out.println("Параллельное умножение - "+(finish - start));
            start = System.currentTimeMillis();
            tmp2 = a1.product(b1);
            finish = System.currentTimeMillis();
            System.out.println("Обычное умножение - "+(finish - start));
        }*/

        ParallelVectorProduct c = new ParallelVectorProduct(2);

        ArrayList<Integer> vector1 = new ArrayList<>(5);
        vector1.add(4);
        vector1.add(1);
        vector1.add(3);
        vector1.add(5);
        vector1.add(1);

        IMatrix b = new UsualMatrix(5, 3);
        b.setElement(0,0,3);
        b.setElement(0,1,1);
        b.setElement(1,0,6);
        b.setElement(1,1,2);
        b.setElement(2,2,15);
        b.setElement(0,2,3);

        b = c.prod(vector1, b);
        //System.out.println(b);



        ArrayList<Integer> vector = new ArrayList<>(100000);
        for (int j = 0; j < 100000; j++) {
            vector.add((int)Math.random() * 10);
        }

        IMatrix a = new UsualMatrix(100000, 2000);
        IMatrix answer;
        answer = new UsualMatrix(0, 0);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 2000; j++) {
                a.setElement(i, j, (int) (Math.random() * 10));
            }
        }
        out.println("Многопоточное умножение вектора длиной (100000) на матрицу (100000х2000)");
        for (int i = 1; i < 6; i++) {
            ParallelVectorProduct c1 = new ParallelVectorProduct(i);
            out.println("Число потоков:" + i);
            long start = currentTimeMillis();
            answer = c1.prod(vector, a);
            long finish = currentTimeMillis();
            out.println(finish - start);
        }

    }

}











