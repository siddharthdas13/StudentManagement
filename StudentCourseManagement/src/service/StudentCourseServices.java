package service;

import java.util.ArrayList;
import java.util.HashSet;

import entity.Course;
import entity.Student;
import exceptions.serviceexceptions.CourseServiceExceptions;
import exceptions.serviceexceptions.StudentServiceException;

public interface StudentCourseServices {
	public boolean addCourse(String courseID, String courseName, int marks) throws CourseServiceExceptions;

	public boolean addStudent(String studentID, String studentName, String gender) throws StudentServiceException;

	public ArrayList<Course> viewCourses() throws CourseServiceExceptions;

	public ArrayList<Student> viewStudents() throws StudentServiceException;

	public HashSet<String> getStudentsByCourse(String courseName) throws CourseServiceExceptions;

	public boolean assignCourses(String courseID, String studentID)
			throws CourseServiceExceptions, StudentServiceException;
}
