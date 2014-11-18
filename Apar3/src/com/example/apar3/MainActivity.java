package com.example.apar3;


//import android.R;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;



public class MainActivity extends Activity {

	private static final SQLiteDatabase DBAdapter = null;


	private Cursor c;
	

	DBAdapter db;
       
    TextView wypisz_nazwe, wypisz_model;
    
    String pobrana_nazwa, pobrany_model, zwracana_nazwa, zwacany_model;
    Button wyswietl, pobierz;
    EditText pobierz_nazwe, pobierz_model;
    SimpleCursorAdapter adapter;
    
   
    
    //ListView av;
    
    
    
   
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
                	                   
                db = new DBAdapter(this);
                
                
                EkranGlowny(); 
                
                //DadajSprzet();
                //DajWszystkieSprzety();
                //DajSprzet();
               // AktualizujSprzet();
                //UsunSprzet();
                //WyswietlSprzet();
                      
            }
                
                
				public void EkranGlowny() {
					Button pobierz = (Button)findViewById(R.id.button1);
					Button wyswietl = (Button)findViewById(R.id.button2);
					
					pobierz.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							DadajSprzet();
							
							
						}
					});
					
					
					wyswietl.setOnClickListener(new OnClickListener() {
						
						public void onClick(View v) {
							//WyswietlSprzet(c);
							
							DajWszystkieSprzety();
							
							
						}
					});
					
									
				}
	
					
				public void DadajSprzet() {
            		
					final EditText pobierz_nazwe = (EditText)findViewById(R.id.editText1);
					final EditText pobierz_model = (EditText)findViewById(R.id.editText2);
					
					
					
					db.open();
            		if(db.wstawSprzet(pobierz_nazwe.getText().toString(), pobierz_model.getText().toString())>=0) {
            			Toast.makeText(this, "Dodanie powiodlo sie!!!", Toast.LENGTH_LONG).show();
            			AktualizujSprzet();
            			DajWszystkieSprzety();
            		}
            		db.close();
            		
            	}
        	
        	
        	
			public void DajWszystkieSprzety() {
        		  		
        		setContentView(R.layout.lista_wpisanych);  		
        		 db = new DBAdapter(this);
        		
        		 db.open();
        		
        		c = db.wezWszystkieSprzety();
        		adapter = new SimpleCursorAdapter(this,
        				R.layout.rejestr, c,
        				new String[] {db.KLUCZN, db.KLUCZM},
        				new int[] {R.id.wyswietlNazwa, R.id.wyswietlModel});

        		
        		ListView av = (ListView)findViewById(R.id.lista1);
        		av.setAdapter(adapter);
        	
        		db.close();
        	} 
        	
        		

        		
        	


        	public void DajSprzet() {
        		db.open();
        		Cursor c = db.wezJedenSprzet(1);
        		if(c.moveToFirst())
        			WyswietlSprzet(c);
        		else
        			Toast.makeText(this, "Sprzetu nie znaleziono!!", Toast.LENGTH_LONG).show();
        		db.close();
        	}

            public void AktualizujSprzet() {
        	
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
        		
            	final TextView wypisz_nazwe = (TextView) findViewById(R.id.textNazwa);
            	final TextView wypisz_model = (TextView) findViewById(R.id.textModel);
            	
            	String nazwa = c.getString(1);
            	String model = c.getString(2);
            	
            	wypisz_nazwe.setText(nazwa);
            	wypisz_model.setText(model); 
            	
            	Toast.makeText(this, "id:" + c.getString(0) + "\n" + "Nazwa" + c.getString(1) + "\n"
        				+ "Model:" + c.getString(2), Toast.LENGTH_LONG).show();
        		
        	}
            
            
        	
        
 
    
    }


  