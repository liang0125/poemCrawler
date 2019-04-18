package io.github.poemHelper.crawler.parse;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import io.github.poemHelper.config.ConfigProperties;
import io.github.poemHelper.crawler.common.Page;

import java.io.IOException;
import java.util.List;

/**
 * 文档页（内含连接）
 *
 * Author:lorrie
 * Create:2019/3/17
 */
public class DocumentParse implements Parse {

    @Override
    public void parse(final Page page) {
        if (page.isDetail()) {
            return;
        }
        HtmlPage htmlPage = page.getHtmlPage();
        htmlPage.getBody()
                .getElementsByAttribute("div", "class", "typecont")
                .forEach(div -> {
                    DomNodeList<HtmlElement> aNodeList = div.getElementsByTagName("a");
                    aNodeList.forEach(
                            aNode -> {
                                String path = aNode.getAttribute("href");
                                Page subPage = new Page(
                                        page.getBase(),
                                        path,
                                        true
                                );
                                page.getSubPage().add(subPage);
                            }
                    );
                });
    }
}
