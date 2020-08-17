package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class QuizPlayer {
    private JFrame frame;
    private JTextArea display;
    private JButton button;
    private ArrayList<QuizCard> cardlist = new ArrayList<>();
    private QuizCard currentCard;
    private boolean showAnswer;
    private int currentCardIndex;

    public void go(){
        frame = new JFrame("Quiz Card Player");
        JPanel panel = new JPanel();
        Font font = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10,20);
        display.setFont(font);
        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        button = new JButton("SHow Question");

        panel.add(scrollPane);
        panel.add(button);

        button.addActionListener(new ShowQuestionActionListener());

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
        loadMenuItem.addActionListener(new LoadCardSetActionListener());

        menu.add(loadMenuItem);
        menubar.add(menu);

        frame.setJMenuBar(menubar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(640,500);
        frame.setVisible(true);




    }

    public class ShowQuestionActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(showAnswer){
                display.setText(currentCard.getAnswer());
                button.setText("Next Card");
                showAnswer = false;
            }
            else{
                if(currentCardIndex<cardlist.size()){
                    showNextCard();
                }
                else{
                    display.setText("No more cards!!");
                }
            }

        }
    }

    public class LoadCardSetActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(frame);
            loadfile(jf.getSelectedFile());
        }
    }

    public void loadfile(File file){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) !=null){
                makeCard(line);
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void makeCard(String str){
        String []list = str.split("/");
        QuizCard card= new QuizCard(list[0], list[1]);
        cardlist.add(card);
        System.out.println("MAde a card");
    }

    public void showNextCard(){
        currentCard = cardlist.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        button.setText("Show Answer");
        showAnswer = true;

    }
}
