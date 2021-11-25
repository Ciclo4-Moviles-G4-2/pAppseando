package com.ciclo4_moviles_g4_2.pappseando;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotsLayout;
    private Button btnBack, btnNext;

    private String[] content= {"Hola1", "Hola2", "Hola3", "Hola4"};
    private String[] title= {"titulo1", "titulo2", "titulo3", "titulo4"};
    private int[] image={R.drawable.inicio,R.drawable.prueba,R.drawable.prueba,R.drawable.prueba};
    private int[] colorBackground, colorDot;
    private TextView[] dots;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorDot= getResources().getIntArray(R.array.array_dots);
        colorBackground= getResources().getIntArray(R.array.array_background);
        viewPager= findViewById(R.id.viewPager);
        btnBack= findViewById(R.id.btnBack);
        btnNext= findViewById(R.id.btnNext);
        dotsLayout= findViewById(R.id.layoutDots);
        addDot(0);
        loadViewPager();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewPager.getCurrentItem()==title.length-1){
                    finish();
                }else{
                    int back=getItem(-1);
                    viewPager.setCurrentItem(back);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next=getItem(+1);
                if(next<title.length){
                    viewPager.setCurrentItem(next);
                }else{
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                }
            }
        });


    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

    private  void addDot(int currentPage){
        dots= new TextView[title.length];

        dotsLayout.removeAllViews();

        for(int i =0; i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            if(i==currentPage)
                dots[i].setTextColor(colorDot[currentPage]);
            else
                dots[i].setTextColor(Color.LTGRAY);
            dotsLayout.addView(dots[i]);
        }

    }

    private void loadViewPager() {
        adapter= new MyViewPagerAdapter(getSupportFragmentManager());
        for(int i=0;i<title.length;i++){
            adapter.addFragment(newInstance(title[i],content[i],image[i],colorBackground[i]));
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pagerListener);
    }

    private SliderFragment newInstance(String title, String content, int image,int color) {
        Bundle bundle= new Bundle();
        bundle.putString("title",title);
        bundle.putString("content",content);
        bundle.putInt("image",image);
        bundle.putInt("color",color);

        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    ViewPager.OnPageChangeListener pagerListener=new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            addDot(position);

            if(position==title.length-1){
                btnNext.setText("Finalizar");
                btnBack.setText("Salir");

            }else{
                btnNext.setText("Siguiente");
                btnBack.setText("Atras");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }

    };



}