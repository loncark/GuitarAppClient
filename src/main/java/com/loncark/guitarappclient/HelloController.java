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

import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.loncark.guitarappclient.module.Material.*;

public class HelloController implements Initializable {
    @FXML
    private TextField codeFieldGET;
    @FXML
    private TableView<Guitar> tableGET;
    @FXML
    private TableColumn<Guitar, String> codeColumn;
    @FXML
    private TableColumn<Guitar, String> nameColumn;
    @FXML
    private TableColumn<Guitar, String> priceColumn;
    @FXML
    private TableColumn<Guitar, String> stockColumn;


    @FXML
    private TextField codeFieldPOST;
    @FXML
    private TextField nameFieldPOST;
    @FXML
    private TextField priceFieldPOST;
    @FXML
    private ComboBox<Material> neckComboPOST;
    @FXML
    private ComboBox<Material> bodyComboPOST;
    @FXML
    private ComboBox<Long> stockComboPOST;
    @FXML
    private ComboBox<Long> idComboPOST;
    @FXML
    private Label messagePOST;

    @FXML
    private ComboBox<String> codeComboDELETE;
    @FXML
    private Label messageDELETE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Guitar> guitars = retrieveAllGuitars();
        setInitialValues(guitars);
    }

    private List<Guitar> retrieveAllGuitars() {
        RestTemplate restTemplate = new RestTemplate();

        String restEndpointUrl = "http://localhost:7000/";
        ResponseEntity<Guitar[]> guitarArrayResponse =
                restTemplate.getForEntity(restEndpointUrl, Guitar[].class);

        for(Guitar guitar : guitarArrayResponse.getBody()) {
            System.out.println("Guitar name: " + guitar.getName());
            System.out.println("Guitar price: " + guitar.getPrice());
        }

        Guitar[] guitarArray = guitarArrayResponse.getBody();
        return Arrays.asList(guitarArray);
    }

    private void setInitialValues(List<Guitar> guitars) {

        codeColumn.setCellValueFactory(new PropertyValueFactory<Guitar, String>("Code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Guitar, String>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Guitar, String>("Price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<Guitar, String>("Stock"));

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

        // setting the values of the combo box in POST
        ObservableList<Material> neckMaterials = FXCollections.observableArrayList(Rosewood, Maple);
        neckComboPOST.setItems(neckMaterials);
        ObservableList<Material> bodyMaterials = FXCollections.observableArrayList(Alder, Ebony, Mahogany);
        bodyComboPOST.setItems(bodyMaterials);
        ObservableList<Long> stock = FXCollections.observableArrayList(1L, 2L, 3L, 4L, 5L, 6L);
        stockComboPOST.setItems(stock);

        messagePOST.setText("");
        messageDELETE.setText("");
    }

    @FXML
    protected void onGetAllButtonClick() {
        List<Guitar> guitars = retrieveAllGuitars();
        ObservableList<Guitar> guitarList = FXCollections.observableArrayList();
        guitarList.addAll(guitars);

        tableGET.setItems(guitarList);
    }


}

