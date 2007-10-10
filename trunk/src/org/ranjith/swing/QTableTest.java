/**
 * 
 */
package org.ranjith.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 * @author ranjith
 * P U R E L Y  F O R  T E S T I N G
 */
public class QTableTest extends JFrame implements ActionListener{
	QTable employeeTable = new QTable();

	public QTableTest() {
		super("Test QTable");
		JButton populateButton = new JButton("Populate");
		getContentPane().setLayout(new BorderLayout());
		employeeTable.setIsAlternateRowHightLighted(true);
		getContentPane().add(new JScrollPane(employeeTable));
		populateButton.addActionListener(this);
		getContentPane().add(populateButton,BorderLayout.SOUTH);
		pack();
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		List employeeList = getEmployeeList();
		QTableModel dataModel = new QTableModel(employeeList,new String[]{"Emp Id","Join Date","Name","Salary"},new String[] {"empNo","joinDate","name","salary"});
		employeeTable.setQTableModel(dataModel);
	}	
	public static void main(String[] args) {
		QTableTest test = new QTableTest();
		
	}
	
	private List getEmployeeList() {
		List employees = new ArrayList();
		Employee e1 = new Employee("1",new Date(),"John Doe",1000.00f);
		employees.add(e1);
		Employee e2 = new Employee("2",new Date(),"Bill Gates",2000.00f);
		employees.add(e2);
		Employee e3 = new Employee("3",new Date(),"Steve Jobbs",1500.00f);
		employees.add(e3);
		Employee e4 = new Employee("4",new Date(),"James Gosling",2000.00f);
		employees.add(e4);
		Employee e5 = new Employee("5",new Date(),"James Layman",800.00f);
		employees.add(e5);
		return employees;
		
	}
	
}
