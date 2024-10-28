package practice;

import java.util.Scanner;

import practice.Human.Gender;

public class NewStudent {
	public Student readStudentFromInput() {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student's first name:");
        String name = scanner.nextLine();

        System.out.println("Enter student's last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter student's gender (MALE/FEMALE):");
        String genderInput = scanner.nextLine();
        Gender gender = Gender.valueOf(genderInput.toUpperCase());
        System.out.println("Enter student's group name:");
        String groupName = scanner.nextLine();

        return new Student(name, lastName, gender);
    }
}
