package com.example.assignment01.dto;

import com.example.assignment01.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserLoginDTO extends User implements UserDetails {
    @Getter
    private int id;
    private String fullName;
    private boolean isAdmin;

    private Role role;


    public UserLoginDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserLoginDTO(String username, String password, Collection<? extends GrantedAuthority> authorities, com.example.assignment01.entity.User user, Role role) {
        super(username, password, authorities);
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.isAdmin = checkAdmin(authorities);
        this.role = role;
    }


    public boolean checkAdmin(Collection<? extends GrantedAuthority> authorities){
        for (Object au : authorities.toArray()){
            if (au.toString().equals("Admin")){
                return true;
            }
        }
        return false;
    }

    public UserLoginDTO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
