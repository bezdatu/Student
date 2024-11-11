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
	
	public void addStudent(Student student) throws GroupOverflowException {
		student.setId(nextId);
		student.setGroupName(this.groupName);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return nextId == group.nextId &&
                Objects.equals(groupName, group.groupName) &&
                Arrays.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(groupName, nextId);
        result = 31 * result + Arrays.hashCode(students);
        return result;
    }
}
