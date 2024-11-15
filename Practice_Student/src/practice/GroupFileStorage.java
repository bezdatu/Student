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
import java.util.List;

public class GroupFileStorage {
	private CSVStringConverter converter = new CSVStringConverter();
	public void saveGroupToCSV(Group group, File workFolder) {
        if (!workFolder.exists() || !workFolder.isDirectory()) {
            throw new IllegalArgumentException("The specified work folder is invalid.");
        }

        File file = new File(workFolder, group.getName() + ".csv");
        List<Student> students = group.getStudents();
        students.sort(Comparator.comparing(Student::getLastName, Comparator.nullsFirst(Comparator.naturalOrder())));

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Student student : students) {
                writer.println(converter.toStringRepresentation(student));
            }
            System.out.println("Group saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving group to CSV: " + e.getMessage());
        }
    }
	public Group loadGroupFromCSV(File file) {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("The specified file is invalid.");
        }

        Group group = new Group();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Student student = converter.fromStringRepresentation(line);
                    group.addStudent(student);
                } catch (GroupOverflowException e) {
                    System.err.println("Unable to add student: " + e.getMessage());
                }
            }
            System.out.println("Group loaded from: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error loading group from CSV: " + e.getMessage());
        }

        return group;
    }
	
	public File findFileByGroupName(String groupName, File workFolder) {
        if (!workFolder.exists() || !workFolder.isDirectory()) {
            throw new IllegalArgumentException("The specified work folder is invalid.");
        }

        File[] files = workFolder.listFiles();
        if (files == null) {
            return null;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().equalsIgnoreCase(groupName + ".csv")) {
                return file;
            }
        }

        return null;
    }
}

