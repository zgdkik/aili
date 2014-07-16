package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Video extends Msg
{

	private String mediaId;
	private String thumbMediaId;
	private String msgId;

	public Msg4Video()
	{
		head = new Msg4Head();
		head.setMsgType("video");
	}

	public Msg4Video(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document)
	{
		Element root = document.createElement("xml");
		head.write(root, document);
		Element videoElement = document.createElement("Video");
		Element mediaIdElement = document.createElement("MediaId");
		Element thumbMediaIdElement = document.createElement("ThumbMediaId");
		videoElement.appendChild(mediaIdElement);
		videoElement.appendChild(thumbMediaIdElement);
		root.appendChild(videoElement);
		document.appendChild(root);
	}

	public void read(Document document)
	{
		mediaId = getElementContent(document, "MediaId");
		thumbMediaId = getElementContent(document, "ThumbMediaId");
		msgId = getElementContent(document, "MsgId");
	}

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	public String getThumbMediaId()
	{
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId)
	{
		this.thumbMediaId = thumbMediaId;
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
