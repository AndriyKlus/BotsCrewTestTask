package com.botscrew.task;

import com.botscrew.task.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private UniversityService universityService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start of communication");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            if (input.startsWith("Who is head of department")) {
                String departmentName = input.replace("Who is head of department ", "");
                System.out.println(universityService.getHeadOfDepartment(departmentName));
            } else if (input.startsWith("Show ")) {
                if (input.endsWith("statistics.")) {
                    String departmentName = input.replace("Show ", "").replace(" statistics.", "");
                    System.out.println(universityService.getDepartmentStatistics(departmentName));
                } else if (input.contains("the average salary for the department ")) {
                    String departmentName = input.replace("Show the average salary for the department ", "").replace(".", "");
                    System.out.println(universityService.getAverageSalary(departmentName));
                } else if (input.contains(" count of employee for ")) {
                    String departmentName = input.replace("Show count of employee for ", "");
                    System.out.println(universityService.getEmployeeCount(departmentName));
                } else {
                    System.out.println("Invalid command");
                }
            } else if (input.startsWith("Global search by ")) {
                String template = input.replace("Global search by ", "");
                System.out.println(universityService.globalSearch(template));
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}