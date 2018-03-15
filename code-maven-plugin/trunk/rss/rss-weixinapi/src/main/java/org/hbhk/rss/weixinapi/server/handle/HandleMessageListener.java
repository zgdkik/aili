package org.hbhk.rss.weixinapi.server.handle;

import org.hbhk.rss.weixinapi.server.msg.Msg4Event;
import org.hbhk.rss.weixinapi.server.msg.Msg4Image;
import org.hbhk.rss.weixinapi.server.msg.Msg4Link;
import org.hbhk.rss.weixinapi.server.msg.Msg4Location;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;
import org.hbhk.rss.weixinapi.server.msg.Msg4Video;
import org.hbhk.rss.weixinapi.server.msg.Msg4Voice;

public interface HandleMessageListener
{

	public abstract void onTextMsg(Msg4Text msg4text);

	public abstract void onImageMsg(Msg4Image msg4image);

	public abstract void onEventMsg(Msg4Event msg4event);

	public abstract void onLinkMsg(Msg4Link msg4link);

	public abstract void onLocationMsg(Msg4Location msg4location);

	public abstract void onVoiceMsg(Msg4Voice msg4voice);

	public abstract void onErrorMsg(int i);

	public abstract void onVideoMsg(Msg4Video msg4video);
}
