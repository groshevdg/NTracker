package ru.groshevdg.data.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import ru.groshevdg.data.network.annotation.Json;
import ru.groshevdg.data.network.annotation.Xml;

public class XmlOrJsonConverterFactory extends Converter.Factory {
    final Converter.Factory xml = SimpleXmlConverterFactory.create();
    final Converter.Factory gson = GsonConverterFactory.create();

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {

        // Retrofit gives us all the annotations so we just need to check
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Xml.class) {
                return xml.responseBodyConverter(type, annotations, retrofit);
            }
            if (annotation.annotationType() == Json.class) {
                return gson.responseBodyConverter(type, annotations, retrofit);
            }
        }
        // There is no annotation so we cannot handle it
        return null;
    }
}
