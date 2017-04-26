package hr.ferit.mdudjak.bugsy;

import java.util.List;

/**
 * Created by Mario on 23.4.2017..
 */

public class News{
    private String mLink;
    private String mDescription;
    private String mTitle;
    private String mPubDate;
    private String mImage;
    private String mCategory;
    public News(String title, String description, String link, String pubDate,String image, String category){
        this.mTitle=title;
        this.mDescription=description;
        this.mLink=link;
        this.mPubDate= pubDate;
        this.mImage = image;
        this.mCategory = category;
    }

    public String getTitle() { return mTitle; }
    public String getLink() { return mLink; }
    public String getImage() { return mImage;}
    public String getDescription() { return mDescription; }
    public String getPubDate() { return mPubDate; }
    public String getCategory() { return mCategory; }
}