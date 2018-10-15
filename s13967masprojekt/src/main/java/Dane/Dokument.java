package Dane;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Dokument {


    @DatabaseField(canBeNull = false)
    private String nazwa;

    @DatabaseField(foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Bazaelektroniczna bazaelektroniczna;

    @DatabaseField(foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Bazafizyczna bazafizyczna;


    public Dokument() {
    }

    public Dokument(String nazwa) {
        setNazwa(nazwa);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Bazaelektroniczna getBazaelektroniczna() {
        return bazaelektroniczna;
    }

    public void setBazaelektroniczna(Bazaelektroniczna bazaelektroniczna) {
        if(bazafizyczna!=null) {
            this.bazafizyczna=null;
        }
        this.bazaelektroniczna = bazaelektroniczna;
    }

    public Bazafizyczna getBazafizyczna() {
        return bazafizyczna;
    }

    public void setBazafizyczna(Bazafizyczna bazafizyczna) {
        if(bazaelektroniczna!=null) {
            bazaelektroniczna=null;
        }
        this.bazafizyczna = bazafizyczna;
    }
}
