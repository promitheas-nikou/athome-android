package com.example.athome.ui.offers;

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
import com.example.athome.databinding.FragmentOffersBinding;

public class OffersFragment extends Fragment {

    private FragmentOffersBinding binding;
    private LinearLayout cardContainerLayout;

    public void AddCardView(ProfessionalSuggestionData data) {
        CardView cardView = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.professional_suggestion_card_layout, cardContainerLayout, false);
        TextView titleTextView = cardView.findViewById(R.id.card_title_text);
        titleTextView.setText(Html.fromHtml(data.getTitle()));
        TextView priceTextView = cardView.findViewById(R.id.card_price_text);
        priceTextView.setText(Html.fromHtml(data.getCost()));
        TextView typeTextView = cardView.findViewById(R.id.card_type_text);
        typeTextView.setText(Html.fromHtml(data.getTypeString()));
        cardContainerLayout.addView(cardView);
        ImageView imageView = cardView.findViewById(R.id.card_professional_thumbnail_image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OffersViewModel offersViewModel =
                new ViewModelProvider(this).get(OffersViewModel.class);

        binding = FragmentOffersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cardContainerLayout = binding.offersCardContainerLinearLayout;
        AddCardView(new ProfessionalSuggestionData("TITLE123","NAME","COST", ProfessionalType.HAIRDRESSER));
        AddCardView(new ProfessionalSuggestionData("TITLE456","NAME","COST", ProfessionalType.HAIRDRESSER));
        AddCardView(new ProfessionalSuggestionData("<b>TITLE789</b>","NAME","COST", ProfessionalType.HAIRDRESSER));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}