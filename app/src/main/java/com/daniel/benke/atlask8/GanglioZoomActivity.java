package com.daniel.benke.atlask8;

/**
 * Created by Andrea on 06/02/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;


public class GanglioZoomActivity extends AppCompatActivity  {
    private FloatingActionButton fabz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gangliozoom);




        setTitle("Gânglio Nervoso");
        fabz = (FloatingActionButton) this.findViewById(R.id.fabzoom);
        this.findViewById(R.id.tabClean).setVisibility(View.VISIBLE);
        final HorizontalScrollView hs = (HorizontalScrollView) findViewById(R.id.horizontal);
        TabHost host = (TabHost) findViewById(R.id.tabHost);
        String current = host.getCurrentTabTag();

        if (current == "Zoom") {
            findViewById(R.id.fabzoom).setVisibility(View.VISIBLE);
        } else findViewById(R.id.fabzoom).setVisibility(View.INVISIBLE);

        fabz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabHost host = (TabHost) findViewById(R.id.tabHost);
                String current = host.getCurrentTabTag();



                if (current=="Zoom") {

                    Intent nextScreen = new Intent(getApplicationContext(), GanglioZoomActivity.class);
                    startActivity(nextScreen);}

            }
        });

        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");

        //Tab 2
        spec = host.newTabSpec("neuronio");
        spec.setContent(R.id.tab2);
        spec.setIndicator("neurônio");
        host.addTab(spec);

//Tab 2
        spec = host.newTabSpec("nucleo");
        spec.setContent(R.id.tab3);
        spec.setIndicator("núcleo");
        host.addTab(spec);

//Tab 2
        spec = host.newTabSpec("nucleolo");
        spec.setContent(R.id.tab4);
        spec.setIndicator("nucléolo");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("celulassatelites");
        spec.setContent(R.id.tab5);
        spec.setIndicator("células satélites");
        host.addTab(spec);


        ViewTreeObserver vto = hs.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hs.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        hs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ScrollValue", Integer.toString(hs.getScrollX()));

                if (hs.getScrollX() == 0) {
                    findViewById(R.id.previous).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.previous).setVisibility(View.VISIBLE);
                }

                if (hs.getScrollX() >= hs.getChildAt(0).getMeasuredWidth() - getWindowManager().getDefaultDisplay().getWidth()) {
                    findViewById(R.id.next).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.next).setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                findViewById(R.id.tabClean).setVisibility(View.INVISIBLE);
                TabHost host = (TabHost) findViewById(R.id.tabHost);
                String current = host.getCurrentTabTag();

                WexternalActivity log = new WexternalActivity();
                log.Write(getApplicationContext(),"ganglioz"+current+", ");

                if (current=="Zoom") {
                    findViewById(R.id.fabzoom).setVisibility(View.VISIBLE);
                }
                else findViewById(R.id.fabzoom).setVisibility(View.INVISIBLE);

//Wext2Activity.writelog("oioi",Wext2Activity.);




            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_laminas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.laminas_action_settings:
                // Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT).show();
                Intent nextScreen = new Intent(getApplicationContext(), GanglioTextoActivity.class);
                startActivity(nextScreen);
                break;
            default:
                break;
        }

        return true;
    }




}