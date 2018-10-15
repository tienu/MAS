package Dane;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Typ {


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false )
    private String nazwa;

    @ForeignCollectionField
    private ForeignCollection<Sprawa> sprawy;

    public Typ() {
    }

    public Typ(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public ForeignCollection<Sprawa> getSprawy() {
        return sprawy;
    }

    public void addSprawa(Sprawa sprawa) throws Exception {
        if(sprawa==null){
            throw new Exception("podana sprawa jest nulem");
        }
        this.sprawy.add(sprawa);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return nazwa+"" ;
    }
}
