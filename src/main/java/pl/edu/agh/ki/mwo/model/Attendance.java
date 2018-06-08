package pl.edu.agh.ki.mwo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendances")
public class Attendance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private double studentId;
	
	@Column
	private String classDate;
	
	@Column
	private double attendance;
	
	@Column
	private double courseId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getStudentId() {
		return studentId;
	}

	public void setId(double studentId) {
		this.studentId = studentId;
	}
	
	public String getClassDate() {
		return classDate;
	}

	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	
	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attend) {
		this.attendance = attendance;
	}
	
	public double getCourseId() {
		return courseId;
	}

	public void setCourseId(double courseId) {
		this.courseId = courseId;
	}

}
