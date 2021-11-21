import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources){
        inventoryName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        inventorySerial.setCellValueFactory(new PropertyValueFactory<>("Serial"));
        inventoryValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        inventoryView.setItems(list);
    }

    @FXML
    private TableColumn<MineItemData, String> inventoryName;

    @FXML
    private TableColumn<MineItemData, String> inventorySerial;

    @FXML
    private TableColumn<MineItemData, Float> inventoryValue;

    @FXML
    private TableView<MineItemData> inventoryView;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private MenuItem closeList;

    @FXML
    private MenuItem helpGuide;

    @FXML
    private MenuItem loadList;

    @FXML
    private MenuItem saveList;

    @FXML
    private TextField searchInv;

    ObservableList<MineItemData> list = FXCollections.observableArrayList();

    @FXML
    void addItem(ActionEvent event) {
        ListManipulator add = new ListManipulator();
        //need to call the ListManipulator class and the addItem method
        list = add.addItem(list);
        //passing the observable list with it
        inventoryView.refresh();


    }

    @FXML
    void clearList(ActionEvent event) {
        ListManipulator clear = new ListManipulator();
        //need to call the ListManipulator class and the clearList method
        //passing the observable list with it
        list = clear.clearList(list);
        inventoryView.refresh();
    }

    @FXML
    void editItem(ActionEvent event) {
        ListManipulator edit = new ListManipulator();
        //need to call the ListManipulator class and the editItem method
        //passing the observable list with it
        MineItemData selectedItem = inventoryView.getSelectionModel().getSelectedItem();
        list = edit.editList(list, selectedItem);
        inventoryView.refresh();
    }

    @FXML
    void removeItem(ActionEvent event) {
        ListManipulator remove = new ListManipulator();
        MineItemData selectedItem = inventoryView.getSelectionModel().getSelectedItem();
        //need to call the ListManipulator class and the removeItem method
        //passing the observable list with it
        list = remove.removeItem(list, selectedItem);
        inventoryView.refresh();
    }

    @FXML
    void openHelp(ActionEvent event){
        //create a new scene
        //load the extra fxml scene (will be created)
        //create a title for the scene
        //make scene uneditable
        //show scene
    }

    @FXML
    void saveFile(ActionEvent event) {
        Save saveFile = new Save();
        //pass the current observable list to the Save class to handle saving the file
        saveFile.save(list);
    }

    @FXML
    void loadFile(ActionEvent event) throws IOException {
        //Create a temporary observable list to hold the new lists' data
        //if the new list is not empty, it will clear the current list
        //set the current list to the passed observable list and set the tableview to the new list
        Load loadFile = new Load();

        ObservableList<MineItemData> loadedData = FXCollections.observableArrayList();

        loadedData = loadFile.load(loadedData);

        if(loadedData != null){
            list.clear();
            list = loadedData;
            inventoryView.setItems(list);
        }
    }

    @FXML
    void closeProgram(ActionEvent event) {
        //Confirmation for whether the user wants to exit or not
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation needed");
        alert.setHeaderText("Are you sure you would like to exit?");
        alert.setContentText("All unsaved data will be lost");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            //getting the stage from a button on the stage (addButton)
            //because for some reason you can't use the menu button item
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void searchTextField(ActionEvent event) {
        //need an active listener to make sure the table is updating
        //everytime a new letter or number is written
    }

}
