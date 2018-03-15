// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HandleMessageAdapter.java

package org.hbhk.rss.weixinapi.server.handle;

import org.hbhk.rss.weixinapi.server.msg.Msg4Event;
import org.hbhk.rss.weixinapi.server.msg.Msg4Image;
import org.hbhk.rss.weixinapi.server.msg.Msg4Link;
import org.hbhk.rss.weixinapi.server.msg.Msg4Location;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;
import org.hbhk.rss.weixinapi.server.msg.Msg4Video;
import org.hbhk.rss.weixinapi.server.msg.Msg4Voice;

// Referenced classes of package org.marker.weixin:
//			HandleMessageListener

public class HandleMessageAdapter
	implements HandleMessageListener
{

	public HandleMessageAdapter()
	{
	}

	public void onTextMsg(Msg4Text msg4text)
	{
	}

	public void onImageMsg(Msg4Image msg4image)
	{
	}

	public void onEventMsg(Msg4Event msg4event)
	{
	}

	public void onLinkMsg(Msg4Link msg4link)
	{
	}

	public void onLocationMsg(Msg4Location msg4location)
	{
	}

	public void onErrorMsg(int i)
	{
	}

	public void onVoiceMsg(Msg4Voice msg4voice)
	{
	}

	public void onVideoMsg(Msg4Video msg4video)
	{
	}
}
