package com.android.margheritaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuDataModel> mDataset;
    private ImageLoader mImageLoader;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView menuId;
        public TextView menuName;
        public TextView menuPrice;
        public NetworkImageView image;
        public TextView menuDescription;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View v) {
            super(v);
            menuId = v.findViewById(R.id.menu_id);
            menuName = v.findViewById(R.id.menu_name);
            menuPrice = v.findViewById(R.id.menu_price);
            menuDescription = v.findViewById(R.id.menu_description);
            image = v.findViewById(R.id.imgAvatar);
            image.setDefaultImageResId(R.mipmap.ic_launcher);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //set new page here
                    //showSingleMenu();
                    Toast.makeText(v.getContext(),"Position: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /** Called when the user taps click the menu card*/
    public  void showSingleMenu() {
        // show single menu page
        Intent mIntent = new Intent(mContext, SingleMenuFragment.class);
        //mIntent.putExtra("Menu Id", (mDataset.get(position)).getMenuId());
        //mIntent.putExtra("Image", (mDataset.get(position)).getImage());
        //mIntent.putExtra("Name", (mDataset.get(position)).getName());
        //mIntent.putExtra("Price", (mDataset.get(position)).getPrice());
        //mIntent.putExtra("Description",(mDataset.get(position)).getDescription());

        mContext.startActivity(mIntent);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<MenuDataModel> myDataset, Context mContext) {
        Log.d("TEST",myDataset.get(0).getName());
        mDataset = myDataset;
        mImageLoader = MySingleton.getInstance(mContext).getImageLoader();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("TEST","Printing Names onBindView Holder"+mDataset.get(position).getName());
        holder.menuId.setText(mDataset.get(position).getMenu_id());
        holder.menuName.setText(mDataset.get(position).getName());
        holder.menuPrice.setText(mDataset.get(position).getPrice());
        holder.menuDescription.setText(mDataset.get(position).getDescription());
        holder.image.setImageUrl(mDataset.get(position).getImage(),mImageLoader);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}