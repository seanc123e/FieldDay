package org.seancorbett.FieldDay.model;

public interface UserRules {
    int userId = 0;
    String firstName = null;
    String lastName = "";
    Branch branch = null;
    Boolean active = false;

    UserRules getUserById();
}
