/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.context.notification;

import org.mule.api.MuleContext;
import org.mule.api.context.notification.BlockingServerEvent;
import org.mule.api.context.notification.ServerNotification;

/**
 * <code>MuleContextNotification</code> is fired when an event such as the mule
 * context starting occurs. The payload of this event will always be a reference to
 * the muleContext.
 */
public class MuleContextNotification extends ServerNotification implements BlockingServerEvent
{
    /**
     * Serial version
     */
    private static final long serialVersionUID = -3246036188011581121L;
    
    public static final int CONTEXT_INITIALISING = CONTEXT_EVENT_ACTION_START_RANGE + 1;
    public static final int CONTEXT_INITIALISED = CONTEXT_EVENT_ACTION_START_RANGE + 2;
    public static final int CONTEXT_STARTING = CONTEXT_EVENT_ACTION_START_RANGE + 3;
    public static final int CONTEXT_STARTED = CONTEXT_EVENT_ACTION_START_RANGE + 4;
    public static final int CONTEXT_STOPPING = CONTEXT_EVENT_ACTION_START_RANGE + 5;
    public static final int CONTEXT_STOPPED = CONTEXT_EVENT_ACTION_START_RANGE + 6;
    public static final int CONTEXT_DISPOSING = CONTEXT_EVENT_ACTION_START_RANGE + 7;
    public static final int CONTEXT_DISPOSED = CONTEXT_EVENT_ACTION_START_RANGE + 8;
    
    // The following events are not context events, but rather more general global
    // mule events.
    public static final int CONTEXT_DISPOSING_CONNECTORS = CONTEXT_EVENT_ACTION_START_RANGE + 9;
    public static final int CONTEXT_DISPOSED_CONNECTORS = CONTEXT_EVENT_ACTION_START_RANGE + 10;
    public static final int CONTEXT_STARTING_MODELS = CONTEXT_EVENT_ACTION_START_RANGE + 11;
    public static final int CONTEXT_STARTED_MODELS = CONTEXT_EVENT_ACTION_START_RANGE + 12;
    public static final int CONTEXT_STOPPING_MODELS = CONTEXT_EVENT_ACTION_START_RANGE + 13;
    public static final int CONTEXT_STOPPED_MODELS = CONTEXT_EVENT_ACTION_START_RANGE + 14;

    static {
        registerAction("mule context initialising", CONTEXT_INITIALISING);
        registerAction("mule context initialised", CONTEXT_INITIALISED);
        registerAction("mule context starting", CONTEXT_STARTING);
        registerAction("mule context started", CONTEXT_STARTED);
        registerAction("mule context stopping", CONTEXT_STOPPING);
        registerAction("mule context stopped", CONTEXT_STOPPED);
        registerAction("mule context disposing", CONTEXT_DISPOSING);
        registerAction("mule context disposed", CONTEXT_DISPOSED);
        registerAction("disposing connectors", CONTEXT_DISPOSING_CONNECTORS);
        registerAction("disposed connectors", CONTEXT_DISPOSED_CONNECTORS);
        registerAction("starting models", CONTEXT_STARTING_MODELS);
        registerAction("started models", CONTEXT_STARTED_MODELS);
        registerAction("stopping models", CONTEXT_STOPPING_MODELS);
        registerAction("stopped models", CONTEXT_STOPPED_MODELS);
    }

    private String clusterId;
    private String domain;


    public MuleContextNotification(MuleContext context, String action)
    {
        this(context, getActionId(action));
    }

    public MuleContextNotification(MuleContext context, int action)
    {
        super(getId(context), action);
        resourceIdentifier = getId(context);
        this.clusterId = context.getConfiguration().getClusterId();
        this.domain = context.getConfiguration().getDomainId();
    }

    private static String getId(MuleContext context)
    {
        return context.getConfiguration().getDomainId() + "." + context.getConfiguration().getClusterId() + "." + context.getConfiguration().getId();
    }

    public String getClusterId()
    {
        return clusterId;
    }

    public String getDomain()
    {
        return domain;
    }

    protected String getPayloadToString()
    {
        return ((MuleContext) source).getConfiguration().getId();
    }

    public String toString()
    {
        return EVENT_NAME + "{" + "action=" + getActionName(action) + ", resourceId=" + resourceIdentifier
                + ", timestamp=" + timestamp + "}";
    }
    
}
