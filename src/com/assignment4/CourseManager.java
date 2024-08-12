package com.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class CourseManager {

	public static void main(String[] args) {

		String masterListFile = "student-master-list.csv";

		Student[] course1 = new Student[100];
		Student[] course2 = new Student[100];
		Student[] course3 = new Student[100];
		int course1Count = 0;
		int course2Count = 0;
		int course3Count = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(masterListFile))) {
			String line = br.readLine(); // Read header
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
							course1[course1Count++] = student;
						}

						else if (course.startsWith("APMTH")) {
							course2[course2Count++] = student;
						}

						else if (course.startsWith("STAT")) {
							course3[course3Count++] = student;
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

		Arrays.sort(course1, 0, course1Count, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(course2, 0, course2Count, Comparator.comparingInt(Student::getGrade).reversed());
		Arrays.sort(course3, 0, course3Count, Comparator.comparingInt(Student::getGrade).reversed());

		writeToFile(course1, course1Count, "course1.csv");
		writeToFile(course2, course2Count, "course2.csv");
		writeToFile(course3, course3Count, "course3.csv");
	}

	private static void writeToFile(Student[] students, int studentCount, String fileName) {
		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write("Student ID, Student Name, Course, Grade\n");
			System.out.println();
			System.out.println();
			System.out.println("Content of " + fileName + ":");
			System.out.println();
			System.out.println("Student ID, Student Name, Course, Grade");

			for (int i = 0; i < studentCount; i++) {
				writer.write(students[i].toString() + "/n");
				System.out.println(students[i].toString());
			}
			System.out.println();

		}

		catch (IOException e) {
			e.printStackTrace();

		}
	}

}

//System.out.println(); //separating each course content in console by adding newline 