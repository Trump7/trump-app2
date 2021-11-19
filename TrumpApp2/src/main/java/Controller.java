import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {

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

    }

    @FXML
    void clearList(ActionEvent event) {

    }

    @FXML
    void closeProgram(ActionEvent event) {

    }

    @FXML
    void editItem(ActionEvent event) {

    }

    @FXML
    void loadFile(ActionEvent event) {

    }

    @FXML
    void openHelp(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    @FXML
    void saveFile(ActionEvent event) {

    }

    @FXML
    void searchTextField(ActionEvent event) {

    }

}
