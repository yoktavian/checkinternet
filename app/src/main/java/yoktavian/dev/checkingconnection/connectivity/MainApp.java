package yoktavian.dev.checkingconnection.connectivity;

import android.app.Application;

/**
 * Created by yoktavian on 11/19/17.
 */

public class MainApp extends Application {
    private static MainApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MainApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
