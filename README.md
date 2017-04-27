#Android app for showing news from internet using API


Description of problems & solutions:


*User interface:

User interface consists of only one layout. Layout consists of one EditText, ImageButton and ListView.
In EditText user can input name of category according to which content will be filtered after pressing on button.
Layout of list's elements is defined in separate .xml layout file. 
Content of one element in the list consists of: Title of the news, description of the news, date when news was published and cover image of news.
By clicking on one element of the list, app redirects to url of the link which opens in browser.


*News class

News class contains some information about the news. All variables in the class are: link, description, title, publishDate, image and category. 
All variables are type string. For all variables getter function is defined.
Title, description, image and publish date of the news are presented in ListView element. Link is used for redirecting app to browser app.
Category variable is used for filtering news based on users's input in EditText.



*Working with ListView

Working with ListView required couple of things. 
First it was neccessary to put ListView container in main layout. 
After that I created News class which contains information about news, and a news_item.xml file which represents a layout for one item in the ListView. 
In MainActivity a ListView object is created and connected with associated ID. 
To the ListView object an NewsAdapter was set. 
NewsAdapter is class that extends BaseAdapter class and overrides its functions. 
Also, in MainActivity, I created ArrayList of News and news are stored dynamically.
Storing objects of News class in ArrayList of News is done in the for loop. The loop iterates by 1 from 1 to size of titles array. 
After parsing RSS webpage i.e. XML file, number of arrays are created for storing results of parsing. 
All arrays have equal size, except images and categories which have one element less than other arrays. 
That was concluded after analyzing content of RSS file. 

Source: https://loomen.carnet.hr/pluginfile.php/781974/mod_resource/content/2/LV4%20-%20predlo%C5%BEak%20%282017%29.pdf


*NewsAdapter Class

NewsAdapter class extends BaseAdapter class and overrides functions: getCount(), getItem(), getItemId(), and getView(). 
In a getView() function View object is returned. 
Function uses ViewHolder object for setting Tag on returned View object. 
If an object is null, it inflates task_item layout. 
Also, in NewsAdapter class inner class NewsViewHolder is defined. That class contains all elements in the layout for one element of the ListView and that elements are connected with associated ID.
Constructor of NewsAdapter class receives object of News class. Using its getter methods NewsAdapter class sets text in all TextViews that are defined in NewsViewHolder class.
For ImageView Picasso library was used for converting url to image. 


*XMLParser

I used XMLPullParser for parsing RSS feed. 
The first step is to identify the fields in the XML data in which you are interested in.
In the next step, XMLPullParser object is created , but in order to create that firstly it was neccessary to create XmlPullParserFactory object and then its newPullParser() method was called to create XMLPullParser.
The next step involves specifying the file for XmlPullParser that contains XML. It could be a file or could be a Stream. 
The last step is to parse the XML. An XML file consist of events, Name, Text, AttributesValue e.t.c. So XMLPullParser has a separate function for parsing each of the component of XML file.
In case of event and specificaly END TAG, switch - case statement checks value of that tag. And on the appearance of the appropriate tag, value under that tag is stored in appropriate array.
Arrays are just like in the News class: titles, descriptions, link, images, pubDates and categories.
For accessing page on the internet, HttpUrlConnection library was used. 

Source: https://www.tutorialspoint.com/android/android_xml_parsers.htm
