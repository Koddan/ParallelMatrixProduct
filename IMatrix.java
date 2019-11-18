package com.company.matrixes;


public interface IMatrix {

    void setElement(int row, int column, int value);
    int getElement(int row, int column);
    int getRows();
    int getColumns();
    IMatrix product(IMatrix obj);
}
