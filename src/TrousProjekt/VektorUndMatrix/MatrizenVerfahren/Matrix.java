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

    public Matrix(Matrix m) {
        this.matrix = Arrays.stream(m.matrix).map(double[]::clone).toArray(double[][]::new);
        this.anzahlSpalten = m.anzahlSpalten;
        this.anzahlZeilen = m.anzahlZeilen;
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

    public boolean istGleich(Matrix m) {
        if (anzahlSpalten != m.anzahlSpalten || anzahlZeilen != m.anzahlZeilen) {
            return false;
        }
        for (int i = 0; i < anzahlSpalten; i++) {
            for (int j = 0; j < anzahlSpalten; j++) {
                if (matrix[i][j] != m.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix multipliziere(Matrix m) {
        return new MatrixMultiplikator().multipliziere(this, m);
    }

    public double getDeterminante() {
        return determinantenRechner.getDeterminante(this);
    }



    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}
