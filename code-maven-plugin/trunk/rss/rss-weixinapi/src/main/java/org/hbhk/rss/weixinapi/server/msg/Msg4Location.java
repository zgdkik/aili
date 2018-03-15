package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.*;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Location extends Msg
{

	private String location_X;
	private String location_Y;
	private String scale;
	private String label;
	private String msgId;

	public Msg4Location()
	{
		head = new Msg4Head();
		head.setMsgType("location");
	}

	public Msg4Location(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document1)
	{
	}

	public void read(Document document)
	{
		location_X = document.getElementsByTagName("Location_X").item(0).getTextContent();
		location_Y = document.getElementsByTagName("Location_Y").item(0).getTextContent();
		scale = document.getElementsByTagName("Scale").item(0).getTextContent();
		label = document.getElementsByTagName("Label").item(0).getTextContent();
		msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
	}

	public String getLocation_X()
	{
		return location_X;
	}

	public void setLocation_X(String location_X)
	{
		this.location_X = location_X;
	}

	public String getLocation_Y()
	{
		return location_Y;
	}

	public void setLocation_Y(String location_Y)
	{
		this.location_Y = location_Y;
	}

	public String getScale()
	{
		return scale;
	}

	public void setScale(String scale)
	{
		this.scale = scale;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
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
