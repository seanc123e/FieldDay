package org.seancorbett.FieldDay.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String time;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "host_user_id")
    private Veteran host;

    private String eventImage = "https://picsum.photos/300/200";

    @ManyToMany
    @JoinTable (name = "user_events",
                joinColumns = @JoinColumn(name = "eventId"),
                inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<Veteran> attendees = new ArrayList<Veteran>();

    //CREATE METHODS
    public Event createEvent(){
        return null;
    }

    public Host createHost(){
        return null;
    }

    //READ METHODS
    public Event getEvent(){
        return null;
    }

    public List<User> getAttendees(){
        return null;
    }

    public List<Event> getAllEvents(){
        return null;
    }

    //UPDATE METHODS
    public void updateEvent(){

    }

    //DELETE METHODS
    public void deleteEvent(){

    }
}
