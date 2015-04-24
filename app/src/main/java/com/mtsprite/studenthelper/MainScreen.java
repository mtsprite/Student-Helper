package com.mtsprite.studenthelper;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class MainScreen extends AppCompatActivity {

    private Toolbar toolbar;
    String TITLES[] = {"All Classes", "Current Classes", "Assignments", "Schedule"};
    int ICONS[] = {R.drawable.ic_all_classes, R.drawable.ic_current_class, R.drawable.ic_assignments, R.drawable.ic_sched};

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Fragment fragment = new AllClassesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();


        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle("All Classes");
        setSupportActionBar(toolbar);



        mRecyclerView =(RecyclerView) findViewById(R.id.drawer_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new DrawerAdapter(TITLES, ICONS);

        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        };

        // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        //create Gesture Detector object to check for SingleTapUp touch events
        final GestureDetector mGestureDetector = new GestureDetector(MainScreen.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        //add and onClickListener and check the touch to make sure its of the singleTapUp type
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());


                //return true of the event is of type singleTapUp
                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){

                    switch(recyclerView.getChildPosition(child)){
                        case 0: {
                            Fragment fragment = new AllClassesFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();
                            toolbar.setTitle("All Classes");
                            break;
                        }
                        case 1:{
                            Fragment fragment = new CurrentClassesFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();
                            toolbar.setTitle("Current Classes");
                            break;
                        }
                        case 2:{
                            Fragment fragment = new AssignmentsFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();
                            toolbar.setTitle("Assignments");
                            break;
                        }
                        case 3:{
                            Fragment fragment = new ScheduleFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();
                            toolbar.setTitle("Schedule");
                        }
                    }

                    Drawer.closeDrawers();
                    return true;

                }


                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
