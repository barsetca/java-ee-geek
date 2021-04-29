package com.cherniak.persist.dto;

import com.cherniak.persist.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto implements Serializable {

  private Long id;

  private String login;

  private String password;

  private Set<RoleDto> roles;


  public UserDto(User user) {
    this.id = user.getId();
    this.login = user.getLogin();
    this.password = user.getPassword();
    this.roles = new HashSet<>();
    user.getRoles().forEach(r -> this.roles.add(new RoleDto(r)));
  }

  @Override
  public String toString() {
    return "UserRepr{" +
        "id=" + id +
        ", login='" + login + '\'' +
        '}';
  }
}
