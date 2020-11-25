package service.servicesimpl;

import java.util.ArrayList;
import java.util.HashSet;

import dao.StudentCourseDao;
import dao.daoimpl.StudentCourseDaoImpl;
import entity.Course;
import entity.Student;
import exceptions.daoexceptions.CourseDaoExceptions;
import exceptions.daoexceptions.StudentDaoExceptions;
import exceptions.serviceexceptions.CourseServiceExceptions;
import exceptions.serviceexceptions.StudentServiceException;
import service.StudentCourseServices;

public class StudentCourseServicesImpl implements StudentCourseServices {

	private StudentCourseDao studentCourseDao = new StudentCourseDaoImpl();

	@Override
	public boolean addCourse(String courseID, String courseName, int marks) throws CourseServiceExceptions {
		Course course = new Course();
		course.setCourseID(courseID);
		course.setCourseName(courseName);
		course.setTotalMarks(marks);
		boolean isAdded = false;
		try {
			isAdded = studentCourseDao.addCourse(course);
		} catch (CourseDaoExceptions e) {
			throw new CourseServiceExceptions(e.getMessage());
		}
		return isAdded;
	}

	@Override
	public ArrayList<Course> viewCourses() throws CourseServiceExceptions {
		ArrayList<Course> courses = new ArrayList<>();
		try {
			courses = studentCourseDao.viewCourses();
		} catch (CourseDaoExceptions e) {
			throw new CourseServiceExceptions(e.getMessage());
		}
		return courses;
	}

	@Override
	public ArrayList<Student> viewStudents() throws StudentServiceException {
		ArrayList<Student> students = new ArrayList<>();
		try {
			students = studentCourseDao.viewStudents();
		} catch (StudentDaoExceptions e) {
			throw new StudentServiceException(e.getMessage());
		}
		return students;
	}

	@Override
	public HashSet<String> getStudentsByCourse(String courseName) throws CourseServiceExceptions {
		HashSet<String> names = new HashSet<>();
		try {
			names = studentCourseDao.getStudentsByCourse(courseName);
		} catch (CourseDaoExceptions e) {
			throw new CourseServiceExceptions(e.getMessage());
		}
		if (names.isEmpty()) {
			throw new CourseServiceExceptions("No students in this department");
		}
		return names;
	}

	@Override
	public boolean addStudent(String studentID, String studentName, String gender) throws StudentServiceException {
		Student student = new Student();
		boolean isAdded = false;
		student.setStudentID(studentID);
		student.setStudentName(studentName);
		student.setGender(gender);
		try {
			isAdded = studentCourseDao.addStudent(student);
		} catch (StudentDaoExceptions e) {
			throw new StudentServiceException(e.getMessage());
		}
		return isAdded;
	}

	@Override
	public boolean assignCourses(String courseID, String studentID)
			throws CourseServiceExceptions, StudentServiceException {
		boolean isAssigned = false;
		try {
			isAssigned = studentCourseDao.assignCourse(courseID, studentID);
		} catch (CourseDaoExceptions e) {
			throw new CourseServiceExceptions(e.getMessage());
		} catch (StudentDaoExceptions e) {
			throw new StudentServiceException(e.getMessage());
		}
		return isAssigned;
	}

}
