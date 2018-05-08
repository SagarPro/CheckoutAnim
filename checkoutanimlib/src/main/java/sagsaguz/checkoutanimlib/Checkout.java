package sagsaguz.checkoutanimlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sagsaguz.checkoutanimlib.adapter.ViewPagerAdapter;
import sagsaguz.checkoutanimlib.fragments.CardCVVFragment;
import sagsaguz.checkoutanimlib.fragments.CardExpiryFragment;
import sagsaguz.checkoutanimlib.fragments.CardHolderFragment;
import sagsaguz.checkoutanimlib.fragments.CardNumberFragment;

public class Checkout extends RelativeLayout {

    public TextView tvCardNumber, tvCardHolder, tvExpiry, tvCVV;
    private Button btnNext;
    public Button btnDone;
    private ViewPager viewPager;
    @SuppressLint("StaticFieldLeak")
    public static Checkout checkout;
    private FlipView flipView;
    private int flippedPos = 0;
    private View backView, frontView;

    private Context context;
    private View view;
    private int cardColor;
    private int buttonColor;

    public Checkout(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    public Checkout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs){

        checkout = this;

        final LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflate.inflate(R.layout.checkout_layout, this, true);

        tvCardNumber = view.findViewById(R.id.etCardNumber);
        tvCardHolder = view.findViewById(R.id.etCardHolder);
        tvExpiry = view.findViewById(R.id.etExpiry);
        tvCVV = view.findViewById(R.id.tvCVV);

        flipView = view.findViewById(R.id.flipView);

        viewPager = view.findViewById(R.id.viewPager);
        setViewPager(viewPager);

        btnNext = view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
            }
        });

        btnDone = view.findViewById(R.id.btnDone);

        backView = view.findViewById(R.id.backView);
        frontView = view.findViewById(R.id.frontView);

        if (attrs != null) {
            // Attribute initialization
            final TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.checkout_card, 0, 0);
            try {
                cardColor = attrArray.getColor(R.styleable.checkout_card_cardColor, getResources().getColor(R.color.blue));
                buttonColor = attrArray.getColor(R.styleable.checkout_card_buttonColor, getResources().getColor(R.color.blue));
            } finally {
                attrArray.recycle();
            }
        }

        cardBGColor();
        buttonBGColor();

    }

    private void setViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context);
        viewPagerAdapter.addFragment(new CardNumberFragment());
        viewPagerAdapter.addFragment(new CardHolderFragment());
        viewPagerAdapter.addFragment(new CardExpiryFragment());
        viewPagerAdapter.addFragment(new CardCVVFragment());
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2: if (flippedPos == 3){
                        flipView.flipTheView();
                    }
                        break;
                    case 3: flipView.flipTheView();
                        break;
                }
                flippedPos = position;
                if (position == 3){
                    btnNext.setVisibility(GONE);
                    btnDone.setVisibility(VISIBLE);
                } else {
                    btnNext.setVisibility(VISIBLE);
                    btnDone.setVisibility(GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void cardBGColor(){
        backView.setBackgroundColor(cardColor);
        frontView.setBackgroundColor(cardColor);
    }

    private void buttonBGColor(){
        btnNext.setBackgroundColor(buttonColor);
        btnDone.setBackgroundColor(buttonColor);
    }

    public void setCardColor(int color){
        this.cardColor = getResources().getColor(color);
        cardBGColor();
    }

    public void setButtonColor(int color){
        this.buttonColor = getResources().getColor(color);
        buttonBGColor();
    }

    public String getCardNumber(){
        return tvCardNumber.getText().toString();
    }

    public String getCardHolderName(){
        return tvCardHolder.getText().toString();
    }

    public String getExpiryDate(){
        return tvExpiry.getText().toString();
    }

    public String getCVV(){
        return tvCVV.getText().toString();
    }

}
