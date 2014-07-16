// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DefaultSession.java

package org.hbhk.rss.weixinapi.server.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hbhk.rss.weixinapi.server.handle.HandleMessageListener;
import org.hbhk.rss.weixinapi.server.msg.Msg4Event;
import org.hbhk.rss.weixinapi.server.msg.Msg4Image;
import org.hbhk.rss.weixinapi.server.msg.Msg4Link;
import org.hbhk.rss.weixinapi.server.msg.Msg4Location;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;
import org.hbhk.rss.weixinapi.server.msg.Msg4Video;
import org.hbhk.rss.weixinapi.server.msg.Msg4Voice;

// Referenced classes of package org.marker.weixin:
//			Session, HandleMessageListener

public class DefaultSession extends Session
{

	private List listeners;

	private DefaultSession()
	{
		listeners = new ArrayList(3);
	}

	public static DefaultSession newInstance()
	{
		return new DefaultSession();
	}

	public void addOnHandleMessageListener(HandleMessageListener handleMassge)
	{
		listeners.add(handleMassge);
	}

	public void removeOnHandleMessageListener(HandleMessageListener handleMassge)
	{
		listeners.remove(handleMassge);
	}

	public void onTextMsg(Msg4Text msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onTextMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onImageMsg(Msg4Image msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onImageMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onEventMsg(Msg4Event msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onEventMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onLinkMsg(Msg4Link msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onLinkMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onLocationMsg(Msg4Location msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onLocationMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onErrorMsg(int errorCode)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onErrorMsg(errorCode))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onVoiceMsg(Msg4Voice msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onVoiceMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}

	public void onVideoMsg(Msg4Video msg)
	{
		HandleMessageListener currentListener;
		for (Iterator iterator = listeners.iterator(); iterator.hasNext(); currentListener.onVideoMsg(msg))
			currentListener = (HandleMessageListener)iterator.next();

	}
}
