package dotadodge.core.misc;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StdOutErrLog {

    private static final Logger logger = LoggerFactory.getLogger(StdOutErrLog.class);

    public static void tieSystemOutAndErrToLog() {
        System.setOut(createLoggingProxy(System.out));
        System.setErr(createLoggingProxy(System.err));
    }

    public static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
                //realPrintStream.print(string); // commented because we use log4j ConsoleAppender
                logger.info(string);
            }
        };
    }
}