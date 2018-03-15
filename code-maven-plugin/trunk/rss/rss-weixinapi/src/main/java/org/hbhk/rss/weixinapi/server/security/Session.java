// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Session.java

package org.hbhk.rss.weixinapi.server.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hbhk.rss.weixinapi.server.msg.Msg;
import org.hbhk.rss.weixinapi.server.msg.Msg4Event;
import org.hbhk.rss.weixinapi.server.msg.Msg4Head;
import org.hbhk.rss.weixinapi.server.msg.Msg4Image;
import org.hbhk.rss.weixinapi.server.msg.Msg4Link;
import org.hbhk.rss.weixinapi.server.msg.Msg4Location;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;
import org.hbhk.rss.weixinapi.server.msg.Msg4Video;
import org.hbhk.rss.weixinapi.server.msg.Msg4Voice;
import org.xml.sax.SAXException;

public abstract class Session
{

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	private InputStream is;
	private OutputStream os;
	private static DocumentBuilder builder;
	private static TransformerFactory tffactory = TransformerFactory.newInstance();

	public Session()
	{
	}

	public void process(InputStream is, OutputStream os)
	{
		this.os = os;
		try
		{
			org.w3c.dom.Document document = builder.parse(is);
			Msg4Head head = new Msg4Head();
			head.read(document);
			String type = head.getMsgType();
			if ("text".equals(type))
			{
				Msg4Text msg = new Msg4Text(head);
				msg.read(document);
				onTextMsg(msg);
			} else
			if ("image".equals(type))
			{
				Msg4Image msg = new Msg4Image(head);
				msg.read(document);
				onImageMsg(msg);
			} else
			if ("event".equals(type))
			{
				Msg4Event msg = new Msg4Event(head);
				msg.read(document);
				onEventMsg(msg);
			} else
			if ("link".equals(type))
			{
				Msg4Link msg = new Msg4Link(head);
				msg.read(document);
				onLinkMsg(msg);
			} else
			if ("location".equals(type))
			{
				Msg4Location msg = new Msg4Location(head);
				msg.read(document);
				onLocationMsg(msg);
			} else
			if ("voice".equals(type))
			{
				Msg4Voice msg = new Msg4Voice(head);
				msg.read(document);
				onVoiceMsg(msg);
			} else
			if ("video".equals(type))
			{
				Msg4Video msg = new Msg4Video(head);
				msg.read(document);
				onVideoMsg(msg);
			} else
			{
				onErrorMsg(-1);
			}
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void callback(Msg msg)
	{
		org.w3c.dom.Document document = builder.newDocument();
		msg.write(document);
		try
		{
			Transformer transformer = tffactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(os, "utf-8")));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			if (is != null)
				is.close();
			if (os != null)
			{
				os.flush();
				os.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public abstract void onTextMsg(Msg4Text msg4text);

	public abstract void onImageMsg(Msg4Image msg4image);

	public abstract void onEventMsg(Msg4Event msg4event);

	public abstract void onLinkMsg(Msg4Link msg4link);

	public abstract void onLocationMsg(Msg4Location msg4location);

	public abstract void onVoiceMsg(Msg4Voice msg4voice);

	public abstract void onVideoMsg(Msg4Video msg4video);

	public abstract void onErrorMsg(int i);

	static 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			builder = factory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
	}
}
