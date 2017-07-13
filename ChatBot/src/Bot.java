import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Bot extends JFrame {

    //create user entering textbox
    private JTextField txtEnter = new JTextField();

    //create char area
    private JTextArea txtArea = new JTextArea();

    public void responses() {
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

    public void setUpScreen() {
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
    }

    //helper method to display bot response
    public void botSay(String s) {
        txtArea.append("COUNTRY Financial: " + s + "\n");
    }

    //METHOD 2: REQUIRES MAIN CLASS
    //gets greeting
    public String getGreeting() {
        return "Hi! How may I help you today?";
    }

    /**
     * Take a statement with "I want to <something>." and transform it into
     * "What would it mean to <something>?"
     */
    private String transformIWantToStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }

    /**
     * Take a statement with "I want <something>." and transform it into
     * "Would you really be happy if you had <something>?"
     */
    private String transformIWantStatement(String statement) {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals(".")) {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 6).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }

    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no").
     *
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @param startPos
     *            the character of the string to begin the
     *            search at
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos) {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in the line below
        int psn = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a word
        while (psn >= 0) {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0) {
                before = phrase.substring(psn - 1, psn);
            }
            if (psn + goal.length() < phrase.length()) {
                after = phrase.substring(
                        psn + goal.length(),
                        psn + goal.length() + 1);
            }

            // If before and after aren't letters, we've found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a letter
                    && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0))) {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal, psn + 1);
        }
        return -1;
    }

    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.
     */
    private int findKeyword(String statement, String goal) {
        return findKeyword (statement, goal, 0);
    }

    /**
     * Pick a default response to use if nothing else fits.
     */
    private String getRandomResponse () {
        Random r = new Random();
        return randomResponses [r.nextInt(randomResponses.length)];
    }

    private String [] randomResponses = {"I'm sorry I don't understand.",
            "Could you please rephrase that?",
            "I didn't catch that. Would you like to talk to a representative?"};
}