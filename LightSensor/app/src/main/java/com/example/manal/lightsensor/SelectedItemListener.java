package com.example.manal.lightsensor;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by Manal on 08.11.2015.
 */
public class SelectedItemListener implements OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();


        String s = parent.getItemAtPosition(position).toString();
        Object obj = parent.getSelectedItem();
        Log.d("OnItemSelectedListener", "OnItemSelectedListener :" + obj.toString());

        //Hide  spinner.setVisibility(View.GONE);
        //Show spinner.setVisibility(View.VISIBLE);
        Log.v("item", (String) parent.getItemAtPosition(position));

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected

                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
         // TODO Auto-generated method stub
        }


}
