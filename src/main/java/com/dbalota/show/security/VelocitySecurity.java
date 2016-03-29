package com.dbalota.show.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class VelocitySecurity {

    /**
     * Check if user is login by remember me cookie, refer
     * org.springframework.security.authentication.AuthenticationTrustResolverImpl
     */
    public static boolean rememberMeAuthenticated() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * Gets the user name of the user from the Authentication object
     *
     * @return the user name as string
     */
    public static String getPrincipal() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj instanceof UserDetails) {
            return ((UserDetails) obj).getUsername();
        } else {
            return "Guest";
        }
    }

    /**
     * Is the user granted all of the grantedAuthorities passed in
     *
     * @param checkForAuths a string array of grantedAuth
     * @return true if user has all of the listed authorities/roles, otherwise false
     */
    public static boolean allGranted(String[] checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                continue;
            return false;
        }
        return true;
    }

    /**
     * Is the user granted any of the grantedAuthorities passed into
     *
     * @param checkForAuths a string array of grantedAuth
     * @return true if user has any of the listed authorities/roles, otherwise false
     */
    public static boolean anyGranted(String[] checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return true;
        }
        return false;
    }

    /**
     * is the user granted none of the supplied roles
     *
     * @param roles a string array of roles
     * @return true only if none of listed roles are granted
     */
    public static boolean noneGranted(String[] roles) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : roles) {
            if (userAuths.contains(auth))
                return false;
        }
        return true;
    }

    private static Set<String> getUserAuthorities() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> roles = new HashSet<String>();
        if (obj instanceof UserDetails) {
            Collection gas = ((UserDetails) obj).getAuthorities();
            for (Object ga : gas) {
                roles.add(((GrantedAuthority) ga).getAuthority());
            }
        }
        return roles;
    }
}
