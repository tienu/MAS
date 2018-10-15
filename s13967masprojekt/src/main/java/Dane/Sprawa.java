package Dane;

import Aktorzy.Klient;
import Aktorzy.Pracownik;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@DatabaseTable
public class Sprawa {



    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false,dataType = DataType.DATE_STRING,format = "yyyy-mm-dd")
    private Date datazalozenia;

    @DatabaseField
    private Statussprawy status;

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Typ typ;

    /*
    @ForeignCollectionField()
    private ForeignCollection<Klient> klienci;
    */

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Klient klient;

    @ForeignCollectionField
    private ForeignCollection<PracownikSprawa> pracownicy ;

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Pracownik pracownikkierujacysprawa;

    public Sprawa() {
    }

    public Sprawa(String datazalozenia, Statussprawy status, Typ typ, Klient klient) throws ParseException {

        setDatazalozenia(datazalozenia);
        this.status = status;
        this.typ = typ;
        this.klient = klient;
    }

    public int getId() {
        return id;
    }

    public Date getDatazalozenia() {
        return datazalozenia;
    }

    public void setDatazalozenia(String datazalozenia) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        this.datazalozenia = sdf.parse(datazalozenia);
    }


    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Statussprawy status) {
        this.status = status;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }
/*
    public ForeignCollection<Klient> getKlienci() {
        return klienci;
    }

    public void addKlienci(Klient klient) {
        this.klienci.add(klient);
    }
*/

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public ForeignCollection<PracownikSprawa> getPracownicy() {
        return pracownicy;
    }

    public void addPracownicy(PracownikSprawa pracownik) {
        this.pracownicy.add(pracownik);
    }

    public Pracownik getPracownikkierujacysprawa() {
        return pracownikkierujacysprawa;
    }

    public void setPracownikkierujacysprawa(Pracownik pracownik) throws Exception {

        if(pracownik.isKierownik()==true ) {

            for(PracownikSprawa pr:this.pracownicy){

                if(pr.getPracownik().getId()==pracownik.getId()){
                    this.pracownikkierujacysprawa = pracownik;
                }

            }

        }else{
            throw new Exception("pracownik nie jest kierownikiem lub nie jest w sprawie");
        }

    }

    @Override
    public String toString() {
        return "Sprawa{" +
                "id=" + id +
                ", datazalozenia=" + datazalozenia +
                ", status=" + status +
                ", typ=" + typ +
                ", klient=" + klient +
                ", pracownicy=" + pracownicy +
                ", pracownikkierujacysprawa=" + pracownikkierujacysprawa +
                '}';
    }
}
