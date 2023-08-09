package org.seancorbett.FieldDay.service.impl;

import org.seancorbett.FieldDay.model.Host;
import org.seancorbett.FieldDay.model.User;
import org.seancorbett.FieldDay.repository.HostRepository;
import org.seancorbett.FieldDay.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository hostRepository;

    public Host findOrCreateHost(User user) {
        // Check if a host already exists for the given user
        Host existingHost = hostRepository.findByUser(user);

        if (existingHost != null) {
            return existingHost;
        } else {
            // Create a new host and associate it with the user
            Host newHost = new Host();
            newHost.setFirstName(user.getFirstName());
            newHost.setLastName(user.getLastName());
            newHost.setBranch(user.getBranch());
            newHost.setActive(user.getActive());
            newHost.setUser(user);
            newHost = hostRepository.save(newHost);
            user.setHost(newHost); // Update the user's hostId
            return newHost;

            //return hostRepository.save(newHost);

        }
    }
}
