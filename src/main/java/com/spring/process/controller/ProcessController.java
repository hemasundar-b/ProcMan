package com.spring.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {
	
	@Autowired
	private Executor executor;
	
	@RequestMapping("command")
	public String runCommand() {
		return "Hello";
		//return executor.runCommand(command);
		
	}

}
