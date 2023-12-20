package Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixTest {

    @Test
    void multipliziere() {

        Matrix matrix1 = new Matrix(2,3);
        Matrix matrix2 = new Matrix(3, 2);
        Matrix test = new Matrix(2,2);
        Matrix test2 = new Matrix(3, 3);
        Matrix test3 = new Matrix(4,2);

        double[][] werte1 = {{1,1,1}, {1,1,1}};
        double[][] werte2 = {{2, 2}, {2,2}, {2,2}};
        double[][] testWerte = {{6,6}, {6,6}};
        double[][] testWerte2 = {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}};
        double[][] testWerte3 = {{0,0}, {0,0}, {0,0}, {0,0}};

        matrix1.setMatrix(werte1);
        matrix2.setMatrix(werte2);
        test3.setMatrix(testWerte3);
        test.setMatrix(testWerte);
        test2.setMatrix(testWerte2);


        assertTrue(matrix1.multipliziere(matrix2).istGleich(test));
        assertTrue(matrix2.multipliziere(matrix1).istGleich(test2));
        assertThrows(IllegalArgumentException.class, () -> test3.multipliziere(matrix2));
    }
}