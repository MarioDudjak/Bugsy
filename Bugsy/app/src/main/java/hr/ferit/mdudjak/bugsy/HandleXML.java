package hr.ferit.mdudjak.bugsy;

/**
 * Created by Mario on 23.4.2017..
 */

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class HandleXML {

    private String urlString = null;
    List links,titles,descriptions,pubdates,images,categories;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public HandleXML(String url){
        this.urlString = url;
    }

    public List getLinks(){
       return links;
   }
    public List getTitles(){
        return titles;
    }
    public List getDescriptions(){
        return descriptions;
    }
    public List getPubDates(){
        return pubdates;
    }
    public List getImages(){
        return images;
    }
    public List getCategories(){
        return categories;
    }

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        links = new ArrayList();
        descriptions = new ArrayList();
        titles = new ArrayList();
        pubdates = new ArrayList();
        images = new ArrayList();
        categories = new ArrayList();
        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(name.equals("title")){
                            titles.add(text);
                        }

                        else if(name.equals("link")){
                            links.add(text);
                        }
                        else if(name.equals("description")){
                            descriptions.add(text);
                        }

                        else if(name.equals("pubDate")){
                            pubdates.add(text);
                        }
                        else if(name.equals("category")){
                            categories.add(text);
                        }

                        else if(name.equals("enclosure")){
                            String url= myParser.getAttributeValue(null,"url");
                            images.add(url);
                        }

                        else{
                        }

                        break;
                }

                event = myParser.next();
            }

            parsingComplete = false;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }

                catch (Exception e) {
                }
            }
        });
        thread.start();
    }
}
