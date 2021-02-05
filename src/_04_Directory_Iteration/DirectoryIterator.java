package _04_Directory_Iteration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
//		JFileChooser jfc = new JFileChooser();
//		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		int returnVal = jfc.showOpenDialog(null);
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
		DirectoryIterator di = new DirectoryIterator();
		File directory = new File("src");
		File[] files = directory.listFiles();
		if(files != null) {
			for(File f : files) {
				di.copyrightFile(f);
			}
		}
		
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright © 2019 FirstName LastName)
		 */
	}
	
	void copyrightFile(File f) {
		String filePath = f.getPath();
		try {
			if (filePath.substring(filePath.indexOf('.'), filePath.length()).equals(".java")) {
				try {
					FileWriter fw = new FileWriter(filePath, true);
					fw.append("//This is code from Miller!");
					fw.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			File[] newFiles = f.listFiles();
			for (int i = 0; i < newFiles.length; i++) {
				copyrightFile(newFiles[i]);
			}
		}
		
	}
}
//This is code from Miller!//This is code from Miller!//This is code from Miller!//This is code from Miller!