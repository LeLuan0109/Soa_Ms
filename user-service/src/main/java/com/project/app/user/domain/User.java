package com.project.app.user.domain;


import com.project.app.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "is_active")
  private Boolean isActive;
  private String address;
  private String avatar;
  @Column(nullable = false, length = 200)
  private String email;
  @Column(nullable = false, length = 100)
  private String fullname;
  private Integer gender;
  @Column(nullable = false, length = 200)
  private String password;
  private String phone;
  private String username;
  private String birthday;
  @Column(name = "user_version", nullable = false)
  private Integer userVersion = 1;

  @Transient
  private Set<GrantedAuthority> authorities = new HashSet<>();

  public void setAuthorities(Set<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getUsername() {
    return this.username;
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
