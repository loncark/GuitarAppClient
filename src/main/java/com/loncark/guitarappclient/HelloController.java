package com.loncark.guitarappclient;

import com.loncark.guitarappclient.module.Guitar;
import com.loncark.guitarappclient.module.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.loncark.guitarappclient.module.Material.*;

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
        // setting the values of the combo box in POST
        ObservableList<Material> neckMaterials = FXCollections.observableArrayList(Rosewood, Maple);
        neckComboPOST.setItems(neckMaterials);
        ObservableList<Material> bodyMaterials = FXCollections.observableArrayList(Alder, Ebony, Mahogany);
        bodyComboPOST.setItems(bodyMaterials);
        ObservableList<Long> stock = FXCollections.observableArrayList(1L, 2L, 3L, 4L, 5L, 6L);
        stockComboPOST.setItems(stock);

        // retrieving the list of guitars
        RestTemplate restTemplate = new RestTemplate();

        String restEndpointUrl = "http://localhost:7000/";
        ResponseEntity<Guitar[]> guitarArrayResponse =
                restTemplate.getForEntity(restEndpointUrl, Guitar[].class);

        for(Guitar guitar : guitarArrayResponse.getBody()) {
            System.out.println("Guitar name: " + guitar.getName());
            System.out.println("Guitar price: " + guitar.getPrice());
        }

        ObservableList<Guitar> guitarList = FXCollections.observableArrayList();
        Guitar[] guitarArray = guitarArrayResponse.getBody();
        List<Guitar> guitars = Arrays.asList(guitarArray);
        guitarList.addAll(guitars);

        // setting the values of the combo box in POST
        ObservableList<Long> guitarIds = FXCollections.observableArrayList();
        for (Guitar guitar : guitars) {
            long id = guitar.getId();
            guitarIds.add(id);
        }
        idComboPOST.setItems(guitarIds);

        // setting the values of the combo box in DELETE
        ObservableList<String> guitarCodes = FXCollections.observableArrayList();
        for (Guitar guitar : guitars) {
            String code = guitar.getCode();
            guitarCodes.add(code);
        }
        codeComboDELETE.setItems(guitarCodes);

        tableGET.setItems(guitarList);
        messagePOST.setText("");
        messageDELETE.setText("");
    }

    @FXML
    protected void onHelloButtonClick() {

    }


}