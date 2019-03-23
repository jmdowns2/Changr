package com.changr.runner;

public class Diff {

    final String DIFF_SCRIPT = "/scripts/diff.js";

    Boolean compare(String outputFile, String baselineFile, String diffFile)
    {
        String command = String.format("nodejs %s %s %s %s", DIFF_SCRIPT, outputFile, baselineFile, diffFile);
        System.out.println("Command="+command);
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
            int code = process.waitFor();
            return code == 0; // 0 == success
        }
        catch(Exception e)
        {
            System.out.println("Error running diff");
        }
        return false;
    }

}
