package com.illustration.entity.vo;

import com.illustration.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class LoginUserVO implements UserDetails {
    private Long id;
    private String token;
    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getPerms() != null && !user.getPerms().isEmpty()) {
            return user.getPerms().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
