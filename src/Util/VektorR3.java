package Util;

public class VektorR3 {

    //Klasse zum erstellen eines Vektors im R3
    private final double x;
    private final double y;
    private final double z;

    public VektorR3(double x, double y, double z) {
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

    public VektorR3 skaliere(double skalar) {
        return new VektorR3(x * skalar, y * skalar, z * skalar);
    }
    public VektorR3 skaliere(VektorR3 skalar) {
        return new VektorR3(x * skalar.x, y * skalar.y, z * skalar.z);
    }

    public VektorR3 addiere(VektorR3 vektorR3) {
        return new VektorR3(x + vektorR3.x, y + vektorR3.y, z + vektorR3.z);
    }

    public double getSkalarprodukt(VektorR3 vektorR3) {
        return x * vektorR3.x + y * vektorR3.y + z * vektorR3.z;
    }

    public double getLaenge() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getWinkel(VektorR3 vektorR3) {
        return Math.acos(this.getSkalarprodukt(vektorR3) / (this.getLaenge() * vektorR3.getLaenge()));
    }

    public boolean istGleich(VektorR3 vektorR3) {
        return x == vektorR3.x && y == vektorR3.y && z == vektorR3.z;
    }

    public VektorR4 getVektorR4() {
        return new VektorR4(x, y, z, 1);
    }

    public VektorR3 multipliziere(Matrix matrix) {
        //zu kompliziert
        if (matrix.getAnzahlSpalten() != 3 || matrix.getAnzahlZeilen() != 3) {
            throw new IllegalArgumentException("Matrix muss 3 Kreuz 3 sein");
        }

        double[][] vektorAlsArray = {{this.x}, {this.y}, {this.z}};
        Matrix vektorAlsMatrix = new Matrix(3,1);
        vektorAlsMatrix.setMatrix(vektorAlsArray);

        Matrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);
        double[][] array = neueMatrix.getMatrix();
        return new VektorR3(array[0][0], array[1][0], array[2][0]);
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
