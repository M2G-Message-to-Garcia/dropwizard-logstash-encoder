package es.urbanoalvarez.dropwizard.logstash.appender.access;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import com.fasterxml.jackson.annotation.JsonTypeName;
import es.urbanoalvarez.dropwizard.logstash.appender.AbstractLogstashAppenderFactory;
import es.urbanoalvarez.dropwizard.logstash.appender.LogstashAppenderFactoryHelper;
import io.dropwizard.logging.async.AsyncAppenderFactory;
import io.dropwizard.logging.filter.LevelFilterFactory;
import io.dropwizard.logging.layout.LayoutFactory;
import net.logstash.logback.appender.LogstashAccessTcpSocketAppender;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashAccessEncoder;

import java.io.IOException;

@JsonTypeName("logstash-access")
public class LogstashAccessAppenderFactory extends AbstractLogstashAppenderFactory {

    public LogstashAccessAppenderFactory() {
        this.port = LogstashTcpSocketAppender.DEFAULT_PORT;
    }

    public Appender build(LoggerContext context, String applicationName, LayoutFactory layoutFactory, LevelFilterFactory levelFilterFactory, AsyncAppenderFactory asyncAppenderFactory) {
        final LogstashAccessTcpSocketAppender appender = new LogstashAccessTcpSocketAppender();
        final LogstashAccessEncoder encoder = new LogstashAccessEncoder();

        appender.setName("logstash-tcp-appender");
        appender.setContext(context);
        appender.setRemoteHost(host);
        appender.setPort(port);
        appender.setQueueSize(getQueueSize());


        if (customFields != null) {
            try {
                String custom = LogstashAppenderFactoryHelper.getCustomFieldsFromHashMap(customFields);
                encoder.setCustomFields(custom);
            } catch (IOException e) {
                System.out.println("unable to parse customFields: " + e.getMessage());
            }
        }

        if (fieldNames != null) {
            encoder.setFieldNames(LogstashAppenderFactoryHelper.getAccessFieldNamesFromHashMap(fieldNames));
        }

        appender.setEncoder(encoder);
        levelFilterFactory.build(threshold);
        encoder.start();
        appender.start();

        return wrapAsync(appender, asyncAppenderFactory);
    }
}
