package com.example.athome.ui.booking;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.athome.R;
import com.example.athome.data.ProfessionalSuggestionData;
import com.example.athome.data.ProfessionalType;
import com.example.athome.databinding.FragmentArrangementsBinding;
import com.example.athome.databinding.FragmentBookingBinding;
import com.example.athome.databinding.FragmentOffersBinding;
import com.example.athome.ui.arrangements.ArrangementsViewModel;

public class BookingFragment extends Fragment {

    private FragmentBookingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrangementsViewModel arrangementsViewModel =
                new ViewModelProvider(this).get(ArrangementsViewModel.class);

        binding = FragmentBookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}