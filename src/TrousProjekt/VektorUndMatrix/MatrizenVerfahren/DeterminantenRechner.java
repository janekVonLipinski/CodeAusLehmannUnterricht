package TrousProjekt.VektorUndMatrix.MatrizenVerfahren;

public class DeterminantenRechner {

    protected double getDeterminante(Matrix dieseMatrix) {
        int anzahlZeilen = dieseMatrix.getAnzahlZeilen();
        int anzahlSpalten = dieseMatrix.getAnzahlSpalten();
        double[][] matrix = dieseMatrix.getMatrix();
        double ergebnis = 0;

        if (anzahlZeilen != anzahlSpalten) {
            throw new IllegalArgumentException("Matrix muss n x n sein");
        }

        if (anzahlZeilen == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
        }

        for (int i = 0; i < anzahlSpalten; i++) {
            Matrix verkleinerteMatrix = getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(dieseMatrix, i);
            double zunahme = Math.pow(-1, i) * matrix[0][i] * getDeterminante(verkleinerteMatrix);
            ergebnis += zunahme;
        }

        return ergebnis;
    }


    private Matrix getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(Matrix ursprungsMatrix, int spalte) {
        int anzahlSpalten = ursprungsMatrix.getAnzahlSpalten();
        int anzahlZeilen = ursprungsMatrix.getAnzahlZeilen();
        double[][] matrix = ursprungsMatrix.getMatrix();

        double[][] neueMatrix = new double[anzahlZeilen - 1][anzahlSpalten - 1];
        double[][] alteMatrix = matrix.clone();

        int rowAlteMatrix = 1;
        for (int rowNeueMatrix = 0; rowNeueMatrix < neueMatrix.length; rowNeueMatrix++) {
            int colAlteMatrix = 0;
            for (int colNeueMatrix = 0; colNeueMatrix < neueMatrix.length; colNeueMatrix++) {
                if (colAlteMatrix == spalte) {
                    colAlteMatrix++;
                }
                neueMatrix[rowNeueMatrix][colNeueMatrix] = alteMatrix[rowAlteMatrix][colAlteMatrix];
                colAlteMatrix++;
            }
            rowAlteMatrix++;
        }
        return new Matrix(neueMatrix);
    }
}
