package com.spring.process.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutorController {

	private static final Logger logger = LogManager.getLogger(ExecutorController.class);

	@Autowired
	private ExecutorService executor;

	@RequestMapping(value = "/command", method = RequestMethod.POST, consumes = "text/plain")
	public void runCommand(@RequestBody String commandPayLoad) throws Exception {
		logger.info(commandPayLoad);
		executor.runCommand(commandPayLoad);

	}

	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	public String monitor() {
		return executor.getResults();
	}

}
