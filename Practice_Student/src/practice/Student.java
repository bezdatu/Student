package practice;

import java.util.Objects;

import practice.Human.Gender;

public class Student extends Human {
	private int id;
	private String groupName;
	public Student(String name, String lastName, Gender gender, int id, String groupName) {
		super(name, lastName, gender);
		this.id = id;
		this.groupName = groupName;
	}
	public Student(String name, String lastName, Gender gender) {
		super(name, lastName, gender);
		this.id = 0;
		this.groupName = "";
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", lastName=" + getLastName() + ", groupName=" + groupName + "]";
	}
	public String toStringWithoutGroup() {
	    return "Student [id=" + id + ", lastName=" + getLastName() + "]";
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(groupName, student.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, groupName);
    }

}

