package org.example.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student Alice = new Student(
                    "Alicia",
                    "alicica@gmail.com",
                    LocalDate.of(2000,1,23),
                    24
            );
            Student Bob = new Student(
                    "Bruno",
                    "Bruno@gmail.com",
                    LocalDate.of(2005,10,25),
                    19
            );
            studentRepository.saveAll(List.of(Alice, Bob));
        };
    }

}
