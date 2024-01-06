package TrousProjekt.VektorUndMatrix;

public class Vektor {

    private final double x;
    private final double y;
    private final double z;

    public Vektor(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vektor skaliere(double skalar) {
        return new Vektor(x * skalar, y * skalar, z * skalar);
    }
    public Vektor skaliere(Vektor skalar) {
        return new Vektor(x * skalar.x, y * skalar.y, z * skalar.z);
    }

    public Vektor addiere(Vektor vektor) {
        return new Vektor(x + vektor.x, y + vektor.y, z + vektor.z);
    }

    public double getSkalarprodukt(Vektor vektor) {
        return x * vektor.x + y * vektor.y + z * vektor.z;
    }

    public double getLaenge() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getWinkel(Vektor vektor) {
        return Math.acos(this.getSkalarprodukt(vektor) / (this.getLaenge() * vektor.getLaenge()));
    }

    public void normiere() {
        this.skaliere(this.getLaenge());
    }

    public boolean istGleich(Vektor vektor) {
        return x == vektor.x && y == vektor.y && z == vektor.z;
    }

    public Vektor multipliziere(Matrix matrix) {
        if (matrix.getAnzahlSpalten() != 3 || matrix.getAnzahlZeilen() != 3) {
            throw new IllegalArgumentException("Matrix muss 3 Kreuz 3 sein");
        }

        double[][] vektorAlsArray = {{this.x}, {this.y}, {this.z}};
        Matrix vektorAlsMatrix = new Matrix(3,1);
        vektorAlsMatrix.setMatrix(vektorAlsArray);

        Matrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);
        double[][] array = neueMatrix.getMatrix();
        return new Vektor(array[0][0], array[1][0], array[2][0]);
    }

    @Override
    public String toString() {
        return "Vektor{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
