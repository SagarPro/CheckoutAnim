package sagsaguz.checkoutanimlib.utils;

import android.text.InputFilter;
import android.widget.EditText;

public class MaxLength {

    public void setMaxLength(EditText editText, int length){
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(filterArray);
    }

}
