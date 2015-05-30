package me.limichael.whodoiowe;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddOwe extends ActionBarActivity {

    private static final String TAG = "AddOwe Tag";

    private static final double INVALID_AMOUNT = Double.NEGATIVE_INFINITY;

    private Button doneButton;

    private EditText nameTextEdit;
    private EditText amountTextEdit;
    private EditText descriptionTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_owe);

        nameTextEdit = (EditText) findViewById(R.id.nameTextEdit);
        amountTextEdit = (EditText) findViewById(R.id.amountTextEdit);
        descriptionTextEdit = (EditText) findViewById(R.id.descriptionTextEdit);

        doneButton = (Button) findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAmount = amountTextEdit.getText().toString();
                double amount = validMoney(strAmount);
                if(amount != INVALID_AMOUNT) {
                    String name = nameTextEdit.getText().toString();
                    String description = descriptionTextEdit.getText().toString();
                    Owes owe = new Owes(name, amount, description);
//                    getIntent().putExtra("data", owe);
                    Log.i(TAG, "made the owe object");
                    setResultOk(owe);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Amount given isn't valid",
                            Toast.LENGTH_SHORT).show();
                }

            }

            private void setResultOk(Owes owe) {
                if(getParent() == null) {
                    setResult(RESULT_OK, getIntent().putExtra("data", owe));
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent().putExtra("data", owe));
                }
                finish();
            }
        });

    }

    private double validMoney(String amount) {
        try {
            return Double.parseDouble(amount);
        } catch (Exception e) {
            return INVALID_AMOUNT;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_owe, menu);
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
