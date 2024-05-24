package com.boiko.aston_hibernate.repository;

import com.boiko.aston_hibernate.dto.employee.InsertEmployeeRequest;
import com.boiko.aston_hibernate.dto.employee.UpdateEmployeeRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.model.*;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final EntityManagerFactory factory;

    public void insert(InsertEmployeeRequest request) {
        PassportData passport = new PassportData();
        passport.setSeries(request.series());
        passport.setNumber(request.number());
        passport.setDepartmentCode(request.departmentCode());
        passport.setDateOfIssue(request.dateOfIssue());
        passport.setIssuingAuthority(request.issuingAuthority());

        Employee employee = new Employee();
        employee.setPassport(passport);
        employee.setName(request.name());
        employee.setSurname(request.surname());
        employee.setEmail(request.email());
        employee.setPhone(request.phone());

        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Position position = entityManager.find(Position.class, request.positionID());
            Department department = entityManager.find(Department.class, request.departmentID());
            employee.setPosition(position);
            employee.setDepartment(department);
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedInsertException(e);
        }
    }

    public void update(UpdateEmployeeRequest request) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, request.id());
            Position position = entityManager.find(Position.class, request.positionID());
            Department department = entityManager.find(Department.class, request.departmentID());
            employee.setName(request.name());
            employee.setSurname(request.surname());
            employee.setEmail(request.email());
            employee.setPhone(request.phone());
            employee.setDepartment(department);
            employee.setPosition(position);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }

    public void delete(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, id);
            entityManager.remove(employee);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedDeleteException(e);
        }
    }

    public Employee getByID(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, id);
            entityManager.getTransaction().commit();
            return employee;
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public List<Employee> getAll() {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Employee> employees = entityManager.createQuery("from Employee", Employee.class).getResultList();
            entityManager.getTransaction().commit();
            return employees;
        }
    }

    public List<Employee> getAllByProjectID(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            List<Employee> employees = project.getEmployees();
            entityManager.getTransaction().commit();
            return employees;
        }
    }
}
