/*
 * authors: Ziyun Zhi, Junren Zhu
 */

package data;

import java.io.*;
import java.util.ArrayList;

public class CSVTool {

    private static String seperator = ";";

    public static ArrayList<Song> readCsv(String pathToCsv){
        ArrayList<Song> songs = new ArrayList<Song>();
        File csvFile = new File(pathToCsv);
        if (csvFile.isFile()) {
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(seperator);
                    Song song = new Song();
                    song.setName(data[0]);
                    song.setArtist(data[1]);
                    song.setAlbum(data[2]);
                    song.setYear(Integer.parseInt(data[3]));
                    songs.add(song);
                }
                csvReader.close();
                System.out.println("[I] loaded data from CSV file...");
            }catch (Exception e){
                System.out.println("[E] Cannot find data file...");
                System.out.println(e.toString());
            }
        }else{
            System.out.println("[E] No data file...");
        }
        System.out.println("[I] Current directory: " + System.getProperty("user.dir"));
        return songs;
    }

    public static void writeCsv(String pathToCsv, ArrayList<Song> songs){
        try {
            FileWriter csvWriter = new FileWriter(pathToCsv);
            for(int i = 0; i < songs.size(); i++) {
                csvWriter.append(songs.get(i).getName());
                csvWriter.append(seperator);
                csvWriter.append(songs.get(i).getArtist());
                csvWriter.append(seperator);
                csvWriter.append(songs.get(i).getAlbum());
                csvWriter.append(seperator);
                csvWriter.append(String.valueOf(songs.get(i).getYear()));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){

        }
    }

}
