package com.angularspring.SpringBootApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularspring.model.Employee;
import com.angularspring.model.User;

@CrossOrigin()
@RestController
@RequestMapping({ "/employees" })
public class EmployeeController {
	private List<Employee> employees = createList();
	private static List<Employee> createList() {
		List<Employee> tempEmployees = new ArrayList<Employee>();
		Employee emp1 = new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId("1");
		emp1.setSalary(3000);

		Employee emp2 = new Employee();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId("2");
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}
	
	@GetMapping(produces = "application/json")
	public List<Employee> firstPage(){
		return employees;
	}
	
	@DeleteMapping(path = {"/{id}"})
	public Employee deleteEmp(@PathVariable("id") String id) {
		employees = employees.stream().filter(emp -> !id.equalsIgnoreCase(emp.getEmpId())).collect(Collectors.toList());
		Employee deletedEmp = employees.stream().filter(emp -> id.equalsIgnoreCase(emp.getEmpId())).findAny().orElse(null);
		System.out.println("deletedEmp : "+deletedEmp);
		
		return deletedEmp;
	}
	
	@PostMapping
	public Employee createEmp(@RequestBody Employee emp) {
		employees.add(emp);
		return emp;
	}
	
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public User validateLogin() {
		System.out.println("in validateLogin");
		return new User("User successfully authenticated");
	}
}
