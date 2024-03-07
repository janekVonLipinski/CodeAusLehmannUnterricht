package TrousProjekt.VektorUndMatrix.MatrizenVerfahren;

import TrousProjekt.VektorUndMatrix.Matrix;

public class MatrixMultiplikator {

    public Matrix multipliziere(Matrix m1, Matrix m2) {
        Matrix ergebnis = new Matrix(new double[m1.getAnzahlZeilen()][m2.getAnzahlSpalten()]);

        if (m1.getAnzahlSpalten() != m2.getAnzahlZeilen()) {
            throw new IllegalArgumentException(
                    "Zeilen von linker Matrix müssen geleich den Spalten der zweiten sein");
        }

        for (int i = 0; i < ergebnis.getAnzahlZeilen(); i++) {
            for (int j = 0; j < ergebnis.getAnzahlSpalten(); j++) {
                ergebnis.getMatrix()[i][j] = 0;
                for (int k = 0; k < m1.getAnzahlZeilen(); k++) {
                    ergebnis.getMatrix()[i][j] += (m1.getMatrix()[i][k] * m2.getMatrix()[k][j]);
                }
            }
        }

        return ergebnis;
    }
}
