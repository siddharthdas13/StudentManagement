package dao;

import java.util.ArrayList;
import java.util.HashSet;

import entity.Course;
import entity.Student;
import exceptions.daoexceptions.CourseDaoExceptions;
import exceptions.daoexceptions.StudentDaoExceptions;

public interface StudentCourseDao {
	public boolean addCourse(Course course) throws CourseDaoExceptions;

	public boolean addStudent(Student student) throws StudentDaoExceptions;

	public ArrayList<Course> viewCourses() throws CourseDaoExceptions;

	public ArrayList<Student> viewStudents() throws StudentDaoExceptions;

	public HashSet<String> getStudentsByCourse(String courseName) throws CourseDaoExceptions;

	public boolean assignCourse(String courseID, String studentID) throws CourseDaoExceptions, StudentDaoExceptions;

}
