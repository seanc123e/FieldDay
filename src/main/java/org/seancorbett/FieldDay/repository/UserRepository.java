package org.seancorbett.FieldDay.repository;

import org.seancorbett.FieldDay.model.Veteran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Veteran, Long> {

}
