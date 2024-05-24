package com.boiko.aston_hibernate.repository;

import com.boiko.aston_hibernate.dto.project.InsertProjectRequest;
import com.boiko.aston_hibernate.dto.project.UpdateProjectRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.model.Employee;
import com.boiko.aston_hibernate.model.Position;
import com.boiko.aston_hibernate.model.Project;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {
    private final EntityManagerFactory factory;

    public void insert(InsertProjectRequest request) {
        Project project = new Project();
        project.setName(request.name());
        project.setStartDate(request.startDate());
        project.setEndDate(request.endDate());

        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedInsertException(e);
        }
    }

    public void update(UpdateProjectRequest request) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, request.id());
            project.setName(request.name());
            project.setStartDate(request.startDate());
            project.setEndDate(request.endDate());
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }

    public void delete(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            entityManager.remove(project);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedDeleteException(e);
        }
    }

    public Project getByID(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            entityManager.getTransaction().commit();
            return project;
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public List<Project> getAll() {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Project> projects = entityManager.createQuery("from Project", Project.class).getResultList();
            entityManager.getTransaction().commit();
            return projects;
        }
    }

    public void addEmployeeToProject(Long projectID, Long employeeID) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, projectID);
            Employee employee = entityManager.find(Employee.class, employeeID);
            List<Employee> employees = project.getEmployees();
            employees.add(employee);
            project.setEmployees(employees);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }

    public void removeEmployeeFromProject(Long projectID, Long employeeID) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, projectID);
            Employee employee = entityManager.find(Employee.class, employeeID);
            List<Employee> employees = project.getEmployees();
            employees.remove(employee);
            project.setEmployees(employees);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }
}
