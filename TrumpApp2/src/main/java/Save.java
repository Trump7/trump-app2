import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Save {

    public void save(ObservableList<MineItemData> mainList){
        //Creating a file chooser to make it easier for the user to save
        FileChooser fileChooser = new FileChooser();

        //Setting a default file saved name
        fileChooser.setInitialFileName("MinecraftInv");

        //Set extension filter for TSV, JSON, and HTML
        FileChooser.ExtensionFilter tsvFilter = new FileChooser.ExtensionFilter("TSV files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");

        //Adding the extension filter as options for the user
        fileChooser.getExtensionFilters().addAll(tsvFilter, jsonFilter, htmlFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, file.getName().length());

            switch (fileExtension) {
                case "txt" -> saveTxtToFile(mainList, file);
                case "json" -> saveJsonToFile(mainList, file);
                case "html" -> saveHtmlToFile(mainList, file);
            }
        }
    }

    public void saveTxtToFile(ObservableList<MineItemData> list, File file){
        try{
            PrintWriter writer;
            writer = new PrintWriter(file);

            //Goes through every item in the Item list and adds tabs in between each item
            for (MineItemData mine : list) {
                writer.write(mine.getName() + "\t" + mine.getValue() +
                        "\t" + mine.getSerial() + "\n");
            }
            //the above for loop adds all items line by line to a text file
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void saveJsonToFile(ObservableList<MineItemData> list, File file){

    }

    public void saveHtmlToFile(ObservableList<MineItemData> list, File file){

    }
}
