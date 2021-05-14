package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static sample.PasswordHasher.hashPassword;

public class Controller
{
    @FXML
    public GridPane gpMain;
    @FXML
    public MenuBar menuBar;
    @FXML
    public MenuItem userHelp;
    @FXML
    public MenuItem javaDoc;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnSearch;
    @FXML
    public Button btnRemove;
    @FXML
    public TextField txtAddSearchRemove;
    @FXML
    public TextField txtHash;
    @FXML
    public ListView<String> lvList = new ListView<>();
    public BinarySearchTree tree = new BinarySearchTree();


    /**
     * This method displays the user help file.
     * @param actionEvent Left mouse click.
     */
    public void userHelp(ActionEvent actionEvent)
    {
        //Help file name.
        String path = "help.html";
        //File object.
        File htmlHelpFile = new File(path);
        try
        {
            //Open help file in default browser
            Desktop.getDesktop().browse(htmlHelpFile.toURI());
        }
        catch (IOException ex)
        {
            System.out.println("Error opening file: " + ex);
        }
    }

    /**
     * This method displays the JavaDoc.
     * @param actionEvent Left mouse click.
     */
    public void javaDoc(ActionEvent actionEvent)
    {
        //Help file name.
        String path = "javadoc/index.html";
        //File object.
        File htmlHelpFile = new File(path);
        try
        {
            //Open help file in default browser
            Desktop.getDesktop().browse(htmlHelpFile.toURI());
        }
        catch (IOException ex)
        {
            System.out.println("Error opening file: " + ex);
        }
    }
    public boolean isNullOrWhitespace(String _string)
    {
        return _string == null || _string.isBlank();
    }
    /**
     * This method adds item to binary search tree and listview.
     * @param actionEvent Left mouse click.
     */
    public void add(ActionEvent actionEvent)
    {
        if (!isNullOrWhitespace(txtAddSearchRemove.getText()))
        {
            try
            {
                boolean duplicate = false;
                for(int i = 0; i <= lvList.getItems().size()-1; i++)
                {
                    if (!duplicate && lvList.getItems().size() > 0 && tree.countStaff() > 0 && lvList.getItems().get(i).equals(tree.find(txtAddSearchRemove.getText())))
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Staff member already exists!", ButtonType.OK);
                        alert.show();
                        duplicate = true;
                    }
                }

                tree.insert(txtAddSearchRemove.getText());
                lvList.getItems().clear();
                tree.ClearStaff();
                tree.inOrder(tree.getRoot());
                ObservableList<String> convertList = FXCollections.observableArrayList(tree.getStaffList());
                lvList.setItems(convertList);
                txtHash.clear();
            }
            catch(Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Staff member already exists!", ButtonType.OK);
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter staff name in first text field.", ButtonType.OK);
            alert.show();
        }
    }

    /**
     * This method removes item from binary search tree and listview.
     * @param actionEvent Left mouse click.
     */
    public void remove(ActionEvent actionEvent)
    {
        boolean found = false;
        for (int i = 0; i < lvList.getItems().size(); i++)
        {
            if (tree.elementStaff(i).equals(tree.find(txtAddSearchRemove.getText())))
            {
                tree.delete(txtAddSearchRemove.getText());
                lvList.getItems().clear();
                tree.ClearStaff();
                tree.inOrder(tree.getRoot());
                for (int j = 0; j < tree.countStaff(); j++)
                {
                    lvList.getItems().add(tree.elementStaff(j));
                }
                found = true;
                txtAddSearchRemove.clear();
                txtHash.clear();
            }
        }
        if (!found)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Staff member not found!", ButtonType.OK);
            alert.show();
        }
    }

    /**
     * This method searches item in binary search tree and selects it in listview.
     * @param actionEvent Left mouse click.
     */
    public void search(ActionEvent actionEvent)
    {
        boolean found = false;
        lvList.getSelectionModel().select(-1);
        for (int i = 0; i < lvList.getItems().size(); i++)
        {
            if (lvList.getItems().get(i).equals(tree.find(txtAddSearchRemove.getText())))
            {
                lvList.getSelectionModel().select(i);
                createHash();
                found = true;
            }
        }
        if (!found || lvList.getItems().size() < 1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Staff member not found!", ButtonType.OK);
            alert.show();
        }
    }


    /**
     * This method creates a hash from string selected from listview.
     */
    public void createHash()
    {
        if (lvList.getSelectionModel().getSelectedIndex() > -1 && lvList.getItems().size() > 0)
        {
            String selectedStaff = lvList.getSelectionModel().getSelectedItem();
            txtAddSearchRemove.setText(selectedStaff);
            String hashedStaff = hashPassword(selectedStaff);
            txtHash.setText(hashedStaff);
        }
    }

    /**
     * This method calls the createHash method on a listview mouseclick.
     * @param mouseEvent Left mouse click.
     */
    public void generateHash(MouseEvent mouseEvent)
    {
        createHash();
    }
}
