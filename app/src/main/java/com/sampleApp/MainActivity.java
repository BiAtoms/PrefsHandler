package com.sampleApp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.sharedpreferencesmanager.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private SharedPreferencesManager sharedPreferencesManager;

    @BindView(R.id.spnr_keys_main)
    Spinner spnrKeys;

    @BindView(R.id.edt_value_submit_main)
    EditText value;

    @BindView(R.id.spnr_find_keys_main)
    Spinner spnrFindKeys;

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
/*
        SharedPrefKeys[] arrayOfKeys = SharedPrefKeys.values();
        String[] items = new String[SharedPrefKeys.values().length];

        for (int i = 0; i < arrayOfKeys.length; i++) {
            items[i] = arrayOfKeys[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spnrKeys.setAdapter(adapter);
        spnrFindKeys.setAdapter(adapter);
        spnrKeys.setSelection(0);
        spnrFindKeys.setSelection(0);
        sharedPreferencesManager = new SharedPreferencesManager(this);
*/
        //TODO: Fix this part! To make a new release, you should comment all potential errors!
    }

    @OnClick(R.id.btn_submit_main)
    public void submit(View view) {
      /*
        String result = value.getText().toString();
        if (!result.equals("")) {
            sharedPreferencesManager.setValue(spnrKeys.getSelectedItemPosition(), value.getText().toString());
            return;
        }
        Toast.makeText(this, "Cannot be blank", Toast.LENGTH_LONG).show();
        */
    }

    @OnClick(R.id.btn_find_main)
    public void find(View view) {
/*
        String result = sharedPreferencesManager.getValue(String.class);

        // the result will be null if the wanted value is not found.
        if (result != null) {
            txtFoundedValue.setText(result);
            return;
        }
        Toast.makeText(this, "No result found!", Toast.LENGTH_LONG).show();
  */
    }


}
