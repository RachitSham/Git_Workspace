package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<Employee, Integer> {

	@Query("FROM Employee WHERE companyName IN (:company1, :company2, :company3) ORDER BY name ASC")
	List<Employee> findEmployeeByCompany(String company1, String company2, String company3);

	@Query("FROM Employee WHERE name=:name")
	List<Employee> findIdByName(String name);

	@Query("delete FROM Employee WHERE salary>=:start and salary<=:end")
	@Transactional
	@Modifying
	public int rempoveEmployeeBySalaryRange(double start, double end);

}
