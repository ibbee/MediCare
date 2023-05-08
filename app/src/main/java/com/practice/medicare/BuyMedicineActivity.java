package com.practice.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Feronia -XT Tablet", "", "", "", "130"},
                    {"Levoflox Tablet", "", "", "", "210"},
                    {"Cetirizine Syrup", "", "", "", "50"},
                    {"Esomeprazole Capsule", "", "", "", "180"},
                    {"Paracetamol Suspension", "", "", "", "75"},
                    {"Alprazolam Tablet", "", "", "", "90"},
                    {"Metformin Tablet", "", "", "", "120"},
                    {"Montelukast Tablet", "", "", "", "200"},
                    {"Rosuvastatin Tablet", "", "", "", "220"},
                    {"Azithromycin Tablet", "", "", "", "150"}
            };
    private String[] package_details = {
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron\n",
            "Treats bacterial infections such as pneumonia, bronchitis, and sinusitis\n",
            "Relieves allergy symptoms such as runny nose, watery eyes, and sneezing\n",
            "Reduces the amount of acid produced in the stomach to treat conditions such as heartburn and GERD\n",
            "Relieves pain and reduces fever in conditions such as headache, toothache, and colds\n",
            "Treats anxiety disorders and panic disorder with or without agoraphobia\n",
            "Controls high blood sugar levels in people with type 2 diabetes\n",
            "Treats asthma and allergic rhinitis by blocking the action of leukotrienes\n",
            "Lowers cholesterol and triglycerides in the blood to prevent heart disease and stroke\n",
            "Treats bacterial infections such as respiratory infections and skin infections\n"
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBMCart);
        btnBack = findViewById(R.id.buttonBMCartCheckout);
        btnGoToCart = findViewById(R.id.buttonBMCartBack);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        //mapping
        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"}, //from
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}); //to
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }
        });
    }
}