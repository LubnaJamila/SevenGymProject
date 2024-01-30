package com.febri.sevengymproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private exerciseAdapter adapter;
    private ArrayList<exercise> Exercise;
    private RequestQueue queue;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private void addData() {
//        Exercise = new ArrayList<>();
//        Exercise.add(new exercise("Beginner"));
//        Exercise.add(new exercise("Medium"));
//        Exercise.add(new exercise("Expert"));
////        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));
////        modelExerciseArrayList.add(new exercise("Exercise 1", R.drawable.ic_done));
////        modelExerciseArrayList.add(new exercise("Exercise 2", 0));
////        modelExerciseArrayList.add(new exercise("Exercise 3", R.drawable.ic_done));
////        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));
////        modelExerciseArrayList.add(new exercise("Exercise 1", R.drawable.ic_done));
////        modelExerciseArrayList.add(new exercise("Exercise 2", 0));
////        modelExerciseArrayList.add(new exercise("Exercise 3", R.drawable.ic_done));
////        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));
//
//    }

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //setRecyclerView(view);
        Exercise = new ArrayList<>();
        if(getContext() != null){
            queue = Volley.newRequestQueue(getContext());
            //parseJSON();
            setRecyclerView(view);
        }else {
            Toast.makeText(getContext(), "Tidak Ada Konteks", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void parseJSON(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Api.urlExercise, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String exercises = hit.getString("nama_paket");
                        String id = hit.getString("id_paket");
                        //String gambar = hit.getString("gambar");

                        Exercise.add(new exercise(id, exercises));
                    }
                    adapter = new exerciseAdapter(getContext(), Exercise);
                    recyclerView.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    private void setRecyclerView(View view){
        parseJSON();
       //addData();

        recyclerView = view.findViewById(R.id.recycle_exercise);
        adapter = new exerciseAdapter(getContext(), Exercise);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}