package com.example.byg.exam_0120.activities;

import android.app.ListActivity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.databinding.ItemNewsBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XMLParsingActivity extends ListActivity {

    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClient = new OkHttpClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("https://news.google.co.kr/news?cf=all&hl=ko&pz=1&ned=kr&output=rss")
                            .build();

                    Response response = null;
                    response = mClient.newCall(request).execute();
                    final String xml = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 어댑터 꽂아주기
                            List<News> data = parse(xml);
                            NewsAdapter adapter = new NewsAdapter(data);
                            setListAdapter(adapter);
                            //Toast.makeText(XMLParsingActivity.this, xml, Toast.LENGTH_SHORT).show();
                        }
                    });
                    // 파싱

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private List<News> parse(String xml) {
        try {
            try {
                return new NewsParser().parse(xml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class News {
        String title;
        String link;
        String pubDate;
        String category;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("News{");
            sb.append("title='").append(title).append('\'');
            sb.append(", link='").append(link).append('\'');
            sb.append(", pubDate='").append(pubDate).append('\'');
            sb.append(", category='").append(category).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    private static class NewsAdapter extends BaseAdapter {

        private List<News> mData;
        private ItemNewsBinding mBinding;

        public NewsAdapter(List<News> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                        , R.layout.item_news, parent, false);

                convertView = mBinding.getRoot();

                holder = new ViewHolder();

                holder.titleTextView = mBinding.titleText;
                holder.dateTextView = mBinding.dateText;
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            News news = (News) getItem(position);
            holder.titleTextView.setText(news.title);
            holder.dateTextView.setText(news.pubDate);

            return convertView;
        }
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
    }

    private static class NewsParser {
        public List<News> parse(String xml) throws XmlPullParserException, IOException {

            List<News> newsList = new ArrayList<>();
            News news = null;
            String text = "";
            boolean isItem = false;

            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,
                    false);

            parser.setInput(new StringReader(xml));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equals("item")) {
                            news = new News();
                            isItem = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (isItem) {
                            text = parser.getText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (isItem) {
                            // 태그가 item 일때만 동작
                            if (tagName.equals("item")) {
                                newsList.add(news);
                                isItem = false;
                            } else if (tagName.equals("title")) {
                                news.title = text;
                            } else if (tagName.equals("link")) {
                                news.link = text;
                            } else if (tagName.equals("category")) {
                                news.category = text;
                            } else if (tagName.equals("pubDate")) {
                                news.pubDate = text;
                            }
                        }
                        break;
                    default:
                }
                eventType = parser.next();
            }
            return newsList;
        }
    }
}
