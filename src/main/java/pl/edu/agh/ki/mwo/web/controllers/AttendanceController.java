package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.model.StudentGroup;
import pl.edu.agh.ki.mwo.model.DegreeCourse;
import pl.edu.agh.ki.mwo.model.Grade;
import pl.edu.agh.ki.mwo.model.Student;
import pl.edu.agh.ki.mwo.persistence.DatabaseConnector;
@Controller
public class AttendanceController {
	
	@RequestMapping(value="/Attendances")
    public String listAttendances(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";

    	model.addAttribute("attendances", DatabaseConnector.getInstance().getAttendances());
    	
        return "attendancesList";    
    }
	

	
    /*
    @RequestMapping(value="/AddStudent")
    public String addStudent(Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	model.addAttribute("studentGroups", DatabaseConnector.getInstance().getSchoolClasses());
    	model.addAttribute("courseDegrees", DatabaseConnector.getInstance().getSchools());
        return "studentForm";    
    }
    
    
    
    @RequestMapping(value="/ModifyStudent")
   public String modifyStudent(@RequestParam(value="studentId", required=false) String studentId, 
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	Student student = DatabaseConnector.getInstance().getStudent(studentId);
    	model.addAttribute("studentName", student.getName());
    	model.addAttribute("studentSurname", student.getSurname());
    	model.addAttribute("studentAdress", student.getAdress());
    	model.addAttribute("studentDateOfBirth", student.getDateOfBirth());
    	model.addAttribute("studentYear", student.getYear());
    	model.addAttribute("studentId",student.getId());
    	model.addAttribute("schoolClassStudent", DatabaseConnector.getInstance().getSpecificStudentClass(studentId));
    	model.addAttribute("studentGroups", DatabaseConnector.getInstance().getGroupBasedOnCourseDagree(student.getStudentGroup().getDegreeCourse().getId()));
        
    	return "studentModForm";
    }
    
    @RequestMapping(value="/showGrades")
   public String showMarks(@RequestParam(value="studentId", required=false) String studentId, 
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	model.addAttribute("grades", DatabaseConnector.getInstance().getGrades(studentId));
    	return "studentGrades";
    }
//    
    @RequestMapping(value="/SaveStudent", method=RequestMethod.POST)
    public String saveStudent(@RequestParam(value="studentName", required=false) String name,
    		@RequestParam(value="studentSurname", required=false) String surname,
    		@RequestParam(value="studentAdress", required=false) String adress,
    		@RequestParam(value="studentDateOfBirth",required=false)  String dateOfBirth,
    		@RequestParam(value="studentYear", required=false) int year,
    		@RequestParam(value="studentId", required=false) String studentId,
    		@RequestParam(value="studentGroups", required=false) StudentGroup group,
    		@RequestParam(value="studentDegreeGroupName", required=false) String studentGroupId,
    		
    		
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	
    	Student student = DatabaseConnector.getInstance().getStudent(studentId);
    
    	student.setName(name);
    	student.setSurname(surname);
    	student.setAdress(adress);
    	student.setDateOfBirth(dateOfBirth);
    	student.setStudentGroup(group);
    	student.setYear(year);
    	
    	   
    	DatabaseConnector.getInstance().addStudent(student, studentGroupId);
       	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	model.addAttribute("message", "Dane studenta zostały zapisane");
         	
    	return "studentList";
    }
//    
//    
//
    @RequestMapping(value="/CreateStudent", method=RequestMethod.POST)
    public String createStudent(@RequestParam(value="studentName", required=false) String name,
    		@RequestParam(value="studentSurname", required=false) String surname,
    		@RequestParam(value="studentAdress", required=false) String adress,
    		@RequestParam(value="studentDateOfBirth",required=false)  String dateOfBirth,
    		@RequestParam(value="studentYear", required=false) int year,
    		@RequestParam(value="studentDegreeGroupName", required=false) String studentGroupId,
//    		@RequestParam(value="studentDegreeName", required=false) String studentDegree,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	
    	
    	Student student = new Student(name, surname, adress, dateOfBirth, year);
    	
    	
    	System.out.println("Aj DI: " +studentGroupId);
    	DatabaseConnector.getInstance().addStudent(student, studentGroupId);
    	//DatabaseConnector.getInstance().getSpecificSchool(studentDegree).addGroup(group);;
    	   
    	
       	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	model.addAttribute("message", "Nowa uczen zostal dodany");
         	
    	return "studentList";
    }
//    
//
//    
//    
//    
    @RequestMapping(value="/DeleteStudent", method=RequestMethod.POST)
    public String deleteStudent(@RequestParam(value="studentId", required=false) String studentId,
    		Model model, HttpSession session) {    	
    	if (session.getAttribute("userLogin") == null)
    		return "redirect:/Login";
    	
    	DatabaseConnector.getInstance().deleteStudent(studentId);  	
       	model.addAttribute("students", DatabaseConnector.getInstance().getStudents());
    	model.addAttribute("message", "Student został usunięty");
         	
    	return "studentList";
    }
*/
}
