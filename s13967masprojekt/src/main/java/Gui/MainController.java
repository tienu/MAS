package Gui;

import Aktorzy.Klient;
import Dane.Sprawa;
import Zapytania.ZapytaniaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MainController {

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn tableColumnID;

    @FXML
    private TableColumn surnameColumnID;

    @FXML
    private TableColumn nameColumnID;

    @FXML
    private TableColumn stateColumnID;

    @FXML
    private TableColumn typeColumnID;

    @FXML
    private TableColumn dateColumnID;

    @FXML
    private TableView tableKlienciView;

    @FXML
    private TableColumn ktableColumnID;

    @FXML
    private TableColumn ksurnameColumnID;

    @FXML
    private TableColumn knameColumnID;

    @FXML
    private TableColumn kpeselColumnID;

    @FXML
    private TableColumn kdateColumnID;

    @FXML
    private TextField findClientField;

    @FXML
    private TextField findSprawaField;


    private List<String[]> dataSprawy;
    private ObservableList<SprawaWiersz> observableList;

    private List<String[]> dataKlienci;
    private ObservableList<KlientWiersz> kobservableList;

    public MainController() throws SQLException {

        dataSprawy = new ZapytaniaDao().getPodstawoweInformacjeSprawa();
        observableList = FXCollections.observableArrayList();

        dataKlienci = new ZapytaniaDao().getPodstawoweInformacjeKlient();
        kobservableList = FXCollections.observableArrayList();

    }

    private void loadData() {


        for (String[] danesprawa:dataSprawy) {

            SprawaWiersz sprawaWiersz = new SprawaWiersz();

            sprawaWiersz.setId( danesprawa[0]);

            sprawaWiersz.setName(danesprawa[1]);

            sprawaWiersz.setSurname(danesprawa[2]);

            sprawaWiersz.setDate(danesprawa[3]);

            sprawaWiersz.setState(danesprawa[4]);

            sprawaWiersz.setType(danesprawa[5]);

            observableList.add(sprawaWiersz);
        }

        for (String[] daneklient:dataKlienci) {

            KlientWiersz klientWiersz = new KlientWiersz();

            klientWiersz.setId( daneklient[0]);

            klientWiersz.setName(daneklient[1]);

            klientWiersz.setSurname(daneklient[2]);

            klientWiersz.setPesel(daneklient[3]);

            klientWiersz.setDate(daneklient[4]);

            kobservableList.add(klientWiersz);
        }




    }

    public void initialize() {


        loadData();

        setSprawa();
        setKlient();
        

    }

    private void setKlient() {
        ktableColumnID = new TableColumn("Id");
        knameColumnID = new TableColumn("Imie");
        ksurnameColumnID = new TableColumn("Nazwisko");
        kpeselColumnID = new TableColumn("Pesel");
        kdateColumnID = new TableColumn("Data Urodzenia");


        ktableColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("id") );
        knameColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("name"));
        ksurnameColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("surname"));
        kpeselColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("pesel"));
        kdateColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("date"));



        tableKlienciView.getColumns().add(ktableColumnID);
        tableKlienciView.getColumns().add(knameColumnID);
        tableKlienciView.getColumns().add(ksurnameColumnID);
        tableKlienciView.getColumns().add(kpeselColumnID);
        tableKlienciView.getColumns().add(kdateColumnID);


        tableKlienciView.setItems(kobservableList);

        TableView.TableViewSelectionModel kselectionModel = tableKlienciView.getSelectionModel();
        kselectionModel.select(0);

    }

    private void setSprawa() {
        tableColumnID = new TableColumn("Id");
        nameColumnID = new TableColumn("Imie");
        surnameColumnID = new TableColumn("Nazwisko");
        stateColumnID = new TableColumn("Stan");
        typeColumnID = new TableColumn("Typ");
        dateColumnID = new TableColumn("Data Rozpoczecia");


        tableColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("id") );
        nameColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("name"));
        surnameColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("surname"));
        stateColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("state"));
        dateColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("date"));
        typeColumnID.setCellValueFactory(new PropertyValueFactory<SprawaWiersz, String>("type"));


        tableView.getColumns().add(tableColumnID);
        tableView.getColumns().add(nameColumnID);
        tableView.getColumns().add(surnameColumnID);
        tableView.getColumns().add(stateColumnID);
        tableView.getColumns().add(dateColumnID);
        tableView.getColumns().add(typeColumnID);

        tableView.setItems(observableList);

        TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        selectionModel.select(0);
    }

    @FXML
    public void openClientForm() throws IOException {
        ClientForm cf = new ClientForm();
        openForm("/ClientForm.fxml", "Tworzenie klienta", () -> {
            System.out.println("Zapisano do bazy danych");
            kobservableList.clear();

            try {
                dataKlienci = new ZapytaniaDao().getPodstawoweInformacjeKlient();
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }, cf);
    }

    @FXML
    public void openTicketForm() throws IOException {
        TicketForm tf = new TicketForm();
        openForm("/TicketForm.fxml", "Zakładanie sprawy", () -> {
            observableList.clear();
            try {
                dataSprawy = new ZapytaniaDao().getPodstawoweInformacjeSprawa();
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, tf);

    }

    @FXML
    public void openTicketEditForm() throws IOException {

        SprawaWiersz sprawa =new SprawaWiersz();

        if (tableView.getSelectionModel().getSelectedItem() != null) {

            sprawa = (SprawaWiersz) tableView.getSelectionModel().getSelectedItem();
        }

       // ClientEditForm cef = new ClientEditForm(client.getId(),client.getName(),client.getSurname(),client.getPesel(),client.getDate());
        String id = sprawa.getId();

        TicketEditForm tef = new TicketEditForm(id);


        openForm("/TicketEditForm.fxml", "Edycja sprawy", () -> {
            observableList.clear();
            try {

                dataSprawy = new ZapytaniaDao().getPodstawoweInformacjeSprawa();
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, tef);

    }

    @FXML
    public void openClientEditForm() throws IOException {

        KlientWiersz client= new KlientWiersz();

        if (tableKlienciView.getSelectionModel().getSelectedItem() != null) {

            client = (KlientWiersz) tableKlienciView.getSelectionModel().getSelectedItem();
        }

        ClientEditForm cef = new ClientEditForm(client.getId(),client.getName(),client.getSurname(),client.getPesel(),client.getDate());
        openForm("/ClientEditForm.fxml", "Edycja klienta", () -> {
            System.out.println("Zapisano do bazy danych");
            kobservableList.clear();
            observableList.clear();
            try {
                dataKlienci = new ZapytaniaDao().getPodstawoweInformacjeKlient();
                dataSprawy = new ZapytaniaDao().getPodstawoweInformacjeSprawa();
                loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }, cef);
    }

    @FXML
    public void findClient() throws SQLException {
        if(findClientField.getText().equals("") || findClientField.getText().equals("Wyszukaj")){
            kobservableList.clear();
            observableList.clear();

            dataKlienci = new ZapytaniaDao().getPodstawoweInformacjeKlient();
            dataSprawy = new ZapytaniaDao().getPodstawoweInformacjeSprawa();
            loadData();
        }else {
            List<Klient> klist = new ZapytaniaDao().findKlient(findClientField.getText());
            List<String[]> data = new ArrayList<String[]>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            klist.forEach(e -> {
                String strDate = dateFormat.format(e.getDanepersonalne().getDataurodzenia());
                String[] row = {e.getId() + "", e.getDanepersonalne().getImie(), e.getDanepersonalne().getNazwisko(), e.getDanepersonalne().getPesel(), strDate};
                data.add(row);


            });
            kobservableList.clear();
            this.dataKlienci = data;
            loadData();
        }
    }
    @FXML
    public void findSprawa() throws SQLException {
        if(findSprawaField.getText().equals("") || findSprawaField.getText().equals("Wyszukaj")){
            kobservableList.clear();
            observableList.clear();

            dataKlienci = new ZapytaniaDao().getPodstawoweInformacjeKlient();
            dataSprawy = new ZapytaniaDao().getPodstawoweInformacjeSprawa();
            loadData();
        }else {
            List<Sprawa> slist = new ZapytaniaDao().findSprawa(findSprawaField.getText());
            List<String[]> data = new ArrayList<String[]>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            slist.forEach(e -> {
                String strDate = dateFormat.format(e.getDatazalozenia());
                String[] row = {e.getId() + "", e.getKlient().getDanepersonalne().getImie(), e.getKlient().getDanepersonalne().getNazwisko(),strDate,e.getStatus(),e.getTyp().getNazwa()};
                data.add(row);


            });

            observableList.clear();
            this.dataSprawy = data;
            loadData();
        }
    }

    private void openForm(String pathToViewFile, String title, Runnable runnable, Callbackable controller) throws IOException {
        Stage stage = new Stage();
        controller.setCallback(runnable);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToViewFile));
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.show();
    }

}
