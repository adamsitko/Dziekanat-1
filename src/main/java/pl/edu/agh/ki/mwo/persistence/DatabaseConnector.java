package pl.edu.agh.ki.mwo.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.edu.agh.ki.mwo.model.Attendance;
import pl.edu.agh.ki.mwo.model.Course;
import pl.edu.agh.ki.mwo.model.DegreeCourse;
import pl.edu.agh.ki.mwo.model.Grade;
import pl.edu.agh.ki.mwo.model.StudentGroup;
import pl.edu.agh.ki.mwo.model.Teacher;
import pl.edu.agh.ki.mwo.model.Student;

public class DatabaseConnector {
	
	protected static DatabaseConnector instance = null;
	
	public static DatabaseConnector getInstance() {
		if (instance == null) {
			instance = new DatabaseConnector();
		}
		return instance;
	}
		
		Session session;
	
		protected DatabaseConnector() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void teardown() {
		session.close();
		HibernateUtil.shutdown();
		instance = null;
	}
	
	public Iterable<DegreeCourse> getSchools() {
		String hql = "FROM DegreeCourse";
		Query query = session.createQuery(hql);
		List <DegreeCourse>schools = query.list();
		
		for(DegreeCourse scho: schools) {
			System.out.println(scho.getName());
		}
		
		return schools;
	}
	
	public DegreeCourse getSpecificSchool(String schoolId) {
		String hql = "FROM DegreeCourse S WHERE S.id="+schoolId;
		Query query = session.createQuery(hql);
		List<DegreeCourse> results = query.list();
		return results.get(0);
	}
	
	
	
	
	
	public void addSchool(DegreeCourse school) {
		Transaction transaction = session.beginTransaction();
		session.save(school);
		transaction.commit();
	}
	
	public void deleteSchool(String schoolId) {
		String hql = "FROM DegreeCourse S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<DegreeCourse> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (DegreeCourse s : results) {
			session.delete(s);
		}
		transaction.commit();
	}

	public Iterable<StudentGroup> getSchoolClasses() {
		String hql = "FROM StudentGroup";
		Query query = session.createQuery(hql);
		List <StudentGroup> schoolClasses = query.list();
		
		for(StudentGroup stu: schoolClasses) {
			System.out.println(stu.getName());
		}
		
		return schoolClasses;
	}
	
	public Iterable<StudentGroup> getGroupBasedOnCourseDagree(long l) {
		String hql = "SELECT S FROM StudentGroup S  INNER JOIN S.degreeCourse degreeCourse WHERE degreeCourse.id="+l;
		Query query = session.createQuery(hql);
		List <StudentGroup> schoolClasses = query.list();
		for(StudentGroup stu:schoolClasses) {
			System.out.println("LOL " +stu.getName() );
		}
		return schoolClasses;
	}
	
	
	
	public void addSchoolClass(StudentGroup schoolClass, String schoolId) {
		String hql = "FROM DegreeCourse S WHERE S.id=" + schoolId;
		Query query = session.createQuery(hql);
		List<DegreeCourse> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(schoolClass);
		} else {
			DegreeCourse school = results.get(0);
			school.addGroup(schoolClass);
			session.save(school);
		}
		transaction.commit();
	}
	
	public void deleteSchoolClass(String schoolClassId) {
		String hql = "FROM StudentGroup S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<StudentGroup> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (StudentGroup s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
	//Students
	public Iterable<Student> getStudents() {
		String hql = "FROM Student";
		Query query = session.createQuery(hql);
		List <Student> students = query.list();
		
		for (Student stu: students) {
			System.out.println(stu.getStudentGroup().getDegreeCourse());
			
		}
		
		return students;
	}
	
	public void getTestStudents() {
		Student student = (Student) session.get(Student.class, 1);
		System.out.println(student.getName());
	}
	
	public Student getStudent(String studentId) {
		String hql = "FROM Student S WHERE S.id="+studentId;
		Query query = session.createQuery(hql);
		List<Student> results = query.list();
		return results.get(0);
	}
//	
	public void addStudent(Student student, String schoolClassId) {
		String hql = "FROM StudentGroup S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<StudentGroup> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(student);
		} else {
			StudentGroup schoolClass = results.get(0);
			schoolClass.add(student);
			session.save(student);
			session.save(schoolClass);
		}
		session.getTransaction().commit();
		System.out.println("Do widzenia");
	}
	
//	
	public void saveModifiedStudent(Student student, String schoolClassId, String degreeCourseId) {
		String hql = "FROM StudentGroup S WHERE S.id=" + schoolClassId;
		Query query = session.createQuery(hql);
		List<StudentGroup> results = query.list();
		Transaction transaction = session.beginTransaction();
		if (results.size() == 0) {
			session.save(student);
		} else {
			StudentGroup schoolClass = results.get(0);
			session.save(schoolClass);
	
		transaction.commit();
	}
	}
//	
//	
	public long getSpecificStudentClass(String studentId) {
		String hql = "SELECT S FROM StudentGroup S  INNER JOIN S.studList student WHERE student.id="+studentId;
		Query query = session.createQuery(hql);
		StudentGroup sclass = (StudentGroup) query.list().get(0);
		return sclass.getId();
	}
//
//	
	public void deleteStudent(String studentId) {
		String hql = "FROM Student S WHERE S.id=" + studentId;
		Query query = session.createQuery(hql);
		List<Student> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (Student s : results) {
			session.delete(s);
		}
		transaction.commit();
	}
	
	public Iterable<Grade> getGrades(String studentId) {
		String hql = "SELECT S FROM Grade S INNER JOIN S.student stud WHERE stud.id="+studentId;
		Query query = session.createQuery(hql);
		List <Grade> grades = query.list();
		return grades;
	}
//courses
	public Iterable<Course> getCourses() {
		String hql = "FROM Course";
		Query query = session.createQuery(hql);
		List <Course> courses = query.list();
		
		for (Course c: courses) {
			System.out.println(c.getName());
			
		}
		
		return courses;
	}

	public Course getCourse(String courseId) {
		String hql = "FROM Course C WHERE C.id="+courseId;
		Query query = session.createQuery(hql);
		List<Course> results = query.list();
		return results.get(0);
	}
	

	public void deleteCourse(String courseId) {
		String hql = "FROM Course C WHERE C.id=" + courseId;
		Query query = session.createQuery(hql);
		List<Course> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (Course course : results) {
			session.delete(course);
		}
		transaction.commit();
		
	}
	
//teacher
	public Teacher getTeacher(String teacherId) {
		String hql = "FROM Teacher t WHERE t.id="+teacherId;
		Query query = session.createQuery(hql);
		List<Teacher> results = query.list();
		return results.get(0);
	}
//attendance
	public Iterable<Attendance> getAttendances() {
		String hql = "FROM Attendance";
		Query query = session.createQuery(hql);
		List <Attendance> attendances = query.list();
		
		return attendances;
	}




}


