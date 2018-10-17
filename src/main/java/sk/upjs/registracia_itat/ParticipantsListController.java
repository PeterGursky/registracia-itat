package sk.upjs.registracia_itat;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParticipantsListController {

	private ParticipantDao participantDao 
				= ParticipantDaoFactory.INSTANCE.getParticipantDao();
	private ObservableList<Participant> participantsModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Participant> selectedParticipant = new SimpleObjectProperty<>();
	
    @FXML
    private TableView<Participant> participantsTableView;
    
    @FXML
    private Button editParticipantButton;
	
    @FXML
    void initialize() {
    	participantsModel = FXCollections.observableArrayList(participantDao.getAll());
    	
    	editParticipantButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ParticipantEditController editController = new ParticipantEditController(selectedParticipant.get());            
					FXMLLoader fxmlLoader = new	FXMLLoader(getClass().getResource("ParticipantEdit.fxml"));
					fxmlLoader.setController(editController);
					Parent rootPane	= fxmlLoader.load();
					Scene scene	= new Scene(rootPane);
					
					Stage dialog = new Stage();
					dialog.setScene(scene);
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.showAndWait();
					// tento kod sa spusti az po zatvoreni okna
					//participantsModel = FXCollections.observableArrayList(participantDao.getAll());
					//participantsTableView.setItems(participantsModel);
					participantsModel.setAll(participantDao.getAll());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	TableColumn<Participant, Long> idCol = new TableColumn<>("ID");
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	participantsTableView.getColumns().add(idCol);
    	columnsVisibility.put("ID", idCol.visibleProperty());

    	TableColumn<Participant, String> nameCol = new TableColumn<>("Meno");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	participantsTableView.getColumns().add(nameCol);
    	columnsVisibility.put("Meno", nameCol.visibleProperty());

    	TableColumn<Participant, String> surnameCol = new TableColumn<>("Priezvisko");
    	surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
    	participantsTableView.getColumns().add(surnameCol);
    	columnsVisibility.put("Priezvisko", surnameCol.visibleProperty());
    	
    	TableColumn<Participant, String> emailCol = new TableColumn<>("E-mail");
    	emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    	emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
    	emailCol.setEditable(true);
    	emailCol.setVisible(false);
    	participantsTableView.getColumns().add(emailCol);
    	columnsVisibility.put("E-mail", emailCol.visibleProperty());
    	

    	participantsTableView.setItems(participantsModel);
    	participantsTableView.setEditable(true);

    	ContextMenu contextMenu = new ContextMenu();
    	for (Entry<String, BooleanProperty> entry: columnsVisibility.entrySet()) {
	    	CheckMenuItem menuItem = new CheckMenuItem(entry.getKey());
	    	menuItem.selectedProperty().bindBidirectional(entry.getValue());
	    	contextMenu.getItems().add(menuItem);
    	}
    	participantsTableView.setContextMenu(contextMenu);
    	
    	participantsTableView.getSelectionModel().
    		selectedItemProperty().addListener(new ChangeListener<Participant>() {
				@Override
				public void changed(ObservableValue<? extends Participant> observable, 
						Participant oldValue,
						Participant newValue) {
					selectedParticipant.set(newValue);
				}
			});
    }
}
