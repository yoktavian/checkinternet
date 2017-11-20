package yoktavian.dev.checkingconnection;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import yoktavian.dev.checkingconnection.connectivity.ConnectivityReceiver;
import yoktavian.dev.checkingconnection.connectivity.MainApp;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private boolean isConnected;
    private TextView txt_result;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_result = (TextView) findViewById(R.id.txt_result);
        checkConnection();
    }

    private void checkConnection() {
        isConnected = ConnectivityReceiver.isConnected();
        message(isConnected);
    }

    private void message(boolean isConnected) {
        int color;
        if (isConnected) {
            txt_result.setText("connected");
            message = "You are connected";
            color = Color.WHITE;
        } else {
            txt_result.setText("no internet connection");
            message = "Not connected to internet";
            color = Color.RED;
        }
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.mainBar), message, Snackbar.LENGTH_LONG);
        View v = snackbar.getView();
        TextView textView = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.getInstance().setConnectivityListener(this);
    }

    // Callback network connection
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        message(isConnected);
    }
}
