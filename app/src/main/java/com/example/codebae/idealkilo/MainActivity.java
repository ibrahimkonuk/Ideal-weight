package com.example.codebae.idealkilo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView durum_tv, ideal_tv, kilo_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean erkekmi = true;
    private double boy = 0.0;
    private int  kilo = 30;
    TabHost tabHost;
    private ImageView imageView1;
    private Button buton_hesapla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.simpleWebView);
        webView.setPadding(0, 0, 0, 0);
        webView.setInitialScale(getScale());
        webView.loadUrl("file:///android_asset/htmls/text_ideal_k.html");

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("İdeal Kilo");
        spec.setContent(R.id.tab1);
        spec.setIndicator("İdeal Kilo");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("İdeal Kilo Nedir");
        spec.setContent(R.id.tab2);
        spec.setIndicator("İdeal Kilo Nedir");
        host.addTab(spec);

        editText = findViewById(R.id.editText);
        durum_tv = findViewById(R.id.durum_tv);
        durum_tv.setBackgroundResource(R.color.beyaz);
        ideal_tv = findViewById(R.id.ideal_tv);
        kilo_tv = findViewById(R.id.kilo_tv);
        radioGroup = findViewById(R.id.radioGrup);
        seekBar = findViewById(R.id.seekBar);
        imageView1 = findViewById(R.id.imageView1);
        buton_hesapla = findViewById(R.id.buton_hesapla);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    boy = Double.parseDouble(s.toString()) / 100.0;
                }
                catch (NumberFormatException e) {
                    boy = 0.0;
                }

                /* guncelle(); */
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                kilo = 30 + progress;
                kilo_tv.setText(String.valueOf(kilo) + "  kg");
                /* guncelle(); */

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (checkedId == R.id.bay)
                    erkekmi = true;
                else if (checkedId == R.id.bayan)
                    erkekmi = false;
                /* guncelle(); */
            }
        });

        buton_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                guncelle();

            }
        });


    }


    private void guncelle() {


        int ideal_kiloBay = (int) (50 + 2.3 * (boy * 100 * 0.4 - 60));
        int ideal_kiloBayan = (int) (45.5 + 2.3 * (boy * 100 * 0.4 - 60));
        double vki = kilo / (boy * boy);

        if (erkekmi) {
            ideal_tv.setText(String.valueOf(ideal_kiloBay));
            if (vki <= 20.7) {
                durum_tv.setText(R.string.zayif);
                imageView1.setImageResource(R.drawable.cok_zayif);
            } else if (20.7 < vki && vki <= 26.4) {
                durum_tv.setText(R.string.ideal);
                imageView1.setImageResource(R.drawable.normal);
            } else if (26.4 < vki && vki <= 27.8) {
                durum_tv.setText(R.string.normalden_fazla);
                imageView1.setImageResource(R.drawable.normalden_fazla);
            } else if (27.8 < vki && vki <= 31.1) {
                durum_tv.setText(R.string.fazla_kilolu);
                imageView1.setImageResource(R.drawable.fazla_kilo);
            } else if (31.1 < vki && vki <= 34.9) {
                durum_tv.setText(R.string.obez);
                imageView1.setImageResource(R.drawable.obez);

            } else {

                durum_tv.setText(R.string.doktora);
                imageView1.setImageResource(R.drawable.tedavi);
            }

        } else
            ideal_tv.setText(String.valueOf(ideal_kiloBayan));

        if (vki <= 20.7) {

            durum_tv.setText(R.string.zayif);
            imageView1.setImageResource(R.drawable.cok_zayif);
        } else if (20.7 < vki && vki <= 26.4) {

            durum_tv.setText(R.string.ideal);
            imageView1.setImageResource(R.drawable.normal);
        } else if (26.4 < vki && vki <= 27.8) {
            durum_tv.setText(R.string.normalden_fazla);
            imageView1.setImageResource(R.drawable.normalden_fazla);
        } else if (27.8 < vki && vki <= 31.1) {
            durum_tv.setText(R.string.fazla_kilolu);
            imageView1.setImageResource(R.drawable.fazla_kilo);
        } else if (31.1 < vki && vki <= 34.9) {

            durum_tv.setText(R.string.obez);
            imageView1.setImageResource(R.drawable.obez);

        } else {

            durum_tv.setText(R.string.doktora);
            imageView1.setImageResource(R.drawable.tedavi);
        }
    }

    private int getScale() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / 360;
        val = val * 100d;
        return val.intValue();
    }

}
