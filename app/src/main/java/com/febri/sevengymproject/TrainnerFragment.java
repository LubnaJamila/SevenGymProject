package com.febri.sevengymproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Use the {@link TrainnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainnerFragment extends Fragment {

    private RecyclerView recycle;
    private iklanAdapter adapt;
    private TrainnerAdapter adapter;
    private ArrayList<iklan> modelIklanTrain;
    private ArrayList<trainner> trainners;
    private RequestQueue requestQueue;

    LinearLayoutManager layoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private void addData() {
        modelIklanTrain = new ArrayList<>();
        modelIklanTrain.add(new iklan("Trainner Budi", R.drawable.iklan));
        modelIklanTrain.add(new iklan("Trainner Adi", R.drawable.iklan));


    }

//    private void addDatabegin() {
//        trainners = new ArrayList<>();
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//
//
//
//    }
//
//    private void addDatamedium() {
//        trainners = new ArrayList<>();
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//
//    }
//    private void addDataexpert() {
//        trainners = new ArrayList<>();
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//        trainners.add(new trainner("budi", "50000",R.drawable.train));
//
//    }

    public TrainnerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainnerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainnerFragment newInstance(String param1, String param2) {
        TrainnerFragment fragment = new TrainnerFragment();
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
        View view = inflater.inflate(R.layout.fragment_trainner, container, false);
        setRecyclerView(view);
        //setRecycleBegin(view);
//        setRecycleMedium(view);
//        setRecycleExpert(view);

        trainners = new ArrayList<>();
        if(getContext() != null){
            requestQueue = Volley.newRequestQueue(getContext());
            //parseJSON();
            setRecycleBegin(view);
        }else {
            Toast.makeText(getContext(), "Tidak Ada Konteks", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void parseJSON(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Api.urlTrainner, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String profil = hit.getString("profil_trainner");
                        String harga = hit.getString("harga_trainner");
                        String nama_lengkap = hit.getString("nama_lengkap");
                        String tanggal = hit.getString("tanggal_lahir");
                        String jenis_kelamin = hit.getString("jenis_kelamin");
                        String nohp = hit.getString("nohp");
                        String deskripsi = hit.getString("deskripsi_pelatih");
//                        String tb = hit.getString("tb");
//                        String bb = hit.getString("bb");

                        trainners.add(new trainner(tanggal, deskripsi, jenis_kelamin, profil, nama_lengkap, nohp, harga));
                    }
                    adapter = new TrainnerAdapter(getContext(), trainners);
                    recycle.setAdapter(adapter);
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
        requestQueue.add(request);
    }

    private void setRecyclerView(View view){
        addData();



        recycle = (RecyclerView) view.findViewById(R.id.recycle_iklan);
        adapt = new iklanAdapter(modelIklanTrain);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapt);
    }
    private void setRecycleBegin(View view){
        //addDatabegin();
        parseJSON();

        recycle = (RecyclerView) view.findViewById(R.id.recycle_trainnerbegin);
        adapter = new TrainnerAdapter(getContext(), trainners);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(adapter);
    }

//    private void setRecycleMedium(View view){
//        //addDatamedium();
//
//
//        recycle = (RecyclerView) view.findViewById(R.id.recycle_trainnermedium);
//        adapter = new TrainnerAdapter(getContext(), trainners);
//        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recycle.setLayoutManager(layoutManager);
//        recycle.setAdapter(adapter);
//    }
//
//    private void setRecycleExpert(View view){
//        //addDataexpert();
//
//        recycle = (RecyclerView) view.findViewById(R.id.recycle_trainnerexpert);
//        adapter = new TrainnerAdapter(getContext(), trainners);
//        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recycle.setLayoutManager(layoutManager);
//        recycle.setAdapter(adapter);
//    }
}