package comp1110.ass2.gui;


import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayerSetting extends JFrame implements ActionListener {

    public static JComboBox comboBox;
    public static JComboBox comboBoxP1;
    public static JComboBox comboBoxP2;
    public static JComboBox comboBoxP3;
    public static JComboBox comboBoxP4;
    public static int i = 100;

    PlayerSetting() {
        this.setTitle("Players' information");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(7, 1, 2, 6));
        this.setLocation(400, 200);

        JPanel ChoosePlayerNumber = new JPanel();
        ChoosePlayerNumber.setLayout(new GridLayout(1, 2));
        JLabel label1 = new JLabel(" Select number of players: ");
        ChoosePlayerNumber.add(label1);
        String[] SelectNumber = {"2 Players", "3 Players", "4 Players"};
        comboBox = new JComboBox(SelectNumber);
        comboBox.addActionListener(this);
        ChoosePlayerNumber.add(comboBox);
        this.add(ChoosePlayerNumber);

        JPanel Tips = new JPanel();
        Tips.setLayout(new GridLayout(1, 2));
        JLabel Tip1 = new JLabel(" Want to set player 1 as a computer?");
        Tips.add(Tip1);
        JLabel Tip2 = new JLabel("Type in player's name or Choose one ");
        Tips.add(Tip2);
        this.add(Tips);

        OnePlayer();
        TwoPlayer();
        ThreePlayer();
        FourPlayer();


        JPanel GameStart = new JPanel();
        GameStart.setLayout(new GridLayout(1, 2));
        JLabel label2 = new JLabel(" Do you prepared to start the game?");
        GameStart.add(label2);
        String[] SelectNameOfPlayer1 = {"No, I still need to prepare", "Yes, please start", "I don't want to play"};
        comboBox = new JComboBox(SelectNameOfPlayer1);
        comboBox.addActionListener(this);
        GameStart.add(comboBox);
        this.add(GameStart);

        this.pack();
        this.setVisible(true);
    }

    public void TwoPlayer() {
        JPanel ChoosePlayer1 = new JPanel();
        ChoosePlayer1.setLayout(new GridLayout(1, 2));
        String[] SelectKind = {"Player 2", "Computer Player 2"};
        comboBox = new JComboBox(SelectKind);
        comboBox.addActionListener(this);
        ChoosePlayer1.add(comboBox);
        String[] SelectNameOfPlayer1 = {"Type in", "Player 2"};
        comboBoxP1 = new JComboBox(SelectNameOfPlayer1);
        comboBoxP1.setEditable(true);
        comboBoxP1.addActionListener(this);
        ChoosePlayer1.add(comboBoxP1);
        this.add(ChoosePlayer1);
    }

    public void OnePlayer() {
        JPanel ChoosePlayer2 = new JPanel();
        ChoosePlayer2.setLayout(new GridLayout(1, 2));
        JLabel label2 = new JLabel(" You : ");
        ChoosePlayer2.add(label2);
        String[] SelectNameOfPlayer2 = {"Type in", "Player 1"};
        comboBoxP2 = new JComboBox(SelectNameOfPlayer2);
        comboBoxP2.setEditable(true);
        comboBoxP2.addActionListener(this);
        ChoosePlayer2.add(comboBoxP2);
        this.add(ChoosePlayer2);
    }

    public void ThreePlayer() {
        JPanel ChoosePlayer3 = new JPanel();
        ChoosePlayer3.setLayout(new GridLayout(1, 2));
        String[] SelectKind = {"Player 3", "Computer Player 2"};
        comboBoxP3 = new JComboBox(SelectKind);
        comboBoxP3.addActionListener(this);
        ChoosePlayer3.add(comboBoxP3);
        String[] SelectNameOfPlayer3 = {"Type in", "Player 3"};
        comboBoxP3 = new JComboBox(SelectNameOfPlayer3);
        comboBoxP3.setEditable(false);
        comboBoxP3.setEnabled(false);
        comboBoxP3.addActionListener(this);
        ChoosePlayer3.add(comboBoxP3);
        this.add(ChoosePlayer3);
    }

    public void FourPlayer() {
        JPanel ChoosePlayer4 = new JPanel();
        ChoosePlayer4.setLayout(new GridLayout(1, 2));
        String[] SelectKind = {"Player 4", "Computer Player 3"};
        comboBoxP4 = new JComboBox(SelectKind);
        comboBoxP4.addActionListener(this);
        ChoosePlayer4.add(comboBoxP4);
        String[] SelectNameOfPlayer4 = {"Type in", "Player 4"};
        comboBoxP4 = new JComboBox(SelectNameOfPlayer4);
        comboBoxP4.addActionListener(this);
        comboBoxP4.setEditable(false);
        comboBoxP4.setEnabled(false);
        ChoosePlayer4.add(comboBoxP4);
        this.add(ChoosePlayer4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String mes = (String) cb.getSelectedItem();

        //follow the choose, make the comboBox and text disabled.
        if (mes.equals("2 Players")) {
            i = 2;
        }
        if (mes.equals("3 Players")) {
            i = 3;
        }
        if (mes.equals("4 Players")) {
            i = 4;
        }
        if (i == 2) {
            comboBoxP3.setEditable(false);
            comboBoxP3.setEnabled(false);
            comboBoxP4.setEditable(false);
            comboBoxP4.setEnabled(false);
        }
        if (i == 3) {
            comboBoxP3.setEditable(true);
            comboBoxP3.setEnabled(true);
            comboBoxP4.setEditable(false);
            comboBoxP4.setEnabled(false);
        }
        if (i == 4) {
            comboBoxP3.setEditable(true);
            comboBoxP3.setEnabled(true);
            comboBoxP4.setEditable(true);
            comboBoxP4.setEnabled(true);
        }

        //choose to start the game or exit
        if (mes.equals("Yes, please start")) {
            Viewer viewer = new Viewer();
            viewer.setupViewer();
            //new Viewer().close();
            //new Viewer().middleprocess();
            //new Viewer().setupViewer();
            //new Viewer().start_page();
        }
        if (mes.equals("I don't want to play")) {
            System.exit(0);
        }

        //future plan
        if (mes.equals("Computer Player 1")) {

            //"Yes, please start" link to computer player
        }
        if (mes.equals("Computer Player 2")) {

            //"Yes, please start" link to computer player
        }
        if (mes.equals("Computer Player 3")) {

            //"Yes, please start" link to computer player
        }
        //if (mes.equals("Player 1")) {

            //"Yes, please start" link to Human player
        //}

    }

    public void start(Stage primaryStage) throws Exception {
        new PlayerSetting();
    }

    public static void main(String[] args) {
        new PlayerSetting();
        //launch(args);
    }

}
