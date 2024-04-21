package io.nomard.church_dashboard_api.services.auth;

import io.nomard.church_dashboard_api.entities.User;
import io.nomard.church_dashboard_api.principals.SpotyUserPrincipal;
import io.nomard.church_dashboard_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpotyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepo.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new SpotyUserPrincipal(user);
    }
}