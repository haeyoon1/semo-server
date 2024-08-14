package arom_semo.server.login.dto;

import arom_semo.server.login.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final Member userEntity;

    public CustomUserDetails(Member userEntity) {
        this.userEntity = userEntity;
    }

    @Override //role값 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });

        return collection;
    }

    @Override //password값 반환
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override //id값 반환
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override //계정이 expired
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //계정이 lock
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

