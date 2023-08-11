package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;

public interface HostService {

    //CREATE AND READ
    public Host findOrCreateHost(User user);
}
