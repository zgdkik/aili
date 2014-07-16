package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.*;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Image extends Msg
{

	private String picUrl;
	private String msgId;
	private String mediaId;

	public Msg4Image()
	{
		head = new Msg4Head();
		head.setMsgType("image");
	}

	public Msg4Image(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document)
	{
		Element root = document.createElement("xml");
		head.write(root, document);
		Element imageElement = document.createElement("Image");
		Element mediaIdElement = document.createElement("MediaId");
		imageElement.appendChild(mediaIdElement);
		root.appendChild(imageElement);
		document.appendChild(root);
	}

	public void read(Document document)
	{
		picUrl = document.getElementsByTagName("PicUrl").item(0).getTextContent();
		mediaId = getElementContent(document, "MediaId");
		msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}
}
