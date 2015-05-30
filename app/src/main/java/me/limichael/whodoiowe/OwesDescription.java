package me.limichael.whodoiowe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class OwesDescription extends ActionBarActivity {

    private TextView personOwed;
    private EditText amountOwed;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owes_description);

        Bundle extras = getIntent().getExtras();

        Owes data = (Owes) extras.get("data");
        personOwed = (TextView) findViewById(R.id.PersonOwed);
        amountOwed = (EditText) findViewById(R.id.editOwed);
        description = (TextView) findViewById(R.id.OwedDescription);

        personOwed.setText(data.getPersonToPay());
        amountOwed.setText(String.valueOf(data.getAmount()));
        description.setText(data.getDescription());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_owes_description, menu);
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
}
