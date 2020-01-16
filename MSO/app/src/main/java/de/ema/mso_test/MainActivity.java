package de.ema.mso_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import de.ema.mso_test.dto.NutzerDTO;
import de.ema.mso_test.dto.factory.NutzerDTOFactory;
import de.ema.mso_test.helper.StoredDataHelper;

public class MainActivity extends AppCompatActivity {

    private NutzerDTO nutzerDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Daten aus dem Speicher holen
        nutzerDTO = StoredDataHelper.readStorageData(getSharedPreferences(getString(R.string.shared_preferences_user_data), Context.MODE_PRIVATE), getString(R.string.shared_preferences_user_data_json));

        if (nutzerDTO == null) {
            // wenn nein, direkt weiterrooten auf Eingabemaske
            nutzerDTO = NutzerDTOFactory.createInstance();
        }
        Intent intent = new Intent(this, UserDatenErfassenActivity.class);
        intent.putExtra("ShowIntToast", true);
        intent.putExtra("nutzerDTO", nutzerDTO);
        startActivity(intent);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Methode für den Button "meine Daten"
     *
     * @param view
     */
    public void meineDaten(View view) {
        Intent intent = new Intent(this, UserDatenAnzeigenActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    /**
     * Methode für den Button "Daten Erfassen
     *
     * @param view
     */
    public void datenErfassen(View view) {

        Intent intent = new Intent(this, UserDatenErfassenActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }


}
