package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@RequestMapping("/employee")
	public String healthCheck() {
		return "Employee Controller is Up !!!";
	}
}
