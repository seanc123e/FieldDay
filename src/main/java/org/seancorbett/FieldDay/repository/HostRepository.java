package org.seancorbett.FieldDay.repository;

import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

    //CREATE
    Host save(Host newHost);

    //READ
    Host findByUser(User user);
}
