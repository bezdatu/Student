package practice;

public interface StringConverter {
	public default String toStringRepresentation (Student student) {
		return student.getId() + "," + student.getLastName() + "," + student.getName() + "," + student.getGender() + "," + student.getGroupName();
    }
	public default Student fromStringRepresentation (String str) {
		String[] parts = str.split(",");
		Integer id = Integer.valueOf(parts[0]);
		String lastName = parts[1];
		String name = parts[2]; 		
		Human.Gender gender = Human.Gender.valueOf(parts[3]);		
		String groupName = parts[4]; 
		return new Student(name, lastName, gender, id, groupName);
	}
}
