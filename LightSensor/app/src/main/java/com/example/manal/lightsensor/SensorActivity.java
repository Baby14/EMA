package com.example.manal.lightsensor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SensorActivity extends  Activity {

    private double lx_of_illuminance;
    private ProgressBar lightMeterPB;
    private TextView maxLuxV;
    private TextView currentLuxV;
    private TextView readingLuxV;
    private TextView readingAperture;
    private TextView ev;
    private Button startButton;

    private Spinner isoSpinner, shutterSpeedSpinner, apertureSpinner;
    private Button btnSubmit;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    private CalculateLightValues calculate;
    private String[]shutterValues, apertureValues;
    private List<String> isoValues;
    private Map<Integer, Double> isoList;

    public SensorActivity() {
        calculate = new CalculateLightValues();
        //TODO: Die liste auserhalb der klasse legen
        shutterValues = new String[]{"8", "4", "2", "1", "1/2", "1/4", "1/8"
                , "1/15", "1/30", "1/60", "1/125", "1/250", "1/500", "1/1000", "1/2000", "1/4000", "1/8000", "1/16000"};
        apertureValues = new String[]{"1/32", "1/22", "1/16", "1/11", "1/8.0", "1/5.6", "1/4.0", "1/2.8", "1/2.0", "1/1.4"};

        isoValues = new ArrayList<String>();
        int isoMin = 100;
        int isoMax = 12800;
        isoList = new HashMap<Integer, Double>();
        for (int i = isoMin; i <= isoMax; i *= 2) {
            isoValues.add(new String(String.valueOf(i)));
            System.out.println("isoValue for the list: " + i);
            for (int n = 0; n <= isoValues.size(); n++) {
                isoList.put(i, (double) Math.round(Math.pow(Math.sqrt(2), n)));
                System.out.println("Map Iso list:  " + isoList.get(i));
            }

        }

    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sensor);
        // lightMeterPB = (ProgressBar)findViewById(R.id.lightmeter);
        maxLuxV = (TextView)findViewById(R.id.maxLux);
        readingLuxV = (TextView) findViewById(R.id.readingLux);
        currentLuxV = (TextView)findViewById(R.id.currentLux);
        readingAperture = (TextView)findViewById(R.id.kValue);
        ev = (TextView)findViewById(R.id.evView);

        startButton = (Button) findViewById(R.id.start);

//        Intent intent = new Intent(this, DropDownList.class);
//        startActivity(intent);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        addItemsOnSpinners();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        if (lightSensor == null){
            // A toast is a view containing a quick little message for the user.
            Toast.makeText(this, "Sorry, your device doesn't support Light Sensor!", Toast.LENGTH_LONG).show();
            Log.d("", "Sorry, your device doesn't support Light Sensor!");

        }else{
            //maximum range of the sensor in the sensor's unit.
            float maxRange =  lightSensor.getMaximumRange();
            maxLuxV.append(String.valueOf(maxRange));
            Log.d("LightSensor", "Max Lux :" + maxRange);
            sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

    }

    SensorEventListener lightSensorEventListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            lx_of_illuminance = event.values[0];
            if(event.sensor.getType()==Sensor.TYPE_LIGHT){
                //lightMeterPB.setProgress((int) lx_of_illuminance);
                currentLuxV.setText(String.valueOf("Current (Lux): " + lx_of_illuminance));
                startButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        readingLuxV.setText("Current Reading Lux: " + String.valueOf(lx_of_illuminance));
                        Log.d("setOnClickListener", "Current Reading Lux: " + lx_of_illuminance);

                    }
                });

            }
        }

    };

    @Override
    protected void onResume() {
        // TODO Register a listener for the sensor.
        super.onResume();
        //sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        // TODO Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        //sensorManager.unregisterListener(lightSensorEventListener);
    }
    //add items into spinner dynamically
    public void addItemsOnSpinners() {

        isoSpinner = (Spinner) findViewById(R.id.iso_spinner);
        shutterSpeedSpinner = (Spinner) findViewById(R.id.shutterSpeed_spinner);
        apertureSpinner = (Spinner) findViewById(R.id.aperture_spinner);


        ArrayAdapter<String> isoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, isoValues);
        isoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isoSpinner.setAdapter(isoAdapter);

        ArrayAdapter<String> tAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, shutterValues);
        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shutterSpeedSpinner.setAdapter(tAdapter);

        ArrayAdapter<String> fAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, apertureValues);
        fAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apertureSpinner.setAdapter(fAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        isoSpinner = (Spinner) findViewById(R.id.iso_spinner);
        isoSpinner.setOnItemSelectedListener(new SelectedItemListener());

        shutterSpeedSpinner = (Spinner) findViewById(R.id.shutterSpeed_spinner);
        shutterSpeedSpinner.setOnItemSelectedListener(new SelectedItemListener());

        apertureSpinner = (Spinner) findViewById(R.id.aperture_spinner);
        apertureSpinner.setOnItemSelectedListener(new SelectedItemListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        isoSpinner = (Spinner) findViewById(R.id.iso_spinner);
        shutterSpeedSpinner = (Spinner) findViewById(R.id.shutterSpeed_spinner);
        apertureSpinner = (Spinner) findViewById(R.id.aperture_spinner);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(SensorActivity.this,
                        "OnClickListener : " +
                                "\n isoSpinner : " + String.valueOf(isoSpinner.getSelectedItem()) +
                                "\n tSpinner : " + String.valueOf(shutterSpeedSpinner.getSelectedItem()) +
                                "\n fSpinner : " + String.valueOf(apertureSpinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                // iso wählen, ev berechnen, k berechne
                int selectedIso= Integer.valueOf(isoSpinner.getSelectedItem().toString());
                double test = 1/2.;
                System.out.println("Test double: "+test );
                DecimalFormat deziFormat = new DecimalFormat("0.0");
                String selectedTString = shutterSpeedSpinner.getSelectedItem().toString().trim();
                //double parseSelectedT= Double.parseDouble(selectedTString);
                //System.out.println("Test parseSelectedT: "+parseSelectedT );
                String[] parts ;
                double selectedT;
                if(selectedTString.contains("/")) {
                    parts = selectedTString.split("/");
                    String part1 = parts[0]; // 1
                    String part2 = parts[1]; // /....
                    selectedT= 1/Double.valueOf(part2);
                    //System.out.println("Test selectedT: "+deziFormat.format(selectedT) );
                    System.out.println("Test selectedT: "+ selectedT );
                }else {
                    selectedT = Double.valueOf(selectedTString);
                    System.out.println("Test selectedT: " + selectedT);
                }

               // isoSpinner.setEnabled(false);
                //isoSpinner.setClickable(false);
                //shutterSpeedSpinner.setClickable(false);

                // int calculateEV =calculate.calculateEV(lx_of_illuminance, selectedIso);
                double calculateEVRatio = calculate.calculateEVRatio(lx_of_illuminance, selectedIso);
                double evRound = Math.round(calculateEVRatio);
                ev.setText("EV :"+String.valueOf(evRound)+" for ISO:" + selectedIso + ", t: " + selectedTString + ", Lux: " + lx_of_illuminance );
                //Extra 1/..., da das Öffnungsverhältnis (z. B. 1:8) zusammen mit der Brennweite als nicht ganz
                // ausmultipliziertes Produkt angegeben (f · 1/8 = f/8) wie BeeCam
                double calculateKEV = calculate.calculateApertureEV(selectedIso, selectedT);
                System.out.println("Calculate K aus EV-Formel: "+ calculateKEV);
                //double calculateK = 1/calculate.calculateAperture(selectedIso, selectedT, lx_of_illuminance);
                //######################
               // double calculateK = calculate.calculateAperture(selectedIso, selectedT, lx_of_illuminance);
                lx_of_illuminance = 500;
                double calculateK = calculate.calculateAperture(selectedIso, selectedT, lx_of_illuminance);

                if(selectedIso >100)
                    calculateK = calculateK * isoList.get(selectedIso);
                System.out.println("isoList.get(selectedIso): " + isoList.get(selectedIso));
                DecimalFormat d = new DecimalFormat("#.00");
                // round a double to 2 decimal places
                double roundK = Math.round((calculateK * 100.0))/ 100.0;
                        readingAperture.setText("k: 1/" +roundK);
                //System.out.println("Calaculate K aus Lux-Formel: " +  Math.round(calculateK));
                System.out.println("EV: "+ String.valueOf(calculateEVRatio)+" for ISO:" + selectedIso + ", t: " + selectedTString + ", Lux: " + lx_of_illuminance
                        + ", Calculate K aus EV-Formel: "+ calculateKEV
                        +", k aus Lux-Formel: " +  calculateK+ ", round K: 1/"+ roundK);


//                for (int i=0; i<apertureValues.length; i++) {
//                    System.out.println(apertureValues[i]);
//                    if(!apertureValues[i].equals(Math.round(calculateK))){
//                        //TODO: add k~1/k to the apertureSpinner
//                        apertureSpinner.setSelection(i);
//                        System.out.println("apertureSpinner.setSelection(i): " + apertureSpinner.getSelectedItem().toString());
//                    }
//                }
            }

        });
    }


}
