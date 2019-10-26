package com.challenge.backend.services;

import com.challenge.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * @author HAYTHAM DAHRI
 * ShopServiceImpl class
 * ShopService interface implementor
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Inject a UserService instance
     */
    @Autowired
    private UserService userService;

    /**
     * Redefine method to load a user using the dao repository
     *
     * @return UserDetails
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Fetch user from database using his email
        User user = this.userService.getUser(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user != null) {
            user.getRoles().forEach(role -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
            });
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("No user found with " + email);
    }
}
