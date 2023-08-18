package com.deanofwalls.CRUD_DEMO.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ServerStatsService {

    public int getCpuCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public String getCpuSpeed() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "grep 'cpu MHz' /proc/cpuinfo | uniq");

        Process process = processBuilder.start();
        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.readLine();
        }
        process.waitFor();
        return output.split(":")[1].trim();
    }

    public String getRam() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "free -h | grep Mem | awk '{print $2}'");

        Process process = processBuilder.start();
        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.readLine();
        }
        process.waitFor();
        return output;
    }
}
