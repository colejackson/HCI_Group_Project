package com.example.coleman.hcigroupproject;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coleman.adapters.TodoAdapter;
import com.example.coleman.app_code.CatColors;
import com.example.coleman.app_code.Category;
import com.example.coleman.app_code.Todo;
import com.example.coleman.xml.DataParser;

import java.util.ArrayList;

public class Main extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
    private static final int MODE_ALL = 0;

    private DataParser parser;
    private ListView events;
    private AddTodo creater;
    private Context context;
    private TextView greeting;

    private boolean firsttime = true;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private int mode = MODE_ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parser = new DataParser(this.getApplicationContext());
        context = this.getApplicationContext();
        creater = new AddTodo(parser, this);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        greeting=(TextView) findViewById(R.id.greeting);
        events = (ListView) findViewById(R.id.events);

        update();

        if(firsttime)
        {
            parser.addCat("Default", CatColors.WHITE.id);
            firsttime = false;
        }

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                creater.showTodo();
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        parser.saveData();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number)
    {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void update()
    {

        TodoAdapter adapter=new TodoAdapter(this,parser.getData(),parser);

        if(parser.getData().length>0){
            greeting.setVisibility(View.GONE);
        }

        events.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public DataParser getParser()
    {
        return parser;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
