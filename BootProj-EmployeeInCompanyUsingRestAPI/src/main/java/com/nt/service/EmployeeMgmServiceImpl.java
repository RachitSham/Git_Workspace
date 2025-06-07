package com.nt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.exceptions.EmployeeNotFoundException;
import com.nt.repository.EmployeeInfoRepository;
import com.nt.vo.EmployeeVo;

@Service
public class EmployeeMgmServiceImpl implements IEmployeeMgmtService {

	@Autowired
	private EmployeeInfoRepository employeeRepository;

	@Override
	public String registerEmployee(EmployeeVo employeeVo) {
		// To Convert EmployeeVo class object to Employee class obbject
		Employee emp = new Employee();
		BeanUtils.copyProperties(employeeVo, emp);
		emp.setCreatedBy(System.getProperty("user.name"));

		int idVal = employeeRepository.save(emp).getId();
		return "Employee is Registered Successfully !! " + idVal;
	}

	@Override
	public String registerEmployeeBatch(Iterable<EmployeeVo> employeeVo) {
		List<Employee> listEntity = new ArrayList<Employee>();
		employeeVo.forEach(vo -> {
			Employee employeeEntity = new Employee();
			BeanUtils.copyProperties(vo, employeeEntity);
			employeeEntity.setCreatedBy(System.getProperty("user.name"));
			listEntity.add(employeeEntity);
		});

		// save all the object
		Iterable<Employee> savedEmployee = employeeRepository.saveAll(listEntity);
		List<Integer> ids = StreamSupport.stream(savedEmployee.spliterator(), false).map(Employee::getId)
				.collect(Collectors.toList());

		return ids.size() + "Successfully saved All Records to the Database !! " + ids;
	}

	@Override
	public Iterable<EmployeeVo> showAllEmployee() {
		Iterable<Employee> listEntities = employeeRepository.findAll();
		// Convert List Of Entities To List Of Vo
		List<EmployeeVo> listVo = new ArrayList<EmployeeVo>();
		listEntities.forEach(entity -> {
			EmployeeVo empVo = new EmployeeVo();
			BeanUtils.copyProperties(entity, empVo);
			listVo.add(empVo);
		});
		return listVo;
	}

	@Override
	public List<Employee> showEmployeeByCompanies(String company1, String company2, String company3) {
		return employeeRepository.findEmployeeByCompany(company1, company2, company3);
	}

	@Override
	public Employee fetchEmployeeById(int id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(id + "Employee ID not found in the database "));
	}

	@Override
	public String updateEmployeeDeatils(EmployeeVo employeeVo) {
		Employee empEntity = employeeRepository.findById(employeeVo.getId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
		BeanUtils.copyProperties(employeeVo, empEntity);
		empEntity.setUpdatedBy("os-user");
		int idVal = employeeRepository.save(empEntity).getId();
		return idVal + "Employee Deatils have been Updated Successfully !!";
	}

	@Override
	public List<Employee> fetchEmployeeByName(String name) {
		return employeeRepository.findIdByName(name);
	}

	@Override
	public String updateEmployeeSalaryById(int id, double hikePercentage) {
		// get the Employee Id
		Optional<Employee> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			Employee employeeEntity = optional.get();
			double empSalary = employeeEntity.getSalary();
			double newSalary = empSalary + (empSalary * hikePercentage / 100.0);

			employeeEntity.setUpdatedBy("osuser");
			// update employee by new Salarey
			employeeRepository.save(employeeEntity);

			return " Employee budget successfully revised — the updated amount is " + newSalary;

		} else {
			throw new EmployeeNotFoundException(id + " Unable to update: Employee does not exist in the records ");
		}
	}

	@Override
	public String deleteEmployeeById(int id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			employeeRepository.deleteById(id);

			return id + " Employee with the given ID was found and deleted successfully !";
		} else {
			throw new EmployeeNotFoundException(
					id + " Deletion failed — no matching record found for the provided ID ");
		}

	}

	@Override
	public String deleteEmployeeBySalaryRange(double start, double end) {
		int count = employeeRepository.rempoveEmployeeBySalaryRange(start, end);
		return count == 0 ? "Employee not found for deletion" : count + "No.of Employee are found and Deleted";
	}

}
