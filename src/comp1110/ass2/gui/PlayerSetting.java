package comp1110.ass2.gui;

import comp1110.ass2.Constants;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Si Bo, Hu
 * PlayerSetting is the first page, players use it to fill the information include: player number, names, kind,
 * It has no function, just get information and provide to viewer page then going to show them.
**/
public class PlayerSetting extends JFrame implements ActionListener, Constants {

    public static Game game;
    public static Viewer viewer;
    public static int PLAYER_NUMBER;
    public static boolean game_starts = false;
    public static ArrayList<String> playerNamesSetting = new ArrayList<>();
    public static ArrayList<Character> playerTypesSetting = new ArrayList<>();

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
        JLabel label0 = new JLabel("  If you do not choose, you will play with one other people  ");
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
        String[] SelectStart = {"No, I still need to think", "Yes, submit"};
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
        String[] SelectKind = {HUMAN_STRING, COMPUTER_STRING};
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
        String[] SelectKind = {HUMAN_STRING, COMPUTER_STRING};
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
        String[] SelectKind = {HUMAN_STRING, COMPUTER_STRING};
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
            getChoosePlayerNumber();
            getPlayerName();
            getPlayerType();
            game_starts = true;
            setVisible(false);
            dispose();
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
        String Player1Type = HUMAN_STRING;
        String Player2Type = String.valueOf(comboBoxP11.getSelectedItem());
        String Player3Type = String.valueOf(comboBoxP31.getSelectedItem());
        String Player4Type = String.valueOf(comboBoxP41.getSelectedItem());
        char PlayType1 = typeToChar(Player1Type);
        char PlayType2 = typeToChar(Player2Type);
        char PlayType3 = typeToChar(Player3Type);
        char PlayType4 = typeToChar(Player4Type);
        playerTypesSetting.clear();
        playerTypesSetting.add(PlayType1);
        playerTypesSetting.add(PlayType2);
        if (PLAYER_NUMBER>=3){
            playerTypesSetting.add(PlayType3);
            if (PLAYER_NUMBER>=4){
                playerTypesSetting.add(PlayType4);
            }
        }
    }

    public char typeToChar(String player_type){
        if(player_type.equals(HUMAN_STRING)){
            return HUMAN_PLAYER;
        }
        else if(player_type.equals(COMPUTER_STRING)){
            return COMPUTER_PLAYER;
        }
        else{
            return ' ';
        }

    }

    public void getChoosePlayerNumber(){
        int ChoosePlayerNumber = comboBoxP0.getSelectedIndex()+2;
        PLAYER_NUMBER = ChoosePlayerNumber;
        //System.out.println(ChoosePlayerNumber);
    }


    public static void getPlayerName(){
        String PlayerA = String.valueOf(comboBoxP2.getSelectedItem());
        String PlayerB = String.valueOf(comboBoxP1.getSelectedItem());
        String PlayerC = String.valueOf(comboBoxP3.getSelectedItem());
        String PlayerD = String.valueOf(comboBoxP4.getSelectedItem());
        playerNamesSetting.clear();
        playerNamesSetting.add(PlayerA);
        playerNamesSetting.add(PlayerB);
        if (PLAYER_NUMBER>=3){
            playerNamesSetting.add(PlayerC);
            if (PLAYER_NUMBER>=4){
                playerNamesSetting.add(PlayerD);
            }
        }
    }

    public static void main(String[] args) {
        new PlayerSetting();
    }

}
