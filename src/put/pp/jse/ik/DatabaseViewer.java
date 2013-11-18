/**
 * Created with IntelliJ IDEA.
 * User: Ireneusz
 * Date: 16.11.13
 * Time: 23:14
 * Platformy Programowania Lab5 DatabseViewer
 */

package put.pp.jse.ik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.sql.*;

public class DatabaseViewer extends JFrame implements MouseMotionListener, ActionListener {

    static Container pane;
    static JLabel mousePos;
    JButton clean, execute;
    JTextArea output;
    JTextField input;

    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
              new DatabaseViewer().setVisible(true);
          }
      });
    }

    public DatabaseViewer(){
        this.setSize(500,400);
        initComponents();
    }
    public void  initComponents(){
        pane = this.getContentPane();

        mousePos = new JLabel("Pozycja myszy");
        clean = new JButton("Wyczyść");
            clean.addActionListener(this);
            clean.setActionCommand("clean");
        output = new JTextArea("Output");
        execute = new JButton("Wykonaj");
            execute.addActionListener(this);
            execute.setActionCommand("execute");
        input = new JTextField("Enter commands here");

        pane.add(mousePos,BorderLayout.PAGE_START);
        pane.add(clean, BorderLayout.LINE_START);
        pane.add(output, BorderLayout.CENTER);
        pane.add(execute, BorderLayout.LINE_END);
        pane.add(input, BorderLayout.PAGE_END);

        mousePos.addMouseMotionListener(this);
        clean.addMouseMotionListener(this);
        output.addMouseMotionListener(this);
        execute.addMouseMotionListener(this);
        input.addMouseMotionListener(this);

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        //System.out.println(arg0.getX());
        mousePos.setText( "Pozycja myszy: x=" + arg0.getX() + ", y=" + arg0.getY() );
        mousePos.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("clean".equals(e.getActionCommand())){
            output.setText("");
        } else if ("execute".equals(e.getActionCommand())){
            sendQuery(input.getText());
        }
    }

    public void sendQuery(String query){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=NORTHWND;user=Kinmarui;password=kinmarui";

        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement PreSt = null;
        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            // Create and execute an SQL statement that returns some data.
            PreSt = con.prepareStatement(query);
            stmt = con.createStatement();
            rs = PreSt.executeQuery();

            // I know I should use string builder but i wanted that code be as simple as possible
            String out = "";
            // Iterate through the data in the result set and display it.
             while (rs.next()) {
                out += rs.getString(1) + "  " + rs.getString(3) + "  " + rs.getString(6);
             }
            output.setText(out);
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
}
