package pl.edu.agh.ki.mwo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="attendances")
public class Attendance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id",nullable=true)
	private Student student;
	
	@Column
	private String class_date;
	
	@Column
	private double attendance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id",nullable=true)
	private Course course;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setId(Student student) {
		this.student = student;
	}
	
	public String getClassDate() {
		return class_date;
	}

	public void setClassDate(String class_date) {
		this.class_date = class_date;
	}
	
	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
