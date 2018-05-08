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

public class CardNumberFragment extends Fragment {

    private TextView cardName;
    private EditText cardDetails;

    public CardNumberFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_details_fragment, container, false);

        cardName = view.findViewById(R.id.cardName);
        cardName.setText("CARD NUMBER");
        cardDetails = view.findViewById(R.id.cardDetails);
        cardDetails.setInputType(InputType.TYPE_CLASS_PHONE);
        MaxLength maxLength = new MaxLength();
        maxLength.setMaxLength(cardDetails, 19);

        cardDetails.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    final char c = s.charAt(s.length() - 1);
                    if (space == c) {
                        s.delete(s.length() - 1, s.length());
                    }
                }
                // Insert char where needed.
                if (s.length() > 0 && (s.length() % 5) == 0) {
                    char c = s.charAt(s.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                        s.insert(s.length() - 1, String.valueOf(space));
                    }
                }
                checkout.tvCardNumber.setText(s);
            }
        });

        return view;
    }
}
