package org.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController (StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudent();
    }
    @PostMapping
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") String studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}
