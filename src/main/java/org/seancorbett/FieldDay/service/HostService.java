package org.seancorbett.FieldDay.service;

import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;

public interface HostService {
    public Host findOrCreateHost(User user);
}
