package com.example.athome.ui.arrangements;

import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.athome.R;
import com.example.athome.activities.HairdresserArrangementReviewActivity;
import com.example.athome.data.ArrangementData;
import com.example.athome.data.ArrangementsList;
import com.example.athome.databinding.FragmentArrangementsBinding;

import java.util.Map;

public class ArrangementsFragment extends Fragment {

    private FragmentArrangementsBinding binding;
    private LinearLayout cardContainerLayout;

    public void AddCardView(ArrangementData data, int id) {
        CardView cardView = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.professional_suggestion_card_layout, cardContainerLayout, false);
        TextView titleTextView = cardView.findViewById(R.id.card_title_text);
        titleTextView.setText(Html.fromHtml(data.getTitle()));
        TextView priceTextView = cardView.findViewById(R.id.card_price_text);
        priceTextView.setText(Html.fromHtml(String.format("%.2f",data.getCost()/100.f)));
        TextView typeTextView = cardView.findViewById(R.id.card_type_text);
        typeTextView.setText(Html.fromHtml(data.getTypeString()+" ("+data.getOption().name()+")"));
        cardContainerLayout.addView(cardView);
        ImageView imageView = cardView.findViewById(R.id.card_professional_thumbnail_image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ArrangementsFragment.this.getContext(), HairdresserArrangementReviewActivity.class);
                Bundle b = new Bundle();
                b.putInt("arrangementID", id);
                i.putExtras(b);
                startActivity(i);
            }
        });
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cardContainerLayout.removeAllViews();
                for(Map.Entry<Integer, ArrangementData> e: ArrangementsList.getEntrySet()) {
                    if (l == 3)
                        AddCardView(e.getValue(), e.getKey());
                    else if(e.getValue().getOption().ordinal()==l)
                        AddCardView(e.getValue(), e.getKey());
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