package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import entity.Course;
import entity.Student;
import exceptions.serviceexceptions.CourseServiceExceptions;
import exceptions.serviceexceptions.StudentServiceException;
import service.StudentCourseServices;
import service.Validations;
import service.servicesimpl.StudentCourseServicesImpl;

public class StudentCourseManagementSystem {
	private Scanner scanner = new Scanner(System.in);

	public void init() {
		int choice = 0;
		do {
			choice = displayMenu();
			switch (choice) {
			case 1:
				createCourse();
				break;

			case 2:
				createStudent();
				break;

			case 3:
				assignStudents();
				break;

			case 4:
				displayStudentsByCourses();
				break;

			default:
				System.out.println("Invalid Input");
				break;
			}
		} while (choice < 5);
	}

	private void displayStudentsByCourses() {
		String courseName = "";
		scanner.nextLine();
		StudentCourseServices studentCourseServices = new StudentCourseServicesImpl();
		ArrayList<Course> courses = new ArrayList<>();
		try {
			courses = studentCourseServices.viewCourses();
		} catch (CourseServiceExceptions e) {
			System.out.println(e.getMessage());
		}
		if (courses != null) {
			System.out.println("List Of available courses");
			for (int i = 0; i < courses.size(); i++) {
				System.out.println(courses.get(i).getCourseName());
			}
		}
		ArrayList<Student> nameList=new ArrayList<Student>();
		System.out.println("Enter Course Name:");
		courseName = scanner.nextLine();
		Set<String> names = new HashSet<>();
		try {
			names = studentCourseServices.getStudentsByCourse(courseName);
		} catch (CourseServiceExceptions e) {
			System.out.println(e.getMessage());
		}
		if (!names.isEmpty()) {
			
			System.out.println("Students in Course "+courseName);
			Iterator<String> iterator = names.iterator();
			
			while (iterator.hasNext()) {
				Student student=new Student();
				student.setStudentName(iterator.next());
				nameList.add(student);
				CompareNames compareNames=new CompareNames();
				Collections.sort(nameList,compareNames);
			}
			for(int i=0;i<nameList.size();i++) {
				System.out.println(nameList.get(i).getStudentName());
			}
		}

	}

	private void createStudent() {
		Validations validations = new Validations();
		StudentCourseServices studentCourseServices = new StudentCourseServicesImpl();
		String studentID = "", studentName = "", gender = "";
		boolean validName = false, validGender = false;
		System.out.print("Enter Student ID:");
		studentID = scanner.next();
		scanner.nextLine();
		do {
			System.out.print("Enter Student Name:");
			studentName = scanner.nextLine();
			validName = validations.checkName(studentName);
		} while (!validName);
		do {
			System.out.print("Enter Gender[Boy:Girl]:");
			gender = scanner.nextLine();
			validGender = validations.checkGender(gender);
		} while (!validGender);
		boolean isAdded = false;
		try {
			isAdded = studentCourseServices.addStudent(studentID, studentName, gender);
		} catch (StudentServiceException e) {
			System.out.println(e.getMessage());
		}
		if (isAdded) {
			System.out.println("Student Record Added");
		} else {
			System.out.println("Student Record Addition Failed");
		}
	}
	
	

	private void assignStudents() {
		scanner.nextLine();
		StudentCourseServices studentCourseServices = new StudentCourseServicesImpl();
		String studentID = "", courseID = "";
		ArrayList<Course> courses = new ArrayList<>();
		try {
			courses = studentCourseServices.viewCourses();
		} catch (CourseServiceExceptions e) {
			System.out.println(e.getMessage());
		}
		if (courses != null) {
			for (int i = 0; i < courses.size(); i++) {
				System.out.println(courses.get(i).getCourseID() + "-" + courses.get(i).getCourseName());
			}
		}
		System.out.print("Enter Course ID from the above list:");
		courseID = scanner.nextLine();
		ArrayList<Student> students = new ArrayList<>();
		try {
			students = studentCourseServices.viewStudents();
		} catch (StudentServiceException e) {
			System.out.println(e.getMessage());
		}
		if (students != null) {
			for (int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i).getStudentID() + "-" + students.get(i).getStudentName());
			}
		}
		System.out.print("Enter Student ID from the above list:");
		studentID = scanner.nextLine();

		boolean isAssigneed = false;
		try {
			isAssigneed = studentCourseServices.assignCourses(courseID, studentID);
		} catch (CourseServiceExceptions | StudentServiceException e) {
			System.out.println(e.getMessage());
		}
		if (isAssigneed) {
			System.out.println("Course Assigned");
		} else {
			System.out.println("Course Not Assigned");
		}

	}

	private void createCourse() {
		Validations validations = new Validations();
		StudentCourseServices studentCourseServices = new StudentCourseServicesImpl();
		String courseID = "", courseName = "";
		int marks = 0;
		boolean isAdded = false, validName = false;
		scanner.nextLine();
		System.out.print("Enter Course ID:");
		courseID = scanner.next();
		scanner.nextLine();
		do {
			System.out.print("Enter Course Name:");
			courseName = scanner.nextLine();
			validName = validations.checkName(courseName);
		} while (!validName);
		do {
			System.out.print("Enter Marks:");
			marks = scanner.nextInt();
		} while (marks <= 0);
		try {
			isAdded = studentCourseServices.addCourse(courseID, courseName, marks);
		} catch (CourseServiceExceptions e) {
			System.out.println(e.getMessage());
		}
		if (isAdded) {
			System.out.println("Course Added");
		} else {
			System.out.println("Failed to add course");
		}
	}

	private int displayMenu() {
		int choice = 0;
		do {
			System.out.println("1.To Create Course");
			System.out.println("2.To Create Student Details");
			System.out.println("3.To Assign a student to a course");
			System.out.println("4.To get all students by course name");
			choice = scanner.nextInt();
		} while (choice < 1);
		return choice;
	}
}
class CompareNames implements Comparator<Student>
{

	@Override
	public int compare(Student o1, Student o2) {
		
		return (o2.getStudentName().compareToIgnoreCase(o1.getStudentName()));
	}
	
}