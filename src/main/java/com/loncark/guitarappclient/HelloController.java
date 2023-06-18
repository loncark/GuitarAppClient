package com.loncark.guitarappclient;

import com.loncark.guitarappclient.module.Guitar;
import com.loncark.guitarappclient.module.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

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
    private Label messagePOST;

    @FXML
    private ComboBox<String> codeComboDELETE;
    @FXML
    private Label messageDELETE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Guitar> guitars = getAllGuitars();
        setInitialValues(guitars);
    }

    private List<Guitar> getAllGuitars() {
        RestTemplate restTemplate = new RestTemplate();

        String restEndpointUrl = "http://localhost:7000/";
        ResponseEntity<Guitar[]> guitarArrayResponse =
                restTemplate.getForEntity(restEndpointUrl, Guitar[].class);

        Guitar[] guitarArray = guitarArrayResponse.getBody();

        for (Guitar guitar : guitarArray) {
            System.out.println("Guitar name: " + guitar.getName());
            System.out.println("Guitar price: " + guitar.getPrice());
        }

        return Arrays.asList(guitarArray);
    }

    private void setInitialValues(List<Guitar> guitars) {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("Code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        messagePOST.setText("");
        messageDELETE.setText("");

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

    }

    @FXML
    protected void onGetAllButtonClick() {
        List<Guitar> guitars = getAllGuitars();
        ObservableList<Guitar> guitarList = FXCollections.observableArrayList();
        guitarList.addAll(guitars);

        tableGET.setItems(guitarList);
    }

    @FXML
    protected void onGetByCodeButtonClick() {
        Guitar guitar = getAGuitarByCode(codeFieldGET.getText());

        ObservableList<Guitar> guitarList = FXCollections.observableArrayList();
        guitarList.add(guitar);

        tableGET.setItems(guitarList);
    }

    private Guitar getAGuitarByCode(String guitarCode) {
        RestTemplate restTemplate = new RestTemplate();

        String restEndpointUrl = "http://localhost:7000/" + guitarCode;
        ResponseEntity<Guitar> guitarResponse =
                restTemplate.getForEntity(restEndpointUrl, Guitar.class);

        Guitar guitar = guitarResponse.getBody();

        System.out.println("Guitar name: " + guitar.getName());
        System.out.println("Guitar price: " + guitar.getPrice());

        return guitar;
    }

    @FXML
    protected void onSubmitButtonClick() {
        List<Guitar> guitars = getAllGuitars();

        for (Guitar guitar : guitars) {
            if(Objects.equals(guitar.getCode(), codeFieldPOST.getText())) {
                makeAPUTRequest(guitar.getId());
                return;
            }
        }
        makeAPOSTRequest();
    }

    protected void makeAPOSTRequest() {

        RestTemplate restTemplate = new RestTemplate();
        String restEndpointUrl = "http://localhost:7000/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Guitar newGuitar = new Guitar();
        newGuitar.setCode(codeFieldPOST.getText());
        newGuitar.setName(nameFieldPOST.getText());
        newGuitar.setPrice(new BigDecimal(priceFieldPOST.getText().trim()));
        newGuitar.setStock(stockComboPOST.getValue());
        newGuitar.setBody(neckComboPOST.getValue());
        newGuitar.setNeck(neckComboPOST.getValue());

        String requestJson = new Gson().toJson(newGuitar);
        System.out.println(requestJson);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(restEndpointUrl, requestEntity, String.class);

        System.out.println("POST RESPONSE: " + response.getStatusCode());
        if(response.getStatusCode() == HttpStatus.CREATED) {
            messagePOST.setText("(201) Successfully created.");
        } else if (response.getStatusCode() == HttpStatus.CONFLICT){
            messagePOST.setText("(409) A guitar with the same code already exists.");
        } else {
            messagePOST.setText("Server error.");
        }

    }

    protected void makeAPUTRequest(Long id) {

        RestTemplate restTemplate = new RestTemplate();
        String restEndpointUrl = "http://localhost:7000/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Guitar newGuitar = new Guitar();
        newGuitar.setId(id);
        newGuitar.setCode(codeFieldPOST.getText());
        newGuitar.setName(nameFieldPOST.getText());
        newGuitar.setPrice(new BigDecimal(priceFieldPOST.getText().trim()));
        newGuitar.setStock(stockComboPOST.getValue());
        newGuitar.setBody(neckComboPOST.getValue());
        newGuitar.setNeck(neckComboPOST.getValue());

        String requestJson = new Gson().toJson(newGuitar);
        System.out.println(requestJson);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        restTemplate.put(restEndpointUrl, requestEntity);
        System.out.println("Updated hardware with id: " + newGuitar.getId());

        messagePOST.setText("Item updated.");
    }
}

