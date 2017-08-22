package es.urbanoalvarez.dropwizard.logstash.appender.classic;

import com.fasterxml.jackson.annotation.JsonProperty;
import es.urbanoalvarez.dropwizard.logstash.appender.AbstractLogstashAppenderFactory;

public abstract class AbstractLogstashLoggingEventFactory extends AbstractLogstashAppenderFactory {

    protected boolean includeCallerInfo;
    protected boolean includeContext;
    protected boolean includeMdc;

    public AbstractLogstashLoggingEventFactory() {
        super();
        this.includeCallerInfo = false;
        this.includeContext = true;
        this.includeMdc = true;
    }

    @JsonProperty
    public boolean isIncludeCallerInfo() {
        return includeCallerInfo;
    }

    @JsonProperty
    public void setIncludeCallerInfo(boolean includeCallerInfo) {
        this.includeCallerInfo = includeCallerInfo;
    }

    @JsonProperty
    public boolean isIncludeContext() {
        return includeContext;
    }

    @JsonProperty
    public void setIncludeContext(boolean includeContext) {
        this.includeContext = includeContext;
    }

    @JsonProperty
    public boolean isIncludeMdc() {
        return includeMdc;
    }

    @JsonProperty
    public void setIncludeMdc(boolean includeMdc) {
        this.includeMdc = includeMdc;
    }
}
