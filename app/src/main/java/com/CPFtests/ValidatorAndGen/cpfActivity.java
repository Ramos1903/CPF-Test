package com.CPFtests.ValidatorAndGen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import com.CPFtests.perfumaria.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Random;

import static android.widget.Toast.makeText;

public class cpfActivity extends AppCompatActivity {

    private EditText digito1;
    private EditText digito2;
    private EditText digito3;
    private EditText digito4;
    private EditText digito5;
    private EditText digito6;
    private EditText digito7;
    private EditText digito8;
    private EditText digito9;
    private EditText[] digitos;

    private TextView digito10;
    private TextView digito11;

    private Button btGerar;
    private Button btClear;

    private View btn0;
    private View btn1;
    private View btn2;
    private View btn3;
    private View btn4;
    private View btn5;
    private View btn6;
    private View btn7;
    private View btn8;
    private View btn9;

    public AdView adView;

    private ImageView copyIcon;
    private RewardedVideoAd mAd;

    Intent intent;

    int dig1;
    int dig2;
    int dig3;
    int dig4;
    int dig5;
    int dig6;
    int dig7;
    int dig8;
    int dig9;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeKeyboard();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cpficon:
                cpfIntent();
                return true;

            case R.id.cnpjicon:
                cnpjIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cnpjIntent(){
        intent = new Intent(cpfActivity.this, cnpjActivity.class);
        startActivity(intent);
    }

    public void cpfIntent(){
        //already here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpf);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        MobileAds.initialize(getApplicationContext(),
                getString(R.string.admob_app_id));

        bind();
        init();
        keyboardOpen();
        digito1.requestFocus();

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        // sample ca-app-pub-3940256099942544/5224354917
        // sample ca-app-pub-3940256099942544/6300978111
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.LARGE_BANNER);

        adView.setAdUnitId("ca-app-pub-8766426329693423/4165723218");

        //video ad unit
        //ca-app-pub-8766426329693423/6898377509
        //app-id
        //ca-app-pub-8766426329693423~7271765687

        // Get reference to singleton RewardedVideoAd object
        mAd = MobileAds.getRewardedVideoAdInstance(this);

        mAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                Toast.makeText(getBaseContext(),
                        "Ad loaded.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Toast.makeText(getBaseContext(),
                        "Ad opened.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoStarted() {
                Toast.makeText(getBaseContext(),
                        "Ad started.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdClosed() {
                Toast.makeText(getBaseContext(),
                        "Ad closed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                Toast.makeText(getBaseContext(),
                        "Ad triggered reward.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Toast.makeText(getBaseContext(),
                        "Ad left application.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
            }

            @Override
            public void onRewardedVideoCompleted() {

            }

        });



    }

 //this was making ads crash
    @Override
    protected void onPause() {
        //Pausando o AdView ao pausar a activity
        adView.pause();
        mAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Resume Adview
        adView.resume();
        mAd.resume(this);
    }

    @Override
    protected void onDestroy() {
        //destroy ads from the view
        adView.destroy();
        mAd.destroy(this);
        super.onDestroy();
    }

    public void keyboardOpen() {
        InputMethodManager inm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inm.toggleSoftInput(InputMethodManager.RESULT_SHOWN, 0);
    }

    private void bind() { //bind digitos to the proper view
        digito1 = findViewById(R.id.dig1);
        digito2 = findViewById(R.id.dig2);
        digito3 = findViewById(R.id.dig3);
        digito4 = findViewById(R.id.dig4);
        digito5 = findViewById(R.id.dig5);
        digito6 = findViewById(R.id.dig6);
        digito7 = findViewById(R.id.dig7);
        digito8 = findViewById(R.id.dig8);
        digito9 = findViewById(R.id.dig9);
        digito10 = findViewById(R.id.dig10);
        digito11 = findViewById(R.id.dig11);
        btClear = findViewById(R.id.btlimp);
        btGerar = findViewById(R.id.btnGera);
        copyIcon = findViewById(R.id.copyicon);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }

    private void init() { // create listeners for each view
        digitos = new EditText[]{digito1, digito2, digito3, digito4, digito5, digito6, digito7, digito8, digito9};
        digito1.addTextChangedListener(new DigitTextWatch(digito1));
        digito2.addTextChangedListener(new DigitTextWatch(digito2));
        digito3.addTextChangedListener(new DigitTextWatch(digito3));
        digito4.addTextChangedListener(new DigitTextWatch(digito4));
        digito5.addTextChangedListener(new DigitTextWatch(digito5));
        digito6.addTextChangedListener(new DigitTextWatch(digito6));
        digito7.addTextChangedListener(new DigitTextWatch(digito7));
        digito8.addTextChangedListener(new DigitTextWatch(digito8));
        digito9.addTextChangedListener(new DigitTextWatch(digito9));
        digito1.requestFocus();

        btGerar.setOnClickListener(new View.OnClickListener() { // on click func to gen new cpf
            @Override
            public void onClick(View v) {
                genCpf();
                mAd.loadAd(getString(R.string.ad_unit_id), new AdRequest.Builder().build());
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() { //clear all the digits
            @Override
            public void onClick(View v) {
                clearCpf();
            }
        });

        copyIcon.setOnClickListener(new View.OnClickListener() { //copy all the digits to clipboard
            @Override
            public void onClick(View v) {

                copyIcon.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                copytoClip();

                if (digito1.getText().toString().isEmpty() || digito2.getText().toString().isEmpty() || digito3.getText().toString().isEmpty() || digito4.getText().toString().isEmpty()
                        || digito5.getText().toString().isEmpty() || digito6.getText().toString().isEmpty() || digito7.getText().toString().isEmpty() || digito8.getText().toString().isEmpty() || digito9.getText().toString().isEmpty() ) {

                    copyIcon.clearColorFilter();

                    LayoutInflater inflater = getLayoutInflater(); //custom toast
                    View layout = inflater.inflate(R.layout.toast_layout_red,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Insira um CPF valido");

                    Toast toastRed = new Toast(getApplicationContext());
                    toastRed.setGravity(Gravity.BOTTOM, 0, 0);
                    toastRed.setDuration(Toast.LENGTH_SHORT);
                    toastRed.setView(layout);
                    toastRed.show();

                }
                else{

                    //Toast.makeText(cpfActivity.this, "CPF copiado", Toast.LENGTH_SHORT).show();

                    LayoutInflater inflater = getLayoutInflater(); //custom toast
                    View layout = inflater.inflate(R.layout.toast_layout,
                            (ViewGroup) findViewById(R.id.toast_layout_root));


                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("CPF copiado");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();


                    Handler handlericon = new Handler();
                    handlericon.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            copyIcon.clearColorFilter();
                        }
                    },700);

                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAd.isLoaded()) {
                    mAd.show();
                }

                final ProgressButton progressButton0 = new ProgressButton(cpfActivity.this, btn0);

                progressButton0.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton0.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("0")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton1 = new ProgressButton(cpfActivity.this, btn1);

                progressButton1.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton1.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("1")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton2 = new ProgressButton(cpfActivity.this, btn2);

                progressButton2.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle2 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton2.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("2")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton3 = new ProgressButton(cpfActivity.this, btn3);

                progressButton3.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton3.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("3")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton4 = new ProgressButton(cpfActivity.this, btn4);

                progressButton4.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton4.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("4")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton5= new ProgressButton(cpfActivity.this, btn5);

                progressButton5.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle2 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton5.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("5")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton6 = new ProgressButton(cpfActivity.this, btn6);

                progressButton6.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton6.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("6")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton7 = new ProgressButton(cpfActivity.this, btn7);

                progressButton7.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton7.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("7")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton8 = new ProgressButton(cpfActivity.this, btn8);

                progressButton8.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle2 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton8.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("8")) {
                            genCpf();
                        }

                    }
                }, 160);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressButton progressButton9 = new ProgressButton(cpfActivity.this, btn9);

                progressButton9.buttonActivated();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Handler handle1 = new Handler();
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                Handler handle2 = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        progressButton9.buttonFinished();
                                    }
                                },100);
                            }
                        }, 10);

                        genCpf();
                        while (!digito11.getText().toString().equals("9")) {
                            genCpf();
                        }

                    }
                }, 160);

            }
        });

    }

    public void random() {
        Random rand = new Random();
        dig1 = rand.nextInt(9) + 1;
        dig2 = rand.nextInt(9) + 1;
        dig3 = rand.nextInt(9) + 1;
        dig4 = rand.nextInt(9) + 1;
        dig5 = rand.nextInt(9) + 1;
        dig6 = rand.nextInt(9) + 1;
        dig7 = rand.nextInt(9) + 1;
        dig8 = rand.nextInt(9) + 1;
        dig9 = rand.nextInt(9) + 1;

    }

    public void genCpf() {
        random();
        digito1.setText(Integer.toString(dig1));
        digito2.setText(Integer.toString(dig2));
        digito3.setText(Integer.toString(dig3));
        digito4.setText(Integer.toString(dig4));
        digito5.setText(Integer.toString(dig5));
        digito6.setText(Integer.toString(dig6));
        digito7.setText(Integer.toString(dig7));
        digito8.setText(Integer.toString(dig8));
        digito9.setText(Integer.toString(dig9));

        digito9.requestFocus();
        digito9.clearFocus();
        closeKeyboard();
    }

    private void getFinalDigts() {

        int dig1 = Integer.parseInt(digito1.getText().toString());
        int dig2 = Integer.parseInt(digito2.getText().toString());
        int dig3 = Integer.parseInt(digito3.getText().toString());
        int dig4 = Integer.parseInt(digito4.getText().toString());
        int dig5 = Integer.parseInt(digito5.getText().toString());
        int dig6 = Integer.parseInt(digito6.getText().toString());
        int dig7 = Integer.parseInt(digito7.getText().toString());
        int dig8 = Integer.parseInt(digito8.getText().toString());
        int dig9 = Integer.parseInt(digito9.getText().toString());

        int dig10 = ((dig1) + (dig2 * 2) + (dig3 * 3) + (dig4 * 4) + (dig5 * 5) + (dig6 * 6) + (dig7 * 7) + (dig8 * 8) + (dig9 * 9)) % 11;

        if (dig10 >= 10) {
            dig10 = 0;
        }

        int dig11 = ((dig2) + (dig3 * 2) + (dig4 * 3) + (dig5 * 4) + (dig6 * 5) + (dig7 * 6) + (dig8 * 7) + (dig9 * 8) + (dig10 * 9)) % 11;

        if (dig11 >= 10) {
            dig11 = 0;
        }

        //exibir
        digito10.setText(String.valueOf(dig10));
        digito11.setText(String.valueOf(dig11));

        if (
                dig1 == 1 && dig2 == 1 && dig3 == 1 && dig4 == 1 && dig5 == 1 && dig6 == 1 && dig7 == 1 && dig8 == 1 && dig9 == 1 ||
                        dig1 == 2 && dig2 == 2 && dig3 == 2 && dig4 == 2 && dig5 == 2 && dig6 == 2 && dig7 == 2 && dig8 == 2 && dig9 == 2 ||
                        dig1 == 3 && dig2 == 3 && dig3 == 3 && dig4 == 3 && dig5 == 3 && dig6 == 3 && dig7 == 3 && dig8 == 3 && dig9 == 3 ||
                        dig1 == 4 && dig2 == 4 && dig3 == 4 && dig4 == 4 && dig5 == 4 && dig6 == 4 && dig7 == 4 && dig8 == 4 && dig9 == 4 ||
                        dig1 == 5 && dig2 == 5 && dig3 == 5 && dig4 == 5 && dig5 == 5 && dig6 == 5 && dig7 == 5 && dig8 == 5 && dig9 == 5 ||
                        dig1 == 6 && dig2 == 6 && dig3 == 6 && dig4 == 6 && dig5 == 6 && dig6 == 6 && dig7 == 6 && dig8 == 6 && dig9 == 6 ||
                        dig1 == 7 && dig2 == 7 && dig3 == 7 && dig4 == 7 && dig5 == 7 && dig6 == 7 && dig7 == 7 && dig8 == 7 && dig9 == 7 ||
                        dig1 == 8 && dig2 == 8 && dig3 == 8 && dig4 == 8 && dig5 == 8 && dig6 == 8 && dig7 == 8 && dig8 == 8 && dig9 == 8 ||
                        dig1 == 9 && dig2 == 9 && dig3 == 9 && dig4 == 9 && dig5 == 9 && dig6 == 9 && dig7 == 9 && dig8 == 9 && dig9 == 9 ||
                        dig1 == 0 && dig2 == 0 && dig3 == 0 && dig4 == 0 && dig5 == 0 && dig6 == 0 && dig7 == 0 && dig8 == 0 && dig9 == 0
        ) {
            makeText(this, "CPF não é válido", Toast.LENGTH_SHORT).show();
        }
        else {
            // Toast.makeText(this, "CPF válido", Toast.LENGTH_SHORT).show();
        }

    }

    private void verifDigts(EditText[] digitos) {
        for (EditText digito : digitos) {
            if (digito.getText().toString().length() <= 0) {
                return;
            }
        }
        getFinalDigts();
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(digito9.getWindowToken(), 0);
    }

    public void copytoClip(){

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text",
                digito1.getText() + "" + digito2.getText() + "" + digito3.getText() + "." +
                        digito4.getText() + "" + digito5.getText() + "" + digito6.getText() + "." +
                        digito7.getText() + "" + digito8.getText() + "" + digito9.getText() +
                        "-" + digito10.getText() + "" + digito11.getText());
        clipboard.setPrimaryClip(clip);

    }


    public void clearCpf() {
        digito1.setText("");
        digito2.setText("");
        digito3.setText("");
        digito4.setText("");
        digito5.setText("");
        digito6.setText("");
        digito7.setText("");
        digito8.setText("");
        digito9.setText("");
        digito10.setText("");
        digito11.setText("");

        digito1.requestFocus();
        digito1.clearFocus();
        closeKeyboard();
    }

    private class DigitTextWatch implements TextWatcher {

        private View view;

        private DigitTextWatch(View view) {
            this.view = view;
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            verifDigts(digitos);

            String text = s.toString();

            switch (view.getId()) {
                case R.id.dig1:
                    if (text.length() == 1) {
                        digito2.requestFocus();
                    }
                    break;
                case R.id.dig2:
                    if (text.length() == 1) {
                        digito3.requestFocus();
                    }
                    break;
                case R.id.dig3:
                    if (text.length() == 1) {
                        digito4.requestFocus();
                    }
                    break;
                case R.id.dig4:
                    if (text.length() == 1) {
                        digito5.requestFocus();
                    }
                    break;
                case R.id.dig5:
                    if (text.length() == 1) {
                        digito6.requestFocus();
                    }
                    break;
                case R.id.dig6:
                    if (text.length() == 1) {
                        digito7.requestFocus();
                    }
                    break;
                case R.id.dig7:
                    if (text.length() == 1) {
                        digito8.requestFocus();
                    }
                    break;
                case R.id.dig8:
                    if (text.length() == 1) {
                        digito9.requestFocus();

                    }

                    break;
                case R.id.dig9:
                    if (text.isEmpty()) {
                        digito8.requestFocus();
                        digito10.setText("");
                        digito11.setText("");
                    }
                    break;
            }
        } //move to another dig


        @Override
        public void afterTextChanged(Editable s) {

            String texts = s.toString();

            switch (view.getId()) {
                case R.id.dig9:
                    if (texts.isEmpty()) {
                        digito8.requestFocus();
                    }

                    break;
                case R.id.dig8:
                    if (texts.isEmpty()) {
                        digito7.requestFocus();
                    }
                    break;
                case R.id.dig7:
                    if (texts.isEmpty()) {
                        digito6.requestFocus();
                    }
                    break;
                case R.id.dig6:
                    if (texts.isEmpty()) {
                        digito5.requestFocus();
                    }
                    break;
                case R.id.dig5:
                    if (texts.isEmpty()) {
                        digito4.requestFocus();
                    }
                    break;
                case R.id.dig4:
                    if (texts.isEmpty()) {
                        digito3.requestFocus();
                    }
                    break;
                case R.id.dig3:
                    if (texts.isEmpty()) {
                        digito2.requestFocus();
                    }
                    break;
                case R.id.dig2:
                    if (texts.isEmpty()) {
                        digito1.requestFocus();
                    }
                    break;

            }


        } //move to another dig
    }
}

