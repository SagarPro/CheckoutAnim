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
import sagsaguz.checkoutanimlib.utils.MaxLength;

import static sagsaguz.checkoutanimlib.Checkout.checkout;

public class CardCVVFragment extends Fragment {

    private TextView cardName;
    private EditText cardDetails;

    public CardCVVFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_details_fragment, container, false);

        cardName = view.findViewById(R.id.cardName);
        cardName.setText("CVV/CVV NUMBER");
        cardDetails = view.findViewById(R.id.cardDetails);
        cardDetails.setInputType(InputType.TYPE_CLASS_NUMBER);
        MaxLength maxLength = new MaxLength();
        maxLength.setMaxLength(cardDetails, 3);

        cardDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                checkout.tvCVV.setText(s);
            }
        });

        return view;
    }
}
