package hr.ferit.mdudjak.bugsy;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String finalUrl = "http://www.bug.hr/rss/vijesti/";
    ListView lvNews;
    List<String> links,descriptions,titles,pubDates,images;
    private HandleXML obj;
    private SwipeRefreshLayout swipeContainer;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setUpUi();

    }
    private void setUpUi() {
        this.lvNews = (ListView) this.findViewById(R.id.lvNewsList);
        this.setUpListView();
        this.lvNews.setOnItemClickListener(this);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(false);

            }
        });
        

    }




    public void setUpListView(){
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);
        links=obj.getLinks();
        descriptions=obj.getDescriptions();
        titles=obj.getTitles();
        pubDates=obj.getPubDates();
        images = obj.getImages();
        List<News> news = new ArrayList<>();
        int i;
        for(i=1;i<titles.size();i++){
            news.add(new News(titles.get(i),descriptions.get(i),links.get(i),pubDates.get(i),images.get(i-1)));
        }
        this.newsAdapter = new NewsAdapter(news);
        this.lvNews.setAdapter(this.newsAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsAdapter adapter = (NewsAdapter) parent.getAdapter();
        News element = (News) adapter.getItem(position);
        Uri uri = Uri.parse(element.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
