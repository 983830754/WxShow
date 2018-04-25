package com.example.demo.message.picAndtext;

import com.example.demo.message.BaseMessage;
import com.example.demo.message.picAndtext.Article;

import java.util.List;

/**
 * Created by wangwei on 17/10/13.
 */
public class NewsMessage extends BaseMessage {

    // 图文消息个数，限制为10条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

}
