package Zapytania;

import Aktorzy.Danepersonalne;
import Aktorzy.Klient;
import Aktorzy.Pracownik;
import Dane.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ZapytaniaDao {

   private static  String urldata = "jdbc:sqlite:database.db" ;

   private static  ConnectionSource connectionSource;

    static {
        try {
            connectionSource = new JdbcConnectionSource(urldata);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ZapytaniaDao() throws SQLException {
    }

    public static List<Sprawa> getSprawa() throws SQLException {
        Dao<Sprawa, String> dao= DaoManager.createDao(connectionSource, Sprawa.class);

        List<Sprawa> sprawy = dao.queryForAll();


        return sprawy;
    }

    public static Sprawa getSprawaId(String id) throws SQLException {
        Dao<Sprawa, String> dao= DaoManager.createDao(connectionSource, Sprawa.class);
        Dao<Klient, String> daoKlient= DaoManager.createDao(connectionSource, Klient.class);

        Sprawa sprawa = dao.queryForId(id);


        return sprawa;
    }

    public static List<String[]> getPodstawoweInformacjeSprawa() throws SQLException {
        Dao<Sprawa, String> dao= DaoManager.createDao(connectionSource, Sprawa.class);

       GenericRawResults<String[]> rawresult=dao.queryRaw("select sp.id,dp.imie,dp.nazwisko,sp.datazalozenia,sp.status,tp.nazwa from sprawa sp join klient kl on sp.klient_id = kl.id join Danepersonalne dp on dp.id = kl.danepersonalne_id join typ tp on tp.id=sp.typ_id");
       List<String[]> result= rawresult.getResults();

       return result;
    }

    public static  List<Klient> getKlient() throws SQLException {
        Dao<Klient, String> dao= DaoManager.createDao(connectionSource, Klient.class);
        List<Klient> klienci = dao.queryForAll();

        return klienci;
    }

    public static List<String[]> getPodstawoweInformacjeKlient() throws SQLException {
        Dao<Klient, String> dao= DaoManager.createDao(connectionSource, Klient.class);

        GenericRawResults<String[]> rawresult=dao.queryRaw(" select kl.id,dp.imie,dp.nazwisko,dp.pesel,dp.dataurodzenia from klient kl join danepersonalne dp on kl.danepersonalne_id = dp.id");
        List<String[]> result= rawresult.getResults();

        return result;
    }



    public static  List<Pracownik> getPracownicy() throws SQLException {
        Dao<Pracownik, String> dao= DaoManager.createDao(connectionSource, Pracownik.class);
        List<Pracownik> pracownicy = dao.queryForAll();

        return pracownicy;
    }


    public static  List<Typ> getTypy() throws SQLException {
        Dao<Typ, String> dao= DaoManager.createDao(connectionSource, Typ.class);
        List<Typ> typy = dao.queryForAll();

        return typy;
    }

    public static void addTyp(String nazwa) throws SQLException {
        Dao<Typ, String> dao= DaoManager.createDao(connectionSource, Typ.class);

        Typ typ= new Typ(nazwa);


        dao.create(typ);


    }

    public void addSprawa(String datazalozenia, Statussprawy status, Typ typ, Klient klient) throws ParseException, SQLException {
        Dao<Sprawa, String> dao= DaoManager.createDao(connectionSource, Sprawa.class);

       Sprawa sprawa= new Sprawa(datazalozenia,status,typ,klient);


        dao.create(sprawa);

    }

    public void updateSprawa(String id,String datazalozenia, Statussprawy status, Typ typ, Klient klient) throws ParseException, SQLException {
        Dao<Sprawa, String> dao= DaoManager.createDao(connectionSource, Sprawa.class);

        Sprawa sprawa;
        sprawa = dao.queryForId(id);

        sprawa.setDatazalozenia(datazalozenia);
        sprawa.setStatus(status);
        sprawa.setTyp(typ);
        sprawa.setKlient(klient);

        dao.update(sprawa);

    }


    public void removeSprawa(String id) throws Exception {
        Dao<Sprawa, String> daoSprawa= DaoManager.createDao(connectionSource, Sprawa.class);

        Sprawa sprawa;
        sprawa = daoSprawa.queryForId(id);

        daoSprawa.delete(sprawa);

    }

    public void addKlient(String imie,String nazwisko,String pesel,String dataurodzenia) throws Exception {

        Dao<Klient, String> daoKlient= DaoManager.createDao(connectionSource, Klient.class);
        Dao<Danepersonalne, String> daoDanepersonalne= DaoManager.createDao(connectionSource, Danepersonalne.class);


        Danepersonalne danepersonalne =new Danepersonalne(imie,nazwisko,pesel,dataurodzenia);
        Klient klient = new Klient();

        klient.setDanepersonalne(danepersonalne);
        danepersonalne.setMasterklient(klient);


        daoKlient.create(klient);
    }


    public void updateKlient(int id,String imie,String nazwisko,String pesel,String dataurodzenia) throws Exception {

        Dao<Klient, String> daoKlient= DaoManager.createDao(connectionSource, Klient.class);
        Dao<Danepersonalne, String> daoDanepersonalne= DaoManager.createDao(connectionSource, Danepersonalne.class);



        Klient klient ;
        klient = daoKlient.queryForId(id+"");
        Danepersonalne danepersonalne =  klient.getDanepersonalne();


        danepersonalne.setImie(imie);
        danepersonalne.setNazwisko(nazwisko);
        danepersonalne.setPesel(pesel);
        danepersonalne.setDataurodzenia(dataurodzenia);

        daoDanepersonalne.update(danepersonalne);
    }

    public void removeKlient(int id) throws Exception {
        Dao<Klient, String> daoKlient= DaoManager.createDao(connectionSource, Klient.class);
        Dao<Danepersonalne, String> daoDanepersonalne= DaoManager.createDao(connectionSource, Danepersonalne.class);
        Dao<Sprawa, String> daoSprawa= DaoManager.createDao(connectionSource, Sprawa.class);

        Klient klient ;
        klient = daoKlient.queryForId(id+"");

        for(Sprawa sp:getSprawa()){

            if(sp.getKlient().getId() == klient.getId()){
                daoSprawa.delete(sp);
            }

        }

        daoDanepersonalne.delete(klient.getDanepersonalne());
        daoKlient.delete(klient);
    }

    public void addPracownik(String imie,String nazwisko,String pesel,String dataurodzenia,String pracujeod,boolean kierownik,boolean pracownikzwykly) throws Exception{
        Dao<Pracownik, String> dao= DaoManager.createDao(connectionSource, Pracownik.class);
        Dao<Danepersonalne, String> daoDanepersonalne= DaoManager.createDao(connectionSource, Danepersonalne.class);


        Danepersonalne danepersonalne =new Danepersonalne(imie,nazwisko,pesel,dataurodzenia);

        Pracownik pracownik = new Pracownik(pracujeod,kierownik,pracownikzwykly);

        pracownik.setDanepersonalne(danepersonalne);
        danepersonalne.setMasterpracownik(pracownik);


        dao.create(pracownik);

    }

    public static List<Klient> findKlient(String klucz) throws SQLException {
        Dao<Klient, String> daoKlient= DaoManager.createDao(connectionSource, Klient.class);
        Dao<Danepersonalne, String> daoDanepersonalne= DaoManager.createDao(connectionSource, Danepersonalne.class);

        List<Klient> klist;
        List<Danepersonalne> dplist;

        QueryBuilder<Danepersonalne, String> danepersonalneQB = daoDanepersonalne.queryBuilder();
        danepersonalneQB.where().eq("imie",klucz).or().eq("nazwisko",klucz).or().eq("pesel",klucz);
        QueryBuilder<Klient,String>  klientQB = daoKlient.queryBuilder();

        klist = klientQB.join(danepersonalneQB).query();



        return klist;
    }
    public static List<Sprawa> findSprawa(String klucz) throws SQLException {
        Dao<Klient, String> daoKlient= DaoManager.createDao(connectionSource, Klient.class);
        Dao<Danepersonalne, String> daoDanepersonalne= DaoManager.createDao(connectionSource, Danepersonalne.class);
        Dao<Sprawa, String> daoSprawa= DaoManager.createDao(connectionSource, Sprawa.class);
        Dao<Typ, String> daotyp= DaoManager.createDao(connectionSource, Typ.class);

        List<Sprawa> slist ;
        List<Sprawa> slist2;

        Date data = null;

        QueryBuilder<Typ, String> typQB = daotyp.queryBuilder();
        typQB.where().eq("nazwa",klucz);

        QueryBuilder<Danepersonalne, String> danepersonalneQB = daoDanepersonalne.queryBuilder();
        try {
            data =new SimpleDateFormat("yyyy-mm-dd").parse(klucz);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        danepersonalneQB.where().eq("imie",klucz).or().eq("nazwisko",klucz).or().eq("pesel",klucz);

        QueryBuilder<Klient,String>  klientQB = daoKlient.queryBuilder();
        klientQB.join(danepersonalneQB);

        QueryBuilder<Sprawa,String>  sprawaQB = daoSprawa.queryBuilder();
        slist = sprawaQB.join(klientQB).query();

        QueryBuilder<Sprawa,String>  sprawa2QB = daoSprawa.queryBuilder();
        slist2=sprawa2QB.join(typQB).query();

        slist.addAll(slist2);


        if(isInteger(klucz)) {
            QueryBuilder<Sprawa, String> sprawa3QB = daoSprawa.queryBuilder();
            sprawa3QB.where().eq("id", klucz);
            slist2 = sprawa3QB.query();

            slist.addAll(slist2);
        }else{
            try {
                QueryBuilder<Sprawa, String> sprawa3QB = daoSprawa.queryBuilder();
                sprawa3QB.where().eq("status", Statussprawy.valueOf(klucz));
                slist2 = sprawa3QB.query();
                if (slist2.isEmpty() == false) {
                    slist.addAll(slist2);
                }
            }catch (Exception e){

            }
        }
        return slist;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public void createBaza() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Bazaelektroniczna.class);
        TableUtils.createTableIfNotExists(connectionSource, Bazafizyczna.class);
        TableUtils.createTableIfNotExists(connectionSource, Dokument.class);
        TableUtils.createTableIfNotExists(connectionSource, Danepersonalne.class);
        TableUtils.createTableIfNotExists(connectionSource, Klient.class);
        TableUtils.createTableIfNotExists(connectionSource, Pracownik.class);
        TableUtils.createTableIfNotExists(connectionSource, Sprawa.class);
        TableUtils.createTableIfNotExists(connectionSource, Datarealizacji.class);
        TableUtils.createTableIfNotExists(connectionSource,Typ.class);
        TableUtils.createTableIfNotExists(connectionSource,Zadania.class);
    }


}
