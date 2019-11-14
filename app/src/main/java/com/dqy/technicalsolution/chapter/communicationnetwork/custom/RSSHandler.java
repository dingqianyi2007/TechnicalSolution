package com.dqy.technicalsolution.chapter.communicationnetwork.custom;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class RSSHandler extends DefaultHandler {
    public class NewsItem{
        public String title;
        public String link;
        public String description;

        @Override
        public String toString() {
            return title;
        }
    }

    private StringBuffer buf;
    private ArrayList<NewsItem> feedItems;
    private NewsItem item;
    private boolean inItem = false;

    public ArrayList<NewsItem> getParsedItems(){
        return feedItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("channel".equals(localName)){
            feedItems = new ArrayList<NewsItem>();
        }else if("item".equals(localName)){
            item = new NewsItem();
            inItem = true;
        }else if("title".equals(localName) && inItem){
            buf = new StringBuffer();
        }else if("link".equals(localName) && inItem){
            buf = new StringBuffer();
        }else if("description".equals(localName) && inItem){
            buf = new StringBuffer();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("item".equals(localName)){
            feedItems.add(item);
            inItem = false;
        }else if("title".equals(localName) && inItem){
            item.title = buf.toString();
        }
        else if("link".equals(localName) && inItem){
            item.title = buf.toString();
        }
        else if("description".equals(localName) && inItem){
            item.title = buf.toString();
        }
        buf = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(buf != null){
            for(int i = start; i < start + length; i++){
                buf.append(ch[i]);
            }
        }
    }
}
