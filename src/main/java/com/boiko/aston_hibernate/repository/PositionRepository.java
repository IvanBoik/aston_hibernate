package com.boiko.aston_hibernate.repository;

import com.boiko.aston_hibernate.dto.position.InsertPositionRequest;
import com.boiko.aston_hibernate.dto.position.UpdatePositionRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.model.Employee;
import com.boiko.aston_hibernate.model.Position;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PositionRepository {
    private final EntityManagerFactory factory;

    public void insert(InsertPositionRequest request) {
        Position position = new Position();
        position.setName(request.name());
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(position);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedInsertException(e);
        }
    }

    public void update(UpdatePositionRequest request) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Position position = entityManager.find(Position.class, request.id());
            position.setName(request.name());
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedUpdateException(e);
        }
    }

    public void delete(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Position position = entityManager.find(Position.class, id);
            entityManager.remove(position);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            throw new FailedDeleteException(e);
        }
    }

    public Position getByID(Long id) {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Position position = entityManager.find(Position.class, id);
            entityManager.getTransaction().commit();
            return position;
        }
        catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public List<Position> getAll() {
        try(var entityManager = factory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Position> positions = entityManager.createQuery("from Position", Position.class).getResultList();
            entityManager.getTransaction().commit();
            return positions;
        }
    }
}
