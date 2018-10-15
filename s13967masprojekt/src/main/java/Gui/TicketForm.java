package Gui;

import Aktorzy.Klient;
import Dane.Statussprawy;
import Dane.Typ;
import Zapytania.ZapytaniaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;


public class TicketForm implements Callbackable {


    @FXML
    private DatePicker date;

    
    @FXML
    private ComboBox client= new ComboBox();

    @FXML
    private ComboBox type= new ComboBox();

    private ObservableList<Klient> klientlist;


    private ObservableList<Typ> typlist;

    @FXML
    public void initialize() throws SQLException {

        klientlist=FXCollections.observableArrayList(new ZapytaniaDao().getKlient());
        client.getItems().addAll(klientlist);



        typlist=FXCollections.observableArrayList(new ZapytaniaDao().getTypy());

        type.getItems().addAll(typlist);

    }

    private Runnable runnable;

    @FXML
    public void submit() throws SQLException, ParseException {


        Klient klient= (Klient) client.getSelectionModel().getSelectedItem();
        Typ typ=(Typ) type.getSelectionModel().getSelectedItem();
        String data=date.getValue().toString();

        new ZapytaniaDao().addSprawa(data,Statussprawy.zlozona,typ,klient);

        Stage stage = (Stage) client.getParent().getScene().getWindow();

        stage.close();

        runnable.run();


    }


    
    @Override
    public void setCallback(Runnable runnable) {
        this.runnable = runnable;
    }
}
