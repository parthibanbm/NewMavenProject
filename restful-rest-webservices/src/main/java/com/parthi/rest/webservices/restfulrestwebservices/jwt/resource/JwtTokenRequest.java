package com.parthi.rest.webservices.restfulrestwebservices.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
  private String password;
    
  //"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU5MTA4NjgxOSwiaWF0IjoxNTkwNDgyMDE5fQ.ZtCFewtl8qQQVHDn-6dUoYBPgKl1fSHh8ElhN3WR0yfpMGt9O7lP08ZM6pYwH9EGAU8NvJBeW-UEmKkcroI9TQ"

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

