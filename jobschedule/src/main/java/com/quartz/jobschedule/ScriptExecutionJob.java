package com.quartz.jobschedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ScriptExecutionJob implements Job {

    private static final String SCRIPT_PATH = "D:\\CODE\\JavaSample\\jobschedule\\src\\main\\resources\\script.bat";
    private static final int MAX_RETRIES = 3;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int attempt = 0;
        boolean success = false;

        while (attempt < MAX_RETRIES && !success) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", SCRIPT_PATH);
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();


                int exitCode = process.waitFor();
                System.out.println("Script exited with code: " + exitCode);

                if (exitCode == 0) {
                    success = true;
                    System.out.println("Script executed successfully.");
                } else {
                    attempt++;
                    System.out.println("Script failed. Retrying... Attempt: " + attempt);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (Exception e) {
                attempt++;
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Error executing script: " + e.getMessage());
            }
        }

        if (!success) {
            throw new JobExecutionException("Script execution failed after " + MAX_RETRIES + " attempts.");
        }
    }
}
