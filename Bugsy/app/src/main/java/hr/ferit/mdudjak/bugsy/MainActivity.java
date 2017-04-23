package hr.ferit.mdudjak.bugsy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String finalUrl = "http://www.bug.hr/rss/vijesti/";
    Button mButton;
    ListView lvNews;
    List<String> links,descriptions,titles;
    private HandleXML obj;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setUpUi();

    }
    private void setUpUi() {
        this.mButton = (Button) this.findViewById(R.id.mButton);
        this.mButton.setOnClickListener(this);
        this.lvNews = (ListView) this.findViewById(R.id.lvNewsList);
    }

    @Override
    public void onClick(View v) {
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);
        links=obj.getLinks();
        descriptions=obj.getDescriptions();
        titles=obj.getTitles();
        List<News> news = new ArrayList<>();
        news.add(new News(titles.get(0),descriptions.get(0),links.get(0)));
        news.add(new News(titles.get(1),descriptions.get(1),links.get(1)));
        news.add(new News(titles.get(2),descriptions.get(2),links.get(2)));
        news.add(new News(titles.get(3),descriptions.get(3),links.get(3)));
        news.add(new News(titles.get(4),descriptions.get(4),links.get(4)));
        this.newsAdapter = new NewsAdapter(news);
        this.lvNews.setAdapter(this.newsAdapter);
    }


}
