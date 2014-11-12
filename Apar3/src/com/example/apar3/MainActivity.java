package com.example.apar3;

import android.app.Activity;
import android.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

	DBAdapter db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
    
        
        	DBAdapter db;
            
                
                db = new DBAdapter(this);
                Cursor c;
                
                DadajSprzet();
                DajWszystkieSprzety();
                DajSprzet();
                AktualizujSprzet();
                UsunSprzet();
                
            }
                
                private void DadajSprzet() {
            		db.open();
            		if(db.wstawSprzet("APARAT RTG", "INFINIX FX")>=0) {
            			Toast.makeText(this, "Dodanie powiodlo sie!!!", Toast.LENGTH_LONG).show();
            		}
            		db.close();
            		
            	}
        	
        	
        	public void DajWszystkieSprzety() {
        		db.open();
        		Cursor c = db.wezWszystkieSprzety();
        		if(c.moveToFirst())
        			do {
        				WyswietlSprzet(c);
        			} while (c.moveToNext());

        	
        	
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
            
        	
            public void UsunSprzet() {
        		// TODO Auto-generated method stub
        		
        	}
        	
            public void WyswietlSprzet(Cursor c) {
        		Toast.makeText(this, "id:"+ getString(0)+"\n"+"Nazwa"+getString(1)+"\n"
        				+"Model:"+getString(2), Toast.LENGTH_LONG).show();
        		
        	}
        	
        
 
    
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } 
}    */
