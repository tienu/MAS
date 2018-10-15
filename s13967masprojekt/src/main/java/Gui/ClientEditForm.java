package Gui;

import Zapytania.ZapytaniaDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ClientEditForm implements Callbackable  {

    @FXML
    private TextField surnameField=new TextField();

    @FXML
    private TextField nameField=new TextField();

    @FXML
    private TextField peselField=new TextField();

    @FXML
    private DatePicker birthdayField;

    private Runnable runnable;

    int id;
    String surname;
    String name;
    String pesel;
    String birthday;

    public ClientEditForm(String id, String name, String surname, String pesel, String birthday) {

        this.id = Integer.parseInt(id);

        this.surname = surname;
        this.name = name;
        this.pesel = pesel;

       // this.birthday = new DatePicker(LocalDate.parse(birthdayField));
        this.birthday = birthday;
    }

    @FXML
    public void initialize() {
        this.surnameField.setText(surname);
        this.nameField.setText(name);
        this.peselField.setText(pesel);
        this.birthdayField.setValue(LocalDate.parse(birthday));
    }

    @FXML
    public void submit() throws Exception {


        surname = surnameField.getText();
        name=nameField.getText();
        pesel=peselField.getText();
        birthday=birthdayField.getValue().toString();

        System.out.println(surname+" "+name+" "+pesel+" " );

        new ZapytaniaDao().updateKlient(id,name,surname,pesel,birthday);

        Stage stage = (Stage) surnameField.getParent().getScene().getWindow();
        stage.close();
        runnable.run();

    }

    @FXML
    public void delete() throws Exception {

        new ZapytaniaDao().removeKlient(this.id);

        Stage stage = (Stage) surnameField.getParent().getScene().getWindow();
        stage.close();
        runnable.run();

    }

    @Override
    public void setCallback(Runnable runnable) {
        this.runnable = runnable;
    }
}
