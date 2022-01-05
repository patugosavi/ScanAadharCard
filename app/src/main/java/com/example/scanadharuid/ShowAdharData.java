package com.example.scanadharuid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowAdharData extends AppCompatActivity {

    TextView tv_uid,tv_name,tv_gender,tv_yob,tv_co,tv_house,tv_street,tv_lm,tv_loc,tv_vtc,
            tv_po,tv_dist,tv_subdist,tv_state,tv_pc,tv_dob;
    Button btn_rescan;

    String uid,name,gender,yob,co,house,street,lm,loc,vtc, po,dist,subdist,state,pc,dob;


    Context mContxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adhar_data);

        mContxt=getApplicationContext();

        tv_uid=(TextView) findViewById(R.id.tv_uid);
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_gender=(TextView) findViewById(R.id.tv_gender);
        tv_yob=(TextView) findViewById(R.id.tv_yob);
        tv_co=(TextView) findViewById(R.id.tv_co);
        tv_house=(TextView) findViewById(R.id.tv_house);
        tv_street=(TextView) findViewById(R.id.tv_street);
        tv_lm=(TextView) findViewById(R.id.tv_lm);
        tv_loc=(TextView) findViewById(R.id.tv_loc);
        tv_vtc=(TextView) findViewById(R.id.tv_vtc);
        tv_po=(TextView) findViewById(R.id.tv_po);
        tv_dist=(TextView) findViewById(R.id.tv_dist);
        tv_subdist=(TextView) findViewById(R.id.tv_subdist);
        tv_state=(TextView) findViewById(R.id.tv_state);
        tv_pc=(TextView) findViewById(R.id.tv_pc);
        tv_dob=(TextView) findViewById(R.id.tv_dob);

        btn_rescan=(Button)findViewById(R.id.btn_rescan);

        SharedPreferences preferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        preferences = mContxt.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
//        editor = preferences.edit();
        uid = preferences.getString("uid","");
        name = preferences.getString("name","");
        gender = preferences.getString("gender","");
        yob = preferences.getString("yob","");
        co = preferences.getString("co","");
        house = preferences.getString("house","");
        street = preferences.getString("street","");
        lm = preferences.getString("lm","");
        loc = preferences.getString("loc","");
        vtc = preferences.getString("vtc","");
        po = preferences.getString("po","");
        dist = preferences.getString("dist","");
        subdist = preferences.getString("subdist","");
        state = preferences.getString("state","");
        pc = preferences.getString("pc","");
        dob = preferences.getString("dob","");



        tv_uid.setText(uid);
        tv_name.setText(name);
        tv_gender.setText(gender);
        tv_yob.setText(yob);
        tv_co.setText(co);
        tv_house.setText(house);
        tv_street.setText(street);
        tv_lm.setText(lm);
        tv_loc.setText(loc);
        tv_vtc.setText(vtc);
        tv_po.setText(po);
        tv_dist.setText(dist);
        tv_subdist.setText(subdist);
        tv_state.setText(state);
        tv_pc.setText(pc);
        tv_dob.setText(dob);

        btn_rescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowAdharData.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}