package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

public class QuizBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardlist;
    private JFrame frame;

    public void go(){
        frame = new JFrame("Open Card Builder");
        JPanel panel = new JPanel();
        Font font = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(font);

        JScrollPane scrollPane = new JScrollPane(question);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        answer = new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(font);

        JScrollPane scrollPane1 = new JScrollPane(answer);
        scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton button =new JButton("Next Card");
        cardlist =new ArrayList<QuizCard>();

        JLabel qlabel = new JLabel("Question");
        JLabel alabel = new JLabel("Answer");

        panel.add(qlabel);
        panel.add(scrollPane);
        panel.add(alabel);
        panel.add(scrollPane1);
        panel.add(button);

        button.addActionListener(new NextCardListener());

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuitem = new JMenuItem("New");
        JMenuItem menuitem1 = new JMenuItem("Save");
        menuitem.addActionListener(new NewCardListener());
        menuitem1.addActionListener(new SaveCardListener());

        menu.add(menuitem);
        menu.add(menuitem1);
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setSize(500,600);
        frame.setVisible(true);

    }

    public class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card = new QuizCard(question.getText(),answer.getText());
            cardlist.add(card);
            clearCard();
        }
    }

    public class NewCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            cardlist.clear();
            clearCard();

        }
    }

    public class SaveCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card = new QuizCard(question.getText(),answer.getText());
            cardlist.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    public void clearCard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    public void saveFile(File file){
        try{
            BufferedWriter bf = new BufferedWriter(new FileWriter(file));
            for(QuizCard c : cardlist){
                bf.write(c.getQuestion()+ "/");
                bf.write(c.getAnswer()+"\n");

            }

            bf.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
