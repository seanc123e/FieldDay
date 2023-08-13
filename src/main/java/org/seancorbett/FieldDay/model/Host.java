package org.seancorbett.FieldDay.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "host")
public class Host implements Serializable {

    //Creation of unique host ID
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int hostId;

    //Creation of relationship to userId
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @JsonBackReference//referencing Jackson to handle serialization and deserialization of relationships to avoid ciruclar dependencies
    private User user;

    private String firstName;

    private String lastName;

    private Branch branch;

    private Boolean active;

    @OneToMany (mappedBy = "host",
                cascade = CascadeType.ALL)
    private List<Event> events;


    //READ METHODS
    public User getUserById() {
        return null;
    }

    public Event getEventById() {
        return null;
    }

    public List<User> getAllAttendees(){
        return null;
    }

}
