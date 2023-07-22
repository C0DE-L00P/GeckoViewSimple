package org.devloop.chattygecko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public class MainActivity extends AppCompatActivity {
    private GeckoSession session = new GeckoSession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeckoView view = findViewById(R.id.geckoview);
        GeckoRuntime runtime = GeckoRuntime.create(this);
        session.setNavigationDelegate(navigationDelegate);

        session.open(runtime);
        view.setSession(session);
        session.loadUri("http://192.168.1.6:5500");
    }

    private class MyNavigationDelegate implements GeckoSession.NavigationDelegate {
        public boolean canGoBack = false;

        @Override
        public void onCanGoBack(GeckoSession session, boolean canGoBack) {
            this.canGoBack = canGoBack;
        }
    }

    private MyNavigationDelegate navigationDelegate = new MyNavigationDelegate();

    public void onBackPressed() {
        if (navigationDelegate.canGoBack) {
            session.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
