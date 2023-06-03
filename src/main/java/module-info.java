module com.loncark.guitarappclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.loncark.guitarappclient to javafx.fxml;
    exports com.loncark.guitarappclient;
}