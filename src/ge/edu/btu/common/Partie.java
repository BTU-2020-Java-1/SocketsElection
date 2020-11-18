package ge.edu.btu.common;

import java.io.Serializable;

public class Partie implements Serializable {

    private static final long serialVersionUID = 1L;

    private int number;

    private String name;

    public Partie() {
    }

    public Partie(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }
}
