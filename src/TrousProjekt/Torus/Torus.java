package TrousProjekt.Torus;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;

import javax.swing.*;
import java.awt.*;

public class Torus extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    private static final double radiusAussen = 200;
    private static final double radiusInnen = 100;
    private static final double deltaAlpha = 0.01;
    private static final double deltaBeta = 0.01;
    private static final double deltaGamma = 0.01;
    private static double alpha;
    private static double beta;
    private static double gamma;
    private final Vektor[] punkte;

    public Torus(int anzahlPunkteAussen, int anzahlPunkteInnen, int width, int height) {
        this.punkte = new Vektor[4 * anzahlPunkteAussen * anzahlPunkteInnen];
        getTorus(anzahlPunkteAussen, anzahlPunkteInnen);
        WIDTH = width;
        HEIGHT = height;
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

                int x1 = (int) ((radiusAussen + radiusInnen * cosTheta) * cosPhi);
                int y1 = (int) (radiusInnen * sinTheta);
                int z1 = (int) (-(radiusAussen + radiusInnen * cosTheta) * sinPhi);

                Vektor v1 = new Vektor(x1, y1, z1);
                punkte[i++] = v1;

                int x2 = (int) ((radiusAussen + radiusInnen * nextCosTheta) * cosPhi);
                int y2 = (int) (radiusInnen * nextSinTheta);
                int z2 = (int) (-(radiusAussen + radiusInnen * nextCosTheta) * sinPhi);

                Vektor v2 = new Vektor(x2, y2, z2);
                punkte[i++] = v2;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(WIDTH / 2, HEIGHT / 2);
        Matrix drehMatrix = Matrix.getDrehMatrix(alpha, beta, gamma);

        for (int i = 0; i < punkte.length - 2; i++) {

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
            alpha += deltaAlpha;
            beta += deltaBeta;
            gamma += deltaGamma;
            this.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
