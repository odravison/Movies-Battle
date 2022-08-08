package odravison.letscodechallenge.moviesbattle.config.security;

import org.springframework.context.ApplicationContext;

public class AppContext {
    private AppContext() {
        throw new IllegalStateException(AppContext.class.getName());
    }

    /**
     * Spring application context
     */
    private static ApplicationContext ctx;

    /**
     * Loads application context
     */
    public static void loadApplicationContext(ApplicationContext appCtx) {
        ctx = appCtx;
    }

    /**
     * Returns a bean in this context
     */
    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }
}
