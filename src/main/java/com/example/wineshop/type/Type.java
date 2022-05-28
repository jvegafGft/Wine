package com.example.wineshop.type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "type")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int   id;
  private String name;


  public Type(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(Object o) {

    if (this == o) {return true;}
    if (!(o instanceof Type)) {return false;}
    Type type = (Type) o;
    return Objects.equals(this.getId(), type.getId()) && Objects.equals(this.name, type.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId(), this.name);
  }

  @Override
  public String toString() {
    return "Type{" +
           "id=" + id +
           ", name='" + name + '\'' +
           '}';
  }

}


