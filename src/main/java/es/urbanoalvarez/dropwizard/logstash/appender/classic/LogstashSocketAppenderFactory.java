package es.urbanoalvarez.dropwizard.logstash.appender.classic;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.net.SyslogConstants;
import com.fasterxml.jackson.annotation.JsonTypeName;
import es.urbanoalvarez.dropwizard.logstash.appender.LogstashAppenderFactoryHelper;
import io.dropwizard.logging.async.AsyncAppenderFactory;
import io.dropwizard.logging.filter.LevelFilterFactory;
import io.dropwizard.logging.layout.LayoutFactory;
import net.logstash.logback.appender.LogstashSocketAppender;

import java.io.IOException;

@JsonTypeName("logstash-socket")
public class LogstashSocketAppenderFactory extends AbstractLogstashLoggingEventFactory {
    public LogstashSocketAppenderFactory() {
        port = SyslogConstants.SYSLOG_PORT;
    }

    @Override
    public Appender build(LoggerContext context, String applicationName, LayoutFactory layoutFactory, LevelFilterFactory levelFilterFactory, AsyncAppenderFactory asyncAppenderFactory) {
        final LogstashSocketAppender appender = new LogstashSocketAppender();

        appender.setName("logstash-socket-appender");
        appender.setContext(context);
        appender.setHost(host);
        appender.setPort(port);

        appender.setIncludeCallerInfo(includeCallerInfo);
        appender.setIncludeMdc(includeMdc);
        appender.setIncludeContext(includeContext);

        if (customFields != null) {
            try {
                String custom = LogstashAppenderFactoryHelper.getCustomFieldsFromHashMap(customFields);
                appender.setCustomFields(custom);
            } catch (IOException e) {
                System.out.println("Unable to parse customFields: " + e.getMessage());
            }
        }

        if (fieldNames != null) {
            appender.setFieldNames(LogstashAppenderFactoryHelper.getFieldNamesFromHashMap(fieldNames));
        }

        asyncAppenderFactory.build();

        levelFilterFactory.build(threshold);
        appender.start();

        return wrapAsync(appender, asyncAppenderFactory);
    }
}