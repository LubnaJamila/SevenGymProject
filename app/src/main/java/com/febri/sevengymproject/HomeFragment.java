package com.febri.sevengymproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private exerciseAdapter adapter;
    private ArrayList<exercise> modelExerciseArrayList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private void addData() {
        modelExerciseArrayList = new ArrayList<>();
        modelExerciseArrayList.add(new exercise("Exercise 1", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 2", 0));
        modelExerciseArrayList.add(new exercise("Exercise 3", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 1", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 2", 0));
        modelExerciseArrayList.add(new exercise("Exercise 3", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 1", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 2", 0));
        modelExerciseArrayList.add(new exercise("Exercise 3", R.drawable.ic_done));
        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));

    }

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
        setRecyclerView(view);
        return view;
    }

    private void setRecyclerView(View view){
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_exercise);
        adapter = new exerciseAdapter(modelExerciseArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}