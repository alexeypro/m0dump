package ${groupId}.controllers;

import ${groupId}.base.Common;
import ${groupId}.base.Constants;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Controller
public class SitemapController extends Common {

    @RequestMapping("/google.do")
    public ModelAndView googleHandler(HttpServletResponse response, HttpServletRequest request) throws IOException {
        this.logger.debug("googleHandler()");
        Document doc = new DocumentImpl();
	Element urlset = doc.createElement("urlset");
	urlset.setAttribute("xmlns", "http://www.google.com/schemas/sitemap/0.84");
	Element url = doc.createElement("url");
	Element loc = doc.createElement("loc");
        loc.setTextContent("http://www.${artifactId}.com");
        url.appendChild(loc);
        Element lastmod = doc.createElement("lastmod");
        SimpleDateFormat datetimeW3C = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	lastmod.setTextContent(datetimeW3C.format(System.currentTimeMillis()));
        url.appendChild(lastmod);
        Element changefreq = doc.createElement("changefreq");
        changefreq.setTextContent("always");
        url.appendChild(changefreq);
        urlset.appendChild(url);
	doc.appendChild(urlset);

        try {
            PrintWriter out = response.getWriter();
            response.setContentType(Constants.CONTENTTYPE_XML);
            OutputFormat formatter = new OutputFormat();
            formatter.setPreserveSpace(true);
            XMLSerializer serializer = new XMLSerializer(out, formatter);
            serializer.serialize(doc);
        } catch ( Exception ex ) {
            logger.error(ex);
        }
        return null;
    }

}
