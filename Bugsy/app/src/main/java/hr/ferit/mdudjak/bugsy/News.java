package hr.ferit.mdudjak.bugsy;

import java.util.List;

/**
 * Created by Mario on 23.4.2017..
 */

public class News{
    private String mLink;
    private String mDescription;
    private String mTitle;
    private List<String> mTitles;
    private List<String> mDescriptions;
    private List<String> mLinks;
    public News(String title, String description, String link){
        this.mTitle=title;
        this.mDescription=description;
        this.mLink=link;
    }
    /*
    public News(List<String> titles, List<String> descriptions, List<String> links){
        this.mTitles=titles;
        this.mDescriptions=descriptions;
        this.mLinks=links;
    }
    */
    public String getTitle() { return mTitle; }
    public String getLink() { return mLink; }
    public String getDescription() { return mDescription; }
}