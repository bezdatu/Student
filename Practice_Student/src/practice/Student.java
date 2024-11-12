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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(groupName, id);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Student other = (Student) obj;
	    return Objects.equals(getLastName(), other.getLastName()) && 
	           Objects.equals(getName(), other.getName());
	}
	
}

