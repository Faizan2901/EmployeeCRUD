package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data



	private EmployeeService employeeService;

	// Constructor Injection
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
			this.employeeService=employeeService;
	}


	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees=employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		for(Employee em:theEmployees){
			System.out.println(em);
		}
		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee theEmployee=new Employee();
		theModel.addAttribute("employee",theEmployee);

		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		System.out.println("Hello"+employee.getFirstName());
		 employeeService.save(employee);
		 return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String updateEmployee(@RequestParam("employeeId") int Id,Model theModel){

		Employee tempEmployee=employeeService.findById(Id);

		theModel.addAttribute("employee",tempEmployee);

		return "employees/employee-form";

	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int Id){
		employeeService.deleteById(Id);
		return "redirect:/employees/list";
	}

}









