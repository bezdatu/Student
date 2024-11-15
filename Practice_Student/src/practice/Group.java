package practice;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Group {
	private String groupName;
	private final List<Student> students;
	private int nextId;
	public Group(String groupName, List<Student> students) {
		super();
		this.groupName = groupName;
		this.students = students != null ? students : new ArrayList<>();
		this.nextId = 1;
	}	
	public Group(String groupName) {
		super();
		this.groupName = groupName;
		this.students = new ArrayList<>();
		this.nextId = 1;
	}
	public Group() {
		super();
		this.students = new ArrayList<>();
		this.groupName = "";
		this.nextId = 1;
	}
	public List<Student> getStudents() {
		return students;
	}
	
	public boolean isStudentEquivalent(Student student) {
        return students.stream().anyMatch(existingStudent -> existingStudent.equals(student));
    } 
	
	
	public void addStudent(Student student) throws GroupOverflowException {
		student.setId(nextId);
		student.setGroupName(this.groupName);
		if (isStudentEquivalent(student)) {
			System.out.println("Student " + student.getLastName() + " is already in the group.");
			return;
		}
		if (students.size() >= 10) {
			throw new GroupOverflowException("Cannot add more students. Group is full.");
        }
		students.add(student);
        System.out.println("Added student: " + student.getLastName());
        nextId++;
	}
	public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
		return students.stream()
                .filter(student -> student.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student with last name " + lastName + " not found."));
    }
	public boolean removeStudentById(int id) {
        return students.removeIf(student -> student.getId() == id);
    }	
	public void sortStudentsByLastName() {
        students.sort(Comparator.comparing(Student::getLastName, Comparator.nullsFirst(Comparator.naturalOrder())));
    }
	@Override
    public String toString() {
        StringBuilder result = new StringBuilder("Group: " + groupName + System.lineSeparator());
        if (students.isEmpty()) {
            result.append("The group is empty").append(System.lineSeparator());
        } else {
            for (Student student : students) {
                result.append(student.toStringWithoutGroup()).append(System.lineSeparator());
            }
        }
        return result.toString();
    }
	public String getName() {
		return groupName;
	}
	@Override
    public int hashCode() {
        return Objects.hash(groupName, students, nextId);
    }
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return Objects.equals(groupName, other.groupName) &&
                nextId == other.nextId &&
                Objects.equals(students, other.students);
    }
}
