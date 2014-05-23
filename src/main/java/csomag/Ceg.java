package csomag;
/**
 * Ceg osztály.
 * @author Jung Krisztián
 */
public class Ceg implements Comparable {

    /**
     * Ország.
     */
    private String orszag;
    /**
     * Irányítószám.
     */
    private int iranyitoszam;
    /**
     * Város.
     */
    private String varos;
    /**
     * Út.
     */
    private String ut;
    /**
     * Házszám.
     */
    private int hazszam;
    /**
     * Cégnév.
     */
    private String cegnev;
    /**
     * Első koordináta.
     */
    private String koord1;
    /**
     * Második koordináta.
     */
    private String koord2;
    /**
     * Távolság.
     */
    private double tavolsag;

        /**
         * A példányosításhoz használt konstrouktor.
         * @param orsz Az ország rövidítése, ahol a cég található.(Pl: A, NL, D)
         * @param irszam A város irányítószáma.
         * @param var A város neve, ahol a cég található.
         * @param utca Az út neve.
         * @param hszam A házszám.
         * @param cnev A cég neve.
         * @param koordi1 A cég pozíciójának első koordinátája.
         * @param koordi2 A cég pozíciójának második koordinátája.
         */
    public Ceg(String orsz, int irszam, String var, String utca, int hszam, String cnev, String koordi1, String koordi2) {

        this.orszag = orsz;
        this.iranyitoszam = irszam;
        this.varos = var;
        this.ut = utca;
        this.hazszam = hszam;
        this.cegnev = cnev;
        this.koord1 = koordi1;
        this.koord2 = koordi2;
    }

        /**
         * Tetszőleges számú szóközt tartalmazó String visszaadására használt
         * metódus.
         * Megkap egy String-et, és egy egész értéket. Az egyes mezőket egy fix
         * hosszig feltölti szóközzel.
         * @param szo A kipótolni kívánt String.
         * @param fixhossz A szóközökkel bővített String kívánt hossza.
         * @return Visszaad a {@code fixhossz} és a {@code szo} hosszának
         * különbségével megeggyező darabszámú szóközt.
         */
    public String szokozrako(String szo, int fixhossz) {
        String str = "";
        for(int i=0; i < fixhossz - szo.length(); i++){
            str = str + " ";
        }
        return str;
    }
	
        /**
         * A speciális keresés során az adott cég kiíratására szolgál, a távolsággal együtt.
         * @return Az adott cég szting reprezentációja, a mezőivel és az aktuális távolsággal.
         */
        public String toStringTavolsaggal(){
            return this.orszag + szokozrako(this.orszag, 10) + this.iranyitoszam + szokozrako(Integer.toString(this.iranyitoszam), 20) + this.varos+ szokozrako(this.varos, 30) + this.ut+ szokozrako(this.ut, 30) + this.hazszam+ szokozrako(Integer.toString(this.hazszam), 20) + this.cegnev + szokozrako(this.cegnev, 30) + this.koord1+ szokozrako(this.koord1, 20) + this.koord2+ szokozrako(this.koord2, 20) + "Távolság: " +this.tavolsag+ " km";
        }

        /**
         * A cégekből álló kollekciók rendezésére használt metódus.
         * A rendezés prioritás szerint csökkenő sorrendben: ország-->irányítószám-->út
         * @param o A másik objektum, amivel összehasonlítjuk a hívó objektumot.
         * @return Az ország összehasonlítás eredményét, vagy az irányítószámok különbségét, vagy pedig az út összehasonlításának az eredményét adja vissza.
         */
	public int compareTo(Object o) {
            if(this.orszag.compareTo(((Ceg)o).orszag) == 0){
                if(this.iranyitoszam -((Ceg)o).iranyitoszam == 0){
                    return this.ut.compareTo(((Ceg)o).ut);
		}
		else
                    return this.iranyitoszam -((Ceg)o).iranyitoszam;
            }
            else
                return this.orszag.compareTo(((Ceg)o).orszag);
        }
        
        /**
         * A cégek kiíratására használt metódus.<br>
         * Az egyes mezőket szóközzel feltöltve fix hosszúságúra.
         * @return Visszaadja a cég String reprezentációját.
         */
	@Override
	public String toString() {
        return this.orszag+ szokozrako(this.orszag, 10) +
               this.iranyitoszam+ szokozrako(Integer.toString(this.iranyitoszam), 20) +
               this.varos+ szokozrako(this.varos, 30) +
               this.ut+ szokozrako(this.ut, 30) +
               this.hazszam+ szokozrako(Integer.toString(this.hazszam), 20) +
               this.cegnev + szokozrako(this.cegnev, 30) +
               this.koord1+ szokozrako(this.koord1, 20) +
               this.koord2+ szokozrako(this.koord2, 20);
	}
        
        
        
        
        /**
         * A cég ország adattagjának lekérdezésére használt metódus.
         * @return Az adott példány {@code orszag} adattagját.
         */
        public String getOrszag(){
            return this.orszag;
        }
        
        /**
         * A cég irányítószámának lekérdezésére használt metódus.
         * @return Az adott példány {@code iranyitoszam} adattagját adja vissza.
         */
        public int getIranyitoszam(){
            return this.iranyitoszam;
        }
        
        /**
         * A cég városának lekérdezésére használt metódus.
         * @return Az adott példány {@code varos} adattagját adja vissza.
         */
        public String getVaros(){
            return this.varos;
        }
        
        /**
         * A cég útjának lekérdezésére használt metódus.
         * @return Az adott példány {@code ut} adattagját adja vissza.
         */
        public String getUt(){
            return this.ut;
        }
        
        /**
         * A cég házszámának lekérdezésére használt metódus.
         * @return Az adott példány {@code hazszam} adattagját adja vissza.
         */
        public int getHazSzam(){
            return this.hazszam;
        }
        
        /**
         * A cég nevének lekérdezésére használt metódus.
         * @return Az adott példány {@code cegnev} adattagját adja vissza.
         */
        public String getCegnev(){
            return this.cegnev;
        }
        
        /**
         * A cég földrajzi helyének első koordinátájának lekérdezésére használt metódus.
         * @return Az adott példány {@code koord1} adattagját adja vissza.
         */
        public String getKoord1(){
            return this.koord1;
        }
        
        /**
         * A cég földrajzi helyének második koordinátájának lekérdezésére használt metódus.
         * @return Az adott példány {@code koord2} adattagját adja vissza.
         */
        public String getKoord2(){
            return this.koord2;
        }
        
        /**
         * A cég és a speciális keresés középpontja közötti távolság lekérdezésére használt metódus.
         * @return Az adott példány {@code tavolsag} adattagját adja vissza.
         */
        public double getTavolsag(){
            return this.tavolsag;
        }
        
        /**
         * A cég ország adattagjának beállítására használt metódus.
         * @param param A beállítani kívánt ország nagybetűs rövidítése.
         */
        public void setOrszag(String param){
            this.orszag = param;
        }
        
        /**
         * A cég irányítószámának beállítására használt metódus.
         * @param param A beállítani kívánt irányítószám.
         */
        public void setIranyitoszam(int param){
            this.iranyitoszam = param;
        }
        
        /**
         * A cég városának beállítására használt metódus.
         * @param param A beállítani kívánt város neve.
         */
        public void setVaros(String param){
            this.varos = param;
        }
        
        /**
         * A cég útjának beállítására használt metódus.
         * @param param A beállítani kívánt út neve.
         */
        public void setUt(String param){
            this.ut = param;
        }
        
        /**
         * A cég házszámának beállítására használt metódus.
         * @param param A beállítani kívánt házszám.
         */
        public void setHazSzam(int param){
            this.hazszam = param;
        }
        
        /**
         * A cég nevének beállítására használt metódus.
         * @param param A beállítani kívánt név.
         */
        public void setCegnev(String param){
            this.cegnev = param;
        }
        
        /**
         * A cég földrajzi helyének az első koordinátájának beállítására használt metódus.
         * @param param A beállítani kívánt koordináta.
         */
        public void setKoord1(String param){
            this.koord1 = param;
        }
        
        /**
         * A cég földrajzi helyének a második koordinátájának beállítására használt metódus.
         * @param param A beállítani kívánt koordináta.
         */
        public void setKoord2(String param){
            this.koord2 = param;
        }
        
        /**
         * A cég és a speciális keresés középpontja közötti távolság beállítására használt metódus.
         * @param param A beállítani kívánt távolság.
         */
        public void setTavolsag(double param){
            this.tavolsag = param;
        }

}
