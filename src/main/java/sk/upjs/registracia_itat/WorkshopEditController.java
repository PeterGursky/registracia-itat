package sk.upjs.registracia_itat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.registracia_itat.entity.Workshop;
import sk.upjs.registracia_itat.persitent.DaoFactory;
import sk.upjs.registracia_itat.persitent.WorkshopDao;

public class WorkshopEditController {

	private WorkshopDao workshopDao;
	private WorkshopFxModel workshopModel;
	
    @FXML
    private ComboBox<Workshop> workshopComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceFullTextField;

    @FXML
    private TextField priceStudentTextField;

    @FXML
    private TextField priceFullLateTextField;

    @FXML
    private TextField priceStudentLateTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button saveButton;

    public WorkshopEditController() {
		workshopDao = DaoFactory.INSTANCE.getWorkshopDao();
		workshopModel = new WorkshopFxModel();
	}
    
    @FXML
    void initialize() {
    	List<Workshop> workshopy = workshopDao.getAll();
    	workshopComboBox.setItems(FXCollections.observableList(workshopy));
    	workshopComboBox.getSelectionModel().selectedItemProperty()
    		.addListener(new ChangeListener<Workshop>() {

				@Override
				public void changed(ObservableValue<? extends Workshop> observable, 
						Workshop oldValue, Workshop newValue) {
					workshopModel.setWorkshop(newValue);
				}
			});
//    	if (! workshopComboBox.getItems().isEmpty()) { // mame aspon 1 workshop
//    		workshopComboBox.getSelectionModel().select(workshopComboBox.getItems().get(0));
//    	}
    	if (!workshopy.isEmpty()) {
    		workshopComboBox.getSelectionModel().select(workshopy.get(0));
    	}
    	nameTextField.textProperty().bindBidirectional(workshopModel.nameProperty());
    	startDatePicker.valueProperty().bindBidirectional(
    			workshopModel.startProperty());    	
    	endDatePicker.valueProperty().bindBidirectional(
    			workshopModel.endProperty());    	
    	priceFullTextField.textProperty().bindBidirectional(
    			workshopModel.priceFullProperty(), new NumberStringConverter());
    	priceStudentTextField.textProperty().bindBidirectional(
    			workshopModel.priceStudentProperty(), new NumberStringConverter());
    	priceFullLateTextField.textProperty().bindBidirectional(
    			workshopModel.priceFullLateProperty(), new NumberStringConverter());
    	priceStudentLateTextField.textProperty().bindBidirectional(
    			workshopModel.priceStudentLateProperty(), new NumberStringConverter());
    }
}

