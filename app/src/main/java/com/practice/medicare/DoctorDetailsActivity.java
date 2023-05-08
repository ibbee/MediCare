package com.practice.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

//    some hardcoded doctors for each category
    private String[][] doctor_details1 =
        {
                {"Doctor Name : Joe Banks", "Hospital Address : Lahore", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
                {"Doctor Name : Sarah Khan", "Hospital Address : Karachi", "Exp : 8yrs", "Mobile No : 9876543210", "700"},
                {"Doctor Name : Ahmed Hassan", "Hospital Address : Islamabad", "Exp : 3yrs", "Mobile No : 8765432109", "550"},
                {"Doctor Name : Aisha Ali", "Hospital Address : Peshawar", "Exp : 6yrs", "Mobile No : 7654321098", "650"},
                {"Doctor Name : Faizan Ahmed", "Hospital Address : Rawalpindi", "Exp : 4yrs", "Mobile No : 6543210987", "575"}
        };
    private String[][] doctor_details2 = {
            {"Doctor Name : Samina Khan", "Hospital Address : Quetta", "Exp : 7yrs", "Mobile No : 5432109876", "625"},
            {"Doctor Name : Hina Ahmed", "Hospital Address : Lahore", "Exp : 4yrs", "Mobile No : 4321098765", "550"},
            {"Doctor Name : Salman Ali", "Hospital Address : Islamabad", "Exp : 2yrs", "Mobile No : 3210987654", "500"},
            {"Doctor Name : Ali Khan", "Hospital Address : Karachi", "Exp : 9yrs", "Mobile No : 2109876543", "750"},
            {"Doctor Name : Maria Khalid", "Hospital Address : Peshawar", "Exp : 5yrs", "Mobile No : 1098765432", "575"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Ahmed Raza", "Hospital Address : Rawalpindi", "Exp : 3yrs", "Mobile No : 8765432109", "550"},
            {"Doctor Name : Mahnoor Qureshi", "Hospital Address : Lahore", "Exp : 6yrs", "Mobile No : 7654321098", "650"},
            {"Doctor Name : Fawad Ali", "Hospital Address : Islamabad", "Exp : 4yrs", "Mobile No : 6543210987", "575"},
            {"Doctor Name : Saima Khan", "Hospital Address : Karachi", "Exp : 8yrs", "Mobile No : 5432109876", "700"},
            {"Doctor Name : Saad Ahmed", "Hospital Address : Peshawar", "Exp : 5yrs", "Mobile No : 4321098765", "600"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Fatima Khan", "Hospital Address : Lahore", "Exp : 2yrs", "Mobile No : 3210987654", "450"},
            {"Doctor Name : Abdul Rehman", "Hospital Address : Rawalpindi", "Exp : 5yrs", "Mobile No : 2109876543", "600"},
            {"Doctor Name : Ayesha Ahmed", "Hospital Address : Karachi", "Exp : 3yrs", "Mobile No : 1098765432", "500"},
            {"Doctor Name : Hamza Ali", "Hospital Address : Peshawar", "Exp : 7yrs", "Mobile No : 0987654321", "725"},
            {"Doctor Name : Maryam Malik", "Hospital Address : Islamabad", "Exp : 4yrs", "Mobile No : 9876543210", "575"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : Nida Hasan", "Hospital Address : Islamabad", "Exp : 6yrs", "Mobile No : 8765432109", "650"},
            {"Doctor Name : Aliya Malik", "Hospital Address : Karachi", "Exp : 9yrs", "Mobile No : 7654321098", "800"},
            {"Doctor Name : Zain Ahmed", "Hospital Address : Lahore", "Exp : 5yrs", "Mobile No : 6543210987", "600"},
            {"Doctor Name : Asim Raza", "Hospital Address : Rawalpindi", "Exp : 3yrs", "Mobile No : 5432109876", "525"},
            {"Doctor Name : Uzair Khan", "Hospital Address : Peshawar", "Exp : 4yrs", "Mobile No : 4321098765", "575"}
    };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewODTitle);
        btn = findViewById(R.id.buttonBMCartCheckout);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0) doctor_details = doctor_details1;
        else if(title.compareTo("Dietician")==0) doctor_details = doctor_details2;
        else if(title.compareTo("Denstist")==0) doctor_details = doctor_details3;
        else if(title.compareTo("Surgeon")==0) doctor_details = doctor_details4;
        else doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++) {
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons. Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        //mapping
        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"}, //from
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}); //to
        ListView lst = findViewById(R.id.listViewBMCart);
        lst.setAdapter(sa);

//        passing details for book activity
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}