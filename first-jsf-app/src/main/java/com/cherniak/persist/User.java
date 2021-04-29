package com.cherniak.persist;


import com.cherniak.persist.dto.UserDto;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "login", unique = true, nullable = false)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  public User(Long id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  public User(UserDto user) {
    this.id = user.getId();
    this.login = user.getLogin();
    this.password = user.getPassword();
    this.roles = new HashSet<>();
    user.getRoles().forEach(r -> roles.add(new Role(r)));
  }
}
