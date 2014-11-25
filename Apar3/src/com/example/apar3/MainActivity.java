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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;



public class MainActivity extends Activity {

	//private static final SQLiteDatabase DBAdapter = null; ble


	private Cursor c;
	

	DBAdapter db;
       
    TextView wypisz_nazwe, wypisz_model;
    
    String pobrana_nazwa, pobrany_model, zwracana_nazwa, zwacany_model, sprzety;
    Button wyswietl, pobierz, dodawanie, lista, zd_odb, zgloszenie;
    EditText pobierz_nazwe, pobierz_model, etext;
    SimpleCursorAdapter adapter;
    
    AutoCompleteTextView textView;
    
   
    
    //ListView av;
    
    
    
   
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        
        
        
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
                	
                	setContentView(R.layout.activity_main);
                	
                	Button dodawanie = (Button)findViewById(R.id.przycisk_dodawanie);
                	Button zd_odb = (Button)findViewById(R.id.btn_pobierz);
                	Button lista = (Button)findViewById(R.id.btn_lista);
                	Button zgloszenie = (Button)findViewById(R.id.przycisk_zgloszenie);
                	
                	dodawanie.setOnClickListener (
                			new OnClickListener() {
                				public void onClick(View v) {
                					EkranWstawiania();
                				}
                			});
                	
                	zd_odb.setOnClickListener(
                			new OnClickListener ()
                			{
                				public void onClick(View v) {
                					EkranZdawczoOdbiorczego1();
                				}
                				
                			});
                	
                	lista.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranListy();
                				}
                			});
                	
                	zgloszenie.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranZgloszenia();
                				}
                			});
                	
                	
                
               
                }
                
				protected void EkranZgloszenia() {
					// TODO Auto-generated method stub
					setContentView(R.layout.activity_zgloszenie);
					
					Button przycisk_powrot1 = (Button)findViewById(R.id.przycisk_powrot1);
					
					przycisk_powrot1.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranGlowny();
                				}
                			});
					
				}


				protected void EkranListy() {
					// TODO Auto-generated method stub
					setContentView(R.layout.activity_ekran_listy);
					
					Button przycisk_powrot4 = (Button)findViewById(R.id.przycisk_powrot4);
					
					przycisk_powrot4.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranGlowny();
                				}
                			});
					
				}


				protected void EkranZdawczoOdbiorczego1() {
					// TODO Auto-generated method stub
					setContentView(R.layout.activity_zdawczo_odbiorczy1);
					
					Button btn_dalej_zd_od1 = (Button)findViewById(R.id.btn_wstecz_zd_odb2);
					
					
					
					
					
					Button przycisk_powrot3 = (Button)findViewById(R.id.przycisk_powrot3);
					
					przycisk_powrot3.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranGlowny();
                				}
                			});
					
					
					
					btn_dalej_zd_od1.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranZdawczoOdbiorczego2();
                				}
                			});
					
				}


				protected void EkranZdawczoOdbiorczego2() {
					setContentView(R.layout.activity_zdawczo_odbiorczy2);
					
					Button btn_dalej_zd_odb2 = (Button)findViewById(R.id.btn_dalej_zd_od2);
					Button btn_wstecz_zd_odb2 = (Button)findViewById(R.id.btn_wstecz_zd_odb2);
					
					String[] sprzety = {"KARDIOMONITOR", "KARDIOTOKOGRAF", "APARAT RTG", "APARAT EKG"}; 
					AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
					ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line, sprzety);
					
					textView.setThreshold(3);
					textView.setAdapter(adapter);
					
					btn_dalej_zd_odb2.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranZdawczoOdbiorczego3();
                				}
                			});
					
					btn_wstecz_zd_odb2.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranZdawczoOdbiorczego1();
                				}
                			});
					
				}


				protected void EkranZdawczoOdbiorczego3() {
					setContentView(R.layout.activity_zdawczo_odbiorczy3);
					
				}


				public void EkranWstawiania() {
					setContentView(R.layout.activity_wstawianie);
					
					Button pobierz = (Button)findViewById(R.id.btn_pobierz);
					Button wyswietl = (Button)findViewById(R.id.btn_lista);
					
					Button przycisk_powrot2 = (Button)findViewById(R.id.przycisk_powrot2);
					
					przycisk_powrot2.setOnClickListener(
                			new OnClickListener()
                			{
                				public void onClick(View v) {
                					EkranGlowny();
                				}
                			});
					
					
					db.open();        	                   
					 
					Toast.makeText(this, "Baza otwarta", Toast.LENGTH_LONG).show();
					
					pobierz.setOnClickListener(
							
							new OnClickListener() {
								public void onClick(View v) {
							
									DadajSprzet();
														
						}
					});
					
					
					wyswietl.setOnClickListener(
							new OnClickListener() {
												
								public void onClick(View v) {
							//WyswietlSprzet(c);
							
									DajWszystkieSprzety();
							
							
						}
					});
					
									
				}
	
					
				public void DadajSprzet() {
            		
					final EditText pobierz_nazwe = (EditText)findViewById(R.id.pobierz_nazwe);
					final EditText pobierz_model = (EditText)findViewById(R.id.pobierz_model);
					
					
					
					
            		if(db.wstawSprzet(pobierz_nazwe.getText().toString(), pobierz_model.getText().toString())>=0) {
            			Toast.makeText(this, "Dodanie powiodlo sie!!!", Toast.LENGTH_LONG).show();
            			AktualizujSprzet();
            			DajWszystkieSprzety();
            		}
            		//db.close();
            		
            	}
        	
        	
        	
			public void DajWszystkieSprzety() {
        		  		
        		setContentView(R.layout.lista_wpisanych);  		
        		 //db = new DBAdapter(this);
        		
        		
        		
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


  