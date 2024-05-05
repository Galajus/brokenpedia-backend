package pl.galajus.brokenpediabackend.admin.common.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SlugifyUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "hello word.png, hello-word.png",
            "ąęśćżźńłó.png, aesczznlo.png",
            "Produkt 1.png, produkt-1.png",
            "Produkt - 1.png, produkt-1.png",
            "Produkt__1.png, produkt-1.png",
    })
    void shouldSlugifyFileName(String in, String out) {
        String fileName = SlugifyUtils.slugifyFileName(in);
        assertEquals(out, fileName);
    }

    @ParameterizedTest
    @CsvSource({
            "test test, test-test",
            "hello word, hello-word",
            "ąęśćżźńłó, aesczznlo",
            "Produkt 1, produkt-1",
            "Produkt - 1, produkt-1",
            "Produkt__1, produkt-1",
    })
    void shouldSlugifySlug(String in, String out) {
        String slug = SlugifyUtils.slugifySlug(in);
        assertEquals(out, slug);
    }
}