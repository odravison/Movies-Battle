package odravison.letscodechallenge.moviesbattle.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;

public class JSONUtils {
    /**
     * Instance of the converter.
     */
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * Converts the object into JSon as string.
     *
     * @param object
     * 		Object.
     * @return JSon as string.
     */
    public static String toJSon(Object object) {
        return GSON.toJson(object);
    }

    /**
     * Converts the JSon as string into object.
     *
     * @param json
     * 		JSon as string.
     * @param clazz
     * 		Class of the target object.
     * @return Object.
     */
    public static <T> T fromJSon(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    /**
     * Converts the JSon as string into object.
     *
     * @param reader
     * 		Reader.
     * @param clazz
     * 		Class of the target object.
     * @return Object.
     */
    public static <T> T fromJSon(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }
}
