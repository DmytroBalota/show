package com.dbalota.show.security;

import com.dbalota.show.models.User;
import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserDetails userDetails = null;
        String [] nameSurname = username.split("_");
        User user = userService.getUserByNameSurname(nameSurname[0],nameSurname[1]);
        if (user == null)
            throw new UsernameNotFoundException("user not found");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String [] roles = user.getRoles().trim().split(",");
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role.trim()));
        }

        userDetails = new org.springframework.security.core.userdetails.User(
                username,user.getPassword(),authorities
        );
        return userDetails;
    }
}
