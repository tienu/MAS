package Dane;

import Aktorzy.Pracownik;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class PracownikSprawa {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true)
    private Sprawa sprawa;

    @DatabaseField(foreign = true)
    private Pracownik pracownik;

    public PracownikSprawa() {
    }

    public PracownikSprawa(Sprawa sprawa, Pracownik pracownik) {
        this.sprawa = sprawa;
        this.pracownik = pracownik;
    }

    public int getId() {
        return id;
    }

    public Sprawa getSprawa() {
        return sprawa;
    }

    public void setSprawa(Sprawa sprawa) {
        this.sprawa = sprawa;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }
}
