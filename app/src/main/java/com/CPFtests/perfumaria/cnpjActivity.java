package com.CPFtests.perfumaria;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class cnpjActivity extends AppCompatActivity {

        private EditText digito1;
        private EditText digito2;
        private EditText digito3;
        private EditText digito4;
        private EditText digito5;
        private EditText digito6;
        private EditText digito7;
        private EditText digito8;
        private EditText digito9;
        private EditText digito10;
        private EditText digito11;
        private EditText digito12;
        private EditText[] digitos;

        private TextView digito13;
        private TextView digito14;

        private Button btGerar;
        private Button btClear;

        private View btn1;
        private View btn3;
        private View btn5;

        public AdView adView2;

        private ImageView copyIcon;
        private ImageView editIcon2;
        private ImageView cancelNumberChanger2;
        private EditText numberChanger2;
        private ConstraintLayout background2;
        private String numberChanged2;

        int dig1;
        int dig2;
        int dig3;
        int dig4;
        int dig5;
        int dig6;
        int dig7;
        int dig8;
        int dig9;
        int dig10;
        int dig11;
        int dig12;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeKeyboard();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater1 = getMenuInflater();
        inflater1.inflate(R.menu.menuitems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cpficon:
                finish();
                return true;
            case R.id.cnpjicon:
                //already here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cnpj);


            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            bind();
            init();
            keyboardOpen();
            digito1.requestFocus();

            //sample ca-app-pub-3940256099942544/6300978111
            AdView adView2 = new AdView(this);
            adView2.setAdSize(AdSize.LARGE_BANNER);
            adView2.setAdUnitId("ca-app-pub-8766426329693423/4165723218");

            adView2 = findViewById(R.id.adView2);
            AdRequest adRequest2 = new AdRequest.Builder().build();
            adView2.loadAd(adRequest2);

        }

    @Override
    protected void onResume() {
        super.onResume();
        //Resume Adview
        //adView.resume();
    }

    @Override
    protected void onDestroy() {
        //destroy ads from the view
//        adView2.destroy();
        super.onDestroy();
    }

        public void keyboardOpen() {

            InputMethodManager inm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inm.toggleSoftInput(InputMethodManager.RESULT_SHOWN, 0);
        }

        private void bind() {
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
            digito12 = findViewById(R.id.dig12);
            digito13 = findViewById(R.id.dig13);
            digito14 = findViewById(R.id.dig14);
            btClear = findViewById(R.id.btlimp);
            btGerar = findViewById(R.id.btnGera);
            copyIcon = findViewById(R.id.copyicon);
            btn1 = findViewById(R.id.btn1);
            btn3 = findViewById(R.id.btn3);
            btn5 = findViewById(R.id.btn5);
            editIcon2 = findViewById(R.id.editIcon2);
            cancelNumberChanger2 = findViewById(R.id.cancelNumberChanger2);
            numberChanger2 = findViewById(R.id.numberChanger2);
            background2 = findViewById(R.id.background2);

        }

        private void init() {
            digitos = new EditText[]{digito1, digito2, digito3, digito4, digito5, digito6, digito7, digito8, digito9, digito10, digito11, digito12};
            digito1.addTextChangedListener(new DigitTextWatch(digito1));
            digito2.addTextChangedListener(new DigitTextWatch(digito2));
            digito3.addTextChangedListener(new DigitTextWatch(digito3));
            digito4.addTextChangedListener(new DigitTextWatch(digito4));
            digito5.addTextChangedListener(new DigitTextWatch(digito5));
            digito6.addTextChangedListener(new DigitTextWatch(digito6));
            digito7.addTextChangedListener(new DigitTextWatch(digito7));
            digito8.addTextChangedListener(new DigitTextWatch(digito8));
            digito9.addTextChangedListener(new DigitTextWatch(digito9));
            digito10.addTextChangedListener(new DigitTextWatch(digito10));
            digito11.addTextChangedListener(new DigitTextWatch(digito11));
            digito12.addTextChangedListener(new DigitTextWatch(digito12));
            digito1.requestFocus();


            btGerar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genCpf();
                }
            });

            btClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearCpf();
                }
            });

            copyIcon.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    copyIcon.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                    copytoClip();

                    if (       digito1.getText().toString().isEmpty()
                            || digito2.getText().toString().isEmpty()
                            || digito3.getText().toString().isEmpty()
                            || digito4.getText().toString().isEmpty()
                            || digito5.getText().toString().isEmpty()
                            || digito6.getText().toString().isEmpty()
                            || digito7.getText().toString().isEmpty()
                            || digito8.getText().toString().isEmpty()
                            || digito9.getText().toString().isEmpty()
                            || digito10.getText().toString().isEmpty()
                            || digito11.getText().toString().isEmpty()
                            || digito12.getText().toString().isEmpty()
                    ) {
                        copyIcon.clearColorFilter();

                        LayoutInflater inflater = getLayoutInflater(); //custom toast
                        View layout = inflater.inflate(R.layout.toast_layout_red,
                                (ViewGroup) findViewById(R.id.toast_layout_root));

                        TextView text = (TextView) layout.findViewById(R.id.text);
                        text.setText("Insira um CNPJ valido");

                        Toast toastRed = new Toast(getApplicationContext());
                        toastRed.setGravity(Gravity.BOTTOM, 0, 0);
                        toastRed.setDuration(Toast.LENGTH_SHORT);
                        toastRed.setView(layout);
                        toastRed.show();

                    }
                    else{
                        LayoutInflater inflater = getLayoutInflater(); //custom toast
                        View layout = inflater.inflate(R.layout.toast_layout,
                                (ViewGroup) findViewById(R.id.toast_layout_root));


                        TextView text = (TextView) layout.findViewById(R.id.text);
                        text.setText("CNPJ copiado");

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

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ProgressButton progressButton1 = new ProgressButton(cnpjActivity.this, btn1);

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
                            while (!digito14.getText().toString().equals("1")) {
                                genCpf();
                            }

                        }
                    }, 160);
                }
            });



            cancelNumberChanger2.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            cancelNumberChanger2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberChanger2.setVisibility(View.GONE);
                    cancelNumberChanger2.setVisibility(View.GONE);
                    closeKeyboard();
                    background2.setVisibility(View.VISIBLE);

                }
            });


            editIcon2.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            editIcon2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    background2.setVisibility(View.GONE);
                    numberChanger2.setVisibility(View.VISIBLE);
                    cancelNumberChanger2.setVisibility(View.VISIBLE);
                    numberChanger2.requestFocus();
                    closeKeyboard();
                    keyboardOpen();


                    numberChanger2.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {

                            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                                    (i == KeyEvent.KEYCODE_ENTER)) {
                                // Perform action on key press
                                numberChanged2 = numberChanger2.getText().toString().trim();

                                if (numberChanged2.equals("0")) {

                                    LayoutInflater inflater = getLayoutInflater(); //custom toast
                                    View layout = inflater.inflate(R.layout.toast_layout_red,
                                            (ViewGroup) findViewById(R.id.toast_layout_root));

                                    TextView text = (TextView) layout.findViewById(R.id.text);
                                    text.setText("Número inválido");

                                    Toast toastRed = new Toast(getApplicationContext());
                                    toastRed.setGravity(Gravity.BOTTOM, 0, 0);
                                    toastRed.setDuration(Toast.LENGTH_SHORT);
                                    toastRed.setView(layout);
                                    toastRed.show();

                                } else if (numberChanger2.getText().toString().isEmpty()) {

                                    LayoutInflater inflater = getLayoutInflater(); //custom toast
                                    View layout = inflater.inflate(R.layout.toast_layout_red,
                                            (ViewGroup) findViewById(R.id.toast_layout_root));

                                    TextView text = (TextView) layout.findViewById(R.id.text);
                                    text.setText("Insira um número");

                                    Toast toastRed = new Toast(getApplicationContext());
                                    toastRed.setGravity(Gravity.BOTTOM, 0, 0);
                                    toastRed.setDuration(Toast.LENGTH_SHORT);
                                    toastRed.setView(layout);
                                    toastRed.show();

                                    btn3.setClickable(false);

                                } else {
                                    btn3.setClickable(true);
                                    btn3.callOnClick();
                                }


                                return true;
                            }
                            return false;
                        }
                    });


                }
            });


            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (numberChanged2 != null && numberChanged2.trim() != null){

                    final ProgressButton progressButton3 = new ProgressButton(cnpjActivity.this, btn3);

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
                            while (!digito14.getText().toString().equals(numberChanged2)) {
                                genCpf();
                            }

                        }
                    }, 160);

                    numberChanger2.setVisibility(View.GONE);
                    closeKeyboard();
                    background2.setVisibility(View.VISIBLE);
                    cancelNumberChanger2.setVisibility(View.GONE);

                } else {

                    LayoutInflater inflater = getLayoutInflater(); //custom toast
                    View layout = inflater.inflate(R.layout.toast_layout_red,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Insira número valido");

                    Toast toastRed = new Toast(getApplicationContext());
                    toastRed.setGravity(Gravity.BOTTOM, 0, 0);
                    toastRed.setDuration(Toast.LENGTH_SHORT);
                    toastRed.setView(layout);
                    toastRed.show();
                }

                }
            });

            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ProgressButton progressButton5 = new ProgressButton(cnpjActivity.this, btn5);

                    progressButton5.buttonActivated();
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
                                            progressButton5.buttonFinished();
                                        }
                                    },100);
                                }
                            }, 10);

                            genCpf();
                            while (!digito14.getText().toString().equals("5")) {
                                genCpf();
                            }

                        }
                    }, 160);

                }
            });

        }

        public void random() { //gen random numbers for the cpf
            Random rand = new Random();
            dig1 = rand.nextInt(9) + 1;
            dig2 = rand.nextInt(9) + 1;
            dig3 = rand.nextInt(9) + 1;
            dig4 = rand.nextInt(9) + 1;
            dig5 = rand.nextInt(9) + 1;
            dig6 = rand.nextInt(9) + 1;
            dig7 = rand.nextInt(9) + 1;
            dig8 = rand.nextInt(9) + 1;
            dig9 = 0;
            dig10 = 0;
            dig11 = 0;
            dig12 = 1;

        }

        public void genCpf() { //display numbers to user
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
            digito10.setText(Integer.toString(dig10));
            digito11.setText(Integer.toString(dig11));
            digito12.setText(Integer.toString(dig12));

            digito12.requestFocus();
            digito12.clearFocus();
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
            int dig10 = Integer.parseInt(digito10.getText().toString());
            int dig11 = Integer.parseInt(digito11.getText().toString());
            int dig12 = Integer.parseInt(digito12.getText().toString());

            int dig13 = ((dig1 * 5) + (dig2 * 4) + (dig3 * 3) + (dig4 * 2)
                    + (dig5 * 9) + (dig6 * 8) + (dig7 * 7) + (dig8 *6 )
                    + (dig9 * 5)+ (dig10 * 4)+ (dig11 * 3)+ (dig12 * 2)) % 11;

            if (dig13 == 0 || dig13 == 1 )
            {
                dig13 = 0;
            }

            {
                dig13 = 11-dig13;
            }


            int dig14 = ((dig1 * 6) + (dig2 * 5) + (dig3 * 4) + (dig4 * 3)
                    + (dig5 * 2) + (dig6 * 9) + (dig7 * 8) + (dig8 * 7)
                    + (dig9 * 6) + (dig10 * 5) + (dig11 * 4) + (dig12 * 3) + (dig13 * 2)) % 11;

            if (dig14 == 0 || dig14 == 1 )
            {
                dig14 = 0;
            }

            {
                dig14 = 11-dig14;
            }


            //exibir
            digito13.setText(String.valueOf(dig13));
            digito14.setText(String.valueOf(dig14));

            if (
                    dig1 == 1 && dig2 == 1 && dig3 == 1 && dig4 == 1 && dig5 == 1 && dig6 == 1 && dig7 == 1 && dig8 == 1 && dig9 == 1 && dig10 == 1 && dig11 == 1 && dig12 == 1||
                            dig1 == 2 && dig2 == 2 && dig3 == 2 && dig4 == 2 && dig5 == 2 && dig6 == 2 && dig7 == 2 && dig8 == 2 && dig9 == 2 && dig10 == 2 && dig11 == 2 && dig12 == 2||
                            dig1 == 3 && dig2 == 3 && dig3 == 3 && dig4 == 3 && dig5 == 3 && dig6 == 3 && dig7 == 3 && dig8 == 3 && dig9 == 3 && dig10 == 3 && dig11 == 3 && dig12 == 3||
                            dig1 == 4 && dig2 == 4 && dig3 == 4 && dig4 == 4 && dig5 == 4 && dig6 == 4 && dig7 == 4 && dig8 == 4 && dig9 == 4 && dig10 == 4 && dig11 == 4 && dig12 == 4||
                            dig1 == 5 && dig2 == 5 && dig3 == 5 && dig4 == 5 && dig5 == 5 && dig6 == 5 && dig7 == 5 && dig8 == 5 && dig9 == 5 && dig10 == 5 && dig11 == 5 && dig12 == 5||
                            dig1 == 6 && dig2 == 6 && dig3 == 6 && dig4 == 6 && dig5 == 6 && dig6 == 6 && dig7 == 6 && dig8 == 6 && dig9 == 6 && dig10 == 6 && dig11 == 6 && dig12 == 6||
                            dig1 == 7 && dig2 == 7 && dig3 == 7 && dig4 == 7 && dig5 == 7 && dig6 == 7 && dig7 == 7 && dig8 == 7 && dig9 == 7 && dig10 == 7 && dig11 == 7 && dig12 == 7||
                            dig1 == 8 && dig2 == 8 && dig3 == 8 && dig4 == 8 && dig5 == 8 && dig6 == 8 && dig7 == 8 && dig8 == 8 && dig9 == 8 && dig10 == 8 && dig11 == 8 && dig12 == 8||
                            dig1 == 9 && dig2 == 9 && dig3 == 9 && dig4 == 9 && dig5 == 9 && dig6 == 9 && dig7 == 9 && dig8 == 9 && dig9 == 9 && dig10 == 9 && dig11 == 9 && dig12 == 9

            ) {
                Toast.makeText(this, "CPF não é válido", Toast.LENGTH_SHORT).show();
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
            imm.hideSoftInputFromWindow(digito12.getWindowToken(), 0);
        }

        public void copytoClip(){

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text",
                    digito1.getText() + "" + digito2.getText() + "" + digito3.getText() + "." +
                            digito4.getText() + "" + digito5.getText() + "" + digito6.getText() + "." +
                            digito7.getText() + "" + digito8.getText() + "" + digito9.getText() +
                            "-" + digito10.getText() + "" + digito11.getText() + "" + digito12.getText() + "" + digito13.getText() + "" + digito14.getText());

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
            digito12.setText("");
            digito13.setText("");
            digito14.setText("");

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
                        if (text.length() == 1) {
                            digito10.requestFocus();
                        }
                        break;
                    case R.id.dig10:
                        if (text.length() == 1) {
                            digito11.requestFocus();
                        }
                        break;
                    case R.id.dig11:
                        if (text.length() == 1) {
                            digito12.requestFocus();
                        }
                        break;

                    case R.id.dig12:
                        if (text.isEmpty()) {
                            digito11.requestFocus();
                            digito13.setText("");
                            digito14.setText("");
                        }
                        break;
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

                String texts = s.toString();

                switch (view.getId())

                {
                    case R.id.dig12:
                        if (texts.isEmpty()) {
                            digito11.requestFocus();
                        }
                        break;
                    case R.id.dig11:
                        if (texts.isEmpty()) {
                            digito10.requestFocus();
                        }
                        break;
                    case R.id.dig10:
                        if (texts.isEmpty()) {
                            digito9.requestFocus();
                        }
                        break;
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


            }
        }

    }
