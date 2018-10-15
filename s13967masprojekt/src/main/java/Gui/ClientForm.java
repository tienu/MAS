package Gui;

import Zapytania.ZapytaniaDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ClientForm implements Callbackable {

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField peselField;

    @FXML
    private DatePicker birthdayField;

    private Runnable runnable;

    @FXML
    public void submit() throws Exception {


        String surname = surnameField.getText();
        String name=nameField.getText();
        String pesel=peselField.getText();
        String birthday=birthdayField.getValue().toString();



        new ZapytaniaDao().addKlient(name,surname,pesel,birthday);

        Stage stage = (Stage) surnameField.getParent().getScene().getWindow();
        stage.close();
        runnable.run();

    }

    @Override
    public void setCallback(Runnable runnable) {
        this.runnable = runnable;
    }
}
