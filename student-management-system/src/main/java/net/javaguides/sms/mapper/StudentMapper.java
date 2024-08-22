package net.javaguides.sms.mapper;

import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;

public class StudentMapper {

    public  static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
        return studentDto;
    }


//    public static Student mapToStudent(StudentDto studentDto){
//
//        Student student = new Student(
//                studentDto.getId(),
//                studentDto.getFirstName(),
//                studentDto.getLastName(),
//                studentDto.getEmail()
//        );
//        return student;



    public static Student mapToStudent(StudentDto studentDto) {
        Student student = new Student();

        // Set id only if it is not null
        if (studentDto.getId() != null) {
            student.setId(studentDto.getId());
        }

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());

        return student;
    }
}
