package net.javaguides.sms.controller;

import jakarta.validation.Valid;
import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }




    //handler method to handle list student request
    @GetMapping("/students")
    public String listStudents(Model model){
      List<StudentDto> students = studentService.getAllStudents();
      model.addAttribute("students", students);
      return "students";
    }

    // handler method to handle new student request
    @GetMapping("/students/new")
    public String newStudent(Model model){  // declares method to handel request and return view name
        StudentDto studentDto = new StudentDto();         //creating an instance (studentDto)to hold the form data of a new student
        model.addAttribute("student", studentDto);
        return "create_student";
    }

    // handler method to handle save student form submit request
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
                              BindingResult result,
                              Model model) {
        // Check for validation errors
        if (result.hasErrors()) {
            // If there are errors, add the student object back to the model
            model.addAttribute("student", student);
            return "create_student"; // Return to the form page to display validation errors
        }

        // Save the student if there are no validation errors
        studentService.createStudent(student);
        return "redirect:/students"; // Redirect to the list of students after successful save
    }


    //handler method to handel Edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId,
                              Model model){
        StudentDto student = studentService.getStudentById(studentId);
     model.addAttribute("student", student); // uses the student key to add the list of students
     return "edit_student";
    }


//handler method to handle edit student form submit request
@PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student")StudentDto studentDto,
                                BindingResult result, //Captures the result of the validation.    //@valid trigger validation on the studentDto object
                                Model model){
        if (result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
    studentDto.setId(studentId);
    studentService.updateStudent(studentDto);
    return "redirect:/students";
}

  //handler method to handle delete student request
    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    // Handler method to handle view student request
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId,
                              Model model ){
         StudentDto studentDto = studentService.getStudentById(studentId);
         model.addAttribute("student", studentDto);
         return "view_student";
    }
}

