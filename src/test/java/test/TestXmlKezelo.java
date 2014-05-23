/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import csomag.Ceg;
import csomag.Program;
import csomag.XmlKezelo;


import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * Az XmlKezelo osztály tesztelésére használt teszt osztály.
 * @author Jumpi
 */
public class TestXmlKezelo {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void peldanyositasTeszt(){
        XmlKezelo muveletek = new XmlKezelo();
        assertNotNull(muveletek);
    }
    
    @Test
    public void beolvasTeszt(){
        XmlKezelo muveletek = new XmlKezelo();
        LinkedList<Ceg> lista = new LinkedList<Ceg>();
        
        for(int i=0; i<100; i++){
            assertNotNull(lista = muveletek.beolvas(new Program().eleresiut));
            lista = null;
        }
    }
    
    @Test
    public void kiirTeszt(){
        XmlKezelo muveletek = new XmlKezelo();
        LinkedList<Ceg> lista = new LinkedList<Ceg>();
        Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
        String eleres = folder.getRoot() + "//teszt.xml";
        
        for(int i=0; i<5; i++){
        	muveletek.kiir(eleres, lista);
        }
        lista.add(tesztelendo);
        for(int i=0; i<5; i++){
        	muveletek.kiir(eleres, lista);
        }   
        
        for(int i=1; i<59; i++){
            char betu = 'a';
            String koord1 = i +"."+i+"."+i+","+i;
            String koord2 = i +"."+i+"."+i+","+i;
            Ceg tmp = new Ceg(String.valueOf(betu), i, String.valueOf(betu), String.valueOf(betu), i, String.valueOf(betu), koord1, koord2);
            lista.add(tmp);
            betu++;
        }
        for(int i=0; i<100; i++){
            muveletek.kiir(eleres, lista);
        }
        
    }
    
    @Test
    public void keresTeszt(){
    	
    	Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo2 = new Ceg("D", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo3 = new Ceg("D", 3465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo4 = new Ceg("H", 3465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	XmlKezelo muveletek = new XmlKezelo();
    	LinkedList<Ceg> eredmeny = new LinkedList<Ceg>();
    	LinkedList<Ceg> lista = new LinkedList<Ceg>();
    	LinkedList<Ceg> akt = new LinkedList<Ceg>();
    	lista.add(tesztelendo);
    	lista.add(tesztelendo2);
    	lista.add(tesztelendo3);
    	lista.add(tesztelendo4);
    	
    	assertNotNull(eredmeny = muveletek.keres("H", "", lista));
    	akt.add(tesztelendo);
    	akt.add(tesztelendo4);
    	assertEquals(akt, eredmeny);
    	
    	akt.clear();
    	eredmeny.clear();
    	assertNotNull(eredmeny = muveletek.keres("", "4465", lista));
    	akt.add(tesztelendo);
    	akt.add(tesztelendo2);
    	assertEquals(akt, eredmeny);
    	
    	akt.clear();
    	eredmeny.clear();
    	assertNotNull(eredmeny = muveletek.keres("H", "4465", lista));
    	assertEquals(false, eredmeny.isEmpty());
    	akt.add(tesztelendo);
    	assertEquals(akt, eredmeny);
    	
    	akt.clear();
    	eredmeny.clear();
    	assertNotNull(eredmeny = muveletek.keres("", "", lista));
    	assertEquals(lista, eredmeny);
     
        for(int i=1; i<59; i++){
            char betu = 'a';
            String koord1 = i +"."+i+"."+i+","+i;
            String koord2 = i +"."+i+"."+i+","+i;
            Ceg tmp = new Ceg(String.valueOf(betu), i, String.valueOf(betu), String.valueOf(betu), i, String.valueOf(betu), koord1, koord2);
            lista.add(tmp);
            betu++;
        }
            
        for(int i=1; i<59; i++){
            char betu = 'a';
            muveletek.keres(String.valueOf(betu), "", lista);
            betu++;
        }
        for(int i=1; i<59; i++){
            muveletek.keres("", String.valueOf(i), lista);
        }
        for(int i=1; i<59; i++){
            char betu = 'a';
            muveletek.keres(String.valueOf(betu), String.valueOf(i), lista);
            betu++;
        }
    }
    @Test(expected = NullPointerException.class)
    public void keresNullTeszt(){
    	XmlKezelo muveletek = new XmlKezelo();
    	LinkedList<Ceg> lista = new LinkedList<Ceg>();
    	lista = null;
    	for(int i=1; i<20; i++){
    		assertNull(muveletek.keres(Integer.toString(i), Integer.toString(i), lista));
    	}
    }
    
    @Test
    public void torolTeszt(){
        XmlKezelo muveletek = new XmlKezelo();
        int[] tomb;
        String[] betuk = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int[] szamok = {1,2,3,4,5};
        
        LinkedList<Ceg> lista = new LinkedList<Ceg>();
        LinkedList<Ceg> akt = new LinkedList<Ceg>();
        LinkedList<Ceg> eredmeny = new LinkedList<Ceg>();
        Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo2 = new Ceg("D", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo3 = new Ceg("D", 3465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo4 = new Ceg("H", 3465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
        
        lista.add(tesztelendo);
    	lista.add(tesztelendo2);
    	lista.add(tesztelendo3);
    	lista.add(tesztelendo4);
        
        tomb = new int[0];
        eredmeny = muveletek.torles(tomb, lista);
        assertEquals(lista, eredmeny);
        assertEquals(false, lista.isEmpty());
        
        tomb = new int[1];
        tomb[0] = 1;
        eredmeny = muveletek.torles(tomb, lista);
        lista.remove(1);
        assertEquals(lista, eredmeny);
        
        lista.clear();
        lista.add(tesztelendo);
    	lista.add(tesztelendo2);
    	lista.add(tesztelendo3);
    	lista.add(tesztelendo4);
        
        tomb = new int[3];
        tomb[0] = 0;
        tomb[1] = 2;
        tomb[2] = 3;
        akt.add(tesztelendo2);
        eredmeny = muveletek.torles(tomb, lista);
        assertEquals(akt, eredmeny);

        for(int k=1; k<59; k++){
            for(int i=1; i<59; i++){
                char betu = 'a';
                String koord1 = i +"."+i+"."+i+","+i;
                String koord2 = i +"."+i+"."+i+","+i;
                Ceg tmp = new Ceg(String.valueOf(betu), i, String.valueOf(betu), String.valueOf(betu), i, String.valueOf(betu), koord1, koord2);
                lista.add(tmp);
                betu++;
            }
        
            tomb = new int[k];
            for(int j=0; j<k; j++){
                tomb[j] = j;
            }
            assertNotNull(muveletek.torles(tomb, lista));
            lista.clear();
        }
        
    }
    
    @Test
    public void googleMapsTest(){
        XmlKezelo muveletek = new XmlKezelo();
        String x = "10.10.10,10";
        String y = "20.20.20,20";
               
        muveletek.googleMaps(x, y);
    }
    
    @Test
    public void specialisKeresesTest(){
        
        XmlKezelo muveletek = new XmlKezelo();
        LinkedList<Ceg> eredmeny = new LinkedList<Ceg>();
        LinkedList<Ceg> lista = new LinkedList<Ceg>();
        
        String x = "10.10.10,10";
        String y = "20.20.20,20";
        
        Ceg tesztelendo = new Ceg("H", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo2 = new Ceg("D", 4465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo3 = new Ceg("D", 3465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
    	Ceg tesztelendo4 = new Ceg("H", 3465, "Rakamaz", "Szent István út", 80, "Jumpi KFT", "10.20.30,40", "10.20.30,40");
        
        lista.add(tesztelendo);
    	lista.add(tesztelendo2);
    	lista.add(tesztelendo3);
    	lista.add(tesztelendo4);
        
        for(int i=0; i<4; i++){
            eredmeny = muveletek.specialisKereses(x, y, Math.pow(20,i), lista);
            assertNotNull(eredmeny);
        }
        
        for(int i=1; i<59; i++){
            char betu = 'a';
            String koord1 = i +"."+i+"."+i+","+i;
            String koord2 = i +"."+i+"."+i+","+i;
            Ceg tmp = new Ceg(String.valueOf(betu), i, String.valueOf(betu), String.valueOf(betu), i, String.valueOf(betu), koord1, koord2);
            lista.add(tmp);
            betu++;
        }
        for(int i=0; i<200; i++){
            String koord1 = i +"."+i+"."+i+","+i;
            String koord2 = i +"."+i+"."+i+","+i;
            eredmeny = muveletek.specialisKereses(koord1, koord2, (double)i*50, lista);
            assertNotNull(eredmeny);
        }
        
        
        
        
        
        
    }
    
    
    
}
