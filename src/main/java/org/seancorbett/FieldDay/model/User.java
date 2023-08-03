package org.seancorbett.FieldDay.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "veteran")
public class User implements UserRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "attendees", cascade = CascadeType.ALL)
    private List<Event> veteranEvents = new ArrayList<Event>();

    @OneToOne (targetEntity = Host.class)
    @JoinColumn(name = "host_id")
    private int hostFk;

    public User(String firstName, String lastName, String email, String password, List<Event> veteranEvents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.veteranEvents = veteranEvents;
    }

    // CREATE METHODS
    public User createUser(){
        return null;
    }

    //READ METHODS
    @Override
    public User getUserById() {
        return null;
    }

    public List<Event> getUserEvents(){
        return null;
    }

    //UPDATE METHODS
    public void updateUser(){

    }
}
