package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Music extends Msg
{

	private String title;
	private String description;
	private String musicUrl;
	private String hQMusicUrl;
	private String thumbMediaId;

	public Msg4Music()
	{
		head = new Msg4Head();
		head.setMsgType("music");
	}

	public void write(Document document)
	{
		Element root = document.createElement("xml");
		head.write(root, document);
		Element musicElement = document.createElement("Music");
		Element titleElement = document.createElement("Title");
		titleElement.setTextContent(title);
		Element descriptionElement = document.createElement("Description");
		descriptionElement.setTextContent(description);
		Element musicUrlElement = document.createElement("MusicUrl");
		musicUrlElement.setTextContent(musicUrl);
		Element hQMusicUrlElement = document.createElement("HQMusicUrl");
		hQMusicUrlElement.setTextContent(hQMusicUrl);
		Element thumbMediaIdElement = document.createElement("ThumbMediaId");
		thumbMediaIdElement.setTextContent(thumbMediaId);
		musicElement.appendChild(titleElement);
		musicElement.appendChild(descriptionElement);
		musicElement.appendChild(musicUrlElement);
		musicElement.appendChild(hQMusicUrlElement);
		musicElement.appendChild(thumbMediaIdElement);
		root.appendChild(musicElement);
		document.appendChild(root);
	}

	public void read(Document document1)
	{
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

	public String getMusicUrl()
	{
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl)
	{
		this.musicUrl = musicUrl;
	}

	public String getHQMusicUrl()
	{
		return hQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl)
	{
		this.hQMusicUrl = hQMusicUrl;
	}

	public String gethQMusicUrl()
	{
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl)
	{
		this.hQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId()
	{
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId)
	{
		this.thumbMediaId = thumbMediaId;
	}
}
