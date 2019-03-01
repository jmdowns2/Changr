package com.changr.runner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

public class Screenshot {

    final String SCREENSHOT_SCRIPT = "/scripts/screenshot.js";

    private String getOutputFilename()
    {
        return "test.jpg";
        /*
        byte[] array = new byte[20];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8")) + ".jpg";
        */
    }

    public File takeScreenshot(String url)
    {
        String outputfile = "/output/"+getOutputFilename();
        String command = String.format("phantomjs %s %s %s", SCREENSHOT_SCRIPT, url, outputfile);

        System.out.println("Command="+command);
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
//                        .exec(String.format("sh -c %s", command));

            int code = process.waitFor();
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
