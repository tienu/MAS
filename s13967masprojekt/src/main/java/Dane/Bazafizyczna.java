package Dane;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Bazafizyczna {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String sciezka;
/*
    @ForeignCollectionField
    private ArrayList<Dokument> dokumenty=new ArrayList<Dokument>();
*/
    @ForeignCollectionField
    private ForeignCollection<Dokument> dokumenty;

    public Bazafizyczna() {
    }

    public int getId() {
        return id;
    }


    public String getSciezka() {
        return sciezka;
    }

    public void setSciezka(String sciezka) {
        this.sciezka = sciezka;
    }

    public ForeignCollection<Dokument> getDokumenty() {
        return dokumenty;
    }

    public void addDokumenty(Dokument dokument) throws Exception {
        if(dokument==null){
            throw new Exception("podany dokument jest nullem");
        }
        this.dokumenty.add(dokument);
    }
}
