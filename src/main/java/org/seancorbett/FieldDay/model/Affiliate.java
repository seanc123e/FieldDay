package org.seancorbett.FieldDay.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "affiliation")
public class Affiliate {

    //affiliate class will eventually count each member who has signed up with specific branches so the application can gather data of total individuals affiliated with a certain branch
    @Id
    private Branch branch;
    private Boolean active;
    private Long count;

    //READ METHODS
    public void getAffiliate(){

    }

    //UPDATE METHODS
    public void updateAffiliate(){

    }
}
