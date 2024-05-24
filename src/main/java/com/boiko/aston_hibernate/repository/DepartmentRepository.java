package com.boiko.aston_hibernate.repository;

import com.boiko.aston_hibernate.dto.department.InsertDepartmentRequest;
import com.boiko.aston_hibernate.dto.department.UpdateDepartmentRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.model.Department;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {
    private final EntityManagerFactory factory;

    public void insert(InsertDepartmentRequest request) {
        Department department = new Department();
        department.setName(request.name());
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(department);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedInsertException(e);
        }
    }

    public void update(UpdateDepartmentRequest request) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Department department = entityManager.find(Department.class, request.id());
            department.setName(request.name());
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }

    public void delete(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Department department = entityManager.find(Department.class, id);
            entityManager.remove(department);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedDeleteException(e);
        }
    }

    public Department getByID(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Department department = entityManager.find(Department.class, id);
            entityManager.getTransaction().commit();
            return department;
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public List<Department> getAll() {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Department> departments = entityManager
                    .createQuery("from Department", Department.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            return departments;
        }
    }
}
