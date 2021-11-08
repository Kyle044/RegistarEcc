package GUITemps;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;

import javax.swing.JPanel;

import GUI.MasterDash;
import Tools.FileHandler;
import Tools.InputSanitazion;
import Tools.Warning;

import javax.swing.JScrollPane;

import DataTypes.Goals;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import static javax.swing.ScrollPaneConstants.*;
public class Attachments extends JDialog {
	ArrayList<File> selectedFile;
	FileHandler fh = new FileHandler();
	Attachments(MasterDash frame, boolean ist,AddGoalGui gui) {

		// this is a must too
		super(frame, ist);
		getContentPane().setLayout(null);
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 11, 462, 381);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 11, 442, 288);
		mainPanel.add(scrollPane);
		
		JPanel FilesPanel = new JPanel();
		
		
		scrollPane.setViewportView(FilesPanel);
		FilesPanel.setLayout(new WrapLayout());
		// you can edit this area ------

		for (File file : gui.selectedFile) {
			try {	
				Btn btn = new Btn(file.getName());
				btn.setPreferredSize(new Dimension(100,50));
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						fh.readFile(new File(file.getAbsolutePath()));
					}
					
				});
				FilesPanel.add(btn);
				revalidate();
				repaint();
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			Btn attachBtn = new Btn("Attach");
			attachBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					FileHandler fh = new FileHandler();
					File[] selected = fh.getAttachment();
					
					for(File file :selected) {
						gui.selectedFile.add(file);
					}
					selected=null;
					
					dispose();
//					for (File file : gui.selectedFile) {
//						try {	
//							Btn btn = new Btn(file.getName());
//							btn.setPreferredSize(new Dimension(100,50));
//							FilesPanel.add(btn);
//							revalidate();
//							repaint();
//						} catch (FontFormatException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} catch (IOException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						
//					}
				
					
				
				}
			});
			attachBtn.setLocation(86, 310);
			attachBtn.setSize(137, 49);
			Btn closeBtn = new Btn("Close");
			closeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			closeBtn.setBounds(233, 310, 137, 49);
			mainPanel.add(attachBtn);
			mainPanel.add(closeBtn);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		



		// this is the must
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setSize(498, 442);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}
	
	
	Attachments(MasterDash frame, Goals goal) {
		 
		// this is a must too
		super(frame, true);
		getContentPane().setLayout(null);
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 11, 462, 381);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 11, 442, 288);
		mainPanel.add(scrollPane);
		
		JPanel FilesPanel = new JPanel();
		
		
		scrollPane.setViewportView(FilesPanel);
		FilesPanel.setLayout(new WrapLayout());
		// you can edit this area ------

		File folder = new File("D:\\goalDirectory//"+goal.Username+"_"+goal.Title);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		
		
		
		
		for (File file : listOfFiles) {
			try {	
				Btn btn = new Btn(file.getName());
				btn.setPreferredSize(new Dimension(100,50));
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						fh.readFile(new File(file.getAbsolutePath()));
					}
					
				});
				FilesPanel.add(btn);
				revalidate();
				repaint();
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

			Btn closeBtn;
			try {
				closeBtn = new Btn("Close");
				closeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				closeBtn.setBounds(160, 310, 137, 49);
				mainPanel.add(closeBtn);
			} catch (FontFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

	

		



		// this is the must
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setSize(498, 442);
		this.setLocationRelativeTo(frame);
		this.setVisible(true);
	}
}
