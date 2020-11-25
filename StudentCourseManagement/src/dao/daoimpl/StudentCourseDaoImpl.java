package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;

import dao.StudentCourseDao;
import entity.Course;
import entity.Student;
import exceptions.daoexceptions.CourseDaoExceptions;
import exceptions.daoexceptions.StudentDaoExceptions;
import utility.ConnectionUtility;

public class StudentCourseDaoImpl implements StudentCourseDao {

	@Override
	public boolean addCourse(Course course) throws CourseDaoExceptions {
		ConnectionUtility connectionUtility = new ConnectionUtility();
		boolean isInserted = false;
		Connection connection = null;
		String errorMesaage = "";
		try {
			errorMesaage = "Connection to DB Failed";
			connection = connectionUtility.getConnection();
		} catch (SQLException e) {
			throw new CourseDaoExceptions(errorMesaage, e);
		}
		PreparedStatement preparedStatement = null;
		String query = "insert into course values(?,?,?)";
		try {
			errorMesaage = "Insertion Error";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, course.getCourseID());
			preparedStatement.setString(2, course.getCourseName());
			preparedStatement.setInt(3, course.getTotalMarks());
			preparedStatement.execute();
			isInserted = true;
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new CourseDaoExceptions("A course with that ID already exists");
		} catch (SQLException e) {
			throw new CourseDaoExceptions(errorMesaage, e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				throw new CourseDaoExceptions(e2.getMessage());
			}
		}
		return isInserted;
	}

	@Override
	public ArrayList<Course> viewCourses() throws CourseDaoExceptions {
		ArrayList<Course> courses = new ArrayList<>();
		ConnectionUtility connectionUtility = new ConnectionUtility();
		Connection connection = null;
		String errorMesaage = "";
		try {
			errorMesaage = "Connection to DB Failed";
			connection = connectionUtility.getConnection();
		} catch (SQLException e) {
			throw new CourseDaoExceptions(errorMesaage, e);
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String query = "select courseid,coursename from course";
		try {
			errorMesaage = "Retreival Error";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course = new Course();
				String courseID = resultSet.getString(1);
				String courseNAme = resultSet.getString(2);
				course.setCourseID(courseID);
				course.setCourseName(courseNAme);
				courses.add(course);
			}
		} catch (Exception e) {
			throw new CourseDaoExceptions();
		} finally {
			try {
				if(resultSet!=null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				throw new CourseDaoExceptions(e2.getMessage());
			}
		}
		return courses;
	}

	@Override
	public ArrayList<Student> viewStudents() throws StudentDaoExceptions {
		ArrayList<Student> students = new ArrayList<>();
		ConnectionUtility connectionUtility = new ConnectionUtility();
		Connection connection = null;
		String errorMesaage = "";
		try {
			errorMesaage = "Connection to DB Failed";
			connection = connectionUtility.getConnection();
		} catch (SQLException e) {
			throw new StudentDaoExceptions(errorMesaage, e);
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String query = "select studentid,studentname from student";
		try {
			errorMesaage = "Retreival Error";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				String studentID = resultSet.getString(1);
				String studentNAme = resultSet.getString(2);
				student.setStudentID(studentID);
				student.setStudentName(studentNAme);
				students.add(student);
			}
		} catch (Exception e) {
			throw new StudentDaoExceptions(errorMesaage, e);
		} finally {
			try {
				if(resultSet!=null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				throw new StudentDaoExceptions(e2.getMessage());
			}
		}
		return students;
	}

	@Override
	public boolean addStudent(Student student) throws StudentDaoExceptions {
		ConnectionUtility connectionUtility = new ConnectionUtility();
		boolean isInserted = false;
		Connection connection = null;
		String errorMesaage = "";
		try {
			errorMesaage = "Connection to DB Failed";
			connection = connectionUtility.getConnection();
		} catch (SQLException e) {
			throw new StudentDaoExceptions(errorMesaage, e);
		}
		PreparedStatement preparedStatement = null;
		String query = "insert into student values(?,?,?)";
		try {
			errorMesaage = "Insertion Error";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, student.getStudentID());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.execute();
			isInserted = true;
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new StudentDaoExceptions("A student with that ID already exists");
		} catch (SQLException e) {
			throw new StudentDaoExceptions(errorMesaage, e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				throw new StudentDaoExceptions(e2.getMessage());
			}
		}
		return isInserted;
	}

	@Override
	public boolean assignCourse(String courseID, String studentID) throws CourseDaoExceptions, StudentDaoExceptions {
		ConnectionUtility connectionUtility = new ConnectionUtility();
		boolean isInserted = false;
		Connection connection = null;
		String errorMesaage = "";
		try {
			errorMesaage = "Connection to DB Failed";
			connection = connectionUtility.getConnection();
		} catch (SQLException e) {
			throw new StudentDaoExceptions(errorMesaage, e);
		}
		PreparedStatement preparedStatement = null;
		String query = "insert into studentcourses values(?,?)";
		try {
			errorMesaage = "Insertion Error";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, courseID);
			preparedStatement.setString(2, studentID);
			preparedStatement.execute();
			isInserted = true;
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new StudentDaoExceptions("No student/course with that ID exist");
		} catch (SQLException e) {
			throw new StudentDaoExceptions(errorMesaage, e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				throw new CourseDaoExceptions(e2.getMessage());
			}
		}
		return isInserted;
	}

	@Override
	public HashSet<String> getStudentsByCourse(String courseName) throws CourseDaoExceptions {
		HashSet<String> names = new HashSet<>();
		ConnectionUtility connectionUtility = new ConnectionUtility();
		Connection connection = null;
		String errorMesaage = "";
		try {
			errorMesaage = "Connection to DB Failed";
			connection = connectionUtility.getConnection();
		} catch (SQLException e) {
			throw new CourseDaoExceptions(errorMesaage, e);
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		String query = "select student.studentname from student,studentcourses where studentcourses.courseid="
				+ "(select distinct courseid from studentcourses where courseid="
				+ "(select courseid from course where coursename='" + courseName + "'))";
		try {
			errorMesaage = "Retreival Error";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String studentName = resultSet.getString(1);
				names.add(studentName);
			}
		} catch (Exception e) {
			throw new CourseDaoExceptions();
		} finally {
			try {
				if(resultSet!=null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				throw new CourseDaoExceptions(e2.getMessage());
			}
		}
		return names;
	}

}
