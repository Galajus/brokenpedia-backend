package pl.galajus.brokenpediabackend.user.common.service;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

@Service
public class SanitizeService {

    public static String cleanCompletely(String content) {
        if (content == null) {
            return null;
        }
        return Jsoup.clean(content, Safelist.none());
    }

    public static String cleanBasic(String content) {
        if (content == null) {
            return null;
        }
        return Jsoup.clean(content, Safelist.basic());
    }

    public static String cleanRelaxed(String content) {
        if (content == null) {
            return null;
        }
        Safelist font = Safelist.relaxed().addTags("font");
        font.addAttributes("font", "size", "color");
        font.removeTags("img");
        font.removeAttributes("img", "align", "alt", "height", "src", "title", "width");
        return Jsoup.clean(content, font);
    }

}
