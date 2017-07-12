import javax.swing.*;
import java.awt.event.*;

public class Bot extends JFrame {

    //create user entering textbox
    private JTextField txtEnter = new JTextField();

    //create char area
    private JTextArea txtArea = new JTextArea();

    //all the disorganized bot actions
    public Bot() {

        //create window & attributes
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Java ChatBot");
        this.setLayout(null);

        //user textbox attributes here
        txtEnter.setLocation(2, 540);
        txtEnter.setSize(590, 30);

        //chat area attributes
        txtArea.setLocation(15, 10);
        txtArea.setSize(560, 510);

        //add both items to window
        this.add(txtEnter);
        this.add(txtArea);

        botSay("Hi there! How may I help you today?");

        //txtArea action event
        txtEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create variable for user-entered text
                String uText = txtEnter.getText();
                //display user entered text to window
                txtArea.append("You: " + uText + "\n");

                //logic based on what the user enters
                if (uText.contains("Hi")) {
                    botSay("Hello there.");
                } else if (uText.contains("How are you?")) {
                    botSay("Not too bad");
                }
                else {
                    int decider = (int) (Math.random() * 3 + 1);
                    if(decider == 1) {
                        botSay("Sorry, I didn't get that");
                    } else if (decider == 2) {
                        botSay("Please rephrase that");
                    } else {
                        botSay("I don't understand, I'm sorry");
                    }
                }
                txtEnter.setText(null);
            }
        });
    }

    //helper method to display bot response
    private void botSay(String s) {
        txtArea.append("COUNTRY Financial: " + s + "\n");
    }

    //main method
    //right now this is only calling the Bot constructor, but we cold easily re-work the organization
    public static void main (String [] args) {
        new Bot();
    }
}