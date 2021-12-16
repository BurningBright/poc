package cn.burningbright.poc;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.OutputStreamAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.OutputStream;
import java.net.URI;

public class ConfigurationTestUtils {

    static void updateLoggers(final Appender appender, final Configuration config) {
        final Level level = null;
        final Filter filter = null;
        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
            loggerConfig.addAppender(appender, level, filter);
        }
        config.getRootLogger().addAppender(appender, level, filter);
    }

    /**
     * Tests that you can add an output stream appender dynamically.
     */
    static void addAppender(final OutputStream outputStream, final String outputStreamName, String location) {
        final LoggerContext context = LoggerContext
                .getContext(null, false, URI.create(location));
        final Configuration config = context.getConfiguration();
        final PatternLayout layout = PatternLayout
                .newBuilder().withPattern(config.getAppender("STDOUT").getLayout().toString()).build();
        final Appender appender = OutputStreamAppender
                .createAppender(layout, null, outputStream, outputStreamName, false, true);
        appender.start();
        config.addAppender(appender);
        ConfigurationTestUtils.updateLoggers(appender, config);
    }

}
