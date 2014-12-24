package com.swimtimeconverter;

import com.google.ads.AdView;
//import com.google.ads.AdSize;
import com.google.ads.AdRequest;
//import com.swimtimeconverter.R.id;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
//import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements TextWatcher
{
	Button btnClosePopup;
	Button btnCreatePopup;
	private EditText et1, et2, et3;
	private TextView tv1, tv2, tv3;
	String onex = "0", twox="0", threex="0", spin1val, spin2val, spin3val, lcm, scm, scy;
	Spinner spin1, spin2, spin3;
	String[] courses={"Long Course Meters", "Short Course Meters", "Short Course Yards"};
	String[] strokes={"Freestyle", "Backstroke", "Breaststroke", "Butterfly", "I.M."};
	String[] distance={"50", "100", "200", "400/500", "800/1000", "1500/1650"};
	double val1, val2, val3, val4, val5, val6;
	LinearLayout ll;
	int height, width;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText1);
        et1.addTextChangedListener(this);
        et2=(EditText)findViewById(R.id.editText2);
        et2.addTextChangedListener(this);
        et3=(EditText)findViewById(R.id.editText3);
        et3.addTextChangedListener(this);
        spin1 = (Spinner)findViewById(R.id.spinner1);
        spin2 = (Spinner)findViewById(R.id.spinner2);
        spin3 = (Spinner)findViewById(R.id.spinner3);
        
        AdView ad = (AdView)findViewById(R.id.ad);
        ad.loadAd(new AdRequest());
        
        //AdRequest adRequest = new AdRequest();
        //adRequest.addTestDevice("45820497F5F92C080F388F2D477BDE3C");
        //adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
        //ll = (LinearLayout)findViewById(R.id.lay);
        //AdView ad = new AdView(MainActivity.this, AdSize.BANNER, "a151ed9827eae17");
        //ll.addView(ad);
        //ad.loadAd(new AdRequest());
        
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        
        spin1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int position, long id) { 
                // TODO Auto-generated method stub
                spin1val = courses[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        spin2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int position, long id) { 
                // TODO Auto-generated method stub
                spin2val = strokes[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        spin3.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int position, long id) { 
                // TODO Auto-generated method stub
                spin3val = distance[position];//saving the value selected


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        ArrayAdapter<String> spin1adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, courses);
        spin1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(spin1adapter);
        ArrayAdapter<String> spin2adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, strokes);
        spin2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(spin2adapter);
        ArrayAdapter<String> spin3adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, distance);
        spin3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(spin3adapter);
        btnCreatePopup = (Button) findViewById(R.id.button1);
        btnCreatePopup.setOnClickListener(new OnClickListener() 
        {

        @Override
        public void onClick(View v) 
        {
        // TODO Auto-generated method stub
        calc();
        initiatePopupWindow();
        }
        });

        }
    
    	@Override
    	public void afterTextChanged(Editable arg0)
    	{
    		onex = et1.getText().toString();
    		if (onex.length()==0)
    			onex = "0";
    		twox = et2.getText().toString();
    		if (twox.length()==0)
    			twox = "0";
    		threex = et3.getText().toString(); 
    		if (threex.length()==0)
    			threex = "0";
    	}
    	
    	@Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) 
        {
            // TODO Auto-generated method stub
             
        }
         
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) 
        {
            // TODO Auto-generated method stub
                         
        }

        private PopupWindow pwindo;
 
        private void initiatePopupWindow() {
        	try {
        	// We need to get the instance of the LayoutInflater
        	LayoutInflater inflater = (LayoutInflater) MainActivity.this
        	.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	View layout = inflater.inflate(R.layout.popup,
        	(ViewGroup) findViewById(R.id.popup_element));
        	pwindo = new PopupWindow(layout, width*3/5, height/2, true);
        	pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        	tv1 = (TextView)pwindo.getContentView().findViewById(R.id.txtView2);
        	tv1.setText(lcm);
        	tv2 = (TextView)pwindo.getContentView().findViewById(R.id.txtView4);
        	tv2.setText(scm);
        	tv3 = (TextView)pwindo.getContentView().findViewById(R.id.txtView6);
        	tv3.setText(scy);
        	btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
        	btnClosePopup.setOnClickListener(cancel_button_click_listener);

        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        	}

        	private OnClickListener cancel_button_click_listener = new OnClickListener() 
        	{
        	public void onClick(View v) 
        	{
        	pwindo.dismiss();

        	}
        	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void calc()
    {
    	val1 = Double.parseDouble(onex);
    	val2 = Double.parseDouble(twox);
    	val3 = Double.parseDouble(threex);
    	
    	if (val1 == 0.0 && val2 == 0.0 && val3 == 0.0)
    		messageBox(0.0,0.0,0.0);
    	
    	else if (spin1val.equals(courses[0]))
        {
        	if (spin2val.equals(strokes[0]))
        	{
        		// 50 FR LCM
        		if (spin3val.equals(distance[0]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 0.8);
					val6 = (turnToSeconds(val1, val2, val3) - 0.8) / 1.11;
					messageBox(val4, val5, val6);
        		}
        		//100 FR LCM
        		if (spin3val.equals(distance[1]))
        		{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 1.6);
					val6 = (turnToSeconds(val1, val2, val3) - 1.6) / 1.11;
					messageBox(val4, val5, val6);
				}
        		//200 FR LCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 3.2);
					val6 = (turnToSeconds(val1, val2, val3) - 3.2) / 1.11;
					messageBox(val4, val5, val6);
				}
				//400 FR LCM
				if (spin3val.equals(distance[3]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 6.4);
					val6 = (turnToSeconds(val1, val2, val3)) / 0.8925;
					messageBox(val4, val5, val6);
				}
				//800 FR LCM
				if (spin3val.equals(distance[4]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 12.8);
					val6 = (turnToSeconds(val1, val2, val3)) / 0.8925;
					messageBox(val4, val5, val6);
				}
				//1500 FR LCM
				if (spin3val.equals(distance[5]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 24.0);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.02;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[1]))
        	{
        		//50 BK LCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 0.6);
					val6 = (turnToSeconds(val1, val2, val3) - 0.6) / 1.11;
					messageBox(val4, val5, val6);
				}
				//100 BK LCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 1.2);
					val6 = (turnToSeconds(val1, val2, val3) - 1.2) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 BK LCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 2.4);
					val6 = (turnToSeconds(val1, val2, val3) - 2.4) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[2]))
        	{
        		//50 BR LCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 1.0);
					val6 = (turnToSeconds(val1, val2, val3) - 1.0) / 1.11;
					messageBox(val4, val5, val6);
				}
				//100 BR LCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 2.0);
					val6 = (turnToSeconds(val1, val2, val3) - 2.0) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 BR LCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 4.0);
					val6 = (turnToSeconds(val1, val2, val3) - 4.0) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[3]))
        	{
				//50 FL LCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 0.7);
					val6 = (turnToSeconds(val1, val2, val3) - 0.7) / 1.11;
					messageBox(val4, val5, val6);
				}
				//100 FL LCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 1.4);
					val6 = (turnToSeconds(val1, val2, val3) - 1.4) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 FL LCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 2.8);
					val6 = (turnToSeconds(val1, val2, val3) - 2.8) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[4]))
        	{
				//200 IM LCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 3.2);
					val6 = (turnToSeconds(val1, val2, val3) - 3.2) / 1.11;
					messageBox(val4, val5, val6);
				}
				//400 IM LCM
				if (spin3val.equals(distance[3]))
				{
					val4 = (turnToSeconds(val1, val2, val3)+.005);
					val5 = (turnToSeconds(val1, val2, val3) - 6.4);
					val6 = (turnToSeconds(val1, val2, val3) - 6.4) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        }
    	else if (spin1val.equals(courses[1]))
        {
        	if (spin2val.equals(strokes[0]))
        	{
    			//50 FR SCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 0.8);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//100 FR SCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 1.6);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 FR SCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 3.2);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//400 FR SCM
				if (spin3val.equals(distance[3]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 6.4);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) * 1.153;
					messageBox(val4, val5, val6);
				}
				//800 FR SCM
				if (spin3val.equals(distance[4]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 12.8);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) * 1.153;
					messageBox(val4, val5, val6);
				}
				//1500 FR SCM
				if (spin3val.equals(distance[5]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 24.0);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) * 1.013;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[1]))
        	{
        		//50 BK SCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 0.6);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//100 BK SCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 1.2);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 BK SCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 2.4);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin3val.equals(strokes[2]))
        	{
        		//50 BR SCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 1.0);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}	
				//100 BR SCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 2.0);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 BR SCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 4.0);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[3]))
        	{
        		//50 FL SCM
				if (spin3val.equals(distance[0]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 0.7);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//100 FL SCM
				if (spin3val.equals(distance[1]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 1.4);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//200 FL SCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 2.8);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        	if (spin2val.equals(strokes[4]))
        	{
        		//200 IM SCM
				if (spin3val.equals(distance[2]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 3.2);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
				//400 IM SCM
				if (spin3val.equals(distance[3]))
				{
					val4 = (turnToSeconds(val1, val2, val3) + 6.4);
					val5 = (turnToSeconds(val1, val2, val3)+.005);
					val6 = (turnToSeconds(val1, val2, val3)) / 1.11;
					messageBox(val4, val5, val6);
				}
        	}
        }
    	else if (spin1val.equals(courses[2]))
        {
        	if (spin2val.equals(strokes[0]))
        	{
        		//50 FR SCY
        		if (spin3val.equals(distance[0]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+0.8);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//100 FR SCY
        		if (spin3val.equals(distance[1]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+1.6);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//200 FR SCY
        		if (spin3val.equals(distance[2]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+3.2);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//500 FR SCY
        		if (spin3val.equals(distance[3]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*.8925);
					val5 = (turnToSeconds(val1, val2, val3))/1.153;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//1000 FR SCY
        		if (spin3val.equals(distance[4]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*.8925);
					val5 = (turnToSeconds(val1, val2, val3))/1.153;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//1650 FR SCY
        		if (spin3val.equals(distance[5]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.02);
					val5 = (turnToSeconds(val1, val2, val3))/1.013;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        	}
        	if (spin2val.equals(strokes[1]))
        	{
        		//50 BK SCY
        		if (spin3val.equals(distance[0]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+0.6);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//100 BK SCY
        		if (spin3val.equals(distance[1]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+1.2);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//200 BK SCY
        		if (spin3val.equals(distance[2]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+2.4);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        	}
        	if (spin2val.equals(strokes[2]))
        	{
        		//50 BR SCY
        		if (spin3val.equals(distance[0]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+1.0);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//100 BR SCY
        		if (spin3val.equals(distance[1]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+2.0);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//200 BR SCY
        		if (spin3val.equals(distance[2]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+4.0);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        	}
        	if (spin2val.equals(strokes[3]))
        	{
        		//50 FL SCY
        		if (spin3val.equals(distance[0]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+0.7);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//100 FL SCY
        		if (spin3val.equals(distance[1]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+1.4);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//200 FL SCY
        		if (spin3val.equals(distance[2]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+2.8);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        	}
        	if (spin2val.equals(strokes[4]))
        	{
        		//200 IM SCY
        		if (spin3val.equals(distance[2]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+3.2);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        		//400 IM SCY
        		if (spin3val.equals(distance[3]))
        		{
        			val4 = (turnToSeconds(val1, val2, val3)*1.11+6.4);
					val5 = (turnToSeconds(val1, val2, val3))*1.11;
					val6 = (turnToSeconds(val1, val2, val3)+.005);
					messageBox(val4, val5, val6);
        		}
        	}
        }
    }
    
	public double turnToSeconds (double num1, double num2, double num3)
	{
		return num1 * 60 + num2 + 0.01 * num3;
	}
	
	public void messageBox (double v1, double v2, double v3)
	{
		int one, two, three, four, five, six, seven , eight, nine;
		String a, b, c, d, e, f;
		
		one = (int)v1/60;
		two = (int)v1%60;
		three = (int)((v1%1) * 100);
		
		four = (int)v2/60;
		five = (int)v2%60;
		six = (int)((v2%1) * 100);
		
		seven = (int)v3/60;
		eight = (int)v3%60;
		nine = (int)((v3%1) * 100);
		
		a = addExtraZero(two);
		b = addExtraZero(three);
		c = addExtraZero(five);
		d = addExtraZero(six);
		e = addExtraZero(eight);
		f = addExtraZero(nine);

		lcm = one + ":" + a + "." + b;
		scm = four + ":" + c + "." + d;
		scy = seven + ":" + e + "." + f;
	}
	
	public String addExtraZero (int nums)
	{
		String s = Integer.toString(nums);
		
		if (s.length() < 2)
		{
			s = "0" + s;
		}
		return s;
	}
}
