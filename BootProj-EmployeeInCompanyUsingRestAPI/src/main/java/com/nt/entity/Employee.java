package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "JPA_EMPLOYEE_HUB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Employee_Name", length = 50)
	@NonNull
	private String name;

	@Column(name = "Employee_Email", length = 25)
	@NonNull
	private String email;

	@Column(name = "Employee_location", length = 40)
	@NonNull
	private String location;

	@Column(name = "Company_Name", length = 40)
	@NonNull
	private String companyName;

	@Column(name = "Employee_Salary")
	@NonNull
	private Double salary;

	@Column(name = "Employee_MobileNo")
	@NonNull
	private Long mobileNo;

	// these are Optional But Very Helpful[Metadata]
	@Version
	@Column(name = "Update_Count")
	private Integer updateCount;

	@Column(name = "Created_BY", length = 30)
	private String createdBy;

	@Column(name = "Updated_BY", length = 30)
	private String updatedBy;

	@Column(name = "Created_Time", updatable = false, insertable = true)
	@CreationTimestamp
	private LocalDateTime createdTime;

	@Column(name = "Update_Time", updatable = false, insertable = true)
	@UpdateTimestamp
	private LocalDateTime updateTime;

}
