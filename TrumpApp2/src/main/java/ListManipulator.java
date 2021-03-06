/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Matthew Trump
 */
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListManipulator {

    Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public ObservableList<MineItemData> addItem(ObservableList<MineItemData> list) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Item");

        // Adding buttons to the dialog
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        //setting up a grid pane to make it look nicer
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        //adding the text boxes and setting the box text to the selected items contents
        TextField name = new TextField();
        name.setPromptText("ex. Bow, Pickaxe, Sword");
        TextField value = new TextField();
        value.setPromptText("value >= 0");
        TextField serial = new TextField();
        serial.setPromptText("A-XXX-XXX-XXX");

        //Adding labels for each of the text fields
        gridPane.add(new Label("Name: "), 0, 0);
        gridPane.add(name, 1, 0);
        gridPane.add(new Label("Value: "), 0, 1);
        gridPane.add(value, 1, 1);
        gridPane.add(new Label("Serial: "), 0, 2);
        gridPane.add(serial, 1, 2);

        dialog.getDialogPane().setContent(gridPane);

        Optional<ButtonType> result = dialog.showAndWait();

        String nameBox = name.getText();
        String serialBox = serial.getText();
        String valueBox = value.getText();

        if(result.isPresent() && result.get() == okButtonType){
            if(validateName(nameBox) && validateValue(valueBox) && validateSerial(serialBox, list)){
                list.add(new MineItemData(nameBox, Float.valueOf(df.format(Float.valueOf(valueBox))), serialBox));
            }
        }
       return list;
    }

    public ObservableList<MineItemData> clearList(ObservableList<MineItemData> list){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmation Needed");
        confirm.setHeaderText("Are you sure you would like to clear the list?");

        Optional<ButtonType> result = confirm.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            list.clear();
        }
        return list;
    }

    public ObservableList<MineItemData> editList(ObservableList<MineItemData> list, MineItemData selectedItem){

        if(selectedItem != null) {
            String selectedName = selectedItem.getName();
            String selectedSerial = selectedItem.getSerial();
            float selectedValue = selectedItem.getValue();

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);

            // Create the custom dialog to edit the selected item
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Edit Item");
            dialog.setHeaderText("Please type new values for item:");

            // Adding buttons to the dialog
            ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            //setting up a grid pane to make it look nicer
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            //adding the text boxes and setting the box text to the selected items contents
            TextField name = new TextField();
            name.setPromptText(selectedName);
            TextField value = new TextField();
            value.setPromptText(String.valueOf(selectedValue));
            TextField serial = new TextField();
            serial.setPromptText(selectedSerial);

            //Adding labels for each of the text fields
            gridPane.add(new Label("Name: "), 0, 0);
            gridPane.add(name, 1, 0);
            gridPane.add(new Label("Value: "), 0, 1);
            gridPane.add(value, 1, 1);
            gridPane.add(new Label("Serial: "), 0, 2);
            gridPane.add(serial, 1, 2);

            dialog.getDialogPane().setContent(gridPane);

            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent()){
                //if any of the text boxes are filled, replace the items corresponding subitems with the new name
                if(!name.getText().equals("") && validateName(name.getText())) {
                    selectedItem.setName(name.getText());
                }
                if(!value.getText().equals("") && validateValue(value.getText())){
                    selectedItem.setValue(Float.valueOf(df.format(Float.valueOf(value.getText()))));
                }
                if(!serial.getText().equals("") && validateSerial(serial.getText(), list)){
                    selectedItem.setSerial(serial.getText());
                }
            }
        }
        return list;
    }

    public ObservableList<MineItemData> removeItem(ObservableList<MineItemData> list, MineItemData selectedItem){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation needed");
        alert.setHeaderText("Are you sure you would like to delete the item:");

        if(selectedItem != null){
            alert.setContentText("Name: " + selectedItem.getName() + "\nValue: " +
                    selectedItem.getValue() + "\nSerial #: " + selectedItem.getSerial());

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                list.remove(selectedItem);
            }
        }
        return list;
    }

    public boolean validateValue(String value){
        Pattern p = Pattern.compile("[0-9]+\\.?[0-9]*");
        Matcher m = p.matcher(value);

        if(!(m.find() && m.group().equals(value))){
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Value box must only contain numbers and one decimal.");
            errorAlert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean validateSerial(String serial, ObservableList<MineItemData> list){
        Pattern p = Pattern.compile("^[A-Za-z]-[A-Za-z0-9]{3}-[A-Za-z0-9]{3}-[A-Za-z0-9]{3}");
        Matcher m = p.matcher(serial);

        if(!(m.find() && m.group().equals(serial) && validateSerialList(serial, list))){
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Serial is invalid format or already in use.");
                errorAlert.showAndWait();
                return false;
        }
        return true;
    }

    public boolean validateName(String name){
        if(!(name.length() >= 2 && name.length() <= 256)){
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Name must be between 2 and 256 characters inclusive.");
            errorAlert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean validateSerialList(String serial, ObservableList<MineItemData> list){
        if(list != null){
            for(MineItemData mine : list){
                if(mine.getSerial().equals(serial)){
                    return false;
                }
            }
        }
        return true;
    }
}