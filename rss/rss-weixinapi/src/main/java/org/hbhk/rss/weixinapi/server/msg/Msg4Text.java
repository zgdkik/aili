// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Msg4Text.java

package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.*;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Text extends Msg
{

	private String content;
	private String msgId;

	public Msg4Text()
	{
		head = new Msg4Head();
		head.setMsgType("text");
	}

	public Msg4Text(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document)
	{
		Element root = document.createElement("xml");
		head.write(root, document);
		Element contentElement = document.createElement("Content");
		contentElement.setTextContent(content);
		root.appendChild(contentElement);
		document.appendChild(root);
	}

	public void read(Document document)
	{
		content = document.getElementsByTagName("Content").item(0).getTextContent();
		msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
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
