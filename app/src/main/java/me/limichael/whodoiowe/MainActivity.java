package me.limichael.whodoiowe;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private List<Owes> list;
    private ArrayAdapter<Owes> adapter;
    private ListView mainList;
    private Button addOwe;

    private SwipeDetector swipeDetector;

    private static final int CREATE_NEW_OWE = 234235352;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeDetector = new SwipeDetector();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        mainList = (ListView) findViewById(R.id.mainList);
        addOwe = (Button) findViewById(R.id.new_owe);

        mainList.setAdapter(adapter);
        mainList.setOnTouchListener(swipeDetector);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Owes item = (Owes) parent.getItemAtPosition(position);
//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                list.remove(item);
//                                adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
                if(swipeDetector.swipeDetected()) {
                    if(swipeDetector.getAction() == SwipeDetector.Action.LR || swipeDetector.getAction() == SwipeDetector.Action.RL) {
                        Intent intent = new Intent(getBaseContext(), OwesDescription.class);
                        intent.putExtra("data", item);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i(TAG, "request " + requestCode + " result code " + resultCode + " ok is " + RESULT_OK + " bad is " + RESULT_CANCELED);
        if(requestCode == CREATE_NEW_OWE) { // the intent with creating a new Owe activity
            if(resultCode == RESULT_OK) {
                if(data != null) {
                    Bundle tmp = data.getExtras();
                    adapter.add((Owes) tmp.get("data"));
                    Log.i(TAG, "finished the data");
                } else {
                    Log.e(TAG, "data is null");
                }
            }
        }
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

    private class StableArrayAdapter extends ArrayAdapter<Owes> {

        public StableArrayAdapter(Context context, int textViewResourceId, List<Owes> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
