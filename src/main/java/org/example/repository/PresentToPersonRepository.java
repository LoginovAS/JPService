package org.example.repository;

import org.example.model.PresentToPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentToPersonRepository extends JpaRepository<PresentToPerson, Long> {

}
