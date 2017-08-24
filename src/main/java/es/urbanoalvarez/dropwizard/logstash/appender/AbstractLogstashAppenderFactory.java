package es.urbanoalvarez.dropwizard.logstash.appender;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.logging.AbstractAppenderFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

public abstract class AbstractLogstashAppenderFactory extends AbstractAppenderFactory {

    @NotNull
    protected String host;

    @Min(1)
    @Max(65535)
    protected int port;

    protected HashMap<String, String> customFields;

    protected HashMap<String, String> fieldNames;

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public HashMap<String, String> getCustomFields() {
        return customFields;
    }

    @JsonProperty
    public void setCustomFields(HashMap<String, String> customFields) {
        this.customFields = customFields;
    }

    @JsonProperty
    public HashMap<String, String> getFieldNames() {
        return fieldNames;
    }

    @JsonProperty
    public void setFieldNames(HashMap<String, String> fieldNames) {
        this.fieldNames = fieldNames;
    }
}