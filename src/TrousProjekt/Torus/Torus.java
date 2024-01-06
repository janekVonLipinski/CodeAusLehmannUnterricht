package TrousProjekt.Torus;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;

import javax.swing.*;
import java.awt.*;

public class Torus extends JPanel {

    private static final double r1 = 200;
    private static final double r2 = 100;
    static final int WIDTH = 1000;
    static final int HEIGHT = 800;

    private static double alpha;
    private static double beta;
    private static double gamma;
    private final Vektor[] punkte;
    private final Vektor[] normalen;

    public Torus(int a, int b) {
        this.punkte = new Vektor[4 * a * b];
        this.normalen = new Vektor[4 * a * b];
        getTorus(a, b);
    }

    public Vektor[] getPunkte() {
        return punkte;
    }

    public Vektor[] getNormalen() {
        return normalen;
    }

    public void getTorus(int a, int b) {

        final double deltaTheta = Math.PI / a;
        final double deltaPhi = Math.PI / b;
        int i = 0;

        for (double theta = 0; theta < 2 * Math.PI; theta += deltaTheta) {

            double cosTheta = Math.cos(theta);
            double sinTheta = Math.sin(theta);

            theta += deltaTheta;

            double nextCosTheta = Math.cos(theta + deltaTheta);
            double nextSinTheta = Math.sin(theta + deltaTheta);

            for (double phi = 0; phi < 2 * Math.PI; phi += deltaPhi) {

                double cosPhi = Math.cos(phi);
                double sinPhi = Math.sin(phi);

                int x1 = (int) ((r1 + r2 * cosTheta) * cosPhi);
                int y1 = (int) (r2 * sinTheta);
                int z1 = (int) (-(r1 + r2 * cosTheta) * sinPhi);

                double nx1 =  (cosTheta * cosPhi);
                double ny1 =  (sinTheta * cosPhi);
                double nz1 =  (sinPhi);

                Vektor n1 = new Vektor(nx1, ny1, nz1);
                normalen[i] = n1;

                Vektor v1 = new Vektor(x1, y1, z1);
                punkte[i++] = v1;

                int x2 = (int) ((r1 + r2 * nextCosTheta) * cosPhi);
                int y2 = (int) (r2 * nextSinTheta);
                int z2 = (int) (-(r1 + r2 * nextCosTheta) * sinPhi);

                double nx2 = (nextCosTheta * cosPhi);
                double ny2 = (nextSinTheta * cosPhi);
                double nz2 = (sinPhi);

                Vektor n2 = new Vektor(nx2, ny2, nz2);
                normalen[i] = n2;

                Vektor v2 = new Vektor(x2, y2, z2);
                punkte[i++] = v2;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(WIDTH / 2, HEIGHT / 2);
        Matrix drehMatrix = new Matrix(3,3)
                .getDrehMatrix(alpha, beta, gamma);

        for (int i = 0; i < 1598; i++) {

            int[] xs = new int[3];
            int[] zs = new int[3];

            for (int k = 0; k < 3; k++) {
                int x = (int) punkte[(i+k)].multipliziere(drehMatrix).getX();
                int y = (int) punkte[(i+k)].multipliziere(drehMatrix).getY();
                int z = (int) punkte[(i+k)].multipliziere(drehMatrix).getZ();

                xs[k] = x - y / 2;
                zs[k] = z - y / 2;
            }
            g.setColor(Color.BLACK);
            g.drawPolygon(xs, zs, 3);
        }
    }

    public void rotate() {
        while (true) {
            alpha += 0.01;
            beta += 0.02;
            gamma += 0.03;
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}

