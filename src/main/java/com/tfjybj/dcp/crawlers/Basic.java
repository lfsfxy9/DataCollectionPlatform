package com.tfjybj.dcp.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

/**  SeimiCrawler 的爬虫框架入口，用来实现具体爬去数据的任务管理
 *
 * Created by zg on 2020/3/2.
 */
@Crawler(name = "basic")
public class Basic extends BaseSeimiCrawler {
    /**
     * @description 用来标记要爬取数据的url地址
     * @author jizg
     * @param
     * @updateTime 2020/3/13 18:08:00
     * @return
     * @throws
     */
    @Override
    public String[] startUrls() {
        return new String[]{"http://www.chaoxing.com/","https://mooc1-1.chaoxing.com/work/getAllWork?classId=14910088&courseId=207434064&isdisplaytable=2&mooc=1&ut=t&enc=eb9a9c828ebd4f8b129c83b2b4bb658e&cpi=123274613&openc=65d00e69846767fa4a03b0497b19deb4"};
    }

    /**
     * @description 处理StartUrl返回的请求数据
     * @author jizg
     * @param
     * @updateTime 2020/3/13 18:08:40
     * @return
     * @throws
     */
    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> urls = doc.sel("//a[@class='searchMenu clearfix']");
            logger.info("{}", urls.size());
            for (Object s:urls){
                push(Request.build(s.toString(), Basic::getTitle));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author jizg
     * @param
     * @updateTime 2020/3/13 18:10:20
     * @return
     * @throws
     */
    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}