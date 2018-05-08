package sagsaguz.checkoutanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sagsaguz.checkoutanimlib.Checkout;


public class MainActivity extends AppCompatActivity {

    Checkout checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkout = findViewById(R.id.cOut);

        checkout.setCardColor(R.color.black);
        checkout.setButtonColor(R.color.blue);

        checkout.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToPay();
            }
        });

    }

    private void proceedToPay(){
        Toast.makeText(MainActivity.this,
                checkout.getCardNumber()+"\n"+
                        checkout.getCardHolderName()+"\n"+
                        checkout.getExpiryDate()+"\n"+
                        checkout.getCVV()
                , Toast.LENGTH_LONG).show();
    }

}
