package com.changr.runner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
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
		String baselineKey = env.get("BASELINEKEY");

		logger.info("FETCHING : "+url);

		File outputFile = new Screenshot().takeScreenshot(url);
		logger.info("Finished taking screenshot");

		if(outputFile != null)
			s3.putObject(bucket, key, outputFile);
		else
		{
			logger.error("Error getting screenshot");
			return;
		}

		logger.info("Comparing screenshot");
		String baselineFile = "/baseline/out.png";
		String diffFile = getDiffFile(outputFile.toString());

		if(!s3.doesObjectExist(bucket, baselineKey))
		{
			logger.info("No baseline");
			return;
		}

		try {
			FileOutputStream os = new FileOutputStream(baselineFile);
			IOUtils.copy(s3.getObject(bucket, baselineKey).getObjectContent(), os);
		} catch (IOException e) {
			logger.error("Error getting baseline");
			e.printStackTrace();
		}

		Diff diff = new Diff();
		boolean same = diff.compare(outputFile.toString(), baselineFile, diffFile);


        if(!same)
		{
		    logger.info("Diff didn't match.  Uploading diff");
            s3.putObject(bucket, getDiffFile(key), new File(diffFile));
		}
		else
        {
            logger.info("Compare matched");
        }
	}

	String getDiffFile(String file)
	{
		return file.toString().replace(".png", "-diff.png");
	}
}

