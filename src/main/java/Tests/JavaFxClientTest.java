package Tests;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.loadui.testfx.*;

import java.io.IOException;
import java.util.Objects;
import org.junit.Test;


public class JavaFxClientTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GuitarClient.fxml")));
            return parent;
        } catch (IOException ex) {
            System.out.println("Failed to get root node.");
        }
        return parent;
    }

    @Test
    public void initialValuesAreOk() {

    }
}
