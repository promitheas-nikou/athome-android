package com.example.athome.ui.arrangements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.athome.databinding.FragmentArrangementsBinding;

public class ArrangementsFragment extends Fragment {

    private FragmentArrangementsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrangementsViewModel arrangementsViewModel =
                new ViewModelProvider(this).get(ArrangementsViewModel.class);

        binding = FragmentArrangementsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}