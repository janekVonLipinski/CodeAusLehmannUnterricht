package TrousProjekt.VektorUndMatrix.MatrixVerfahren;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;

import java.util.Arrays;

public class GaussAlgorithmus {

    public Vektor loeseGleichungssystem(Matrix koeffizientenMatrix, Vektor loesungsVektor) {

        if (koeffizientenMatrix.getAnzahlSpalten() != koeffizientenMatrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException("Matrix muss quadratisch sein");
        }

        double[][] matrixArray = koeffizientenMatrix.getMatrix().clone();
        double[] vektorArray = loesungsVektor.getVektorWerte().clone();

        double[][] dreiecksMatrix = getStufenForm(matrixArray, vektorArray);

        if (hatGleichungKeineEindeutigeLoesung(dreiecksMatrix, dreiecksMatrix.length -1)) {
            throw new IllegalArgumentException("Gleichung nicht eindeutig lösbar");
        }

        double[][] diagonalMatrix = getDiagonalMatrix(dreiecksMatrix, vektorArray);

        double[] loesung = getLoesung(vektorArray, diagonalMatrix);

        return new Vektor(loesung);
    }

    private boolean hatGleichungKeineEindeutigeLoesung(double[][] dreiecksMatrix, int groessterIndex) {
        return dreiecksMatrix[groessterIndex][groessterIndex] == 0;
    }

    private static double[] getLoesung(double[] vektorArray, double[][] diagonalMatrix) {
        double[] loesung = new double[diagonalMatrix.length];
        for (int i = 0; i < diagonalMatrix.length; i++) {
            loesung[i] = vektorArray[i] / diagonalMatrix[i][i];
        }
        return loesung;
    }

    private double[][] getStufenForm(double[][] matrix, double[] vektor) {

        for (int j = 0; j < matrix.length; j++) {
            int i = 1;

            while (matrix[j][j] == 0) {
                tausche(matrix, j, i);
                tausche(vektor, j, i++);
            }
            for (int k = 1; j + k < matrix.length; k++) {
                double[] zeileVonDerSubtrahiertWird = matrix[j + k];
                double[] zeileDieSubtrahiertWird = matrix[j];

                double koeffizient = getKoeffizient(
                        zeileDieSubtrahiertWird[j],
                        zeileVonDerSubtrahiertWird[j]
                );

                matrix[j + k] = subtrahiereZeile(zeileDieSubtrahiertWird,
                        zeileVonDerSubtrahiertWird, koeffizient);

                vektor[j + k] = vektor[j + k] - koeffizient * vektor[j];
            }
        }
        return matrix;
    }

    private double[][] getDiagonalMatrix(double[][] matrix, double[] vektor) {

        for (int j = matrix.length - 1; j >= 0; j--) {
            for (int k = 1; j - k >= 0; k++) {
                double[] zeileVonDerSubtrahiertWird = matrix[j-k];
                double[] zeileDieSubtrahiertWird = matrix[j];

                double koeffizient = getKoeffizient(
                        zeileDieSubtrahiertWird[j],
                        zeileVonDerSubtrahiertWird[j]
                );

                matrix[j - k] = subtrahiereZeile(zeileDieSubtrahiertWird,
                        zeileVonDerSubtrahiertWird,
                        koeffizient);

                vektor[j - k] = vektor[j - k] - koeffizient * vektor[j];
            }
        }
        return matrix;
    }

    private void tausche(double[][] matrixArray, int indexErsteZeile, int indexZweiteZeile) {

        double[] ersteZeile = matrixArray[indexErsteZeile];
        double[] zweiteZeile = matrixArray[indexZweiteZeile];

        matrixArray[indexErsteZeile] = zweiteZeile;
        matrixArray[indexZweiteZeile] = ersteZeile;
    }

    private void tausche(double[] vektor, int ersterIndex, int zweiterIndex) {
        double temp = vektor[ersterIndex];
        vektor[ersterIndex] = vektor[zweiterIndex];
        vektor[zweiterIndex] = temp;
    }

    private double[] subtrahiereZeile(double[] ersteZeile, double[] zweiteZeile,
                                        double koeffiezient) {

        double[] multiplizierteZeile = getMultiplizierteZeile(ersteZeile, koeffiezient);

        double[] returnArray = new double[ersteZeile.length];
        for (int i = 0; i < ersteZeile.length; i++) {
            returnArray[i] = zweiteZeile[i] - multiplizierteZeile[i];
        }

        return returnArray;
    }

    private double[] getMultiplizierteZeile(double[] zeile, double koeffizient)  {
        double[] copy = Arrays.copyOf(zeile, zeile.length);
        return Arrays.stream(copy)
                .map(i -> i * koeffizient)
                .toArray();
    }

    private double getKoeffizient(double ersterWert, double zweiterWert) {
        if (ersterWert == 0) {
            throw new ArithmeticException("Shit man, you have devided by 0");
        }
        return zweiterWert / ersterWert;
    }
}
