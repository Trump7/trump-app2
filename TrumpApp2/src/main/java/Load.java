import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Load {

    public ObservableList<MineItemData> load(ObservableList<MineItemData> mainList) throws IOException {
        //Creating a file chooser to make it easier for the user to save
        FileChooser fileChooser = new FileChooser();

        //Setting a default file selected name based off of save class
        fileChooser.setInitialFileName("MinecraftInv");

        //Set extension filter for TSV, JSON, and HTML
        FileChooser.ExtensionFilter tsvFilter = new FileChooser.ExtensionFilter("TSV files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");

        //Adding the extension filter as options for the user
        fileChooser.getExtensionFilters().addAll(tsvFilter, jsonFilter, htmlFilter);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, file.getName().length());

            switch (fileExtension) {
                case "txt":
                        mainList = loadTxtFromFile(mainList, file);
                        break;
                case "json":
                        mainList = loadJsonFromFile(mainList, file);
                        break;
                case "html":
                        mainList = loadHtmlFromFile(mainList, file);
                        break;
            }
        }
        return mainList;
    }

    public ObservableList<MineItemData> loadTxtFromFile(ObservableList<MineItemData> list, File file) throws IOException {
        FileReader temp = new FileReader(file);
        BufferedReader reader = new BufferedReader(temp);
        String line;

        //While there is still a line present, split the line up into three strings and add it to the
        //temporary observable list
        while((line = reader.readLine()) != null){
            String[] info = line.split("\t");
            list.add(new MineItemData(info[0], Float.valueOf(info[1]), info[2]));
        }

        reader.close();

        return list;
    }

    public ObservableList<MineItemData> loadJsonFromFile(ObservableList<MineItemData> list, File file){
        return list;
    }

    public ObservableList<MineItemData> loadHtmlFromFile(ObservableList<MineItemData> list, File file){
        return list;
    }
}
