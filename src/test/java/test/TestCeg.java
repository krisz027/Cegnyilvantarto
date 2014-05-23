package test;

import csomag.Ceg;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCeg {

	@Test
        /**
         * Teszteli, hogy helyes paraméterekkel létrejön e a Cég osztály példánya, 
         * valamint teszteli, hogy a létrejött példány adattagjainak jó érték lett-e beállítva.
         * 
         */
	public void cegKonstruktorTeszt(){
            Ceg tesztelendo = new Ceg("A",1,"b","c",2,"d","12.20.30,1", "20.34.12,5");
		assertNotNull(tesztelendo);
                assertEquals(tesztelendo.getOrszag(), "A");
                assertEquals(tesztelendo.getIranyitoszam(), 1);
                assertEquals(tesztelendo.getVaros(), "b");
                assertEquals(tesztelendo.getUt(), "c");
                assertEquals(tesztelendo.getHazSzam(), 2);
                assertEquals(tesztelendo.getCegnev(), "d");
                assertEquals(tesztelendo.getKoord1(), "12.20.30,1");
                assertEquals(tesztelendo.getKoord2(), "20.34.12,5");
	}
        
        @Test
        /**
         * Szóközrakó metódus tesztelése, hogy megfelelő számú szóközt ad-e vissza.
         */
        public void szokozrakoTeszt(){
            Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
            String szoveg = "szo";
            
            assertNotNull(tesztelendo.szokozrako(szoveg, 10));
            assertEquals(tesztelendo.szokozrako(szoveg, 10), "       ");
            for(int i=0; i< 20; i++){
                String tmp = tesztelendo.szokozrako(szoveg, i);
                if(szoveg.length() <= i)
                    assert tmp.length() == i-3;
            }
            assert tesztelendo.szokozrako(szoveg, 10).length() == 7;  
        }
        
        @Test
        /**
         * A távolsággal együtt való kiíratás tesztelése.
         */
        public void toStringTavolsaggalTeszt(){
            Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
            
            assertNotNull(tesztelendo.toStringTavolsaggal());
            assertEquals(tesztelendo.getOrszag()+ tesztelendo.szokozrako(tesztelendo.getOrszag(), 10) +
                         tesztelendo.getIranyitoszam()+ tesztelendo.szokozrako(Integer.toString(tesztelendo.getIranyitoszam()), 20) +
                         tesztelendo.getVaros()+ tesztelendo.szokozrako(tesztelendo.getVaros(), 30) +
                         tesztelendo.getUt()+ tesztelendo.szokozrako(tesztelendo.getUt(), 30) +
                         tesztelendo.getHazSzam()+ tesztelendo.szokozrako(Integer.toString(tesztelendo.getHazSzam()), 20) +
                         tesztelendo.getCegnev() + tesztelendo.szokozrako(tesztelendo.getCegnev(), 30) +
                         tesztelendo.getKoord1()+ tesztelendo.szokozrako(tesztelendo.getKoord1(), 20) +
                         tesztelendo.getKoord2()+ tesztelendo.szokozrako(tesztelendo.getKoord2(), 20) +
                         "Távolság: " +tesztelendo.getTavolsag()+ " km" , tesztelendo.toStringTavolsaggal());
        }
        
        @Test
        /**
         * A rendezéshez használt compareTo metódus tesztelés. Van e visszatérési érték, és helyes e.
         */
        public void compareToTeszt(){
            Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
            Ceg tesztelendo2 = new Ceg("H", 4465, "Rakamaz", "Azent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
            Ceg tesztelendo3 = new Ceg("H", 3465, "Rakamaz", "Azent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
            Ceg tesztelendo4 = new Ceg("D", 5465, "Rakamaz", "Azent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
            
            int a = tesztelendo2.compareTo(tesztelendo);
            assertNotNull(a);
            assert a<0;
            
            a = tesztelendo2.compareTo(tesztelendo3);
            assertNotNull(a);
            assert a>0;
            
            a = tesztelendo2.compareTo(tesztelendo4);
            assertNotNull(a);
            assert a>0;
            
            a = tesztelendo.compareTo(tesztelendo);
            assert a==0;
        }
          
         @Test
         /**
          * Sima toString metódus tesztelése.
          */
         public void toStringTeszt(){
             Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
             
             assertNotNull(tesztelendo.toString());
             assertEquals(tesztelendo.getOrszag()+ tesztelendo.szokozrako(tesztelendo.getOrszag(), 10) +
                         tesztelendo.getIranyitoszam()+ tesztelendo.szokozrako(Integer.toString(tesztelendo.getIranyitoszam()), 20) +
                         tesztelendo.getVaros()+ tesztelendo.szokozrako(tesztelendo.getVaros(), 30) +
                         tesztelendo.getUt()+ tesztelendo.szokozrako(tesztelendo.getUt(), 30) +
                         tesztelendo.getHazSzam()+ tesztelendo.szokozrako(Integer.toString(tesztelendo.getHazSzam()), 20) +
                         tesztelendo.getCegnev() + tesztelendo.szokozrako(tesztelendo.getCegnev(), 30) +
                         tesztelendo.getKoord1()+ tesztelendo.szokozrako(tesztelendo.getKoord1(), 20) +
                         tesztelendo.getKoord2()+ tesztelendo.szokozrako(tesztelendo.getKoord2(), 20) , tesztelendo.toString());
         }
         
         @Test
         /**
          * Setter metódusok tesztelése.
          */
         public void setterTeszt(){
             Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
             
             tesztelendo.setOrszag("D");
             assertEquals("D", tesztelendo.getOrszag());
             assertNotNull(tesztelendo.getOrszag());
             for(int i=1; i<21; i++){
                 tesztelendo.setOrszag(Integer.toString(i));
                 assertEquals(Integer.toString(i), tesztelendo.getOrszag());
                 assertNotNull(tesztelendo.getOrszag());
             }
             
             for(int i=1; i<21; i++){
                 tesztelendo.setIranyitoszam(i);
                 assertEquals(i, tesztelendo.getIranyitoszam());
                 assertNotNull(tesztelendo.getIranyitoszam());
             }
             
             
             tesztelendo.setVaros("Tokaj");
             assertEquals("Tokaj", tesztelendo.getVaros());
             assertNotNull(tesztelendo.getVaros());
             for(int i=1; i<21; i++){
                 tesztelendo.setVaros(Integer.toString(i));
                 assertEquals(Integer.toString(i), tesztelendo.getVaros());
                 assertNotNull(tesztelendo.getVaros());
             }
             
             
             tesztelendo.setUt("Semmi");
             assertEquals("Semmi", tesztelendo.getUt());
             assertNotNull(tesztelendo.getUt());
             for(int i=1; i<21; i++){
                 tesztelendo.setUt(Integer.toString(i));
                 assertEquals(Integer.toString(i), tesztelendo.getUt());
                 assertNotNull(tesztelendo.getUt());
             }
             
             
             tesztelendo.setHazSzam(60);
             assertEquals(60, tesztelendo.getHazSzam());
             assertNotNull(tesztelendo.getHazSzam());
             for(int i=1; i<21; i++){
                 tesztelendo.setHazSzam(i);
                 assertEquals(i, tesztelendo.getHazSzam());
                 assertNotNull(tesztelendo.getHazSzam());
             }
             
             
             tesztelendo.setCegnev("Révész Trans");
             assertEquals("Révész Trans", tesztelendo.getCegnev());
             assertNotNull(tesztelendo.getCegnev());
             for(int i=1; i<21; i++){
                 tesztelendo.setCegnev(Integer.toString(i));
                 assertEquals(Integer.toString(i), tesztelendo.getCegnev());
                 assertNotNull(tesztelendo.getCegnev());
             }
             
             
             tesztelendo.setKoord1("12.12.12,12");
             assertEquals("12.12.12,12", tesztelendo.getKoord1());
             assertNotNull(tesztelendo.getKoord1());
             for(int i=1; i<21; i++){
                 tesztelendo.setKoord1(Integer.toString(i));
                 assertEquals(Integer.toString(i), tesztelendo.getKoord1());
                 assertNotNull(tesztelendo.getKoord1());
             }
             
             
             tesztelendo.setKoord2("22.22.22,22");
             assertEquals("22.22.22,22", tesztelendo.getKoord2());
             assertNotNull(tesztelendo.getKoord1());
             for(int i=1; i<21; i++){
                 tesztelendo.setKoord2(Integer.toString(i));
                 assertEquals(Integer.toString(i), tesztelendo.getKoord2());
                 assertNotNull(tesztelendo.getKoord2());
             }
             
             
             tesztelendo.setTavolsag(123.0);
             assert 123.0==tesztelendo.getTavolsag();
             assertNotNull(tesztelendo.getTavolsag());
             for(int i=1; i<20; i++){
                 tesztelendo.setTavolsag(i*5.3);
                 assert (i*5.3) == tesztelendo.getTavolsag();
                 assertNotNull(tesztelendo.getTavolsag());
             }
         } 
}
