package org.example.repository;

import org.example.model.PresentQuantity;
import org.example.model.PresentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresentQuantityRepository extends JpaRepository<PresentQuantity, Long> {

    Optional<PresentQuantity> findByPresentType(PresentType presentType);

}
