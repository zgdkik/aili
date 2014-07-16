
package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.*;

public abstract class Msg
{

	public static final String MSG_TYPE_TEXT = "text";
	public static final String MSG_TYPE_IMAGE = "image";
	public static final String MSG_TYPE_MUSIC = "music";
	public static final String MSG_TYPE_LOCATION = "location";
	public static final String MSG_TYPE_LINK = "link";
	public static final String MSG_TYPE_IMAGE_TEXT = "news";
	public static final String MSG_TYPE_EVENT = "event";
	public static final String MSG_TYPE_VOICE = "voice";
	public static final String MSG_TYPE_VIDEO = "video";
	protected Msg4Head head;

	public Msg()
	{
	}

	public abstract void write(Document document);

	public abstract void read(Document document);

	protected String getElementContent(Document document, String element)
	{
		if (document.getElementsByTagName(element).getLength() > 0)
			return document.getElementsByTagName(element).item(0).getTextContent();
		else
			return null;
	}

	public Msg4Head getHead()
	{
		return head;
	}

	public void setHead(Msg4Head head)
	{
		this.head = head;
	}

	public String getToUserName()
	{
		return head.getToUserName();
	}

	public void setToUserName(String toUserName)
	{
		head.setToUserName(toUserName);
	}

	public String getFromUserName()
	{
		return head.getFromUserName();
	}

	public void setFromUserName(String fromUserName)
	{
		head.setFromUserName(fromUserName);
	}

	public String getCreateTime()
	{
		return head.getCreateTime();
	}

	public void setCreateTime(String createTime)
	{
		head.setCreateTime(createTime);
	}
}
