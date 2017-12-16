package de.neusta_sd.roomsmanager.core.entities;

import javax.persistence.*;

/** Created by Adrian Tello on 29/11/2017. */
@Entity
public class Title {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 20, nullable = false, unique = true)
  private String name;

  public Title() {}

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
