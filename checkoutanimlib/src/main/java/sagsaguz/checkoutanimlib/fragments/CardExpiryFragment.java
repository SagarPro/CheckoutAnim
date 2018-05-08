package sagsaguz.checkoutanimlib.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import sagsaguz.checkoutanimlib.R;
import sagsaguz.checkoutanimlib.utils.MaxLength;

import static sagsaguz.checkoutanimlib.Checkout.checkout;

public class CardExpiryFragment extends Fragment {

    private TextView cardName;
    private EditText cardDetails;

    public CardExpiryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_details_fragment, container, false);

        cardName = view.findViewById(R.id.cardName);
        cardName.setText("EXPIRY DATE");
        cardDetails = view.findViewById(R.id.cardDetails);
        cardDetails.setInputType(InputType.TYPE_CLASS_PHONE);
        MaxLength maxLength = new MaxLength();
        maxLength.setMaxLength(cardDetails, 5);

        cardDetails.addTextChangedListener(new TextWatcher() {
            private static final char slash = '/';

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && (s.length() % 3) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (slash == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                if (s.length() > 0 && (s.length() % 3) == 0) {
                    char c = s.charAt(s.length() - 1);
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(slash)).length <= 1) {
                        s.insert(s.length() - 1, String.valueOf(slash));
                    }
                }
                checkout.tvExpiry.setText(s);
            }
        });

        return view;
    }
}
