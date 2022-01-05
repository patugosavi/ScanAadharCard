package com.example.scanadharuid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isFinishing()) {
            callBarcodeScanner();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (intentResult != null) {
                if (intentResult.getContents() == null) {
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                } else {
                    String result = intentResult.getContents();
                    returnOdkResult(result);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void callBarcodeScanner() {
        Intent intent = new IntentIntegrator(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .createScanIntent();
        startActivityForResult(intent, IntentIntegrator.REQUEST_CODE);
    }


    private void returnOdkResult(String result) {
        ScanResult scanResult = new ScanResult(result);
        ScanResultResponseAdapter responseAdapter = new ScanResultResponseAdapter(scanResult, getIntent());
        if (responseAdapter.getHasOdkIntentDataFieldError()) {
            Toast.makeText(this,
                    getResources().getString(R.string.odk_intent_data_field_error)
                            + " " + responseAdapter.getOdkIntentDataField(),
                    Toast.LENGTH_SHORT).show();
        }
        setResult(Activity.RESULT_OK, responseAdapter.getResponseIntent());

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uid", scanResult.uid);
        editor.putString("name", scanResult.name);
        editor.putString("gender", scanResult.gender);
        editor.putString("yob", scanResult.yob);
        editor.putString("co", scanResult.co);
        editor.putString("house", scanResult.house);
        editor.putString("street", scanResult.street);
        editor.putString("lm", scanResult.lm);
        editor.putString("loc", scanResult.loc);
        editor.putString("vtc", scanResult.vtc);
        editor.putString("po", scanResult.po);
        editor.putString("dist", scanResult.dist);
        editor.putString("subdist", scanResult.subdist);
        editor.putString("state", scanResult.state);
        editor.putString("pc", scanResult.pc);
        editor.putString("dob", scanResult.dob);
        editor.putString("dobGuess",scanResult.dobGuess);
        editor.apply();
        editor.commit();

        Intent i=new Intent(MainActivity.this,ShowAdharData.class);
        startActivity(i);
//        finish();
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
}
