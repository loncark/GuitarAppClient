package com.loncark.guitarappclient.module;

import javafx.scene.control.Alert;

import java.util.Objects;

public class Alerts {

    public static <A,B,C> boolean oneIsNull(A neck, B body, C stock) {
        if (neck == null || body == null || stock == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Null value");
            alert.setContentText("No variable should be null.");

            alert.showAndWait();
            return true;
        }
        return false;
    }

    public static boolean isAllOk(String name, String price, String stock, String neck, String body, String code) {
        if(isNameOk(name) && isBodyMaterialOk(body) && isNeckMaterialOk(neck) && isCodeOk(code)
                && isPriceOk(price) && isStockOk(stock)) {
            return true;
        }
        return false;
    }

    public static boolean isNameOk(String name) {
        if(name.matches("^(?![\\s.]+$)[a-zA-Z0-9\\s.]*$") == false || name.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong value");
            alert.setContentText("The name should consist solely of letters and numbers.");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isPriceOk(String price) {              // real numbers
        if(price.equals("") || !price.matches("((\\+|-)?([0-9]+)(\\.[0-9]+)?)|((\\+|-)?\\.?[0-9]+)")
                || Double.parseDouble(price) < 0.0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong value");
            alert.setContentText("The price should be larger than 0.");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isStockOk(String stock) { // positive, non null integer regex
        if(!stock.matches("^[1-9]\\d*$") || Integer.parseInt(stock) < 1 || Integer.parseInt(stock) > 6) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong value");
            alert.setContentText("The stock should be larger than 0 and less than 7.");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isNeckMaterialOk(String neck) {
        if(!Objects.equals(neck, Material.Rosewood.toString()) && !Objects.equals(neck, Material.Maple.toString())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong value");
            alert.setContentText("The neck material should be one of the following: Rosewood, Maple");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isBodyMaterialOk(String body) {
        if(!Objects.equals(body, Material.Alder.toString()) && !Objects.equals(body, Material.Mahogany.toString())
                && !Objects.equals(body, Material.Ebony.toString())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong value");
            alert.setContentText("The body material should be one of the following: Alder, Mahogany, Ebony.");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isCodeOk(String code) {
        if(code.matches("^\\d{4}$") == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Wrong value");
            alert.setContentText("The code should have exactly 4 digits.");

            alert.showAndWait();
            return false;
        }
        return true;
    }
}
