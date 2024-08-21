package com.botscrew.task.repository;

import com.botscrew.task.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByNameContaining(String template);
}