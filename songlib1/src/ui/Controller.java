/*
 * authors: Ziyun Zhi, Junren Zhu
 */

package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;import data.Song;
import data.Songs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Controller {

    @FXML private ListView songlist;
    @FXML private TextField nameinput;
    @FXML private TextField artistinput;
    @FXML private TextField albuminput;
    @FXML private TextField yearinput;
    @FXML private Button commonbtn;
    @FXML private Button addbtn;
    public static Songs songs = new Songs();
    private ObservableList items = FXCollections.observableArrayList ();
    private String lastString;

    public static void saveToFile(){
        songs.save();
    }

    @FXML
    public void initialize() {
        updateListView();
        songlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>(){

            @Override
            public void changed(ObservableValue<?> observableValue, Object o, Object t1) {
                try {
                    Song selected = songs.findByString(t1.toString());
                    nameinput.setText(selected.getName());
                    artistinput.setText(selected.getArtist());
                    albuminput.setText(selected.getAlbum());
                    yearinput.setText(String.valueOf(selected.getYear()));
                    commonbtn.setDisable(true);
                    commonbtn.setText("Button");
                }catch (NullPointerException e){

                }
            }
        });
        songlist.getSelectionModel().select(0);
    }

    private void updateListView(){
        ArrayList<Song> songsList = songs.getSongs();
        items.clear();
        for (int i = 0; i < songsList.size(); i++)
            items.add(songsList.get(i).getString());
        songlist.setItems(items);
    }

    @FXML protected void addClicked(ActionEvent event) {
        changeStatusOfPanel(true, "Add", true);
    }

    private void changeStatusOfPanel(boolean flag, String commonbtnLabel, boolean clear){
        if(clear) {
            nameinput.setText("");
            artistinput.setText("");
            albuminput.setText("");
            yearinput.setText("");
        }
        nameinput.setEditable(flag);
        artistinput.setEditable(flag);
        albuminput.setEditable(flag);
        yearinput.setEditable(flag);
        commonbtn.setDisable(!flag);
        commonbtn.setText(commonbtnLabel);
    }

    public void commonClicked(ActionEvent actionEvent) {
        System.out.println("Year: " + yearinput.getText());
        if (yearinput.getText().equals("")) {

        } else {
            try {
                int temp = Integer.parseInt(yearinput.getText());
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText("Please input year correctly!");
                a.show();
                return;
            }
        }

        if(commonbtn.getText().equals("Add")){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(null);
            a.setContentText("Do you really want to add new song?");
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.OK){
                Song song = new Song();
                song.setName(nameinput.getText());
                song.setArtist(artistinput.getText());
                song.setAlbum(albuminput.getText());
                try {
                    song.setYear(Integer.parseInt(yearinput.getText()));
                }catch (Exception e){
                    song.setYear(0);
                }
                boolean added = songs.addSong(song);
                if(added){
                    items.add(song.getString());
                    songs.sort();
                    items.sort(Comparator.naturalOrder());
                    changeStatusOfPanel(false, "Button", true);
                    songlist.getSelectionModel().select(songs.getIndex(song.getString()));
                }else{
                    Alert aa = new Alert(Alert.AlertType.NONE);
                    aa.setAlertType(Alert.AlertType.INFORMATION);
                    aa.setHeaderText(null);
                    aa.setContentText("Song with the same name and artist already exists!");
                    aa.show();
                }
            }else{

            }

        }else if(commonbtn.getText().equals("Edit")){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(null);
            a.setContentText("Do you really want to change information of this song?");
            Optional<ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.OK){
                String name = nameinput.getText();
                String artist = artistinput.getText();
                String album = albuminput.getText();
                int year = 0;
                try {
                    year = Integer.parseInt(yearinput.getText());
                } catch (Exception e){

                }
                boolean edited = songs.changeSong(lastString, name, artist, album, year);
                if(!edited){
                    Alert aa = new Alert(Alert.AlertType.NONE);
                    aa.setAlertType(Alert.AlertType.INFORMATION);
                    aa.setHeaderText(null);
                    aa.setContentText("Song with the same name and artist already exists!");
                    aa.show();
                }else{
                    songs.sort();
                    updateListView();
                    changeStatusOfPanel(false, "Button", true);
                }
            }else {

            }
        }
    }

    public void deleteClicked(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Do you really want to delete this song?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
            String selected = (String)songlist.getSelectionModel().getSelectedItem();
            int index = songs.getIndex(selected);
            songs.delete(selected);
            updateListView();
            if(songs.size() == 0){

            }else if (songs.size() == index){
                songlist.getSelectionModel().select(index - 1);
            }else{
                songlist.getSelectionModel().select(index);
            }
        }else{

        }
    }

    public void editClicked(ActionEvent actionEvent) {
        lastString = nameinput.getText()+" ( "+artistinput.getText()+" )";
        changeStatusOfPanel(true, "Edit", false);
    }
}
