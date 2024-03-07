package TrousProjekt.VektorUndMatrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;

public class DeterminatenVerfahren implements LGSLoeser {

    @Override
    public Vektor loeseGleichungssystem(Matrix koeffizientenMatrix, Vektor loesungsVektor) {
        int anzahlZeilen = koeffizientenMatrix.getAnzahlZeilen();
        double[] xVektor = new double[anzahlZeilen];

        double determinanteVonKoeffizientenMatrix = koeffizientenMatrix.getDeterminante();

        for (int i = 0; i < anzahlZeilen; i++) {
            Matrix matrixMitEingetztemVektor = setzeVektorInMatrixEin(koeffizientenMatrix, loesungsVektor, i);
            xVektor[i] = matrixMitEingetztemVektor.getDeterminante() / determinanteVonKoeffizientenMatrix;
        }

        return new Vektor(xVektor);
    }

    protected Matrix setzeVektorInMatrixEin(Matrix koeffizientMatrix, Vektor loesungsVektor, int spaltenIndex) {
        Matrix matrix = new Matrix(koeffizientMatrix);
        double[][] matrixArray = matrix.getMatrix().clone();
        double[] loesungVektorArray = loesungsVektor.getVektorWerte();

        for (int i = 0; i < matrixArray.length; i++) {
                matrixArray[i][spaltenIndex] = loesungVektorArray[i];
        }

        return new Matrix(matrixArray);
    }
}
