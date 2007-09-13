package org.ranjith.util;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.ranjith.swing.RoundButton;
import org.ranjith.swing.SimpleGradientPanel;

public class GroupLayoutTest extends JFrame {
    JLabel firstNameLabel = new JLabel("First Name :");
    JLabel lastNameLabel = new JLabel("Lastt Name :");
    JLabel dobLabel = new JLabel("Date of Birth :");
    JLabel phoneLabel = new JLabel("Phone :");
    
    JTextField firstNameTextField = new JTextField(20);
    JTextField lastNameTextField = new JTextField(20);
    JTextField dobTextField = new JTextField(10);
    JTextField phoneTextField = new JTextField(15);
    
    RoundButton saveButton = new RoundButton("Save");
    RoundButton deleteButton = new RoundButton("Delete");
    RoundButton doneButton = new RoundButton("Done");
    
    public GroupLayoutTest() {
        SimpleGradientPanel panel = new SimpleGradientPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        /**
         * horizontal layout
         *   sequential group {prallelgroup(RIGHT) {Labels},
         *                     parallelgroup(LEFT) {texboxes} 
         *                     }
         * vertical layout
         *   sequential group {parallelgroup(BASELINE) {label1,textfield1},
         *                     parallelgroup(BASELINE) {label2,textfield2},
         *                     ...
         *                     }                    
         */

          gl.setHorizontalGroup(
             gl.createSequentialGroup().
                 addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(firstNameLabel)
                                                  .addComponent(lastNameLabel)
                                                  .addComponent(dobLabel)
                                                  .addComponent(phoneLabel)
                         ).
                 addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(firstNameTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addComponent(lastNameTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addComponent(dobTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addComponent(phoneTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     
                         )
           );
        
        gl.setVerticalGroup(
            gl.createSequentialGroup().addGroup(
                   gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(firstNameLabel).addComponent(firstNameTextField)                                           
                   ).addGroup(
                   gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(lastNameLabel).addComponent(lastNameTextField)
                   ).addGroup(
                   gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(dobLabel).addComponent(dobTextField)
                   ).addGroup(
                   gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(phoneLabel).addComponent(phoneTextField)
                   )

           );     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(doneButton);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel,BorderLayout.CENTER);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        GroupLayoutTest test = new GroupLayoutTest();
    }
}
