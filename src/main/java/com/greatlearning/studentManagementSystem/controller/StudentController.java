package com.greatlearning.studentManagementSystem.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentManagementSystem.model.Student;
import com.greatlearning.studentManagementSystem.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/Student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public String getStudent(Model model) {
        model.addAttribute("students", studentService.getAllStudent());
        return "student";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/add")
    public String addNewStudent(@RequestParam("id") int id, @ModelAttribute Student student) {
        if (id != 0) {
            Student existingStudent = studentService.getStudentById(id);
            existingStudent.setId(id);
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setCountry(student.getCountry());
            existingStudent.setCourse(student.getCourse());
            studentService.updateStudent(existingStudent);
        } else {
            studentService.addStudent(student);
        }

        return "redirect:/Student/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return "redirect:/Student/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student-form";
    }

    @RequestMapping(value = "/403")
    public ModelAndView accesssDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg", "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }

}
