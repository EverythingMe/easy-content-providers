package me.everything.providers.sample;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.InspectorModulesProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

import java.util.ArrayList;

import me.everything.providers.core.Data;
import me.everything.providers.sample.custom.Post;
import me.everything.providers.sample.custom.PostsProvider;
import me.everything.providers.stetho.ProvidersStetho;

public class MainApplication extends Application {

    public void onCreate() {
        super.onCreate();

        Context context = this;
        ProvidersStetho providersStetho = new ProvidersStetho(context);
        providersStetho.enableDefaults();
        providersStetho.registerProvider("provider-custom", "posts", new ProvidersStetho.QueryExecutor<Post>() {
            @Override
            public Data<Post> onQuery(String query) {
                PostsProvider provider = new PostsProvider(getApplicationContext());
                return provider.getPosts();
            }
        });

        // stetho init
        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                        .enableWebKitInspector(new ExtInspectorModulesProvider(context, providersStetho))
                        .build());
    }

    private static class ExtInspectorModulesProvider implements InspectorModulesProvider {

        private Context mContext;
        private ProvidersStetho mProvidersStetho;

        private ExtInspectorModulesProvider(Context context, ProvidersStetho providersStetho) {
            mContext = context;
            mProvidersStetho = providersStetho;
        }

        @Override
        public Iterable<ChromeDevtoolsDomain> get() {
            ArrayList plugins = (ArrayList) Stetho.defaultInspectorModulesProvider(mContext).get();
            plugins.add(mProvidersStetho.getChromeDevtoolsDomain());
            return plugins;
        }
    }
}
