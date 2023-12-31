import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Main extends JFrame {

    JButton addButton;
    JButton sortButton;
    JTextField textField1;
    JTextArea textArea1;

    List<MyDateClass> dates = new ArrayList<MyDateClass>();

    public static void main(String[] args) {
        // define an instance of the Main class (which is the window class)
        new Main();
    }

    public Main() {
        this.setSize(400, 400);
        this.setResizable(false);
        this.setTitle("my nice GUI");
        // put window in center
        this.setLocationRelativeTo(null);
        // exit when clicking close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // creating elements inside the window
        JPanel panel = new JPanel();


        // text of the button is passed in the constructor
        addButton = new JButton("Add");
        sortButton = new JButton("Sort");

        panel.add(addButton);
        panel.add(sortButton);


        // default text is first param and second is how many columns
        // text field = you can write in it
        textField1 = new JTextField("Type here", 15);
        panel.add(textField1);

        // text area (cannot write in it)
        textArea1 = new JTextArea(15, 22);
        textArea1.setLineWrap(true);
        // Makes sure that words stay intact if a line wrap occurs
        textArea1.setWrapStyleWord(true);

        panel.add(textArea1);

        ListenForButton lForButton = new ListenForButton();

        addButton.addActionListener(lForButton);
        sortButton.addActionListener(lForButton);
        // add the panel
        this.add(panel);

        this.setVisible(true);
    }

    private class ListenForButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // if addButton was clicked (the source of the event)
            if (e.getSource() == addButton) {
                // if the date format is okay
                if (MyDateClass.checkFormat(textField1.getText())) {
                    // create an object of MyDateClass
                    MyDateClass date = new MyDateClass(textField1.getText());

                    // add the new date to the list
                    dates.add(date);
                } else {
                    // show an error box
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong date format please use dd/MM/yyyy", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == sortButton) {

                // sort using O(n^2)
                // take element (i) if you got one smaller after it (j) swap
                for (int i = 0; i < dates.size(); i++) {
                    for (int j = i + 1; j < dates.size(); j++) {
                        if (dates.get(i).year > dates.get(j).year) {
                            MyDateClass tempDate = dates.get(i);
                            dates.set(i, dates.get(j));
                            dates.set(j, tempDate);
                        }
                    }
                }
            }

            // clear the text area always
            textArea1.setText("");
            // then fill it with the current list of dates we have (always if we add or sort or do nothing)
            for (
                    int i = 0; i < dates.size(); i++) {
                textArea1.append(dates.get(i).toString());

                // if it's not last element, add a new line
                if (i + 1 != dates.size()) {
                    textArea1.append("\n");
                }
            }

        }
    }
}

// not named Date to not use some java ready class name
class MyDateClass {
    int day;
    int month;
    int year;

    MyDateClass(String s) {
        // here we are sure it's correct format cause we check before creation
        String[] parts = s.split("/"); // split it into 3 strings (one for dd, one for MM and one for yyyy)
        this.day = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

    public String toString() {

        return String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
    }

    public static boolean checkFormat(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // match it precisely
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(s.trim());
        } catch (ParseException pe) {
            return false;
        }

        String[] parts = s.split("/");
        int year = Integer.parseInt(parts[2]);
        // extra check for year
        if(year > 9999 || year <= 0)
        {
            return false;
        }

        return true;
    }
}

