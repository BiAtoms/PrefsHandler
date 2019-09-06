package com.sampleApp;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.prefshandler.PrefsHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spnr_keys_main)
    Spinner spnrKeys;

    @BindView(R.id.edt_value_submit_main)
    EditText value;

    @BindView(R.id.spnr_find_keys_main)
    Spinner spnrFindKeys;

    @BindView(R.id.spnr_delete_keys_main)
    Spinner spnrDeleteKeys;

    @BindView(R.id.txt_found_main)
    TextView txtFoundedValue;

    @BindView(R.id.btn_submit_main)
    Button btnSubmit;

    @BindView(R.id.btn_find_main)
    Button btnFind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //Initialize PrefsHandler once
        new PrefsHandler.Builder(this)
                .setSharedPrefsMode(Context.MODE_PRIVATE) // Optional (default: Context.MODE_PRIVATE)
                .setSharedPrefsTag("MySharedPreferences") // Optional (default: "MySharedPreferences")
                .build();

        PrefsHandler.setValue(SharedPrefKeys.TRAIN.toString(), "My new data here");

        SharedPrefKeys[] arrayOfKeys = SharedPrefKeys.values();
        String[] items = new String[SharedPrefKeys.values().length-1];

        for (int i = 0, j = 0; i < arrayOfKeys.length; i++) {
            if(arrayOfKeys[i].toString().equals(SharedPrefKeys.LIST.toString())) continue;
            items[j] = arrayOfKeys[i].toString();
            j++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items);

        setAdapterAndInitialValueToSpinner(spnrKeys, adapter);
        setAdapterAndInitialValueToSpinner(spnrFindKeys, adapter);
        setAdapterAndInitialValueToSpinner(spnrDeleteKeys, adapter);

        List<String> list = SharedPrefKeys.getKeysAsStringArrayList();

        // Save String List data with PrefsHandler
        PrefsHandler.setListValue(SharedPrefKeys.LIST.toString(), list);

        List<String> result_of = PrefsHandler.getListValue(SharedPrefKeys.LIST.toString());

        System.out.println(result_of);
    }

    private void setAdapterAndInitialValueToSpinner(Spinner spnr, ArrayAdapter arrayAdapter){
        spnr.setAdapter(arrayAdapter);
        spnr.setSelection(0);
    }

    @OnClick(R.id.btn_submit_main)
    public void submit(View view) {
        PrefsHandler.setValue(spnrKeys.getSelectedItem().toString(), value.getText().toString());
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_find_main)
    public void find(View view) {

        String result = PrefsHandler.getValue(spnrFindKeys.getSelectedItem().toString(), String.class);

        //The result will be null if the wanted value is not found.
        if (result != null) {
            txtFoundedValue.setText(result);
            return;
        }

        txtFoundedValue.setText("");
        Toast.makeText(this, "No result found!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_delete_main)
    public void delete(View view) {
        PrefsHandler.clearData(spnrDeleteKeys.getSelectedItem().toString());
        Toast.makeText(this, "Cleared Successfully", Toast.LENGTH_SHORT).show();
    }
}
