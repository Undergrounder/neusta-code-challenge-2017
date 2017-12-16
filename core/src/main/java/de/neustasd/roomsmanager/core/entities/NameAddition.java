package de.neustasd.roomsmanager.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** Created by Adrian Tello on 29/11/2017. */
@Entity
/**
 * NameAddition entity.
 *
 * <p>Represents a persons name addition. Examples: "de", "van", "von"
 *
 * @author Adrian Tello
 */
public class NameAddition {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String name;

  public NameAddition() {}

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
