package odravison.letscodechallenge.moviesbattle.utils;

import javax.servlet.http.HttpServletResponse;

public class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException(SecurityUtils.class.getName());
    }

    /**
     * Fills the Header with the Access Control.
     *
     */
    public static HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type, refresh-token, user");
        response.setHeader("Access-Control-Expose-Headers", "authorization, content-type, refresh-token, user");
        response.setHeader("Content-Type", "application/json");

        return response;
    }

}
