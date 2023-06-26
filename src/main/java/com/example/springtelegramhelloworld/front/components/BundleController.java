package com.example.springtelegramhelloworld.front.components;

import java.util.*;

public final class BundleController {
    private static final Map<Language, ResourceBundle> RESOURCE_BUNDLE_MAP = new HashMap();

    static {
        for (Language lang : Language.values()) {
            RESOURCE_BUNDLE_MAP.put(lang, ResourceBundle.getBundle(
                    "language\\messages"
                    , Locale.forLanguageTag(lang.getValue())));
        }
    }

    public static ResourceBundle getBundle(Language language){
        return RESOURCE_BUNDLE_MAP.get(language);
    }

}
