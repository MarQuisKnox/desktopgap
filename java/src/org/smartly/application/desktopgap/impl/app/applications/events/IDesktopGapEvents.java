package org.smartly.application.desktopgap.impl.app.applications.events;

import org.smartly.commons.event.Events;
import org.smartly.commons.util.StringUtils;

/**
 * Listen frame events
 */
public interface IDesktopGapEvents {

    public static final String EVENT_DATA = "data";
    public static final String EVENT_READY = "ready";
    public static final String EVENT_DEVICEREADY = "deviceready";
    public static final String EVENT_PLUGIN_READY = "pluginready";
    public static final String EVENT_ERROR = "error";

    public static final String FRAME_CLOSE = StringUtils.concatDot("frame", Events.ON_CLOSE);
    public static final String FRAME_OPEN = StringUtils.concatDot("frame", Events.ON_OPEN);
    public static final String FRAME_HIDDEN = StringUtils.concatDot("frame", "onHidden");
    public static final String FRAME_RESIZE = StringUtils.concatDot("frame", "onResize");
    public static final String FRAME_SCROLL = StringUtils.concatDot("frame", "onScroll");
    public static final String FRAME_KEY_PRESSED = StringUtils.concatDot("frame", "onKeyPressed");
    public static final String FRAME_DRAG_DROPPED = StringUtils.concatDot("frame", "onDragDropped");

    public static final String APP_CLOSE = StringUtils.concatDot("app", Events.ON_CLOSE);
    public static final String APP_OPEN = StringUtils.concatDot("app", Events.ON_OPEN);

}
