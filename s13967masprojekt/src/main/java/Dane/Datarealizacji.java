package Dane;

import Aktorzy.Pracownik;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable
public class Datarealizacji {



    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private Date data;

    @DatabaseField(foreign = true)
    private Zadania zadania ;

    @DatabaseField(foreign = true,canBeNull = false)
    private Pracownik pracownik;

    public Datarealizacji() {
    }

    public int getId() {
        return id;
    }

    public Zadania getZadania() {
        return zadania;
    }

    public void setZadania(Zadania zadania) {
        this.zadania = zadania;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Date getData() {
        return data;
    }

    public void setData(String data) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/mm/dd");
        this.data = sdf.parse(data);
    }

}
