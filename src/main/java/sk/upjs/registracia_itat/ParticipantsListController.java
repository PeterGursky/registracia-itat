package sk.upjs.registracia_itat;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ParticipantsListController {

	private ParticipantDao participantDao = new ParticipantDao();
	private ObservableList<ParticipantFxModel> participantsModel;
	
	@FXML
    private ListView<ParticipantFxModel> participantsListView;

    @FXML
    private TableView<ParticipantFxModel> participantsTableView;
	
    @FXML
    void initialize() {
    	List<ParticipantFxModel> models = new ArrayList<>();
    	for (Participant p : participantDao.getAll()) {
    		models.add(new ParticipantFxModel(p));
    	}
    	
    	participantsModel = FXCollections.observableArrayList(models);
    	participantsListView.setItems(participantsModel);
    	
    	TableColumn<ParticipantFxModel, Long> idCol = new TableColumn<>("ID");
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	participantsTableView.getColumns().add(idCol);

    	TableColumn<ParticipantFxModel, String> nameCol = new TableColumn<>("Meno");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	participantsTableView.getColumns().add(nameCol);

    	TableColumn<ParticipantFxModel, String> surnameCol = new TableColumn<>("Priezvisko");
    	surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
    	participantsTableView.getColumns().add(surnameCol);
    	
    	TableColumn<ParticipantFxModel, String> emailCol = new TableColumn<>("E-mail");
    	emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    	emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
    	emailCol.setEditable(true);
    	participantsTableView.getColumns().add(emailCol);
    	

    	participantsTableView.setItems(participantsModel);

    }
}
