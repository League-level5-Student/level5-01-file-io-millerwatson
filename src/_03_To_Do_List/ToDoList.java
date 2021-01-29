package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list. 
	 */
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton add = new JButton("add task");
	JButton view = new JButton("view tasks");
	JButton remove = new JButton("remove task");
	JButton save = new JButton("save list");
	JButton load = new JButton("load list");
	ArrayList<String> tasks = new ArrayList<String>();
	
	public ToDoList() {
		add.addActionListener(this);
		view.addActionListener(this);
		remove.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		panel.add(add);
		panel.add(view);
		panel.add(remove);
		panel.add(save);
		panel.add(load);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		ToDoList td = new ToDoList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(add)) {
			tasks.add(JOptionPane.showInputDialog("Enter a task:"));
		} else if (e.getSource().equals(view)) {
			String taskString = "";
			for (int i = 0; i < tasks.size(); i++) {
				taskString = taskString + tasks.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, taskString);
		} else if (e.getSource().equals(remove)) {
			String removedTask = JOptionPane.showInputDialog("Which task do you want to remove?");
			for (int i = 0; i < tasks.size(); i++) {
				if(tasks.get(i).equals(removedTask)) {
					tasks.remove(i);
				}
			}
		} else if (e.getSource().equals(save)) {
			try {
				FileWriter fr = new FileWriter("src/_03_To_Do_List/saved.txt");
				fr.write("");
				for (int i = 0; i < tasks.size(); i++) {
					fr.append(tasks.get(i) + "\n");
				}
				fr.close();
			} catch (IOException f) {
				f.printStackTrace();
			}
		} else if (e.getSource().equals(load)) {
			String filepath = JOptionPane.showInputDialog("What is the path of your file?");
			ArrayList<String> newList = new ArrayList<String>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(filepath));
				String line = br.readLine();
				while (line != null) {
					newList.add(line);
					line = br.readLine();
				}
				br.close();
			} catch (FileNotFoundException f) {
				JOptionPane.showMessageDialog(null, "The path is incorrect, or you need to save a list first");
				f.printStackTrace();
			} catch (NullPointerException f) {
				JOptionPane.showMessageDialog(null, "The path is incorrect, or you need to save a list first");
				f.printStackTrace();
			} catch (IOException f) {
				f.printStackTrace();
			}
			tasks = newList;
		}
	}
}
