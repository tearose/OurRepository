package com.tcpclient;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIPersonMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Vector<Vector<String>> data = new Vector<Vector<String>>();	
	private JTable tblPeronList;
	private JPanel panNavigator;
	public GUIPersonMain() {
		setTitle("Person List");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setPreferredSize(new Dimension(600,300));

		addComponents();

		
		
		pack();
		setVisible(true);

	}
	private void addComponents() {
	getContentPane().setLayout(new GridLayout(3,1));

	Vector<String> headers=new Vector<String>();
	headers.add("id");
	headers.add("Name");	
	
	getData();	
	
	DefaultTableModel model = new DefaultTableModel(data, headers);

		
	
	tblPeronList = new JTable(model);
	tblPeronList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	getContentPane().add(tblPeronList);
	panNavigator = new JPanel();
	getContentPane().add(panNavigator);
	JButton btnClose = new JButton("Close");
	getContentPane().add(btnClose);
	
	
		
	}
	private void getData() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		GUIPersonMain pl = new GUIPersonMain();
	}

}
