package org.seancorbett.FieldDay.validation;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventDto {

    //********ATTRIBUTES*********
    @NotEmpty(message = "Required")
    private String title;

    @NotEmpty(message = "Required")
    private String description;

    //@NotEmpty(message = "Required")
    private String location;

    //@NotEmpty(message = "Required")
    private String date;

    //@NotEmpty(message = "Required")
    private String time;

    private Double price;

    private Host host;
    private String eventImage = "https://picsum.photos/300/200";
    private List<User> attendees = new ArrayList<User>();

    //*******CONSTRUCTOR*******
    public EventDto(String title, String description, String location, String date, String time, Double price, Host host, String eventImage) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.price = price;
        this.eventImage = eventImage;
        this.host = host;
    }

    //*********GETTERS AND SETTERS*********
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
