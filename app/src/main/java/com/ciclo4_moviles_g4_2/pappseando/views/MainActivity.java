package com.ciclo4_moviles_g4_2.pappseando.views;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ciclo4_moviles_g4_2.pappseando.R;
import com.ciclo4_moviles_g4_2.pappseando.adapters.MyViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotsLayout;
    private Button btnBack, btnNext;

    private String[] content= {"Pappseando es tu mejor opción para guardar tus lugares favoritos", "Pappseando utiliza el GPS de tu teléfono para darte ña ubicación precisa donde te encuentres", "Sobre el mapa y con un solo toque podrás agregar tu lugar favorito", "Agrega, elimina o comparte tus lugares favoritos"};
    private String[] title= {"¡Bienvenido!", "Geolocalización", "¡Muy fácil!", "Cuando quieras"};
    private int[] image={R.drawable.pin,R.drawable.prueba,R.drawable.prueba,R.drawable.prueba};
    private int[] colorBackground, colorDot;
    private TextView[] dots;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
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
                btnNext.setText("INICIAR");
                btnNext.setVisibility(View.VISIBLE);


            }else{
                btnBack.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }

    };



}