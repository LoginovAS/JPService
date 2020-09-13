package org.example.repository;

import org.example.model.PresentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentTypeRepository extends JpaRepository<PresentType, Long> {

    PresentType findByTypeName(String typeName);

}
