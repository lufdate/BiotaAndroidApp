package com.example.android.BiotaApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends Activity {

	private AccessoryEngine mEngine = null;
	private ImageView mButtonImage = null;
	
	private TextView mBrightnessText = null;
	public EditText mTextoEditable;
	public Button mButtonCommand;
	public TextView mConnectionText;
	public TextView mTemperatura;
	public TextView mVelocidad;
	public TextView mHumedadS;
	public TextView mTemperaturaS;
	public TextView mVelocidadS;
	public EditText mVelocidadSet;
	public EditText mTemperaturaSet;
	public EditText mVelocidadSSet;
	public EditText mHumedadSSet;

	public ImageButton mHornoVel;
	public ImageButton mHornoTemp;
	public ImageButton mSecaVel;
	public ImageButton mSecaHum;

	public Switch mHornoSwitch;
	public Switch mSecaSwitch;

	public String velAntes;
    public String tempAntes;
    public String velAntesS;
    public String humAntes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		onNewIntent(getIntent());
		setContentView(R.layout.activity_main);

		mButtonImage = (ImageView) findViewById(R.id.ivButton);
		mButtonImage.setImageResource(R.drawable.button_r);
		mTextoEditable = (EditText) findViewById(R.id.etCantidad);
		mButtonCommand = (Button) findViewById(R.id.btIngrese);
        mConnectionText = (TextView)findViewById(R.id.tvConnection);
		
		mBrightnessText = (TextView) findViewById(R.id.tvBrightness);
		mTemperatura = (TextView) findViewById(R.id.tvHornoTemRTVal);
		mVelocidad = (TextView) findViewById(R.id.tvHornoVelRTVal);
		mHumedadS = (TextView) findViewById(R.id.tvVelHumRTVal);
		mTemperaturaS = (TextView)findViewById(R.id.tvSecaTempRTVal) ;
		mVelocidadS = (TextView) findViewById(R.id.tvSecaVelRTVal);

		mVelocidadSet = (EditText) findViewById(R.id.etHornoVelSetVal);
		mTemperaturaSet = (EditText)findViewById(R.id.etHornoTemSetVal);
		mVelocidadSSet = (EditText) findViewById(R.id.etSecaVelSetVal);
		mHumedadSSet = (EditText) findViewById(R.id.etSecaHumSetVal);

		mHornoVel = (ImageButton)findViewById(R.id.ibSetHornoVel);
		mHornoTemp = (ImageButton)findViewById(R.id.ibSetHornoTemp);
		mSecaVel = (ImageButton) findViewById(R.id.ibSetSecaVel);
		mSecaHum = (ImageButton)findViewById(R.id.ibSetSecaHum);

		mHornoSwitch = (Switch) findViewById(R.id.sbHorno);
		mSecaSwitch = (Switch)findViewById(R.id.sbSecador);

        //set the current state of a Switch
        mHornoSwitch.setChecked(false);
        mSecaSwitch.setChecked(false);

        // comentarios

        final CountDownTimer myTimerHornoVel = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                mVelocidadSet .setBackgroundColor(Color.GREEN);
            }
            public  void onFinish(){
                mVelocidadSet .setBackgroundColor(Color.parseColor("#4fa5d5"));
                mVelocidadSet.setText(velAntes);
            }
        };

        final CountDownTimer myTimerHornoTemp = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                mTemperaturaSet .setBackgroundColor(Color.GREEN);
            }
            public  void onFinish(){
                mTemperaturaSet .setBackgroundColor(Color.parseColor("#4fa5d5"));
                mTemperaturaSet.setText(tempAntes);
            }
        };

        final CountDownTimer myTimerSecaVel = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                mVelocidadSSet .setBackgroundColor(Color.GREEN);
            }
            public  void onFinish(){
                mVelocidadSSet  .setBackgroundColor(Color.parseColor("#4fa5d5"));
                mVelocidadSSet .setText(velAntesS);
            }
        };

        final CountDownTimer myTimerSecaHum = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                mHumedadSSet .setBackgroundColor(Color.GREEN);
            }
            public  void onFinish(){
                mHumedadSSet  .setBackgroundColor(Color.parseColor("#4fa5d5"));
                mHumedadSSet .setText(humAntes);
            }
        };

        mVelocidadSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                velAntes=mVelocidadSet.getText().toString();
            }
        });

        mTemperaturaSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempAntes=mTemperaturaSet.getText().toString();
            }
        });

        mVelocidadSSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                velAntesS=mVelocidadSSet.getText().toString();
            }
        });

        mHumedadSSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                humAntes=mHumedadSSet.getText().toString();
            }
        });


		mHornoVel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() ==  KeyEvent.ACTION_DOWN) {
                    mVelocidadSet .setBackgroundColor(Color.DKGRAY);

                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    mVelocidadSet .setBackgroundColor(Color.parseColor("#4fa5d5"));
                    velAntes=mVelocidadSet.getText().toString();
                    myTimerHornoVel.cancel();
                }
                return false;
            }
        });

        mHornoTemp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    mTemperaturaSet .setBackgroundColor(Color.DKGRAY);

                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    mTemperaturaSet .setBackgroundColor(Color.parseColor("#4fa5d5"));
                    tempAntes=mTemperaturaSet.getText().toString();
                    myTimerHornoTemp.cancel();
                }
                return false;
            }
        });

        mSecaVel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    mVelocidadSSet .setBackgroundColor(Color.DKGRAY);

                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    mVelocidadSSet .setBackgroundColor(Color.parseColor("#4fa5d5"));
                    velAntesS=mVelocidadSSet.getText().toString();
                    myTimerSecaVel.cancel();
                }
                return false;
            }
        });

        mSecaHum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    mHumedadSSet .setBackgroundColor(Color.DKGRAY);

                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    mHumedadSSet .setBackgroundColor(Color.parseColor("#4fa5d5"));
                    humAntes=mHumedadSSet.getText().toString();
                    myTimerSecaHum.cancel();
                }
                return false;
            }
        });

        mHornoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                String datoenviar;
                if(isChecked){
                    datoenviar="ATHONN";
                    mEngine.write(datoenviar.getBytes());
                }else{
                    datoenviar="ATHOFF";
                    mEngine.write(datoenviar.getBytes());
                }
            }

        });

        mSecaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                String datoenviar;
                if(isChecked){
                    datoenviar="ATSONN";
                    mEngine.write(datoenviar.getBytes());
                }else{
                    datoenviar="ATSOFF";
                    mEngine.write(datoenviar.getBytes());
                }
            }

        });

        mVelocidadSet.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do your stuff here
                    myTimerHornoVel.start();
                }
				return false;
            }
        });

        mTemperaturaSet.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do your stuff here
                    myTimerHornoTemp.start();
                }
                return false;
            }
        });

        mVelocidadSSet.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do your stuff here
                    myTimerSecaVel.start();
                }
                return false;
            }
        });

        mHumedadSSet.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do your stuff here
                    myTimerSecaHum.start();
                }
                return false;
            }
        });


    }

	@Override
	protected void onNewIntent(Intent intent) {
		L.d("handling intent action: " + intent.getAction());
		if (mEngine == null) {
			mEngine = new AccessoryEngine(getApplicationContext(), mCallback);
		}
		mEngine.onNewIntent(intent);
		super.onNewIntent(intent);
	}

	@Override
	protected void onDestroy() {
		mEngine.onDestroy();
		mEngine = null;
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	private final AccessoryEngine.IEngineCallback mCallback = new AccessoryEngine.IEngineCallback() {
		@Override
		public void onDeviceDisconnected() {
			L.d("device physically disconnected");
			updateResource(R.drawable.button_r);
			updateText("Desconectado",0);
		}

		@Override
		public void onConnectionEstablished() {
			L.d("device connected! ready to go!");
		}

		@Override
		public void onConnectionClosed() {
			L.d("connection closed");
		}

		@Override
		public void onDataRecieved(byte[] data, int num) {
			if(num > 0){
				String dato = new String(data);
				String tipo = dato.substring(0,2);
				String entero = dato.substring(2,5);
				String decimal = dato.substring(5);
				String numero = entero + "." + decimal;

				switch (tipo){

					case "OK":
						updateText("Conectado",0);
						updateResource(R.drawable.button_g);
						break;

					case "VH":
//						mVelocidad.setText(entero);
						updateText(entero,1);
						break;

					case "TH":
//						mTemperatura.setText(numero);
						updateText(entero,2);
						break;

					case "VS":
//						mVelocidadS.setText(entero);
						updateText(entero,3);
						break;

					case "TS":
						updateText(entero,4);
						break;

					case "HS":
//						mHumedadS.setText(entero);
						updateText(entero,5);
						break;

					default:
						//updateText("                    ",6);
						updateText(dato,6);
						break;
				}

			}
		}
	};
	
	private void updateResource(final int id){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mButtonImage.setImageResource(id);
			}
		});
	}

	private void updateText(final String text, final int numObjeto){
			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					switch(numObjeto){
						case 0:
							mConnectionText.setText(text);
							break;

						case 1:
							mVelocidad.setText(text);
							break;

						case 2:
							mTemperatura.setText(text);
							break;

						case 3:
							mVelocidadS.setText(text);
							break;

						case 4:
							mTemperaturaS.setText(text);
							break;

						case 5:
							mHumedadS.setText(text);
							break;

						default:
							mBrightnessText.setText(text);
					}

				}
			});
	}

	public void OnButtonClick(View view){
	    String datoingresado;
	    datoingresado = mTextoEditable.getText().toString();

	    mEngine.write(datoingresado.getBytes());
    }


	public void OnHornoVelClick(View view){
		String datoingresado,datoenviar;
		datoingresado =mVelocidadSet.getText().toString();

		switch (datoingresado.length()){
			case 1:
				datoenviar = "VH00" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 2:
				datoenviar = "VH0" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 3:
				datoenviar = "VH" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;
		}
	}

	public void OnHornoTempClick(View view){
		String datoenviar,datoingresado;
		//float datodecimal;
		//datodecimal = Float.parseFloat(mTemperaturaSet.getText().toString());
		//datoingresado = Float.toString(datodecimal);
		datoingresado = mTemperaturaSet.getText().toString();
		switch (datoingresado.length()){
			case 1:
				datoenviar = "TH00" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 2:
				datoenviar = "TH0" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 3:
				datoenviar = "TH" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;
		}
	}


	public void OnSecaVelClick(View view){
		String datoingresado,datoenviar;
		datoingresado = mVelocidadSSet.getText().toString();

		switch (datoingresado.length()){
			case 1:
				datoenviar = "VS00" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 2:
				datoenviar = "VS0" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 3:
				datoenviar = "VS" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;
		}
	}

	public void OnSecaHumClick(View view){
		String datoingresado,datoenviar;
		datoingresado = mHumedadSSet.getText().toString();

		switch (datoingresado.length()){
			case 1:
				datoenviar = "HS00" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

			case 2:
				datoenviar = "HS0" + datoingresado+"0";
				mEngine.write(datoenviar.getBytes());
				break;

		}
	}

}
