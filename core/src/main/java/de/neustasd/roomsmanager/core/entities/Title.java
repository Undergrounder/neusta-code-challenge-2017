package de.neustasd.roomsmanager.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** Created by Adrian Tello on 29/11/2017. */
@Entity
/**
 * Title entity.
 *
 * <P>Represents a persons title.
 * Example: "Dr."</P>
 *
 * @author Adrian Tello
 */
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
