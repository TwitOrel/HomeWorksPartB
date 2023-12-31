package lab10.il.ac.telhai.ds.misc;

public class Person implements Comparable<Person> {
    String id;
    String firstName;
    String lastName;

    public Person(String id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    // Implementation of the compareTo method from the Comparable interface.
    // It compares Person objects based on their id attribute.
    @Override
    public int compareTo(Person o) {
        return this.id.compareTo(o.id);
    }
}