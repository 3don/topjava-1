package ru.javawebinar.topjava;

import org.slf4j.Logger;
import ru.javawebinar.topjava.web.UserServlet;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    private static final Logger log = getLogger(UserServlet.class);

    public static void main(String[] args) {
        log.debug("Start in Main");
        System.out.format("Hello TopJava Enterprise!");
    }
}
