package Gui;

import Aktorzy.Klient;
import Dane.Sprawa;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TicketEditForm implements Callbackable {


    @FXML
    private DatePicker date;


    @FXML
    private ComboBox client= new ComboBox();

    @FXML
    private ComboBox type= new ComboBox();

    @FXML
    private ComboBox<Statussprawy> state= new ComboBox<>();

    private ObservableList<Klient> klientlist;


    private ObservableList<Typ> typlist;

    String id;
    Sprawa sprawa;
   // Date datazalozenia;
    LocalDate datazal;
    public TicketEditForm(String id){

        this.id=id;

        try {
            Sprawa sprawa =  ZapytaniaDao.getSprawaId(id);
            this.sprawa= sprawa;
        } catch (SQLException e) {
            e.printStackTrace();
        }

       // datazalozenia=sprawa.getDatazalozenia();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String datazalozenia = dateFormat.format(sprawa.getDatazalozenia());

        datazal = LocalDate.parse(datazalozenia);




    }



    @FXML
    public void initialize() throws SQLException {

        klientlist=FXCollections.observableArrayList(new ZapytaniaDao().getKlient());
        client.getItems().addAll(klientlist);
        client.setValue(sprawa.getKlient());


        typlist=FXCollections.observableArrayList(new ZapytaniaDao().getTypy());
        type.getItems().addAll(typlist);
        type.setValue(sprawa.getTyp());


        ObservableList<Statussprawy> observableList;
        observableList = FXCollections.observableArrayList();
        observableList.setAll(Statussprawy.values());
        state.setItems( observableList);

        state.getItems().setAll(Statussprawy.values());
        state.setValue(Statussprawy.valueOf(sprawa.getStatus()));



        this.date.setValue(datazal);

//TODO wypełnienie formularza enum
    }

    private Runnable runnable;

    @FXML
    public void submit() throws SQLException, ParseException {


        Klient klient= (Klient) client.getSelectionModel().getSelectedItem();
        Typ typ=(Typ) type.getSelectionModel().getSelectedItem();
        String data=date.getValue().toString();

      //  new ZapytaniaDao().addSprawa(data,Statussprawy.zlozona,typ,klient);
        new ZapytaniaDao().updateSprawa(id,data,Statussprawy.zlozona,typ,klient);


        Stage stage = (Stage) client.getParent().getScene().getWindow();

        stage.close();

        runnable.run();


    }

    @FXML
    public void remove() throws SQLException, ParseException {



        try {
            new ZapytaniaDao().removeSprawa(this.id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) client.getParent().getScene().getWindow();

        stage.close();

        runnable.run();


    }


    @Override
    public void setCallback(Runnable runnable) {
        this.runnable = runnable;
    }
}
