package com.spring.process.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Executor {
	
	private static final Logger logger = LogManager.getLogger(Executor.class);
	
	public String runCommand(String command) {	
		List<String> commandList = Arrays.stream(command.split(" ")).map(item -> item.trim()).collect(Collectors.toList());
		
		StringBuilder sb = new StringBuilder();	
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(commandList);
			pb.redirectErrorStream(true);
			Process process = pb.start();
			InputStream inputStream = process.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			bufferedReader.lines().forEach(sb::append);	
			process.waitFor();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return sb.toString();
	}
}	
