package com.ericson.colegiojosemaria.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwt {
    String getToken(UserDetails userDetails);

    String getUsernameToken(String token);

    Boolean validateToken(String token, UserDetails userDetails);
}
