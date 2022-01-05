package at.htl.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicalStaff extends Person{
    private BigDecimal salary;
    private LocalDate hireDate;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}
