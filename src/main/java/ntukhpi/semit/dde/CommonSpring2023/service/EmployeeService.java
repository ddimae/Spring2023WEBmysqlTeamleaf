package ntukhpi.semit.dde.CommonSpring2023.service;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;

import java.util.List;

public interface EmployeeService {
	List<Employee> getAllEmployees();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);
}
