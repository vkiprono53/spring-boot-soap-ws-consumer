package com.vkiprono.studentwsclient;

import com.vkiprono.studentwsclient.services.StudentClient;
import com.vkiprono.studentwsclient.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentwsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentwsClientApplication.class, args);

    }

    @Bean
    CommandLineRunner lookup1(StudentClient studentClient) {
        return args -> {

            System.out.println("================================================");
            System.out.println("::::START GET_STUDENT_BY_ADMNO:::::");
            String admNo = "AC005";
            GetStudentByAdmResponse response = studentClient.getStudentByAdm(admNo);
            System.out.println("Adm : " + response.getStudent().getAdmNo() + " FirstName : " + response.getStudent().getFirstName());
            System.out.println("::::END GET_STUDENT_BY_ADMNO:::::");
            System.out.println("================================================");

            System.out.println("::::START DELETE:::::");
            DeleteStudentResponse deleteStudentResponse = studentClient.deleteStudentByAdm(admNo);
            System.out.println("Status = " + deleteStudentResponse.getServiceStatus().getStatusCode() + " Message " +
                    deleteStudentResponse.getServiceStatus().getMessage());
            System.out.println("::::END DELETE:::::");

            System.out.println("================================================");

            System.out.println("::::START ADD STUDENT:::::");
            Student student = new Student();
            student.setAdmNo("CS010");
            student.setFirstName("Jay");
            student.setLastName("Mo");
            student.setCourse("Telecommunication");
            student.setAge(24);
            student.setGender("Male");

            AddStudentResponse addStudentResponse = studentClient.addStudent(student);

            System.out.println("Status : " + addStudentResponse.getServiceStatus().getStatusCode()
                    + " Message : " + addStudentResponse.getServiceStatus().getMessage());
            System.out.println("::::END ADD STUDENT:::::");
            System.out.println("================================================");

            System.out.println("::::START UPDATE STUDENT:::::");

            Student student1 = new Student();
            student1.setStudentId(9);
            student1.setAdmNo("CS009");
            student1.setFirstName("Mercy");
            student1.setLastName("Nuala");
            student1.setCourse("Computer Science");
            student1.setAge(25);
            student1.setGender("Female");

            UpdateStudentResponse updateStudentResponse = studentClient.updateStudent(student1);
            System.out.println("Status " + updateStudentResponse.getServiceStatus().getStatusCode() +
                    " Message : " + updateStudentResponse.getServiceStatus().getMessage());

            System.out.println("::::END UPDATE STUDENT:::::");
            System.out.println("================================================");


        };

    }
}
