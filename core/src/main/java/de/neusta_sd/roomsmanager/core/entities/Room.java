package de.neusta_sd.roomsmanager.core.entities;

import javax.persistence.*;

/**
 * Created by Adrian Tello on 29/11/2017.
 */
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable=false, length=4)
    private String number;

    public Room() {

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
}
