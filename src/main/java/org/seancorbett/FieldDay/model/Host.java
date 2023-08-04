package org.seancorbett.FieldDay.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "host")
public class Host implements UserRules {

    //Creation of unique host ID
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int hostId;

    //Creation of relationship to userId
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User userFk;
    private String firstName;
    private String lastName;
    private Branch branch;
    private Boolean active;

    @OneToMany (mappedBy = "host",
                cascade = CascadeType.ALL)
//    @JoinColumn (name = "hostId")
    private List<Event> events;


    //READ METHODS
    @Override
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
