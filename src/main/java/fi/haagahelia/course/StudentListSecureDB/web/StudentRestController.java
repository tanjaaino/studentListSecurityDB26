package fi.haagahelia.course.StudentListSecureDB.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.StudentListSecureDB.domain.Student;
import fi.haagahelia.course.StudentListSecureDB.domain.StudentRepository;

@CrossOrigin
@Controller
@RequestMapping("/rest")
public class StudentRestController {

    private StudentRepository studentRepository; 
	

    // contructor injection
    public StudentRestController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
       
    }

    // RESTful service to get all students
    @GetMapping(value="/students")
    public @ResponseBody List<Student> studentListRest() {	
        return (List<Student>) studentRepository.findAll();
    }    

	// RESTful service to get student by id
    @GetMapping("/students/{id}")
    public @ResponseBody Optional<Student> findStudentRest(@PathVariable("id") Long studentId) {	
    	return studentRepository.findById(studentId);
    }    

}
