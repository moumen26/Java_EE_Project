package com.example.appdest.Models;

public class Candidat {
    private int id;
    private String name;
    private String email;
    private String election;
    public Candidat() {}

    public Candidat(int id, String name, String email, String election) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.election = election;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getElection() {
        return election;
    }

    public void setElection(String election) {
        this.election = election;
    }

    public String toString() {
        return "Candidat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", election='" + election + '\'' +
                '}';
    }
}
