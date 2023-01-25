/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hashmapint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Hashmapint<K, V> {
    public class Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
    private int capacity = 16;

    private Entry<K, V>[] table; 

    public Hashmapint(){
        table = new Entry[capacity];
    }

    public Hashmapint(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
    }
    public void put(K key, V value){
        int index = index(key);
        Entry newEntry = new Entry(key, value, null);
        if(table[index] == null){
            table[index] = newEntry;
        }else {
            Entry<K, V> previousNode = null;
            Entry<K, V> currentNode = table[index];
            while(currentNode != null){
                if(currentNode.getKey().equals(key)){
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if(previousNode != null)
                previousNode.setNext(newEntry);
            }
    }
    public V get(K key){
        V value = null;
        int index = index(key);
        Entry<K, V> entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }
    public void remove(K key){
        int index = index(key);
        Entry previous = null;
        Entry entry = table[index];
        while (entry != null){
            if(entry.getKey().equals(key)){
                if(previous == null){
                    entry = entry.getNext();
                    table[index] = entry;
                    return;
                }else {
                    previous.setNext(entry.getNext());
                    return;
                }
            }
            previous = entry;
            entry = entry.getNext();
        }
    }
        public void display(){
        for(int i = 0; i < capacity; i++){
            if(table[i] != null){
                Entry<K, V> currentNode = table[i];
                while (currentNode != null){
                    System.out.println(String.format("Key is %s and value is %s", currentNode.getKey(), currentNode.getValue()));
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

    private int index(K key){
        if(key == null){
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> currentNode = table[i];
                while (currentNode != null) {
                    sb.append(currentNode.getKey());
                    sb.append(" : ");
                    sb.append(currentNode.getValue());
                    sb.append("\n");
                    currentNode = currentNode.getNext();
                }
            }
        }
        return sb.toString();
    }
     
    public static void main(String[] args) {
        Hashmapint<Integer, Person> personMap = new Hashmapint<Integer, Person>();
        Hashmapint<Integer, Song> songMap = new Hashmapint<Integer, Song>();
        Hashmapint<Integer, String> likes = new Hashmapint<Integer, String>();
        MyLinkedList<Person> personList = new MyLinkedList<Person>();
        MyLinkedList<Song> songList = new MyLinkedList<Song>();
        MyLinkedList<String> ss = new MyLinkedList<String>();
        MyLinkedList<Integer> si = new MyLinkedList<Integer>();
        try {
            Scanner girdi = new Scanner(System.in);
            System.out.println("Please enter a filepath : ");
            String filePath = girdi.nextLine(); // For example my filepath was C:\\Users\\PC\\Desktop\\Yeni Metin Belgesi (2).txt
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int tmp = 0;
            int tmp1 =0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("I")) {
                    Person p = new Person();
                    p.PersonName = line.substring(1).trim();
                    if (personList.contains(line.substring(1).trim())) {  
                        System.out.println(p + " is already created before");
                    }else{
                    personList.add(tmp,p);
                    personMap.put(tmp, personList.getNodeData(tmp));
                    tmp++;}
                    
                }
                else if(line.startsWith("L")){
                    
                    Person p = new Person();
                    Song s = new Song();
                    
                    String[] parts = line.split(" ",3);
                    String name = parts[1];
                    String song = parts[2];
                    if (personList.contains(name)) {  // isim daha önce eklenmiş mi
                        ss.add(tmp1,name+" "+song);
                        likes.put(tmp1, ss.getNodeData(tmp1));
                        tmp1++;
                    }
                    else{System.out.println(name + " is not created so Song cannot be liked");}
                }
                else if(line.startsWith("E")){
                    Person p = new Person();
                    Song s = new Song();

                    String[] parts = line.split(" ", 3);
                    p.PersonName = parts[1];
                    s.SongName = parts[2];
                    if (personList.contains(p.PersonName) && songList.contains(s.SongName)) {
                        ss.remove(tmp);
                        likes.put(tmp1, ss.getNodeData(tmp1));
                        tmp1++;
                    } else {
                        System.out.println(p + " is not created so " + s + " cannot be liked");
                    }
//                    String[] parts = line.split(" ",3);
//                    String name = parts[1];
//                    String song = parts[2];
//                    if (personList.contains(name)) {  // isim daha önce eklenmiş mi?
//                        Person p = personList.get(name);
//                        p.dontLike(song);
//                        System.out.println(name + " doesn't like " + song);
//                    } else {
//                        System.out.println(name + " is not created so " + song + " cannot be erased");
//                    }
                }
                else if(line.startsWith("D")){
                    Person p = new Person();
                    Song s = new Song();
                    String[] parts = line.split(" ", 3);
                    p.PersonName = parts[1];
                    
                    int index = personList.searchNode(p);
                    personList.remove(index);
                    personMap.remove(index);
                    likes.remove(index);
//                    Person p = new Person();
//                    Person p1 = new Person();
//                    Song s = new Song();
//                    //s.SongName = songList.
//                    //System.out.println(s);
//                    p.PersonName = line.substring(1).trim();
//                    //personList.Output();
//                    if (personList.contains(line.substring(1).trim())) { 
//                        int index = personList.indexOf(p);
//                        int in = songList.indexOf(s);
//                        //System.out.println(in);
//                        personMap.remove(index);
//                        //System.out.println();
//                        //personList.Output();
//                    }
                    
                }
                else if(line.startsWith("P")){
                    Person p = new Person();
                    String[] parts = line.split(" ", 3);
                    p.PersonName = parts[1];
                    
                }
                else if(line.startsWith("M")){
                    
                }
                else if(line.startsWith("X")){
                    reader.close();
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(personMap);
        System.out.println(likes);
        //personMap.display();

    }
}
