package com.febri.sevengymproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomExerciseFragment extends Fragment {

    private RecyclerView recycle;
    private customexerciseAdapter adapt;
    private ArrayList<customexercise> modelCustomExerciseArrayList;
    private ImageView tbh;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private void addData() {
        modelCustomExerciseArrayList = new ArrayList<>();
        modelCustomExerciseArrayList.add(new customexercise("Latihan Vendri", R.drawable.foto));
        modelCustomExerciseArrayList.add(new customexercise("Latihan Febri", R.drawable.foto));


    }

    public CustomExerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomExerciseFragment newInstance(String param1, String param2) {
        CustomExerciseFragment fragment = new CustomExerciseFragment();
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
        View view = inflater.inflate(R.layout.fragment_custom_exercise, container, false);
        setRecyclerView(view);

        tbh = (ImageView) view.findViewById(R.id.tambah);

        tbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TambahExerciseActivity.class);
                startActivity(intent);
            }
        });
        return view;



    }

    private void setRecyclerView(View view){
        addData();

        recycle = (RecyclerView) view.findViewById(R.id.recycle_custom_exercise);
        adapt = new customexerciseAdapter(modelCustomExerciseArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(gridLayoutManager);
        //recyclerView.setLayoutManager(layoutManager);
        recycle.setAdapter(adapt);
    }
}