package cz.kpmg.java.app.sample.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.apache.commons.lang3.RegExUtils;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.util.Objects;
import java.util.regex.Pattern;

public class MaskLogFilter extends TurboFilter {

    private final boolean mask;
    private static final Marker secured = MarkerFactory.getMarker("secured");
    private static final String PACKAGE = "cz.kpmg.java.app";

    public MaskLogFilter(String mask) {
        this.mask = Boolean.parseBoolean(mask);
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable throwable) {
        if (mask && Objects.nonNull(format) && logger.getName().startsWith(PACKAGE)) {
            boolean isSecured = Objects.nonNull(marker) && (marker.equals(secured) || marker.contains(secured));
            if (!isSecured) {
                if (Objects.isNull(marker)) {
                    marker = secured;
                } else {
                    marker.add(secured);
                }
                String message = MessageFormatter.arrayFormat(format, params).getMessage();
                message = maskMessage(message);
                logger.log(marker, logger.getName(), Level.toLocationAwareLoggerInteger(level), message, null, throwable);
                return FilterReply.DENY;
            }
        }
        return FilterReply.NEUTRAL;
    }

    public static final Pattern EMAIL_PATTERN = Pattern.compile("([a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)");

    private String maskMessage(String message) {
        String result = RegExUtils.replaceAll(message, EMAIL_PATTERN, "*");
        return result;
    }

}
