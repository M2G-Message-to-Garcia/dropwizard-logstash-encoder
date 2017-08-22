package es.urbanoalvarez.dropwizard.logstash.appender;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.fieldnames.LogstashAccessFieldNames;
import net.logstash.logback.fieldnames.LogstashFieldNames;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class LogstashAppenderFactoryHelper {
    public static LogstashFieldNames getFieldNamesFromHashMap(HashMap<String, String> map) {
        LogstashFieldNames fieldNames = new LogstashFieldNames();

        fieldNames.setTimestamp(map.getOrDefault("timestamp", "@timestamp"));
        fieldNames.setVersion(map.getOrDefault("version", "@version"));
        fieldNames.setMessage(map.getOrDefault("message", "message"));
        fieldNames.setLogger(map.getOrDefault("logger", "logger_name"));
        fieldNames.setThread(map.getOrDefault("thread", "thread_name"));
        fieldNames.setLevel(map.getOrDefault("level", "level"));
        fieldNames.setLevelValue(map.getOrDefault("levelValue", "level_value"));
        fieldNames.setCaller(map.getOrDefault("caller", null));
        fieldNames.setCallerClass(map.getOrDefault("callerClass", "caller_class_name"));
        fieldNames.setCallerMethod(map.getOrDefault("callerMethod", "caller_method_name"));
        fieldNames.setCallerFile(map.getOrDefault("callerFile", "caller_file_name"));
        fieldNames.setCallerLine(map.getOrDefault("callerLine", "caller_line_number"));
        fieldNames.setStackTrace(map.getOrDefault("stackTrace", "stack_trace"));
        fieldNames.setTags(map.getOrDefault("tags", "tags"));
        fieldNames.setMdc(map.getOrDefault("mdc", null));
        fieldNames.setContext(map.getOrDefault("context", null));

        return fieldNames;
    }

    public static LogstashAccessFieldNames getAccessFieldNamesFromHashMap(HashMap<String, String> map) {
        LogstashAccessFieldNames fieldNames = new LogstashAccessFieldNames();

        fieldNames.setTimestamp(map.getOrDefault("timestamp", "@timestamp"));
        fieldNames.setVersion(map.getOrDefault("version", "@version"));
        fieldNames.setMessage(map.getOrDefault("message", "message"));
        fieldNames.setFieldsMethod(map.getOrDefault("fieldsMethod", "@fields.method"));
        fieldNames.setFieldsProtocol(map.getOrDefault("fieldsProtocol", "@fields.protocol"));
        fieldNames.setFieldsStatusCode(map.getOrDefault("fieldsStatusCode", "@fields.status_code"));
        fieldNames.setFieldsRequestedUrl(map.getOrDefault("fieldsRequestedUrl", "@fields.requested_url"));
        fieldNames.setFieldsRequestedUri(map.getOrDefault("fieldsRequestedUri", "@fields.requested_uri"));
        fieldNames.setFieldsRemoteHost(map.getOrDefault("fieldsRemoteHost", "@fields.remote_host"));
        fieldNames.setFieldsHostname(map.getOrDefault("fieldsHostname", "@fields.HOSTNAME"));
        fieldNames.setFieldsRemoteUser(map.getOrDefault("fieldsRemoteUser", "@fields.remote_user"));
        fieldNames.setFieldsContentLength(map.getOrDefault("fieldsContentLenght", "@fields.content_length"));
        fieldNames.setFieldsElapsedTime(map.getOrDefault("fieldsElapsedTime", "@fields.elapsed_time"));
        fieldNames.setFieldsRequestHeaders(map.getOrDefault("fieldsRequestHeaders", "@fields.request_headers"));
        fieldNames.setFieldsResponseHeaders(map.getOrDefault("fieldsResponseHeaders", "@fields.response_headers"));

        return fieldNames;
    }

    public static String getCustomFieldsFromHashMap(HashMap<String, String> map) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, map);
        return writer.toString();
    }
}