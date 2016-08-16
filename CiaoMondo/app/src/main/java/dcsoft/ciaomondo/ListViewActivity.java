package dcsoft.ciaomondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        final String[] citta = new String[]{"Torino","Roma","Milano","Napoli","Firenze"};
        ArrayAdapter <String> adapter = new ArrayAdapter <String> (this, R.layout.riga_lista, citta);
        ListView lvLista = (ListView) findViewById(R.id.lvLista);
        lvLista.setAdapter(adapter);
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id)
            {
                Toast.makeText(getApplicationContext(), "Selezionato " + citta[pos], Toast.LENGTH_LONG).show();
            }
        });

        final String[] numeri = new String[]{"11","12","13","21","22","23","31","32","33","41","42","43","51","52","53"};
        ArrayAdapter <String> adapter2 = new ArrayAdapter <String> (this, R.layout.riga_lista, numeri);
        GridView gvGrid = (GridView) findViewById(R.id.gvGrid);
        gvGrid.setAdapter(adapter2);
        gvGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id)
            {
                Toast.makeText(getApplicationContext(), "Selezionato " + numeri[pos], Toast.LENGTH_LONG).show();
            }
        });
    }
}
