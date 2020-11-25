package entity;

public class Student implements Comparable<Student> {
	private String studentName, gender, studentID;

	public Student() {
		studentID = "";
		gender = "";
		studentName = "";
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	@Override
	public int compareTo(Student o) {
		return this.studentName.compareTo(o.studentName);
	}
	

}
