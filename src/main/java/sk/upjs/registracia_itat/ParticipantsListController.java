package sk.upjs.registracia_itat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ParticipantsListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> participantsListView;

    @FXML
    void initialize() {
        assert participantsListView != null : "fx:id=\"participantsListView\" was not injected: check your FXML file 'ParticipantsList.fxml'.";

    }
}
