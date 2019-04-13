import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import io.github.poemHelper.crawler.common.Page;
import io.github.poemHelper.crawler.parse.DataPageParse;
import io.github.poemHelper.crawler.parse.DocumentParse;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Author:lorrie
 * Create:2019/3/17
 */
public class Test {
    public static void main(String[] args) {
        try(WebClient webClient=new WebClient(BrowserVersion.CHROME)){
            webClient.getOptions().setJavaScriptEnabled(false);
            HtmlPage htmlPage=webClient.getPage("https://www.gushiwen.org/");
            HtmlElement htmlElement=htmlPage.getBody();
            String text=htmlElement.asText();
            System.out.println(text);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

}
