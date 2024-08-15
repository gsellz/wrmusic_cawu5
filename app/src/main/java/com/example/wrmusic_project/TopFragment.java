package com.example.wrmusic_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopFragment extends Fragment {

    private static final String ARG_RANKS = "ranks";
    private static final String ARG_TITLES = "titles";
    private static final String ARG_ARTISTS = "artists";
    private static final String ARG_DESCRIPTIONS = "descriptions";
    private static final String ARG_GENRES = "genres";
    private static final String ARG_PRICES = "prices";
    private static final String ARG_IMAGES = "images";
    private static final String ARG_TRACKS = "tracks";

    public static TopFragment newInstance(String[] ranks, String[] titles, String[] artists, String[] descriptions, String[] genres, String[] prices, int[] images, int[] tracks) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_RANKS, ranks);
        args.putStringArray(ARG_TITLES, titles);
        args.putStringArray(ARG_ARTISTS, artists);
        args.putStringArray(ARG_DESCRIPTIONS, descriptions);
        args.putStringArray(ARG_GENRES, genres);
        args.putStringArray(ARG_PRICES, prices);
        args.putIntArray(ARG_IMAGES, images);
        args.putIntArray(ARG_TRACKS, tracks);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        RecyclerView recyclerViewTop = view.findViewById(R.id.recyclerViewTop);
        recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            String[] ranks = getArguments().getStringArray(ARG_RANKS);
            String[] titles = getArguments().getStringArray(ARG_TITLES);
            String[] artists = getArguments().getStringArray(ARG_ARTISTS);
            String[] descriptions = getArguments().getStringArray(ARG_DESCRIPTIONS);
            String[] genres = getArguments().getStringArray(ARG_GENRES);
            String[] prices = getArguments().getStringArray(ARG_PRICES);
            int[] images = getArguments().getIntArray(ARG_IMAGES);
            int[] tracks = getArguments().getIntArray(ARG_TRACKS);
            recyclerViewTop.setAdapter(new TopItemsAdapter(ranks, titles, artists, descriptions, genres, prices, images, tracks));
        }

        return view;
    }
}


