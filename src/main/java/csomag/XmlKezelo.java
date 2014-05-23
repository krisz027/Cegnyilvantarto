package csomag;

import java.io.IOException;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XMLKezelo osztály.
 * @author Jung Krisztián
 */
public class XmlKezelo {
    
    /**
     * Az XML állomány beolvasására használt metódus (cegek.xml).
     * 
     * @param eleres Egy {@code String}. A beolvasni kívánt állomány elérési útja.
     * @return Egy listát ad vissza, a beolvasott állományban lévő elemekkel.
     */
    public LinkedList<Ceg> beolvas(String eleres){

    	boolean hiba = false;
        LinkedList<Ceg> lista = new LinkedList<Ceg>();

        String eleresiut = eleres;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = dbFactory.newDocumentBuilder();    
        } catch (ParserConfigurationException ex) {
            Program.logger.error(ex.getMessage());
            hiba = true;
        }

        Document xmlFile =null; 

        try {
            xmlFile = docBuilder.parse(eleresiut);        
        } catch (SAXException ex) {
            Program.logger.error(ex.getMessage());
            hiba = true;
        } catch (IOException ex) {
            Program.logger.error(ex.getMessage());
            hiba = true;
        }

        NodeList elemek = xmlFile.getElementsByTagName("ceg");
        for(int i=0; i<elemek.getLength(); i++){
            Node elem = elemek.item(i);
            Element aktualis = (Element)elem;
            Ceg akt = new Ceg(aktualis.getElementsByTagName("orszag").item(0).getTextContent(),
                                Integer.parseInt(aktualis.getElementsByTagName("iranyitoszam").item(0).getTextContent()),
                                aktualis.getElementsByTagName("varos").item(0).getTextContent(),
                                aktualis.getElementsByTagName("ut").item(0).getTextContent(),
                                Integer.parseInt(aktualis.getElementsByTagName("hazszam").item(0).getTextContent()),
                                aktualis.getElementsByTagName("cegnev").item(0).getTextContent(),
                                aktualis.getElementsByTagName("koordinata1").item(0).getTextContent(),
                                aktualis.getElementsByTagName("koordinata2").item(0).getTextContent());
           lista.add(akt);  
        }
        if(!hiba){
            if(!lista.isEmpty()){
                Program.logger.info("A cégek beolvasása sikeresen megtörtént!");
            }else;
        }else;
        return lista;
    }

    /**
      * Listában tárolt adatok XML állományba való kiírására használt metódus.
      * @param kiirando A kiírásra szánt lista.
      * @param eleres Annak az állománynak az elérési útja, amibe ki szeretnénk írni az adatokat.
      */
    public void kiir(String eleres, LinkedList<Ceg> kiirando){

            boolean hiba = false;
            String eleresiut = eleres;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            try {
                docBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Program.logger.error(ex.getMessage());
                hiba = true;
            }

            Document doc = docBuilder.newDocument();
            Element cegek = doc.createElement("cegek");
            doc.appendChild(cegek);

            for(int i=0; i<kiirando.size(); i++){
                Element ceg = doc.createElement("ceg");
                cegek.appendChild(ceg);

                Element orsz = doc.createElement("orszag");
                ceg.appendChild(orsz);
                orsz.setTextContent(kiirando.get(i).getOrszag());

                Element irszam = doc.createElement("iranyitoszam");
                ceg.appendChild(irszam);
                irszam.setTextContent(String.valueOf(kiirando.get(i).getIranyitoszam()));

                Element var = doc.createElement("varos");
                ceg.appendChild(var);
                var.setTextContent(kiirando.get(i).getVaros());

                Element utca = doc.createElement("ut");
                ceg.appendChild(utca);
                utca.setTextContent(kiirando.get(i).getUt());

                Element hszam = doc.createElement("hazszam");
                ceg.appendChild(hszam);
                hszam.setTextContent(String.valueOf(kiirando.get(i).getHazSzam()));

                Element cnev = doc.createElement("cegnev");
                ceg.appendChild(cnev);
                cnev.setTextContent(kiirando.get(i).getCegnev());

                Element koord1 = doc.createElement("koordinata1");
                ceg.appendChild(koord1);
                koord1.setTextContent(kiirando.get(i).getKoord1());

                Element koord2 = doc.createElement("koordinata2");
                ceg.appendChild(koord2);
                koord2.setTextContent(kiirando.get(i).getKoord2());
            }

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = null;

            try {
                transformer = transFactory.newTransformer();
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                
            } catch (TransformerConfigurationException ex) {
                Program.logger.error(ex.getMessage());
                hiba = true;
            }

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(eleresiut);

            try {
            transformer.transform(source, result);
            } catch (TransformerException ex) {
                Program.logger.error(ex.getMessage());
                hiba = true;
            }
            if(!hiba){
                Program.logger.info("Az új adatok fájlba való kiírása sikeresen megtörtént!");
            }
    }

    /**
    * Címek közti keresésre használt metódus.<br>
    * Lehetséges ország és irányítószám, csak ország, és csak irányítószám szerinti keresés.<br>
    * Ha az {@code orszag} és az {@code iranyitoszam} is {@code null}, akkor a teljes listát adja vissza.
    * 
    * @param orszag Amely orszag cégeit keressük.
    * @param iranyitoszam Amely irányítószám alatt elhelyezkedő cégeket keressük.
    * @param lista A cégeket tartalmazó teljes lista.
    * @return A kiszűrt adatokat tartalmazó listát adja vissza.
    */
    public LinkedList<Ceg> keres(String orszag, String iranyitoszam, LinkedList<Ceg> lista){

        LinkedList<Ceg> eredmeny = new LinkedList<Ceg>();
        if(orszag.isEmpty() && iranyitoszam.isEmpty()){
            eredmeny = lista;
            Program.logger.info("A paraméter nélküli keresés sikeresen megtörtént!");
        }
        else{/*csak orszag*/
            if(!(orszag.isEmpty()) && iranyitoszam.isEmpty()){
                for(int i=0; i<lista.size(); i++){
                    if(lista.get(i).getOrszag().compareTo(orszag)==0){
                        eredmeny.add(lista.get(i));
                    }
                }
                Program.logger.info("Az ország szerinti keresés siekresen megtörtént!");
            }else{/*csak iranyitoszam*/
                if(orszag.isEmpty() && !(iranyitoszam.isEmpty()) ){
                    for(int i=0; i<lista.size(); i++){
                        if(lista.get(i).getIranyitoszam() == Integer.parseInt(iranyitoszam)){
                            eredmeny.add(lista.get(i));
                        }
                    }
                    Program.logger.info("Az irányítószám szerinti keresés siekresen megtörtént!");
                }else{/*orszag es iranyitoszam*/
                    if(!(orszag.isEmpty()) && !(iranyitoszam.isEmpty())){
                        for(int i=0; i<lista.size(); i++){
                            if( (lista.get(i).getOrszag().compareTo(orszag)==0) && (lista.get(i).getIranyitoszam() == Integer.parseInt(iranyitoszam)) ){
                                eredmeny.add(lista.get(i));
                            }else ;
                        }
                    }
                    Program.logger.info("Az ország és irányítószám szerinti keresés siekresen megtörtént!");
                }
            }
        }
        return eredmeny;
    }

    /**
     * Cégek törlésére használt metódus.<p>
     * Használható 0, 1, de akár tetszőlegesen sok cég törlésére. Ha a paraméterül megkapott {@code indexek} tömb hossza 0, akkor törlés nélkül, a teljes listát visszaadja. 
     * Más esetben törli az ugyancsak paraméterül kapott {@code lista}-ból a megfelelő indexű elemeket.
     * @param indexek A {@code lista} azon elemeinek az indexeit tartalmazza, amelyeket törölni szeretnénk.
     * @param lista A cégeket tartalmazó teljes lista.
     * @return A törlés után előállt listát adja vissza.
     */
    public LinkedList<Ceg> torles(int[] indexek, LinkedList<Ceg> lista){

        if(indexek.length == 0){
            Program.logger.info("Nem történt törlés.");
            return lista;
        }else{
            if(indexek.length == 1){
                lista.remove(indexek[0]);
            }else{
                for(int i=indexek.length-1; i>=0; i--){
                    lista.remove(indexek[i]);
                }
            }
            Program.logger.info("Sikeres törlés. Törölt cégek száma: "+indexek.length);
            return lista;
        }
    }

    /**
     * Cégek, az alapértelmezett böngészőben megnyitott GoogleMaps-en való megjelenítésére használt metódus.<br>
     * A listából kiválasztott cég koordinátáit kapja meg és azok alapján jeleníti meg. 
     * A helyes használatához internetkapcsolat szükséges.
     * 
     * @param koordinata1 A cég első koordinátája.
     * @param koordinata2 A cég második koordinátája.
     */
    public void googleMaps(String koordinata1, String koordinata2) {

        String[] reszkoord, seged;
        String mp1, mp2;
        String  perc1, perc2;
        String fok1,fok2;

        /*koord1 szetszedese*/
        reszkoord = koordinata1.split("\\.");

        fok1 = reszkoord[0];
        perc1 = reszkoord[1];
        seged = reszkoord[2].split(",");
        mp1 = seged[0]+"."+seged[1];

        /*koord2 szetszedese*/
        reszkoord = koordinata2.split("\\.");

        fok2 = reszkoord[0];
        perc2 = reszkoord[1];
        seged = reszkoord[2].split(",");
        mp2 = seged[0]+"."+seged[1];

        String URL = "https://www.google.com/maps/place/"+fok1+"%C2%B0"+perc1+"%27"+mp1+"%22N+"+fok2+"%C2%B0"+perc2+"%27"+mp2+"%22E/@20.5045,19.0643611,1000m/data=!3m2!1e3!4b1!4m2!3m1!1s0x0:0x0";    

        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
            Program.logger.info("A Google Maps-en való megjelenítés sikeresen megtörtént!");
        } catch (IOException ex) {
           Program.logger.error(ex.getMessage());
        }
    }

    /**
     * Cégek egy adott koordináta körüli keresésére használt metódus.<br>
     * Megkapja egy központ X és Y koordinátáját, egy kilóméterben megadott sugarat, és a cégeket tartalmazó teljes listát. 
     * Kiszámítja a központ és a cégek közti távolságot, és ha ez kisebb mint a sugár, akkor az adott céget hozzáadja a visszaadandó listához.
     * 
     * @param kozpontX A keresés középpontjának X koordinátája.
     * @param kozpontY A keresés középpontjának Y koordinátája.
     * @param sugar A keresés hatósugara, kilóméterben megadva.
     * @param lista A cégeket tartalmazó teljes lista.
     * @return Visszaadja a légvonalban a keresés hatókörében lévő cégek listáját.
     */
    public LinkedList<Ceg> specialisKereses(String kozpontX, String kozpontY, double sugar, LinkedList<Ceg> lista){

        double kX, kY, aktX, aktY;
        XmlKezelo muveletek = new XmlKezelo();
        LinkedList<Ceg> eredmeny = new LinkedList<Ceg>();
        double tavolsag;
        char unit = 'K';

        kX = muveletek.koordinataKonvertalo(kozpontX);
        kY = muveletek.koordinataKonvertalo(kozpontY);
        for(int i=0; i<lista.size(); i++){
            aktX = muveletek.koordinataKonvertalo(lista.get(i).getKoord1());
            aktY = muveletek.koordinataKonvertalo(lista.get(i).getKoord2());

            tavolsag = muveletek.distance(aktX, aktY, kX, kY, unit);
            lista.get(i).setTavolsag(Math.round(tavolsag*10) / 10.0);

            if(tavolsag <= sugar){
                eredmeny.add(lista.get(i));
            }else ;
        }
        Program.logger.info("A speciális keresés sikeresen megtörtént!");
        return eredmeny;
    }

    /**
     * Koordináta átkonvertálására használt metódus.<br>
     * Megkap egy {@code xx.xx.xx,xx} formátumú koordinátát és átalakítja {@code xx.xxxxxx} formátumúvá.
     * @param koord1 Az átalakítandó koordináta. ({@code xx.xx.xx,xx})
     * @return Visszaadja a double típusúvá konvertált koordinátát.
     */
    public double koordinataKonvertalo(String koord1){

        double eredmeny;
        String[] tomb;
        String tmp;
        int fok;
        double perc, mp, tortresz;

        tomb = koord1.split("\\.");
            for(int j=0; j<tomb[2].length(); j++){/*a , lecserelese .-ra*/
                if(tomb[2].charAt(j) == ','){
                    tomb[2] = tomb[2].replace(',', '.');
                }
            }
            fok = Integer.parseInt(tomb[0]);
            perc = Double.parseDouble(tomb[1]) / (double)60;
            mp = Double.parseDouble(tomb[2]) / (double)3600;
            tortresz = perc + mp;
            tmp = String.valueOf(tortresz);


            tomb = tmp.split("\\.");
            tmp = fok+"."+tomb[1]; /*átkonvertált adat stringként*/
            eredmeny = Double.parseDouble(tmp);
            eredmeny = Math.round(eredmeny*1000000) / 1000000.0;
        return eredmeny;
    }

    /**
     * Kiszámít két földrajzi hely közötti távolságot.<br>
     * A koordinátákat decimális fokokban kell megadni. (Pl: xx.xxxxx)
     * 
     * @param lat1 Az első hely X koordinátája.
     * @param lon1 Az első hely Y koordinátája.
     * @param lat2 A második hely X koordinátája.
     * @param lon2 A második hely Y koordinátája.
     * @param unit A kiszámított távolság mértékegységét jelöli. Ahol 'K' kilómétert, 'N' tengeri mérföldet, 'M' pedig mérföldet jelent.
     * @return Visszaadja a két pont közti távolságot.
     */
    public double distance(double lat1, double lon1, double lat2, double lon2, char unit) {

        /*forrás: http://www.geodatasource.com/developers/java*/

        double theta = lon1 - lon2;
	double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	dist = Math.acos(dist);
	dist = rad2deg(dist);
	dist = dist * 60 * 1.1515;
	if (unit == 'K') {
	  dist = dist * 1.609344;
	} else if (unit == 'N') {
                    dist = dist * 0.8684;
                }
	return (dist);
    }

    /**
     * Fokot radiánba átváltó metódus.
     * 
     * @param deg Az átváltandó decimális fok.
     * @return a {@code deg} radiánba átváltott értéke.
     */
    public double deg2rad(double deg) {
	  return (deg * Math.PI / 180.0);
	}

    /**
     * Radiánt decimális fokba átváltó metódus.
     * 
     * @param rad Az átváltandó radián érték.
     * @return A {@code rad} decimális fokba átváltott értéke.
     */
    public double rad2deg(double rad) {
	  return (rad * 180 / Math.PI);
	}
}