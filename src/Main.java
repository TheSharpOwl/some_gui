import java.awt.*;
import javax.swing.*;


public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        this.setSize(400,400);
        this.setResizable(false);
        this.setTitle("my nice GUI");
        // put window in center
        this.setLocationRelativeTo(null);
        // exit when clicking close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // creating elements inside the window
        JPanel panel = new JPanel();


        // text of the button is passed in the constructor
        JButton button1 = new JButton("Sort");
        // hide the button border todo remove
        button1.setBorderPainted(false);
        // remove filling of the button
        button1.setContentAreaFilled(false);

        panel.add(button1);



        // add the panel

        this.add(panel);

        this.setVisible(true);
    }
}

