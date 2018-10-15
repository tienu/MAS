package Dane;

import Aktorzy.Pracownik;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Zadania {

    @DatabaseField(generatedId = true)
    int id;
/*
    @DatabaseField
    private ArrayList<String> zadania=new ArrayList<String>();
*/

    @DatabaseField
    private String zadanie;

    @ForeignCollectionField
    private ForeignCollection<Datarealizacji> datarealizacji;

    @DatabaseField(foreign = true)
    private Pracownik kierownikwyznaczającyzadania;



    public Zadania() {
    }

    public int getId() {
        return id;
    }
/*
    public ArrayList<String> getZadania() {
        return zadania;
    }

    public void addZadanie(String zadanie) throws Exception {
        if(zadanie==null){
            throw new Exception("zadanie nie może byc nullem");
        }
        this.zadania.add(zadanie);
    }
*/

    public String getZadanie() {
        return zadanie;
    }

    public void setZadanie(String zadanie) {
        this.zadanie = zadanie;
    }

    public ForeignCollection<Datarealizacji> getDatarealizacji() {
        return datarealizacji;
    }

    public void addDatarealizacji(Datarealizacji datarealizacji) throws Exception {
        if(datarealizacji==null){
            throw new Exception("zadanie nie może byc nullem");
        }
        this.datarealizacji.add(datarealizacji);
    }

    public Pracownik getKierownikwyznaczającyzadania() {
        return kierownikwyznaczającyzadania;
    }

    public void setKierownikwyznaczającyzadania(Pracownik kierownikwyznaczającyzadania) {
        this.kierownikwyznaczającyzadania = kierownikwyznaczającyzadania;
    }

}
