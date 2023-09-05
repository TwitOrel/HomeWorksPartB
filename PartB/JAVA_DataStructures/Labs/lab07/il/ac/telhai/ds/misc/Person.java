package lab07.il.ac.telhai.ds.misc;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private String id;
    private String firstName;
    private String lastName;

    public Person(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String newName) {
        this.firstName = newName;
    }

    public void setLastName(String newName) {
        this.lastName = newName;
    }

    // Compare two Person objects based on their ID attribute.
    @Override
    public int compareTo(Person o) {
        if (o == null) {
            return 1;
        }
        int a = o.id.length();
        int b = this.id.length();
        for (int i = 0; i < Math.min(a, b); i++) {
            char charO = o.id.charAt(i);
            char charI = this.id.charAt(i);
            if (charI != charO)
                return charI - charO;
        }
        return b - a;
    }

    // Check if two Person objects are equal based on their ID attribute.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return "Person [id= " + this.id + ", firstName= " + this.firstName + ", lastName= " + this.lastName + " ]";
    }
}
