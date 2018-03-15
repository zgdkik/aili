// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Msg4Head.java

package org.hbhk.rss.weixinapi.server.msg;

import java.util.Date;

import org.hbhk.rss.weixinapi.server.security.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Msg4Head
{

	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;

	public Msg4Head()
	{
		createTime = Session.DATE_FORMAT.format(new Date());
	}

	public void write(Element root, Document document)
	{
		Element toUserNameElement = document.createElement("ToUserName");
		toUserNameElement.setTextContent(toUserName);
		Element fromUserNameElement = document.createElement("FromUserName");
		fromUserNameElement.setTextContent(fromUserName);
		Element createTimeElement = document.createElement("CreateTime");
		createTimeElement.setTextContent(createTime);
		Element msgTypeElement = document.createElement("MsgType");
		msgTypeElement.setTextContent(msgType);
		root.appendChild(toUserNameElement);
		root.appendChild(fromUserNameElement);
		root.appendChild(createTimeElement);
		root.appendChild(msgTypeElement);
	}

	public void read(Document document)
	{
		toUserName = document.getElementsByTagName("ToUserName").item(0).getTextContent();
		fromUserName = document.getElementsByTagName("FromUserName").item(0).getTextContent();
		createTime = document.getElementsByTagName("CreateTime").item(0).getTextContent();
		msgType = document.getElementsByTagName("MsgType").item(0).getTextContent();
	}

	public String getToUserName()
	{
		return toUserName;
	}

	public void setToUserName(String toUserName)
	{
		this.toUserName = toUserName;
	}

	public String getFromUserName()
	{
		return fromUserName;
	}

	public void setFromUserName(String fromUserName)
	{
		this.fromUserName = fromUserName;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getMsgType()
	{
		return msgType;
	}

	public void setMsgType(String msgType)
	{
		this.msgType = msgType;
	}
}
