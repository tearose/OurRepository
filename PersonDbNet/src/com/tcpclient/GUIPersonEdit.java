package com.tcpclient;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.PopupMenu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.GridLayout;

public class GUIPersonEdit extends JFrame {
	private static final long serialVersionUID = 1L;	
	private JTextField editID;
	private JTextField editFN;
	private JTextField editLN;
	private JSpinner editAge;
	private JTextArea editInfo;
	private JPanel panFile;
	private JTextArea editfile;
	private JButton btnAttachFile;
	private JButton btnDeleteFile;
	private JPanel panPhoto;
	private JPanel panPhotoView;
	private JButton btnAttachPhoto;
	private Component btnDeletePhoto;
	private JButton btnSave;
	private JButton btnCancel;


	public GUIPersonEdit() {
		setTitle("Edit person");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setPreferredSize(new Dimension(500,300));
		//setSize(300,300);
		addComponents();

		
		
		pack();
		setVisible(true);
		
	}


	private void addComponents() {
		
		getContentPane().setLayout(new GridLayout(8, 2));
		
		getContentPane().add(new JLabel("ID"));
		editID = new JTextField();	
//		editID.setPreferredSize(new Dimension(200,30));
		getContentPane().add(editID);
		
		getContentPane().add(new JLabel("First name:"));
		editFN = new JTextField();	
		getContentPane().add(editFN);
		
		getContentPane().add(new JLabel("Last name:"));
		editLN = new JTextField();	
		getContentPane().add(editLN);
		
		getContentPane().add(new JLabel("Age:"));
		editAge = new JSpinner(new SpinnerNumberModel(18, 0, 200, 1));	
		getContentPane().add(editAge);
		
		getContentPane().add(new JLabel("Information:"));
		editInfo = new JTextArea("");	
		getContentPane().add(editInfo);
//		foto begin
		getContentPane().add(new JLabel("Foto:"));
		panPhoto = new JPanel(new GridLayout(1,3));
		panPhoto.setBackground(Color.darkGray);
		getContentPane().add(panPhoto);
	
		panPhotoView = new JPanel(new FlowLayout());
		panPhotoView.setPreferredSize(new Dimension(100,100));
		panPhotoView.setBackground(Color.green);
		panPhoto.add(panPhotoView);
		
		btnAttachPhoto  = new JButton("Attach");
		panPhoto.add(btnAttachPhoto);
		
		btnDeletePhoto  = new JButton("Delete");
		panPhoto.add(btnDeletePhoto);		
		
		
//		foto end
//filechooser begin		
		getContentPane().add(new JLabel("File:"));
		panFile = new JPanel(new FlowLayout());	
		getContentPane().add(panFile);
		
		editfile = new JTextArea("path");
		panFile.add(editfile);
		btnAttachFile  = new JButton("Attach");
		panFile.add(btnAttachFile);
		btnDeleteFile  = new JButton("Delete");
		panFile.add(btnDeleteFile);		
	   //		filechooser end		
		
		btnSave = new JButton("Save");
		btnCancel = new JButton("Cancel");
		getContentPane().add(btnSave);
		getContentPane().add(btnCancel);

		
		
	}
	public static void main(String[] args) {
		
		GUIPersonEdit pe = new GUIPersonEdit();
		
	}


	



}
