package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnfragment1, btnfragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnfragment1 = findViewById(R.id.btn_fragment1);
        btnfragment2 = findViewById(R.id.btn_fragment2);

        btnfragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();

                //fragment jika dibuat dengan replace
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new HomeFragment());
                ft.commit();

                //fragment jika dibuat dengan add
                //fm.beginTransaction()
                       // .add(R.id.container, new HomeFragment())
                        //.commit();
            }
        });

        btnfragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();

                //fragment jika dibuat dengan replace (tdk ditumpuk tp mengganti tempat)
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new ChatFragment());
                ft.commit();

                //fragment jika dibuat dengan add (akan ditumpuk)
               // fm.beginTransaction()
                       // .add(R.id.container, new ChatFragment())
                        //.commit();
            }
        });
    }
}