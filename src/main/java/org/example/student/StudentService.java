package org.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

/*B Rules*/
@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(String studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new IllegalStateException("user does not exists " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(String studentId, String name, String email) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new IllegalStateException("user does not exists " + studentId);
        }
        Student student = studentOptional.get();
        if(name != null && !name.isEmpty() && !name.equals(student.getName())){
            student.setName(name);
        }
        if(email != null && !email.isEmpty() && !email.equals(student.getEmail())){
            Optional<Student> studentOptional1 = studentRepository.findStudentByEmail(email);
            if(studentOptional1.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
        Student save = studentRepository.save(student);
    }
}

/*return List.of(new Student("Marito Baracus",
                "Marito@gmail.com",
                LocalDate.of(2000, Month.JANUARY,25),
                24));*/