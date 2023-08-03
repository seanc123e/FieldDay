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
@Table(name = "event")
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;

    private String eventImage = "https://picsum.photos/300/200";

    @ManyToMany
    @JoinTable (name = "veteran_events",
                joinColumns = @JoinColumn(name = "eventId"),
                inverseJoinColumns = @JoinColumn(name = "userId"))
    private List<User> attendees = new ArrayList<User>();

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

    public List<UserRules> getAttendees(){
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
