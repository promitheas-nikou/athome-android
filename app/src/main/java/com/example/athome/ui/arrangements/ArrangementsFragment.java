package com.example.athome.ui.arrangements;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.athome.R;
import com.example.athome.data.ArrangementData;
import com.example.athome.data.ProfessionalSuggestionData;
import com.example.athome.data.ProfessionalType;
import com.example.athome.databinding.FragmentArrangementsBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrangementsFragment extends Fragment {

    private FragmentArrangementsBinding binding;

    private LinearLayout cardContainerLayout;

    public ArrayList<ArrangementData> dataList;

    public void AddCardView(ArrangementData data) {
        CardView cardView = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.professional_suggestion_card_layout, cardContainerLayout, false);
        TextView titleTextView = cardView.findViewById(R.id.card_title_text);
        titleTextView.setText(Html.fromHtml(data.getTitle()));
        TextView priceTextView = cardView.findViewById(R.id.card_price_text);
        priceTextView.setText(Html.fromHtml(data.getCost()));
        TextView typeTextView = cardView.findViewById(R.id.card_type_text);
        typeTextView.setText(Html.fromHtml(data.getTypeString()+" ("+data.getOption().name()+")"));
        cardContainerLayout.addView(cardView);
        ImageView imageView = cardView.findViewById(R.id.card_professional_thumbnail_image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo));
    }

    final String[] spinnerOptions = {
            "PENDING","CANCELLED","COMPLETED","ANY"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrangementsViewModel arrangementsViewModel =
                new ViewModelProvider(this).get(ArrangementsViewModel.class);

        binding = FragmentArrangementsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Spinner spinner = root.findViewById(R.id.arrangement_type_selection_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.arrangements_lookup_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        cardContainerLayout =  root.findViewById(R.id.arrangements_card_container_linear_layout);
        dataList = new ArrayList<>();
        dataList.add(new ArrangementData("Μαρία Παπαδοπούλου", "42€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.PENDING,Arrays.asList()));
        dataList.add(new ArrangementData("Αναστασία Παπανικολάου", "32€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.CANCELLED, Arrays.asList()));
        dataList.add(new ArrangementData("Ελένη Δημητριάδου", "32€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED,Arrays.asList()));
        dataList.add(new ArrangementData("Αντωνία Καραπέτρου", "52€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.PENDING,Arrays.asList()));
        dataList.add(new ArrangementData("Ιωάννης Παπαδόπουλος", "262€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED,Arrays.asList()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cardContainerLayout.removeAllViews();
                for(ArrangementData data: dataList) {
                    if (l == 3)
                        AddCardView(data);
                    else if(data.getOption().ordinal()==l)
                        AddCardView(data);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}