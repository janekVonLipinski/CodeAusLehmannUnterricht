package TrousProjekt;

import TrousProjekt.Torus.Torus;

import javax.swing.*;

public class Main extends JPanel {


    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(1000, 800);

        Torus torus = new Torus(20, 20);

        window.setVisible(true);

        window.add(torus);
        torus.rotate();
    }
}