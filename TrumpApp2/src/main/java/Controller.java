import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
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
        //pass the current observable list to the Save class to handle saving the file
    }

    @FXML
    void loadFile(ActionEvent event) {
        //Create a temporary observable list to hold the new lists' data
        //if the new list is not empty, it will clear the current list
        //set the current list to the passed observable list and set the tableview to the new list
    }

    @FXML
    void closeProgram(ActionEvent event) {
        //figure out a way to end the program from the file menu
    }

    @FXML
    void searchTextField(ActionEvent event) {
        //need an active listener to make sure the table is updating
        //everytime a new letter or number is written
    }

}
