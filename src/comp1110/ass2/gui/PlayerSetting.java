package comp1110.ass2.gui;

import comp1110.ass2.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//PlayerSetting just used to set the information, and have no function about generate new pages
public class PlayerSetting extends JFrame implements ActionListener, Constants {

    public static JComboBox comboBoxP0;
    public static JComboBox comboBoxP1;
    public static JComboBox comboBoxP11;
    public static JComboBox comboBoxP2;
    public static JComboBox comboBoxP3;
    public static JComboBox comboBoxP31;
    public static JComboBox comboBoxP4;
    public static JComboBox comboBoxP41;
    public static JComboBox comboBoxP5;
    public static int i = 100;

    PlayerSetting() {
        this.setTitle("Players' information");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(8, 1, 2, 6));
        this.setLocation(400, 200);

        JPanel tips = new JPanel();
        tips.setLayout(new GridLayout(1, 1));
        JLabel label0 = new JLabel("  Important: Please submit the information first. If you choose submit, you can start  ");
        this.add(label0);

        JPanel ChoosePlayerNumber = new JPanel();
        ChoosePlayerNumber.setLayout(new GridLayout(1, 2));
        JLabel label1 = new JLabel(" Select number of players: ");
        ChoosePlayerNumber.add(label1);
        String[] SelectNumber = {"2 Players", "3 Players", "4 Players"};
        comboBoxP0 = new JComboBox(SelectNumber);
        comboBoxP0.addActionListener(this);
        ChoosePlayerNumber.add(comboBoxP0);
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
        JLabel label2 = new JLabel(" Do you want to submit the information? ");
        GameStart.add(label2);
        String[] SelectStart = {"No, I still need to think", "Yes, submit", "I don't want to play"};
        comboBoxP5 = new JComboBox(SelectStart);
        comboBoxP5.addActionListener(this);
        GameStart.add(comboBoxP5);
        this.add(GameStart);

        //JButton submit = new JButton("submit");
        //submit.setActionCommand("showMeViewer");
        //submit.addActionListener(this);
        //this.add(submit);

        this.pack();
        this.setVisible(true);
    }

    public void TwoPlayer() {
        JPanel ChoosePlayer1 = new JPanel();
        ChoosePlayer1.setLayout(new GridLayout(1, 2));
        String[] SelectKind = {"Human", "Computer"};
        comboBoxP11 = new JComboBox(SelectKind);
        comboBoxP11.addActionListener(this);
        ChoosePlayer1.add(comboBoxP11);
        String[] SelectNameOfPlayer1 = {"Player 2"};
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
        String[] SelectNameOfPlayer2 = {"Player 1"};
        comboBoxP2 = new JComboBox(SelectNameOfPlayer2);
        comboBoxP2.setEditable(true);
        comboBoxP2.addActionListener(this);
        ChoosePlayer2.add(comboBoxP2);
        this.add(ChoosePlayer2);
    }

    public void ThreePlayer() {
        JPanel ChoosePlayer3 = new JPanel();
        ChoosePlayer3.setLayout(new GridLayout(1, 2));
        String[] SelectKind = {"Human", "Computer"};
        comboBoxP31 = new JComboBox(SelectKind);
        comboBoxP31.addActionListener(this);
        comboBoxP31.setEnabled(false);
        ChoosePlayer3.add(comboBoxP31);
        String[] SelectNameOfPlayer3 = {"Player 3"};
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
        String[] SelectKind = {"Human", "Computer"};
        comboBoxP41 = new JComboBox(SelectKind);
        comboBoxP41.addActionListener(this);
        comboBoxP41.setEnabled(false);
        ChoosePlayer4.add(comboBoxP41);
        String[] SelectNameOfPlayer4 = {"Player 4"};
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
            comboBoxP31.setEnabled(false);
            comboBoxP4.setEditable(false);
            comboBoxP4.setEnabled(false);
            comboBoxP41.setEnabled(false);
        }
        if (i == 3) {
            comboBoxP3.setEditable(true);
            comboBoxP3.setEnabled(true);
            comboBoxP31.setEnabled(true);
            comboBoxP4.setEditable(false);
            comboBoxP4.setEnabled(false);
            comboBoxP41.setEnabled(false);
        }
        if (i == 4) {
            comboBoxP3.setEditable(true);
            comboBoxP3.setEnabled(true);
            comboBoxP31.setEnabled(true);
            comboBoxP4.setEditable(true);
            comboBoxP4.setEnabled(true);
            comboBoxP41.setEnabled(true);
        }

        //choose to start the game or exit
        if (mes.equals("Yes, submit")) {
            getPlayerName();
            getChoosePlayerNumber();
            getPlayerType();
        }
        if (mes.equals("I don't want to play")) {
            System.exit(0);
        }

        //if (e.getActionCommand().equals("showMeViewer")){
            //new Viewer().setupViewer();
        //}

    }

    //We got the information about player name and type, we show it in game Viewer
    //We choose the computer players in what we choose
    //We choose what kind of information about player numbers we got, to generate the state
    //If we got player number 3, we go to make an ArrayList and just have ArrayList[0,player number)

    public void getPlayerType(){
        String Player1Type = "Human";
        System.out.println(Player1Type);
        String Player2Type = String.valueOf(comboBoxP11.getSelectedItem());
        System.out.println(Player2Type);
        String Player3Type = String.valueOf(comboBoxP31.getSelectedItem());
        System.out.println(Player3Type);
        String Player4Type = String.valueOf(comboBoxP41.getSelectedItem());
        System.out.println(Player4Type);
        //System.out.println(Player4Type);
    }

    public void getChoosePlayerNumber(){
        int ChoosePlayerNumber = comboBoxP0.getSelectedIndex()+2;
        System.out.println(ChoosePlayerNumber);
    }


    public static void getPlayerName(){
        String PlayerA = String.valueOf(comboBoxP2.getSelectedItem());
        String PlayerB = String.valueOf(comboBoxP1.getSelectedItem());
        String PlayerC = String.valueOf(comboBoxP3.getSelectedItem());
        String PlayerD = String.valueOf(comboBoxP4.getSelectedItem());
        Game.playerNames.add(PlayerA);
        Game.playerNames.add(PlayerB);
        Game.playerNames.add(PlayerC);
        Game.playerNames.add(PlayerD);
        System.out.println(PlayerA);
        System.out.println(PlayerB);
        System.out.println(PlayerC);
        System.out.println(PlayerD);
    }

    public static void main(String[] args) {
        new PlayerSetting();
    }




}
