package TrousProjekt.VektorUndMatrix.MatrizenVerfahren;

public class MatrixMultiplikator {

    protected Matrix multipliziere(Matrix multiplikatorMatrix, Matrix multiplikandMatrix) {
        Matrix ergebnis = new Matrix(new double[multiplikatorMatrix.getAnzahlZeilen()][multiplikandMatrix.getAnzahlSpalten()]);

        if (multiplikatorMatrix.getAnzahlSpalten() != multiplikandMatrix.getAnzahlZeilen()) {
            throw new IllegalArgumentException(
                    "Zeilen von linker Matrix müssen geleich den Spalten der zweiten sein");
        }

        for (int row = 0; row < ergebnis.getAnzahlZeilen(); row++) {
            for (int col = 0; col < ergebnis.getAnzahlSpalten(); col++) {
                ergebnis.getMatrix()[row][col] = 0;
                for (int schritt = 0; schritt < multiplikatorMatrix.getAnzahlZeilen(); schritt++) {
                    ergebnis.getMatrix()[row][col] += (multiplikatorMatrix.getMatrix()[row][schritt] * multiplikandMatrix.getMatrix()[schritt][col]);
                }
            }
        }

        return ergebnis;
    }
}
