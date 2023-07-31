package org.seancorbett.FieldDay.model;

public interface User {
    public int userId = 0;
    public String firstName = "";
    public String lastName = "";
    public Branch branch = null;
    public Boolean active = false;

    public User getUserById();
}
