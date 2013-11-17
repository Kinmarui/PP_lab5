package put.pp.jse.ik;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ireneusz
 * Date: 16.11.13
 * Time: 23:14
 * Platformy Programowania Lab5 DatabseViewer
 */
public class DatabaseViewer extends JFrame {


    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
              initComponents();
              new DatabaseViewer().setVisible(true);
          }
      });
    }

    public static void  initComponents(){
        JFrame frame;
        Container pane;

        frame = new JFrame("Database Viewer");
        pane = frame.getContentPane();

        JLabel mousePos = new JLabel("Pozycja myszy");
        JButton clean = new JButton("Wyczyść");
        JTextArea output = new JTextArea("Output");
        JButton execute = new JButton("Wykonaj");
        JTextField input = new JTextField("Enter connamds here");

        pane.add(mousePos,BorderLayout.PAGE_START);
        pane.add(clean, BorderLayout.LINE_START);
        pane.add(output, BorderLayout.CENTER);
        pane.add(execute, BorderLayout.LINE_END);
        pane.add(input, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
    }
}
