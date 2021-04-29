package com.cherniak.persist.dto;

import com.cherniak.persist.Role;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class RoleDto implements Serializable {

    private long id;

    private String name;

      public RoleDto(Role r) {
      this.id = r.getId();
      this.name = r.getName();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RoleDto roleDto = (RoleDto) o;
      return name.equals(roleDto.name);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(name);
    }

    @Override
    public String toString() {
      return name;
    }

}
