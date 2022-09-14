/*
 * authors: Ziyun Zhi, Junren Zhu
 */

package data;

public class Song implements Comparable<Song>{

    private String name;
    private String artist;
    private String album;
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return this.name+" "+this.artist+" "+this.album+" "+this.year;
    }

    public String getString(){
        return this.name+" ( "+this.artist+" )";
    }

    @Override
    public int compareTo(Song o) {
        if(this.name.compareToIgnoreCase(o.getName()) > 0){
            return 1;
        }else if(this.name.compareToIgnoreCase(o.getName()) < 0){
            return -1;
        }else{
            if(this.artist.compareToIgnoreCase(o.getArtist()) > 0){
                return 1;
            }else if(this.artist.compareToIgnoreCase(o.getArtist()) < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
    }
}
