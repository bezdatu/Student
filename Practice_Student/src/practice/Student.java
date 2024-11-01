package practice;

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
}

