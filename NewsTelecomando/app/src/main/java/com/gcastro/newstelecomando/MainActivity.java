package com.gcastro.newstelecomando;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btnSegno1, btnSegno2, btnSegno3, btnSegno4;
    String testoLungo = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vehicula nibh et pharetra pharetra. Sed ac tortor mi. Donec a imperdiet turpis. Suspendisse aliquet mi ex, ornare lobortis magna fermentum non. Aliquam sagittis, nulla a imperdiet egestas, sem magna iaculis sem, in dignissim dui neque dictum elit. Etiam convallis, metus eleifend malesuada placerat, nunc tortor efficitur diam, nec sollicitudin augue quam vitae nisl. Morbi porttitor lorem in risus commodo convallis. Donec a nunc aliquet, commodo ex quis, blandit ex. Sed non sapien lectus. Donec gravida vel mauris id fermentum. Maecenas dignissim libero nec est tristique pulvinar. Proin elementum aliquam eros, a condimentum nunc hendrerit in. Duis nec dui consequat nunc elementum pharetra at eget enim.\n" +
            "\n" +
            "Nam ante ex, suscipit quis enim ut, viverra ultricies tortor. Aenean mattis velit et tellus vehicula vulputate. Nunc id ultricies tortor, nec volutpat sapien. Vivamus interdum nunc ex, sed imperdiet eros facilisis vitae. Mauris eu lorem condimentum nisl pulvinar fringilla non eget tortor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec varius congue velit quis feugiat.\n" +
            "\n" +
            "Aliquam suscipit magna urna. Duis nec arcu vitae orci tempus efficitur nec sed odio. Proin vel felis feugiat, consectetur nisi quis, tincidunt ligula. Aenean turpis nunc, mattis vitae neque sit amet, tempus consectetur lacus. Vestibulum bibendum sapien mi, ac condimentum est pulvinar sit amet. Duis condimentum nibh nec blandit venenatis. Suspendisse commodo mauris non diam sollicitudin venenatis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin sagittis tortor. Mauris odio ligula, auctor sed pellentesque in, ornare eget ipsum." +
            "\n" +
            "Aliquam suscipit magna urna. Duis nec arcu vitae orci tempus efficitur nec sed odio. Proin vel felis feugiat, consectetur nisi quis, tincidunt ligula. Aenean turpis nunc, mattis vitae neque sit amet, tempus consectetur lacus. Vestibulum bibendum sapien mi, ac condimentum est pulvinar sit amet. Duis condimentum nibh nec blandit venenatis. Suspendisse commodo mauris non diam sollicitudin venenatis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin sagittis tortor. Mauris odio ligula, auctor sed pellentesque in, ornare eget ipsum.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // https://it.lipsum.com/feed/html
        btnSegno1 = (ImageView)findViewById(R.id.btnSegno1);
        btnSegno2 = (ImageView)findViewById(R.id.btnSegno2);
        btnSegno3 = (ImageView)findViewById(R.id.btnSegno3);
        btnSegno4 = (ImageView)findViewById(R.id.btnSegno4);

        btnSegno1.setOnClickListener(this);
        btnSegno2.setOnClickListener(this);
        btnSegno3.setOnClickListener(this);
        btnSegno4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent destination = new Intent(MainActivity.this, DestinationActivity.class);
        Log.d("[OnClick]","Elemento selezionato:" + view.getId());

        switch (view.getId())
        {
            case R.id.btnSegno1:
                destination.putExtra("fldNomeSegno", "Acquario");
                destination.putExtra("fldSottoTitolo", "Oroscopo del giorno");
                destination.putExtra("fldTesto", testoLungo);
                break;

            case R.id.btnSegno2:
                destination.putExtra("fldNomeSegno", "Cancro");
                destination.putExtra("fldSottoTitolo", "Oroscopo del giorno");
                destination.putExtra("fldTesto", testoLungo);
                break;

            case R.id.btnSegno3:
                destination.putExtra("fldNomeSegno", "Gemelli");
                destination.putExtra("fldSottoTitolo", "Oroscopo del giorno");
                destination.putExtra("fldTesto", testoLungo);
                break;

            case R.id.btnSegno4:
                destination.putExtra("fldNomeSegno", "Leone");
                destination.putExtra("fldSottoTitolo", "Oroscopo del giorno");
                destination.putExtra("fldTesto", testoLungo);
                break;

        }

        startActivity(destination);

    }
}
