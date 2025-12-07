package com.example.project_02_hamstercompanion.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02_hamstercompanion.HamsterDatabase;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = HamsterDatabase.HAMSTER_TABLE)
public class Hamster {
    @PrimaryKey(autoGenerate = true)
    private int hamsterId;

    private int userId;
    private String name;
    private int hunger;
    private int cleanliness;
    private int energy;
    //null adoptionDate = not adopted
    private LocalDateTime adoptionDate;

    public Hamster(int userId, String name, int hunger, int cleanliness, int energy) {
        this.userId = userId;
        this.name = name;
        this.hunger = hunger;
        this.cleanliness = cleanliness;
        this.energy = energy;
        //null adoptionDate = not adopted
    }

    @NonNull
    @Override
    public String toString() {
        return "Hamster{" +
                "adoptionDate=" + adoptionDate +
                ", hamsterId=" + hamsterId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", hunger=" + hunger +
                ", cleanliness=" + cleanliness +
                ", energy=" + energy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hamster)) return false;
        Hamster hamster = (Hamster) o;
        return hamsterId == hamster.hamsterId && userId == hamster.userId && hunger == hamster.hunger && cleanliness == hamster.cleanliness && energy == hamster.energy && Objects.equals(name, hamster.name) && Objects.equals(adoptionDate, hamster.adoptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hamsterId, userId, name, hunger, cleanliness, energy, adoptionDate);
    }

    public int getHamsterId() {
        return hamsterId;
    }

    public void setHamsterId(int hamsterId) {
        this.hamsterId = hamsterId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public LocalDateTime getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDateTime adoptionDate) {
        this.adoptionDate = adoptionDate;
    }
}
