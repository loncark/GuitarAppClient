package com.loncark.guitarappclient;

import com.loncark.guitarappclient.module.Guitar;
import com.loncark.guitarappclient.module.Material;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    TextField codeFieldGET;
    @FXML
    Button getByCodeButton;
    @FXML
    Button getAllButton;
    @FXML
    TableView<Guitar> tableGET;

    @FXML
    TextField codeFieldPOST;
    @FXML
    TextField nameFieldPOST;
    @FXML
    TextField priceFieldPOST;
    @FXML
    ComboBox<Material> neckComboPOST;
    @FXML
    ComboBox<Material> bodyComboPOST;
    @FXML
    ComboBox<Long> stockComboPOST;
    @FXML
    ComboBox<Long> idComboPOST;
    @FXML
    Button submitButtonPOST;
    @FXML
    Label messagePOST;

    @FXML
    ComboBox<String> codeComboDELETE;
    @FXML
    Label messageDELETE;
    @FXML
    Button deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    protected void onHelloButtonClick() {

    }


}