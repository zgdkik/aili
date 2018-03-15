package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.*;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Link extends Msg
{

	private String title;
	private String description;
	private String url;
	private String msgId;

	public Msg4Link()
	{
		head = new Msg4Head();
		head.setMsgType("link");
	}

	public Msg4Link(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document1)
	{
	}

	public void read(Document document)
	{
		title = document.getElementsByTagName("Title").item(0).getTextContent();
		description = document.getElementsByTagName("Description").item(0).getTextContent();
		url = document.getElementsByTagName("Url").item(0).getTextContent();
		msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}
}
