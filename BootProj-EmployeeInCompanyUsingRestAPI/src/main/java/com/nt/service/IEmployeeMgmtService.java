package com.nt.service;

import java.util.List;

import com.nt.entity.Employee;
import com.nt.vo.EmployeeVo;

public interface IEmployeeMgmtService {

	public String registerEmployee(EmployeeVo employeeVo);

	public String registerEmployeeBatch(Iterable<EmployeeVo> employeeVo);

	public Iterable<EmployeeVo> showAllEmployee();

	public List<Employee> showEmployeeByCompanies(String company1, String company2, String company3);
	
	public Employee fetchEmployeeById(int id);
	
	public String updateEmployeeDeatils(EmployeeVo employeeVo);
	
	public List<Employee> fetchEmployeeByName(String name);

	public String updateEmployeeSalaryById(int id, double hikePercentage);
	
	public String deleteEmployeeById(int id);

	public String deleteEmployeeBySalaryRange(double start, double end);
}
