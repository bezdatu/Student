package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class GroupFileStorage {
	private CSVStringConverter converter = new CSVStringConverter();
	public void saveGroupToCSV(Group gr, File workFolder) {
	
		File file = new File(workFolder, gr.getName() + ".csv");
		Student[] students = gr.getStudents();
		Arrays.sort(students, Comparator.nullsFirst(new StudentNameComparator()));
		try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			for (int i = 0; i < students.length; i++) {
                Student student = students[i];
                if (student != null) {
                    writer.println(converter.toStringRepresentation(student));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public Group loadGroupFromCSV(File file) {
		Group group = new Group();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				try {
					Student student = converter.fromStringRepresentation(line);
					group.addStudent(student);
				} catch (GroupOverflowException e) {
					System.out.println("Unable to add student: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return group;
	}
	
	public File findFileByGroupName(String groupName, File workFolder) {
		File[] files = workFolder.listFiles();
		if (files == null) {
			return null;
		    }
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile() && files[i].getName().equals(groupName + ".csv")) {
		        	        
				return files[i];
		      }
		    }
	
		    return null;
		  }
	
}

