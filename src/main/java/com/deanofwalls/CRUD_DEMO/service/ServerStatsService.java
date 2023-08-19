package com.deanofwalls.CRUD_DEMO.service;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

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

    public String getJvmMemory() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory(); // total memory allocated to JVM
        long freeMemory = runtime.freeMemory(); // free memory within the JVM
        long usedMemory = totalMemory - freeMemory; // used memory
        return "Used: " + formatSize(usedMemory) + " / Total Allocated: " + formatSize(totalMemory);
    }

    private String formatSize(long size) {
        String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int unit = 0;
        while (size >= 1024 && unit < units.length - 1) {
            size /= 1024;
            unit++;
        }
        return size + " " + units[unit];
    }

}
