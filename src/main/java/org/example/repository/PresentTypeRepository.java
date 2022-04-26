package org.example.repository;

import org.example.model.PresentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresentTypeRepository extends JpaRepository<PresentType, Long> {

    Optional<PresentType> findByTypeName(String typeName);

}
