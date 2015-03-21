package org.xl.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by meulmees on 3/21/2015.
 */
public class ExceptionUtils {

    /**
     * Returns the StackTrace from a Throwable as a String
     *
     * @param throwable
     * @return StackTrace as a String
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
