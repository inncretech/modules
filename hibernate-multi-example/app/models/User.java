package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
 
  private Long id;
  private String name;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
