package de.neusta_sd.roomsmanager.core.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** Created by Adrian Tello on 29/11/2017. */
@Entity
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
