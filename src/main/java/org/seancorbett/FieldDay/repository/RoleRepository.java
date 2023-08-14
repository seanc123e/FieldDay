package org.seancorbett.FieldDay.repository;

import org.seancorbett.FieldDay.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    //READ
    public Role findRoleByName(String role);

    //queries the database to find a list of users with a specific role
    @Query(value = "select * from roles where roles.id= (select role_id from user_roles where user_id = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(@Param("id") long id);
}
