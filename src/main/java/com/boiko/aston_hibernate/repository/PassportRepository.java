package com.boiko.aston_hibernate.repository;

import com.boiko.aston_hibernate.dto.passport.InsertPassportRequest;
import com.boiko.aston_hibernate.dto.passport.UpdatePassportRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.model.Employee;
import com.boiko.aston_hibernate.model.PassportData;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PassportRepository {
    private final EntityManagerFactory factory;

    public void insert(InsertPassportRequest request) {
        PassportData passport = new PassportData();
        passport.setSeries(request.series());
        passport.setNumber(request.number());
        passport.setDepartmentCode(request.departmentCode());
        passport.setDateOfIssue(request.dateOfIssue());
        passport.setIssuingAuthority(request.issuingAuthority());

        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, request.employeeID());
            passport.setEmployee(employee);
            entityManager.persist(passport);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedInsertException(e);
        }
    }

    public void update(UpdatePassportRequest request) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            PassportData passport = entityManager.find(PassportData.class, request.id());
            passport.setSeries(request.series());
            passport.setNumber(request.number());
            passport.setDepartmentCode(request.departmentCode());
            passport.setDateOfIssue(request.dateOfIssue());
            passport.setIssuingAuthority(request.issuingAuthority());
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }

    public void delete(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            PassportData passport = entityManager.find(PassportData.class, id);
            entityManager.remove(passport);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedDeleteException(e);
        }
    }

    public PassportData getByID(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            PassportData passport = entityManager.find(PassportData.class, id);
            entityManager.getTransaction().commit();
            return passport;
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public List<PassportData> getAll() {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<PassportData> passports = entityManager.createQuery("from PassportData", PassportData.class).getResultList();
            entityManager.getTransaction().commit();
            return passports;
        }
    }
}
