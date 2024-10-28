package practice;

import practice.StringConverter;
import practice.Student;

public class CSVStringConverter implements StringConverter {

	@Override
	public String toStringRepresentation(Student student) {
		return StringConverter.super.toStringRepresentation(student);
	}
	@Override
	public Student fromStringRepresentation(String str) {
		return StringConverter.super.fromStringRepresentation(str);
	}
}