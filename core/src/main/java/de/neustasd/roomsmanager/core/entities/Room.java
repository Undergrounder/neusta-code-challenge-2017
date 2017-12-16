package de.neustasd.roomsmanager.core.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/** Created by Adrian Tello on 29/11/2017. */
@Entity
/**
 * Room entity.
 *
 * <p>Represents a room.
 *
 * @author Adrian Tello
 */
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 4, unique = true)
  private String number;

  @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
  private List<Person> people;

  public Room() {
    people = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public List<Person> getPeople() {
    return people;
  }

  public void setPeople(final List<Person> people) {
    this.people = people;
  }
}
