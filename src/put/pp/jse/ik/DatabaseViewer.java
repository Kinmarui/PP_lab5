package put.pp.jse.ik;

import javax.swing.*;

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
                  new DatabaseViewer().setVisible(true);
              }
          });
      }
}
