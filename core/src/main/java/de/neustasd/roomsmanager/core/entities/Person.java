package de.neustasd.roomsmanager.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/** Created by Adrian Tello on 29/11/2017. */
@Entity
/**
 * Person entity.
 *
 * <p>Represents a person.
 *
 * @author Adrian Tello
 */
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne() private Title title;

  @Column(length = 50)
  private String firstName;

  @Column(length = 100)
  private String lastName;

  @ManyToOne() private NameAddition nameAddition;

  @Column(length = 50, nullable = false)
  private String ldapUser;

  @ManyToOne(optional = false)
  private Room room;

  public Person() {}

  public Long getId() {
    return id;
  }

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public NameAddition getNameAddition() {
    return nameAddition;
  }

  public void setNameAddition(NameAddition nameAddition) {
    this.nameAddition = nameAddition;
  }

  public String getLdapUser() {
    return ldapUser;
  }

  public void setLdapUser(String ldapUser) {
    this.ldapUser = ldapUser;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }
}
