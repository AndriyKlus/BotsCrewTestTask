package com.botscrew.task.service;

import com.botscrew.task.model.Lector;
import com.botscrew.task.repository.DepartmentRepository;
import com.botscrew.task.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorRepository lectorRepository;

    public String getHeadOfDepartment(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(department -> "Head of " + departmentName + " department is " + department.getHeadOfDepartment().getName())
                .orElse("Department not found");
    }

    public String getDepartmentStatistics(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(department -> {
                    long assistants = department.getLectors().stream().filter(l -> l.getDegree() == Lector.Degree.ASSISTANT).count();
                    long associateProfessors = department.getLectors().stream().filter(l -> l.getDegree() == Lector.Degree.ASSOCIATE_PROFESSOR).count();
                    long professors = department.getLectors().stream().filter(l -> l.getDegree() == Lector.Degree.PROFESSOR).count();
                    return String.format("assistants - %d, \nassociate professors - %d, \nprofessors - %d", assistants, associateProfessors, professors);
                })
                .orElse("Department not found");
    }

    public String getAverageSalary(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(department -> {
                    double averageSalary = department.getLectors().stream().mapToDouble(Lector::getSalary).average().orElse(0);
                    return "The average salary of " + departmentName + " is " + averageSalary;
                })
                .orElse("Department not found");
    }

    public String getEmployeeCount(String departmentName) {
        return departmentRepository.findByName(departmentName)
                .map(department -> "Employee count: " + department.getLectors().size())
                .orElse("Department not found");
    }

    public String globalSearch(String template) {
        List<Lector> lectors = lectorRepository.findByNameContaining(template);
        return lectors.isEmpty() ? "No results found" : lectors.stream().map(Lector::getName).collect(Collectors.joining(", "));
    }
}