package org.ranjith.util;

import java.awt.BorderLayout;

import org.jdesktop.layout.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Test calss for creating data forms.
 * Not to be used anywhere as it is.
 * @author ranjith
 *
 */
public class GroupLayoutTest extends JFrame {
    JLabel firstNameLabel = new JLabel("First Name :");
    JLabel lastNameLabel = new JLabel("Lastt Name :");
    JLabel dobLabel = new JLabel("Date of Birth :");
    JLabel phoneLabel = new JLabel("Phone :");
    
    JTextField firstNameTextField = new JTextField(20);
    JTextField lastNameTextField = new JTextField(20);
    JTextField dobTextField = new JTextField(10);
    JTextField phoneTextField = new JTextField(15);
    
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton doneButton = new JButton("Done");
    
    public GroupLayoutTest() {
    	JPanel panel = new JPanel();
        
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
//         gl.setHorizontalGroup(
//        		 				gl.createParallelGroup()
//        		 					.addGroup(
//        		 								gl.createSequentialGroup().addComponent(firstNameLabel)
//        		 								                          .addPreferredGap(ComponentPlacement.RELATED)
//        		 								                          .addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
//        		 							 )
//        		 					.addGroup(
//        		 								gl.createSequentialGroup().addComponent(lastNameLabel)
//        		 								                          .addPreferredGap(ComponentPlacement.RELATED)
//        		 								                          .addComponent(lastNameTextField, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
//        		 							 )
//        		 				    .addGroup(
//        		 				    			gl.createSequentialGroup().addComponent(saveButton)
//        		 				    									  .addPreferredGap(ComponentPlacement.RELATED)
//        		 				    			                          .addComponent(deleteButton)
//        		 				    			                          .addGap(40,40,40)
//        		 				    			                          .addComponent(doneButton)
//        		 				    		 )
//        		              );
//        gl.setVerticalGroup(
//        					  gl.createParallelGroup()
//        					  		.addGroup(
//        					  					gl.createSequentialGroup().addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
//        					  							                                  .addComponent(firstNameLabel)
//        					  							                                  .addComponent(firstNameTextField)
//        					  							                                             
//        					  							                           )
//					  							                           .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
//					  							                                  .addComponent(lastNameLabel)
//					  							                                  .addComponent(lastNameTextField)
//					  							                                             
//					  							                           )
//        					  							                  .addGroup(
//        					  							                		    gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
//        					  							                		          .addComponent(saveButton)
//        					  							                		          .addComponent(deleteButton)
//        					  							                		          .addComponent(doneButton)
//        					  							                		   )
//        					  				 )
//        		           );
         
          gl.setHorizontalGroup(
             gl.createSequentialGroup().
                 addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(firstNameLabel)
                                                  .addComponent(lastNameLabel)
                                                  .addComponent(dobLabel)
                                                  .addComponent(phoneLabel).addGroup(gl.createSequentialGroup().addComponent(saveButton))
                                                     .addGroup(
                                                            gl.createSequentialGroup()
                                                              .addComponent(saveButton)
                                                              )
                                                 
                         ).
                 addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(firstNameTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addComponent(lastNameTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addComponent(dobTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addComponent(phoneTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                                                     .addGroup(
                                                            gl.createSequentialGroup()
                                                              .addComponent(deleteButton)
                                                              .addGap(60)
                                                              .addComponent(doneButton)
                                                            )                                                     

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
                   ).addGroup(
                   gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(saveButton)
                       .addComponent(deleteButton)
                       .addComponent(doneButton)
                   )

           );     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        GroupLayoutTest test = new GroupLayoutTest();
    }
}
