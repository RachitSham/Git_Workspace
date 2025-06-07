package com.nt.restcontroller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeMgmtService;
import com.nt.utility.Constants;
import com.nt.vo.EmployeeVo;
import com.nt.vo.ResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/employee-api")
@Tag(name = "WorkForce360", description = "A 360° view and control of your employees, from profile to performance.")
public class EmployeeController {

	@Autowired
	private IEmployeeMgmtService employeeService;

	@PostMapping("/registeremployee")
	@Operation(summary = "Employee details recorded successfully - Welcome to the Company !!", description = "This message confirms that a new employee's information has been captured and stored successfully in the database, making them an active member of the organization")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Employee details recorded successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> enrolledEmployee(@RequestBody EmployeeVo employeeVo) {
		String employee = employeeService.registerEmployee(employeeVo);
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				" Employee has been successfully registered to the system — Get ready for an New Company !! ",
				employee));

	}

	@PostMapping("/employeebatch")
	@Operation(summary = "Employee records added in batch successfully !! ", description = "The employee batch has been processed and registered successfully !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = " You can now view or manage their profiles in the dashboard !! ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> enrolledAllEmployeeBatch(@RequestBody Iterable<EmployeeVo> employeeVo) {
		String employeeBatch = employeeService.registerEmployeeBatch(employeeVo);

		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"All employees in the batch have been registered successfully !!", employeeBatch));
	}

	@GetMapping("/getallemployee")
	@Operation(summary = "Fetch Employee records in batch successfully !! ", description = "The employee batch has been processed and registered successfully !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "You can now view or manage their profiles in the dashboard !! ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> fetchAllEmployee() {
		Iterable<EmployeeVo> allEmployee = employeeService.showAllEmployee();
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"All employees in the batch have been registered successfully !!", allEmployee));
	}

	@GetMapping("/getbycompanies/{company1}/{company2}/{company3}")
	@Operation(summary = " Successfully fetched employees working in the specified company !!", description = "The system has successfully fetched employee records associated with the specified company !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "You can now view or manage company-specific employee data !!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> getEmployeeByCompanies(@PathVariable String company1,
			@PathVariable String company2, @PathVariable String company3) {
		List<Employee> employeeByCompanies = employeeService.showEmployeeByCompanies(company1, company2, company3);
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Employees filtered by company name fetched successfully.", employeeByCompanies));
	}

	@GetMapping("/getbyid/{id}")
	@Operation(summary = "Employee details fetched successfully by ID !! ", description = "The system has successfully retrieved the employee's complete information using their unique identifier !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "This allows for precise data access and management !!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> getEmployeeById(@PathVariable int id) {
		Employee employeeById = employeeService.fetchEmployeeById(id);
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				" Employee record found using the provided ID !!", employeeById));
	}

	@PutMapping("/updateemployeedata")
	@Operation(summary = "Employee record has been updated successfully !!", description = "The employee's profile has been successfully updated with the latest information !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "It Ensuring that the database remains current and accurate !! ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> updateEmployee(@RequestBody EmployeeVo employeeVo) {
		String employeeDeatils = employeeService.updateEmployeeDeatils(employeeVo);
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Update to employee data was completed successfully !!", employeeDeatils));

	}

	@GetMapping("/fetchemployee/{name}")
	@Operation(summary = " Employee search by name completed successfully !! ", description = "Search result: Employee found by matching name !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "The system has successfully located and returned employee details based on the provided name input.	", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> getEmployeeByName(@PathVariable("name") String name) {
		List<Employee> employeeByName = employeeService.fetchEmployeeByName(name);
		// 200- tourist enrolled Successfully
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Employee details retrieved by name — enabling quick and easy identification !!", employeeByName));
	}

	@PatchMapping("/modifysalarybyid/{id}/{percentage}")
	@Operation(summary = " Successfully modified salary information for the employee by ID !! ", description = "This operation updates an employee’s salary details using their unique ID, ensuring accurate and current compensation records !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Salary details revised successfully for the specified employee !! ", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })

	public ResponseEntity<ResponseMessage> modifyEmployeeSalaryDetailsById(@PathVariable("id") int id,
			@PathVariable("percentage") double percentage) {
		String employeeSalaryById = employeeService.updateEmployeeSalaryById(id, percentage);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Employee salary record has been successfully modified !! ", employeeSalaryById));
	}

	@DeleteMapping("/deleteemployee/{id}")
	@Operation(summary = "Successfully removed employee using the provided ID !! ", description = "This operation deletes an employee’s record from the system using their unique identifier (ID), helping maintain accurate and updated records !! ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Deletion of employee by ID completed successfully !!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeVo.class)) }) })
	public ResponseEntity<ResponseMessage> removeEmployeeById(@PathVariable("id") int id) {
		String employeeById = employeeService.deleteEmployeeById(id);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Employee with the given ID has been removed from the system !! ", employeeById));
	}

	@DeleteMapping("/deleteemployeeincome/{start}/{end}")
	@Operation(summary = "Successfully deleted employees within the specified salary range !!", description = "This operation enables batch deletion of employee records based on a defined salary range, ensuring efficient data management and cleanup.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Employee records filtered and removed based on salary criteria !!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }) })

	public ResponseEntity<ResponseMessage> removeEmployeeBySalaryRange(@PathVariable("start") double start,
			@PathVariable("end") double end) {
		String employeeBySalaryRange = employeeService.deleteEmployeeBySalaryRange(start, end);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"Records of employees with salaries in the selected range deleted successfully !!",
				employeeBySalaryRange));
	}
}
