package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Voice extends Msg
{

	private String mediaId;
	private String format;
	private String recognition;
	private String msgId;

	public Msg4Voice()
	{
		head = new Msg4Head();
		head.setMsgType("voice");
	}

	public Msg4Voice(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document)
	{
		Element root = document.createElement("xml");
		head.write(root, document);
		Element voiceElement = document.createElement("Voice");
		Element mediaIdElement = document.createElement("MediaId");
		voiceElement.appendChild(mediaIdElement);
		root.appendChild(voiceElement);
		document.appendChild(root);
	}

	public void read(Document document)
	{
		mediaId = getElementContent(document, "MediaId");
		format = getElementContent(document, "Format");
		recognition = getElementContent(document, "Recognition");
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

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getRecognition()
	{
		return recognition;
	}

	public void setRecognition(String recognition)
	{
		this.recognition = recognition;
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
