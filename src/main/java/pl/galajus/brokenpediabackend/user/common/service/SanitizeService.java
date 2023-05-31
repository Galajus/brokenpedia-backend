package pl.galajus.brokenpediabackend.user.common.service;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

@Service
public class SanitizeService {

    public static String cleanCompletely(String content) {
        return Jsoup.clean(content, Safelist.none());
    }

    public static String cleanBasic(String content) {
        return Jsoup.clean(content, Safelist.basic());
    }

    public static String cleanRelaxed(String content) {
        Safelist font = Safelist.relaxed().addTags("font");
        font.addAttributes("font", "size", "color");
        return Jsoup.clean(content, font);
    }

}
