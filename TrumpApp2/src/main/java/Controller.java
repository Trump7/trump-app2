/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Trump
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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
    private MenuItem about;

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
    void openHelp(ActionEvent event) throws IOException {
        //load the extra fxml scene (will be created)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Help.fxml"));
        Parent root1 = fxmlLoader.load();
        //create a new scene
        Stage stage = new Stage();
        //create a title for the scene
        stage.setTitle("Help Guide");
        stage.setScene(new Scene(root1));
        //make scene not resizeable
        stage.setResizable(false);
        //show scene
        stage.show();
    }

    @FXML
    void openAbout(ActionEvent event) throws IOException {
        //load the extra fxml scene (will be created)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
        Parent root1 = fxmlLoader.load();
        //create a new scene
        Stage stage = new Stage();
        //create a title for the scene
        stage.setTitle("About page");
        stage.setScene(new Scene(root1));
        //make scene not resizeable
        stage.setResizable(false);
        //show scene
        stage.show();
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
        ObservableList<MineItemData> loadedData = FXCollections.observableArrayList();

        Load loadFile = new Load();

        loadedData = loadFile.load(loadedData);

        //if the new list is not empty, it will clear the current list
        if(loadedData != null){
            list.clear();
            //set the current list to the passed observable list and set the tableview to the new list
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
    void invSearcher(KeyEvent event) {
        //making a new filtered list
        FilteredList<MineItemData> filteredData = new FilteredList<>(list, b -> true);

        //Add a listener to check changes made to text box
        searchInv.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(MineItemData -> {
                //adding a predicate to figure out if the words written are present in the data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                //checking item name
                if(MineItemData.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                //checking item serial
                else if(MineItemData.getSerial().indexOf(newValue) != -1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        //updating the inventory view
        SortedList<MineItemData> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(inventoryView.comparatorProperty());
        inventoryView.setItems(sortedData);
    }
}
