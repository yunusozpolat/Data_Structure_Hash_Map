/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hashmapint;

/**
 *
 * @author PC
 */
public class Person {
    String PersonName;
    Person next;
    public String toString() {
        return this.PersonName;
    }
//    MyLinkedList<Person> PName;
//    public Person() {
//            next = null;
//    }
//    public Person(String PersonName) {
//            this.PersonName = PersonName;
//            
//            next = null;
//    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return PersonName.equals(person.PersonName);
    }
    void dontLike(String song) {
        
    }
    
}
