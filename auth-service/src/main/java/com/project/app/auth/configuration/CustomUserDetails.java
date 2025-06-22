package com.project.app.auth.configuration;

import com.project.app.auth.dto.AuthDto;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
  private final AuthDto authDto;
  private final Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(AuthDto authDto, Collection<? extends GrantedAuthority> authorities) {
    this.authDto = authDto;
    this.authorities = authorities;
  }

  public AuthDto getAuthDto() {
    return authDto;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return authDto.getPassword();
  }

  @Override
  public String getUsername() {
    return authDto.getUsername();
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
    return authDto.getIsActive();
  }
}
