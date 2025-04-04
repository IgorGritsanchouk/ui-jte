package com.ui.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.FutureOrPresent;

import java.lang.Long;
import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@Table("employees")
public class Employee {

    @Id
    private Long employeeId;

    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    private String title;

    @Past(message = "Birth date must be in the past")
    private Date birthDate;

    private Timestamp hireDate;

    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String extension;
    private byte[] photo;
    private String notes;
    private BigDecimal salary;
    private Long mobilePhone;
    private String jobId;
    private BigDecimal commissionPct;
    private Integer managerId;
    private Integer departmentId;

    public void setHireDate(Date hireDate){
        this.hireDate= (hireDate != null) ? new Timestamp(hireDate.getTime()) : new Timestamp(System.currentTimeMillis());
    }
    public Timestamp getHireDate(){
        return (hireDate != null) ? hireDate : new Timestamp(System.currentTimeMillis());
    }

    public Date convertTimestampToDate(Timestamp timestamp){
        return new Date(timestamp.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee emp)) return false;
        return Objects.equals(getEmployeeId(), emp.getEmployeeId()) && Objects.equals(getFirstName(), emp.getFirstName()) && Objects.equals(getLastName(), emp.getLastName()) && Objects.equals(getTitle(), emp.getTitle()) && Objects.equals(getBirthDate(), emp.getBirthDate()) && Objects.equals(getHireDate(), emp.getHireDate()) && Objects.equals(getAddress(), emp.getAddress()) && Objects.equals(getCity(), emp.getCity()) && Objects.equals(getRegion(), emp.getRegion()) && Objects.equals(getPostalCode(), emp.getPostalCode())
                && Objects.equals(getCountry(), emp.getCountry()) && Objects.equals(getHomePhone(), emp.getHomePhone()) && Objects.equals(getExtension(), emp.getExtension()) && Objects.equals(getNotes(), emp.getNotes()) && Objects.equals(getSalary(), emp.getSalary()) && Objects.equals(getMobilePhone(), emp.getMobilePhone()) && Objects.equals(getJobId(), emp.getJobId()) && Objects.equals(getCommissionPct(), emp.getCommissionPct()) && Objects.equals(getManagerId(), emp.getManagerId()) && Objects.equals(getDepartmentId(), emp.getDepartmentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getFirstName(), getLastName(), getTitle(), getBirthDate(), getHireDate(), getAddress(), getCity(), getRegion(), getPostalCode(), getCountry(), getHomePhone(), getExtension(), getNotes(), getSalary(), getMobilePhone(), getJobId(), getCommissionPct(), getManagerId(), getDepartmentId());
    }

}
