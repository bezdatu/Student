package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Group {
	private String groupName;
	private final Student[] students;
	private int nextId;
	public Group(String groupName, Student[] students) {
		super();
		this.groupName = groupName;
		this.students = students;
		this.nextId = 1;
	}	
	public Group(String groupName) {
		super();
		this.groupName = groupName;
		students = new Student[10];
		this.nextId = 1;
	}
	public Group() {
		super();
		students = new Student[10];
		this.groupName = "";
		this.nextId = 1;
	}
	public Student[] getStudents() {
		return students;
	}
	
	public boolean isStudentEquivalent(Student student) {
	    for (Student existingStudent : students) {
	        if (existingStudent != null && existingStudent.equals(student)) {
	            return true;
	        }
	    }
	    return false; 
	}
	
	public void addStudent(Student student) throws GroupOverflowException {
		student.setId(nextId);
		student.setGroupName(this.groupName);
		if (isStudentEquivalent(student)) {
			System.out.println("Student " + student.getLastName() + " is already in the group.");
			return;
		}
		for (int i = 0; i <students.length; i++) {
			if (students[i] == null) {
				students[i] = student;
				System.out.println("Added student: " + student.getLastName()); 
				nextId++;
				return;
			}
		}
			throw new GroupOverflowException("Cannot add more students. Group is full.");		
	}
	public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
	    for (int i = 0; i < students.length; i++) {
	        if (students[i] != null && students[i].getLastName().equals(lastName)) {
	            return students[i]; 
	        }
	    }
	    throw new StudentNotFoundException("Student with last name " + lastName + " not found.");
	}
	public boolean removeStudentById (int id) {
			for (int i = 0; i <students.length; i++) {
				if (students[i] != null) {
					if (students[i].getId() == id) {
					     students[i] = null;
					     return true;
					}
				}
			}
			return false;
		}	
	public void sortStudentsByLastName () {
		Arrays.sort(students, Comparator.nullsFirst(new StudentNameComparator()));
	}
	@Override
    public String toString() {
        StringBuilder result = new StringBuilder("Group: " + groupName + System.lineSeparator());
        int countStudentsGroup = 0;
        for (int i = 0; i < students.length; i++) {     	
            if (students[i] != null) {
            	countStudentsGroup += 1;
            	result.append(students[i].toStringWithoutGroup()).append(System.lineSeparator());
            }           
            }           
            
        if (countStudentsGroup == 0) {
        	result.append("The group is empty").append(System.lineSeparator());
        }
        return result.toString();   
	}
	public String getName() {
		return groupName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(students);
		result = prime * result + Objects.hash(groupName, nextId);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(groupName, other.groupName) && nextId == other.nextId
				&& Arrays.equals(students, other.students);
	}
	
}
