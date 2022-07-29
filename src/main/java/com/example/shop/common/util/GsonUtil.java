package com.example.shop.common.util;

import com.google.gson.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Component
public class GsonUtil {

    public static Gson getTimeFormattingGson() {
        return new GsonBuilder()
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDate>) (localDate, typeOfSrc, context) -> new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate)))
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (localDateTime, typeOfSrc, context) -> new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime)))
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .create();
    }
}
