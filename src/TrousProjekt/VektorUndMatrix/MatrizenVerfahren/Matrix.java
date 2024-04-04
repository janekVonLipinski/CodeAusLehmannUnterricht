package TrousProjekt.VektorUndMatrix.MatrizenVerfahren;

import java.util.Arrays;

public class Matrix {

    private final double[][] matrix;
    private final int anzahlZeilen;
    private final int anzahlSpalten;
    private final DeterminantenRechner determinantenRechner = new DeterminantenRechner();

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        anzahlSpalten = matrix[0].length;
        anzahlZeilen = matrix.length;
    }

    public Matrix(Matrix andereMatrix) {
        this.matrix = Arrays.stream(andereMatrix.matrix).map(double[]::clone).toArray(double[][]::new);
        this.anzahlSpalten = andereMatrix.anzahlSpalten;
        this.anzahlZeilen = andereMatrix.anzahlZeilen;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getAnzahlSpalten() {
        return anzahlSpalten;
    }

    public int getAnzahlZeilen() {
        return anzahlZeilen;
    }

    public boolean istGleich(Matrix andereMatrix) {
        if (anzahlSpalten != andereMatrix.anzahlSpalten || anzahlZeilen != andereMatrix.anzahlZeilen) {
            return false;
        }
        for (int row = 0; row < anzahlSpalten; row++) {
            for (int col = 0; col < anzahlSpalten; col++) {
                if (matrix[row][col] != andereMatrix.matrix[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix multipliziere(Matrix andereMatrix) {
        return new MatrixMultiplikator().multipliziere(this, andereMatrix);
    }

    public double getDeterminante() {
        return determinantenRechner.getDeterminante(this);
    }



    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}
