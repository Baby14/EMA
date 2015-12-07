package com.example.manal.lightsensor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Manal on 08.11.2015.
 */
public class DropDownList {

//    private Spinner isoSpinner, shutterSpeedSpinner, apertureSpinner;
//    private Button btnSubmit;


//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_sensor);
//        addItemsOnSpinners();
//        addListenerOnButton();
//        addListenerOnSpinnerItemSelection();

//        spinner = (Spinner)findViewById(R.id.iso_spinner);
//        ArrayAdapter<String>adapter = new ArrayAdapter<String>(DropDownList.this,
//                android.R.layout.simple_spinner_item,isoValuse);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

//    }

//    // add items into spinner dynamically
//    public void addItemsOnSpinners() {
//
//        isoSpinner = (Spinner) findViewById(R.id.iso_spinner);
//        shutterSpeedSpinner = (Spinner) findViewById(R.id.shutterSpeed_spinner);
//        apertureSpinner = (Spinner) findViewById(R.id.aperture_spinner);
//        //final int []isoValuse = {100, 200, 300};
//        final String[]shutterValuse = {"2", "1", "1/2", "1/4"};
//        final String[] apertureValuse = {"1/32", "1/22", "1/16"};
//        List<String> isoValuse = new ArrayList<String>();
//        isoValuse.add("100");
//        isoValuse.add("200");
//        isoValuse.add("300");
//        ArrayAdapter<String> isoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, isoValuse);
//        isoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        isoSpinner.setAdapter(isoAdapter);
//
//        ArrayAdapter<String> tAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, shutterValuse);
//        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        shutterSpeedSpinner.setAdapter(tAdapter);
//
//        ArrayAdapter<String> fAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, apertureValuse);
//        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        apertureSpinner.setAdapter(fAdapter);
//    }
//
//    public void addListenerOnSpinnerItemSelection() {
//        isoSpinner = (Spinner) findViewById(R.id.iso_spinner);
//        isoSpinner.setOnItemSelectedListener(new SelectedItemListener());
//
//        shutterSpeedSpinner = (Spinner) findViewById(R.id.shutterSpeed_spinner);
//        shutterSpeedSpinner.setOnItemSelectedListener(new SelectedItemListener());
//
//        apertureSpinner = (Spinner) findViewById(R.id.aperture_spinner);
//        apertureSpinner.setOnItemSelectedListener(new SelectedItemListener());
//    }
//
//    // get the selected dropdown list value
//    public void addListenerOnButton() {
//
//        isoSpinner = (Spinner) findViewById(R.id.iso_spinner);
//        shutterSpeedSpinner = (Spinner) findViewById(R.id.shutterSpeed_spinner);
//        apertureSpinner = (Spinner) findViewById(R.id.aperture_spinner);
//
//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//
//        btnSubmit.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DropDownList.this,
//                        "OnClickListener : " +
//                                "\n isoSpinner : " + String.valueOf(isoSpinner.getSelectedItem()) +
//                                "\n tSpinner : " + String.valueOf(shutterSpeedSpinner.getSelectedItem()) +
//                                "\n fSpinner : " + String.valueOf(apertureSpinner.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
    }

