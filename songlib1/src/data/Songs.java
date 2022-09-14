/*
 * authors: Ziyun Zhi, Junren Zhu
 */

package data;

import java.util.ArrayList;
import java.util.Comparator;

public class Songs {

    private ArrayList<Song> songs = new ArrayList<Song>();

    public Songs(){
        songs = CSVTool.readCsv("data.csv");
        this.sort();
        for(int i = 0; i < songs.size(); i++)
            System.out.println("[I] Song: " + songs.get(i).toString());
    }

    public int getIndex(String string){
        for(int i = 0; i < songs.size(); i++){
            if (songs.get(i).getString().equals(string)){
                return i;
            }
        }
        return 0;
    }

    public int size(){
        return songs.size();
    }

    public void save(){
        CSVTool.writeCsv("data.csv", songs);
    }

    public void sort(){
        songs.sort(Comparator.naturalOrder());
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public boolean addSong(Song song){
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getName().equals(song.getName()) && songs.get(i).getArtist().equals(song.getArtist())){
                return false;
            }
        }
        songs.add(song);
        sort();
        return true;
    }

    public Song findByString(String string){
        for(int i = 0; i < songs.size(); i++){
            if (songs.get(i).getString().equals(string)){
                return songs.get(i);
            }
        }
        return null;
    }

    public boolean changeSong(String string, String name, String artist, String album, int year){
        int current = 0;
        for(int i = 0; i < songs.size(); i++){
            if (songs.get(i).getString().equals(string)){
                current = i;
            }
        }
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getName().equals(name) && songs.get(i).getArtist().equals(artist)){
                return false;
            }
        }
        songs.get(current).setName(name);
        songs.get(current).setArtist(artist);
        songs.get(current).setAlbum(album);
        songs.get(current).setYear(year);
        return true;
    }

    public void delete(String string){
        int current = 0;
        for(int i = 0; i < songs.size(); i++){
            if (songs.get(i).getString().equals(string)){
                current = i;
                break;
            }
        }
        songs.remove(current);
        songs.sort(Comparator.naturalOrder());
    }

}
