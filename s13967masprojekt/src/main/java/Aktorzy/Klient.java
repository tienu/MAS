package Aktorzy;

import Dane.Sprawa;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Klient{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Danepersonalne danepersonalne;

    @ForeignCollectionField
    private ForeignCollection<Sprawa> sprawy;

    public Klient() {
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

    public ForeignCollection<Sprawa> getSprawy() {
        return sprawy;
    }

    public void addSprawy(Sprawa sprawa) {
        this.sprawy.add(sprawa);
    }

    public String getImie(){
       return this.danepersonalne.getImie();
    }

    public String getNazwisko(){
        return this.danepersonalne.getNazwisko();
    }

    @Override
    public String toString() {
        return danepersonalne.getImie()+" "+danepersonalne.getNazwisko();
    }
}
