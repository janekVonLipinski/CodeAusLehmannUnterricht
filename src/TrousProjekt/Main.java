package TrousProjekt;

import TrousProjekt.Torus.Torus;

import javax.swing.*;

public class Main extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;


    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(WIDTH, HEIGHT);

        Torus torus = new Torus(20, 20, WIDTH, HEIGHT);

        window.setVisible(true);

        window.add(torus);
        torus.rotate();
    }
}