package com.mtsprite.studenthelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mtsprite on 4/22/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder>{

    // String Array to store the passed titles Value from MainActivity.java
    private String mNavTitles[];

    // Int Array to store the passed icons resource value from MainActivity.java
    private int mIcons[];

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView,int ViewType){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.drawer_row_text);
            imageView = (ImageView) itemView.findViewById(R.id.drawer_row_icon);

        }
    }

    DrawerAdapter(String[] Titles, int[] Icons){
        mNavTitles = Titles;
        mIcons = Icons;
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflating the layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_row,parent,false);

        //Creating ViewHolder and passing the object of type view
        ViewHolder vhItem = new ViewHolder(v,viewType);

        // Returning the created object
        return vhItem;
    }

    //Next we override a method which is called when the item in a row is
    // needed to be displayed, here the int pos
    // Tells us item at which position is being constructed to be displayed
    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder viewHolder, int pos) {

        // Setting the Text with the array of our Titles
        viewHolder.textView.setText(mNavTitles[pos]);

        // Setting the image with array of our icons
        viewHolder.imageView.setImageResource(mIcons[pos]);

    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        // the number of items in the list will be the number of titles.
        return (mNavTitles.length);
    }
}
