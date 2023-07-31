package org.seancorbett.FieldDay.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Host implements User{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int hostId;
    private int userId;
    private String firstName;
    private String lastName;
    private Branch branch;
    private Boolean active;
    private List hostEvents;

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
