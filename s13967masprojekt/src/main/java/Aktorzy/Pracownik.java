package Aktorzy;

import Dane.Datarealizacji;
import Dane.PracownikSprawa;
import Dane.Sprawa;
import Dane.Zadania;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable
public class Pracownik {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false,dataType = DataType.DATE_STRING,format = "yyyy-mm-dd")
    private Date pracujeod;

    @DatabaseField(foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Danepersonalne danepersonalne;

    @DatabaseField
    private boolean kierownik=false;

    @DatabaseField
    private boolean pracownik=false;



    @ForeignCollectionField
    private ForeignCollection<Zadania> zadania;

    @ForeignCollectionField
    private ForeignCollection<Datarealizacji> datyrealizacji;

    @ForeignCollectionField
    private ForeignCollection<PracownikSprawa> sprawy;

    @ForeignCollectionField
    private ForeignCollection<Sprawa> sprawywktorychkieruje;

    public Pracownik() {
    }

    public Pracownik(String pracujeod, boolean kierownik, boolean pracownik) throws ParseException {
        setPracujeod(pracujeod);
        this.kierownik = kierownik;
        this.pracownik = pracownik;
    }

    public int getId() {
        return id;
    }

    public Danepersonalne getDanepersonalne() {
        return danepersonalne;
    }

    public void setDanepersonalne(Danepersonalne danepersonalne) throws Exception {
        if (this.danepersonalne==null) {
            this.danepersonalne = danepersonalne;
        }else{
            throw new Exception("dane personalne już sązaalokowane");
        }
    }

    public Date getPracujeod() {
        return pracujeod;
    }

    public void setPracujeod(String pracujeod) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/mm/dd");
        this.pracujeod = sdf.parse(pracujeod);
    }

    public boolean isKierownik() {
        return kierownik;
    }

    public void setKierownik(boolean kierownik) {
        this.kierownik = kierownik;
    }

    public boolean isPracownik() {
        return pracownik;
    }

    public void setPracownik(boolean pracownik) {
        this.pracownik = pracownik;
    }

    public ForeignCollection<Zadania> getZadania() {
        return zadania;
    }

    public void addZadanie(Zadania zadania) throws Exception {
        if(this.isKierownik()==false){
            throw new Exception("Pracownik nie jest kierownikiem");
        }
        this.zadania.add(zadania);
    }

    public ForeignCollection<Datarealizacji> getDatyrealizacji() {
        return datyrealizacji;
    }

    public void addDatyrealizacji(Datarealizacji datarealizacji) {
        this.datyrealizacji.add(datarealizacji);
    }

    public ForeignCollection<PracownikSprawa> getSprawy() {
        return sprawy;
    }

    public void addSprawa(PracownikSprawa sprawa) throws Exception {
        if(sprawa==null){
            throw new Exception("podana sprawa jest nullem");
        }
        this.sprawy.add(sprawa);
    }

    public ForeignCollection<Sprawa> getSprawywktorychkieruje() {
        return sprawywktorychkieruje;
    }

    public void addSprawawktorejkieruje(Sprawa sprawa) throws Exception {
        if(sprawa == null) {
            throw new Exception("podana sprawa jest nullem");
        }else if(this.isKierownik()==false){
            throw new Exception("pracownik nie jest kierownikiem");
        }
        boolean przypisany=false;

        for(PracownikSprawa pr:this.sprawy){

            if(pr.getSprawa().getId() == sprawa.getId()){
                this.sprawywktorychkieruje.add(sprawa);
                przypisany=true;
            }
        }

        if(przypisany==false){

            throw new Exception("sprawa musi byc przypisana");
        }
    }
}
