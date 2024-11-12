package practice;
import practice.Student;
import practice.Group;
import practice.Human;
import practice.GroupOverflowException;

import java.io.File;

public class Main {

		public static void main(String[] args) {
			 Group group = new Group("Group1");
		        System.out.println(group);
		     
			
			Student student1 = new Student("Andrey", "Petrov", Human.Gender.MALE);
	        Student student2 = new Student("Ivan", "Ivanov", Human.Gender.MALE);
	        Student student3 = new Student("Andrey", "Petrov", Human.Gender.MALE);
	        
    
	        try {	          
	            group.addStudent(student1);
	            group.addStudent(student2);
	            group.addStudent(student3);
	           
	            
	        } catch (GroupOverflowException e) {
	            System.out.println(e.getMessage());
	        }
	        System.out.println();
	        group.sortStudentsByLastName();
	        System.out.println(group);
	        
	        NewStudent newStudentInput = new NewStudent();
	        try {
	            Student newStudent = newStudentInput.readStudentFromInput();
	            group.addStudent(newStudent); 
	        } catch (GroupOverflowException e) {
	            System.out.println(e.getMessage()); 

	        System.out.println(group); 
	        }
	        try {
	            Student foundStudent = group.searchStudentByLastName("Ivanov");
	            System.out.println(foundStudent);
	        } catch (StudentNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
			System.out.println();
	          
			int idToRemove = 11;
	        if (group.removeStudentById(idToRemove)) {
	            System.out.println("Student with ID " + idToRemove + " removed successfully.");
	        } else {
	            System.out.println("Student with ID " + idToRemove + " not found.");
	        }
	        System.out.println();
	        System.out.println(group);	
	        
	        GroupFileStorage storage = new GroupFileStorage();
	        File workFolder = new File("/Users/fedor/eclipse");
	        storage.saveGroupToCSV(group, workFolder);
	        System.out.println("Save group to file - group.csv");
	        
	        
	        File foundFile = storage.findFileByGroupName(group.getName(), workFolder);
	        if (foundFile != null) {
	        Group loadedGroup = storage.loadGroupFromCSV(foundFile);
	        System.out.println("Loaded :" + group.getName());
	        System.out.println(loadedGroup);
	        }else {
	        	return;
	        }
		}
}