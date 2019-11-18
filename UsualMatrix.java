package com.company.matrixes;

public class UsualMatrix implements IMatrix {

    private int rows;
    private int columns;
    private int [][] matrix;

    public UsualMatrix(int i, int j) {
        rows = i;
        columns = j;
        matrix = new int [rows][columns];
    }

    public  void setElement(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public  int getElement(int row, int column) {
        return matrix[row][column];
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public IMatrix sum(IMatrix obj) {
        UsualMatrix tmp = (UsualMatrix) obj;
        UsualMatrix answer = new UsualMatrix(tmp.getRows(), tmp.getColumns());
        for (int i = 0; i < tmp.getRows(); i++) {
            for(int j = 0; j < tmp.getColumns(); j++) {
                answer.setElement(i, j, this.getElement(i, j) + tmp.getElement(i, j));
            }
        }
        return answer;
    }

    public IMatrix product(IMatrix obj) {
        UsualMatrix tmp = (UsualMatrix) obj;
        UsualMatrix answer = new UsualMatrix(this.getRows(), tmp.getColumns());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < tmp.getColumns(); j++) {
                for (int y = 0; y < tmp.getRows(); y++) {
                    answer.setElement(i, j, answer.getElement(i, j) + this.getElement(i, y) * tmp.getElement(y, j));
                }
            }
        }
        return answer;
    }

    public  boolean equals(IMatrix obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        UsualMatrix tmp = (UsualMatrix) obj;
        if ((this.rows != tmp.rows) || (this.columns != tmp.columns)) {
            return false;
        }
        for (int i = 0; i < tmp.rows; i++) {
            for (int j = 0; j < tmp.columns; j++) {
                if (this.getElement(i,j) != tmp.getElement(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                buffer.append(this.getElement(i, j)).append(" ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

}

