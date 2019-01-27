package com.example.radek.handlerthread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        Handler.Callback {
    protected Handler theHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Handler(Handler.Callback callback)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theHandler = new Handler(this);
    }

    @Override
    public boolean handleMessage(Message aMessage) {

        TextView theTextView = (TextView) findViewById(R.id.theTextView);

        switch (aMessage.what) {
            case MyHandlerThread.MSG_RESPONSE_ID:
                theTextView.setText((String) aMessage.obj);
                break;
            default:
                return false;
        }
        return true;
    }

    protected MyHandlerThread theMyHandlerThread;

    @Override
    protected void onResume() {
        super.onResume();
        theMyHandlerThread = new MyHandlerThread(theHandler);
        theMyHandlerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        theMyHandlerThread.quit();
        theMyHandlerThread = null;
    }

    public void onClick_Calculate(View aView) {
        theMyHandlerThread.calculate();


    }
}

