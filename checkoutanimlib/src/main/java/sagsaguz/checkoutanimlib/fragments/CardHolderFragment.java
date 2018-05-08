package sagsaguz.checkoutanimlib.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import sagsaguz.checkoutanimlib.R;

import static sagsaguz.checkoutanimlib.Checkout.checkout;

public class CardHolderFragment extends Fragment {

    private TextView cardName;
    private EditText cardDetails;

    public CardHolderFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_details_fragment, container, false);

        cardName = view.findViewById(R.id.cardName);
        cardName.setText("CARD HOLDER'S NAME");
        cardDetails = view.findViewById(R.id.cardDetails);
        cardDetails.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        cardDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if(!string.equals(string.toUpperCase()))
                {
                    string = string.toUpperCase();
                    //cardDetails.setText(string);
                }
                checkout.tvCardHolder.setText(string);
            }
        });

        return view;
    }
}
