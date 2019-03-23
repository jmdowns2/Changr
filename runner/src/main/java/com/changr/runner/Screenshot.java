package com.changr.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class Screenshot {

    Logger logger = LoggerFactory.getLogger(RunnerApplication.class);

    final String SCREENSHOT_SCRIPT = "/scripts/screenshot.js";

    private String getOutputFilename()
    {
        return "test.png";
    }

    public static final String MOBILE = "375x812";
    public static final String LAPTOP = "1200x800";

    public File takeScreenshot(String url)
    {
        String size = MOBILE;

        String outputfile = "/output/"+getOutputFilename();
        String command = String.format("phantomjs %s %s %s %s", SCREENSHOT_SCRIPT, url, outputfile, size);

        System.out.println("Command="+command);
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});

            int code = process.waitFor();

            logger.info(process.getOutputStream().toString());
            return new File(outputfile);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return null;
    }
}
