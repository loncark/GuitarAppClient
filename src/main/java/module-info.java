module com.loncark.guitarappclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;


    opens com.loncark.guitarappclient to javafx.fxml;
    exports com.loncark.guitarappclient;
    opens com.loncark.guitarappclient.module;
    exports com.loncark.guitarappclient.module;
}