/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hashmapint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class Song {
    String SongName;
    Song next;
    public String toString() {
        return this.SongName;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return SongName.equals(song.SongName);
    }
    public void findSong(int number) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\PC\\Desktop\\Yeni Metin Belgesi (2).txt"));
        String line;
        MyLinkedList<Person> personList = new MyLinkedList<Person>();
        while((line = reader.readLine()) != null){
            
            if(line.startsWith("I")){
                Person p = new Person();
                Song s = new Song();
                String[] parts = line.split(" ",3);
                StringBuilder newString = new StringBuilder();
                p.PersonName = parts[1];
                int no = personList.searchNode(p);
                if(number==no){
                    for(int i=2;i< parts.length;i++){
                        newString.append(parts[i]).append(" ");
                    }
                    System.out.print(newString+" ");
                }
            }
        }
        System.out.print("\n");
    }
}
