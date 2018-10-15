package Aktorzy;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable(tableName = "Danepersonalne")
public class Danepersonalne {

    @DatabaseField( foreign = true)
    private Klient masterklient;

    @DatabaseField( foreign = true)
    private Pracownik masterpracownik;

    @DatabaseField(generatedId = true,canBeNull = false)
    private int id;

    @DatabaseField(canBeNull = false)
    private String imie,nazwisko;

    @DatabaseField(unique = true ,width = 11 )
    private String pesel;

    @DatabaseField(canBeNull = false,dataType = DataType.DATE_STRING,format = "yyyy-mm-dd")
    private Date dataurodzenia;



    private int wiek;

    public Danepersonalne(){

    }



    public Danepersonalne(String imie, String nazwisko, String pesel, String dataurodzenia) throws Exception {
       // this.master = master;

        setImie(imie);
        setNazwisko(nazwisko);
        setPesel(pesel);
        setDataurodzenia(dataurodzenia);

    }

    public Klient getMasterklient() {

            return masterklient;
    }

    public void setMasterklient(Klient masterklient) throws Exception {

        if(this.masterklient==null&&masterpracownik==null) {
            this.masterklient = masterklient;
        }else{
            throw new Exception("master jest przypisany");
        }
    }

    public Pracownik getMasterpracownik() {
        return masterpracownik;
    }

    public void setMasterpracownik(Pracownik masterpracownik) throws Exception {
        if(this.masterklient==null&&masterpracownik==null) {
            this.masterpracownik = masterpracownik;
        }else{
            throw new Exception("master jest przypisany");
        }
    }

    public int getId() {
        return id;
    }
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) throws Exception {

        if(pesel.length()!=11 || pesel.matches("[0-9]+")==false) {
            throw new Exception("Bledny pesel");
        }else {
            this.pesel = pesel;
        }

    }


    public Date getDataurodzenia() {
        return dataurodzenia;
    }

    public void setDataurodzenia(String dataurodzenia) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        this.dataurodzenia = sdf.parse(dataurodzenia);
    }

    public int getWiek() {
        return wiek;
    }

    @Override
    public String toString() {
        if (masterklient==null && masterpracownik!=null){
            return "Danepersonalne{" +
                    ", masterpracownik=" + masterpracownik.getId() +
                    ", id=" + id +
                    '}';
        }else
        if(masterpracownik==null && masterklient!=null ) {
            return "Danepersonalne{" +
                    "masterklient=" + masterklient.getId() +
                    ", id=" + id +
                    '}';
        }

        return "Danepersonalne{" +
                ", id=" + id +
                '}';

    }
}
