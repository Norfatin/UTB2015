package com.android.margheritaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static com.android.volley.VolleyLog.TAG;

public class OrderFragment extends Fragment implements Response.Listener , Response.ErrorListener {

    View view;
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;
    private List<MenuDataModel> menuList  = new ArrayList<>();

    private String localhost = "http://192.168.100.6";
    private String url ="http://"+localhost+"/MOOSwebservice/menu/read.php";

    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order,container,false);
        context = getContext();
        mLayoutManager = new GridLayoutManager(context,2);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getMenuList();

        //FloatingActionButton fab_addtocart = (FloatingActionButton)view.findViewById(R.id.fab_addtocart);
        //fab_addtocart.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View view) {
                //viewCart();
                //Snackbar.make(view, "Menu added to cart", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            //}
        //});

        return view;
    }

    private void getMenuList(){
        Log.d("TEST","Getting Menu list");
        Controller.getInstance(context).makeNetworkCalls(Request.Method.GET,url,this,this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "Error: " + error.getMessage());
        Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        Log.d("TEST","Got the response" +response);
        if (response == null) {
            Toast.makeText(context, "Couldn't fetch the list! Please try again.", Toast.LENGTH_LONG).show();
            return;
        }

        List<MenuDataModel> items = new Gson().fromJson(response.toString(), new TypeToken<List<MenuDataModel>>(){}.getType());
        menuList.addAll(items);
        MyAdapter rcAdapter = new MyAdapter(menuList,context);
        mRecyclerView.setAdapter(rcAdapter);
    }
}