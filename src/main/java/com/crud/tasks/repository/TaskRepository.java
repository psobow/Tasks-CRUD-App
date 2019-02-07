package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface TaskRepository extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();

    Optional<Task> findById(final Long id);

    @Override
    @SuppressWarnings("unchecked")
    Task save(final Task task);

    Integer deleteById(final Long id);

    @Override
    long count();
}
