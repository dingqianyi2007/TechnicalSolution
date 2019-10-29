package com.dqy.technicalsolution.chapter.communicationnetwork.custom;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsItemFactory {

    public static class NewsItem{
        public String title;
        public String link;
        public String description;
        @Override
        public String toString() {
            return title;
        }
    }

    public static List<NewsItem> parseFeed(XmlPullParser parser)
        throws XmlPullParserException, IOException{
        List<NewsItem> items = new ArrayList<NewsItem>();
        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }

            if(parser.getName().equals("rss") ||
                parser.getName().equals("channel")){
            }else if(parser.getName().equals("item")){
                NewsItem newsItem = readItem(parser);
                items.add(newsItem);
            }else {
                skip(parser);
            }
        }
        return items;
    }
   private static NewsItem readItem(XmlPullParser parser) throws XmlPullParserException,IOException{
        NewsItem newsItem = new NewsItem();

        //开头必须是有效的<item>元素
       parser.require(XmlPullParser.START_TAG,null,"item");
       while (parser.next() != XmlPullParser.END_TAG){
           if(parser.getEventType() != XmlPullParser.START_TAG){
               continue;
           }

           String name = parser.getName();
           if(name.equals("title")){
               parser.require(XmlPullParser.START_TAG,null,"title");
               newsItem.title = readText(parser);
               parser.require(XmlPullParser.END_TAG,null,"title");
           }else if(name.equals("link")){
               parser.require(XmlPullParser.START_TAG,null,"link");
               newsItem.link = readText(parser);
               parser.require(XmlPullParser.END_TAG,null,"link");
           }else if(name.equals("description")){
               parser.require(XmlPullParser.START_TAG,null,"description");
               newsItem.description = readText(parser);
               parser.require(XmlPullParser.END_TAG,null,"description");
           }else {
               skip(parser);
           }
       }

       return newsItem;
   }


    private static String readText(XmlPullParser parser) throws IOException,XmlPullParserException {
        String result = "";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    /**/
    private static void skip(XmlPullParser parser) throws IOException,XmlPullParserException{
        if(parser.getEventType() != XmlPullParser.START_TAG){
            throw  new IllegalStateException();
        }
        /**
         *
         */
        int depth = 1;
        while (depth != 0){
            switch (parser.next()){
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }



}
