package com.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CourseManager {

	public static void main(String[] args) {

		String masterListFile = "/Users/JaeCaramelSmith/Desktop/student-master-list.csv";

		List<Student> course1 = new ArrayList<>();
		List<Student> course2 = new ArrayList<>();
		List<Student> course3 = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(masterListFile))) {
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				if (values.length >= 4) {
					try {
						int studentID = Integer.parseInt(values[0].trim());
						String studentName = values[1].trim();
						String course = values[2].trim();
						int grade = Integer.parseInt(values[3].trim());

						Student student = new Student(studentID, studentName, course, grade);

						if (course.startsWith("COMPSCI")) {
							course1.add(student);
						}

						else if (course.startsWith("APMTH")) {
							course2.add(student);
						}

						else if (course.startsWith("STAT")) {
							course3.add(student);
						}

					} catch (NumberFormatException e) {
						System.err.println("Error parsing number, Check the input file for invalid data.");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Sort by grade in descending order

		Comparator<Student> byGradeDescending = Comparator.comparingInt(Student::getGrade).reversed();
		Collections.sort(course1, byGradeDescending);
		Collections.sort(course2, byGradeDescending);
		Collections.sort(course3, byGradeDescending);

		writeToFile(course1, "course1.csv");
		writeToFile(course2, "course2.csv");
		writeToFile(course3, "course3.csv");
	}

	private static void writeToFile(List<Student> students, String fileName) {
		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write("Student ID, Student Name, Course, Grade\n");
			for (Student student : students) {
				writer.write(student.toString() + "\n");
			}
System.out.println(fileName);
		}

		catch (IOException e) {
			e.printStackTrace();

		}
	}

}
