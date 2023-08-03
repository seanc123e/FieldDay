package org.seancorbett.FieldDay.model;

public interface UserRules {
    public int userId = 0;
    public String firstName = "";
    public String lastName = "";
    public Branch branch = null;
    public Boolean active = false;

    public UserRules getUserById();
}
