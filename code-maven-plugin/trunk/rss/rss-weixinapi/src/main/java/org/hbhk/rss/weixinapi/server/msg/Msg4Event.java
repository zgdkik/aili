package org.hbhk.rss.weixinapi.server.msg;

import org.w3c.dom.Document;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head

public class Msg4Event extends Msg
{

	public static final String SUBSCRIBE = "subscribe";
	public static final String UNSUBSCRIBE = "unsubscribe";
	public static final String CLICK = "CLICK";
	public static final String SCAN = "scan";
	public static final String LOCATION = "LOCATION";
	private String event;
	private String eventKey;
	private String ticket;
	private String latitude;
	private String longitude;
	private String precision;

	public Msg4Event(Msg4Head head)
	{
		this.head = head;
	}

	public void write(Document document1)
	{
	}

	public void read(Document document)
	{
		event = getElementContent(document, "Event");
		if ("subscribe".equals(event) || "unsubscribe".equals(event) || "scan".equals(event))
		{
			eventKey = getElementContent(document, "EventKey");
			ticket = getElementContent(document, "Ticket");
		} else
		if ("LOCATION".equals(event))
		{
			latitude = getElementContent(document, "Latitude");
			longitude = getElementContent(document, "Longitude");
			precision = getElementContent(document, "Precision");
		} else
		if ("CLICK".equals(event))
			eventKey = getElementContent(document, "EventKey");
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getEventKey()
	{
		return eventKey;
	}

	public void setEventKey(String eventKey)
	{
		this.eventKey = eventKey;
	}

	public String getTicket()
	{
		return ticket;
	}

	public void setTicket(String ticket)
	{
		this.ticket = ticket;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getPrecision()
	{
		return precision;
	}

	public void setPrecision(String precision)
	{
		this.precision = precision;
	}
}
