package com.spring.process.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Executor {
	
	private static String workingDirectory = "/Users/hemasundar";
	private static String outputPath = "src/main/java/data/output.txt";
	private static final Logger logger = LogManager.getLogger(Executor.class);
	
	public static void writeOutput(String output) {
		
		try {
			Files.write(Paths.get(outputPath), output.getBytes());
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public String getResults() {
		String results = null;
		try {
			byte[] content =  Files.readAllBytes(Paths.get(outputPath));
			results = new String(content);
		} catch (IOException e) {
			logger.error(e);
		}
		return results;
	}
	
	public void runCommand(String command) {	
		List<String> commandList = Arrays.stream(command.split(" ")).map(item -> item.trim()).collect(Collectors.toList());
		logger.info("Executing Command: " + commandList);
		StringBuilder sb = new StringBuilder();	
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(commandList);
			pb.directory(new File(workingDirectory));
			pb.redirectErrorStream(true);
			Process process = pb.start();
			InputStream inputStream = process.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line =bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			process.waitFor();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		writeOutput(sb.toString());
	}
}	
