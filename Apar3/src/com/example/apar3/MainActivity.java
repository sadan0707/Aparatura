package com.example.apar3;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

	DBAdapter db;
       
    TextView wypisz_nazwe;
    TextView wypisz_model;
    
    String nazwa, model;
    
   
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                	                   
                db = new DBAdapter(this);
                
                
                DadajSprzet();
                //DajWszystkieSprzety();
                DajSprzet();
               // AktualizujSprzet();
                //UsunSprzet();
                //WyswietlSprzet();
                      
            }
                
                public void DadajSprzet() {
            		db.open();
            		if(db.wstawSprzet("APARAT RTG", "INFINIX FX")>=0) {
            			Toast.makeText(this, "Dodanie powiodlo sie!!!", Toast.LENGTH_LONG).show();
            		}
            		db.close();
            		
            	}
        	
        	
        	/*public void DajWszystkieSprzety() {
        		db.open();
        		Cursor c = db.wezWszystkieSprzety();
        		if(c.moveToFirst())
        			do {
        				WyswietlSprzet(c);
        			} while (c.moveToNext());

        	
        	
        	db.close();
        		
        	} */


        	public void DajSprzet() {
        		db.open();
        		Cursor c = db.wezJedenSprzet(1);
        		if(c.moveToFirst())
        			WyswietlSprzet(c);
        		else
        			Toast.makeText(this, "Sprzetu nie znaleziono!!", Toast.LENGTH_LONG).show();
        		db.close();
        	}

            /*public void AktualizujSprzet() {
        	
        		db.open();
        		if (db.aktualizujSprzet(1,"APARAT RTG", "INFINIX FX"))
        			Toast.makeText(this, "Aktualizacja powiodla sie!", Toast.LENGTH_LONG).show();
        		else
        			Toast.makeText(this, "Aktualizacja nie powiodla sie!", Toast.LENGTH_LONG).show();
        	db.close();
            }
            
        	
            //public void UsunSprzet() {
        		// TODO Auto-generated method stub
        		
        	//} */
        	
            public void WyswietlSprzet(Cursor c) {
        		
            	final TextView wypisz_nazwe = (TextView) findViewById(R.id.textView1);
            	final TextView wypisz_model = (TextView) findViewById(R.id.textView2);
            	
            	String nazwa = c.getString(1);
            	String model = c.getString(2);
            	
            	wypisz_nazwe.setText(nazwa);
            	wypisz_model.setText(model); 
            	
            	Toast.makeText(this, "id:" + c.getString(0) + "\n" + "Nazwa" + c.getString(1) + "\n"
        				+ "Model:" + c.getString(2), Toast.LENGTH_LONG).show();
        		
        	}
        	
        
 
    
    }


  