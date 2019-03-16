package com.mano.springvaadinapp1.springvaadin.repository;

import com.mano.springvaadinapp1.springvaadin.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(String name);

}
