package com.changr.runner;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Map;


@SpringBootApplication
public class RunnerApplication implements CommandLineRunner {

	@Autowired
	AmazonS3 s3;

	Logger logger = LoggerFactory.getLogger(RunnerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerApplication.class, args);
	}


	@Override
	public void run(String... args) {
		logger.info("EXECUTING : screenshot runner");

		Map<String, String> env = System.getenv();

		String url = env.get("URL");
		String bucket = env.get("BUCKET");
		String key = env.get("KEY");

		logger.info("FETCHING : "+url);


		File outputFile = new Screenshot().takeScreenshot(url);

		System.out.println("Finished taking screenshot");

		if(outputFile != null)
			s3.putObject(bucket, key, outputFile);
		else
		{
			System.out.println("Error getting screenshot");
		}
	}
}

