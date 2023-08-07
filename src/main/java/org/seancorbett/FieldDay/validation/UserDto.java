package org.seancorbett.FieldDay.validation;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.seancorbett.FieldDay.model.Branch;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch.List({@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")})
public class UserDto {

    @NotEmpty(message = "Required")
    @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed")
    private String firstName;

    @NotEmpty(message = "Required")
    @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed")
    private String lastName;

    @NotEmpty(message = "Required")
    @Column(unique = true, name = "username")
    @Email
    private String username;

    //@NotEmpty(message = "Please choose your branch affiliation")
    private Branch branch;

    //@NotEmpty(message = "Please choose if you are still active or not. This can be edited later")
    private Boolean active;

    @NotEmpty(message = "Required")
    private String password;

    @NotEmpty(message = "Required")
    private String matchingPassword;

    public UserDto(@Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed") String firstName, @Pattern(regexp = "[A-Za-z]+$", message = "Only alphabetic allowed") String lastName, @NotEmpty(message = "Required") @Email String username, @NotEmpty(message = "Please choose your branch affiliation") Branch branch, @NotEmpty(message = "Please choose if you are still active or not. This can be edited later") Boolean active, @NotEmpty(message = "Required") String password, @NotEmpty(message = "Required") String matchingPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.branch = branch;
        this.active = active;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username(email)='" + username + '\'' +
                ", branch=" + branch +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }
}
