package com.tfjybj.dcp.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Response;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;

import java.util.List;

/**  SeimiCrawler 的爬虫框架入口，用来实现具体爬去数据的任务管理
 *
 * Created by zg on 2020/3/2.
 */
@Crawler(name = "basic")
public class Basic extends BaseSeimiCrawler {

    //教师账号登陆学习通后，查看课程首页
    private static String teacherCourseUrl = "https://mooc1-1.chaoxing.com/mycourse/teachercourse?moocId=207434064&clazzid=14910088&edit=true&v=0";
    private static  String courseMenuPath = "//div[@class='timeline']/div[@class='units']/div[@class='leveltwo']";

    //(无需登录）学习通首页-Portal
    private static String portalUrl = "https://mooc1-1.chaoxing.com/#";
    private static String portalCourseListPath = "//div[@class='listCon']/dl";

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
        return new String[]{portalUrl};
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
            List<Object> objList = doc.sel(portalCourseListPath);
            for (Object obj : objList){
                if(obj instanceof Element){

                    //console输出Portal页面中显示的课程名称（title)
                    System.out.println(((Element) obj).getElementsByAttribute("title").text());

                    //console输出当前Element内容
                    //System.out.println(obj.toString());
                }
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