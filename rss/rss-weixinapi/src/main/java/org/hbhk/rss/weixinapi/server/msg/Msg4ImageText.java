package org.hbhk.rss.weixinapi.server.msg;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Referenced classes of package org.marker.weixin.msg:
//			Msg, Msg4Head, Data4Item

public class Msg4ImageText extends Msg {

	private String articleCount;
	private List<Object> items;

	public Msg4ImageText() {
		items = new ArrayList<Object>(3);
		head = new Msg4Head();
		head.setMsgType("news");
	}

	public void write(Document document) {
		Element root = document.createElement("xml");
		head.write(root, document);
		Element articleCountElement = document.createElement("ArticleCount");
		articleCountElement.setTextContent(articleCount);
		Element articlesElement = document.createElement("Articles");
		int size = Integer.parseInt(articleCount);
		for (int i = 0; i < size; i++) {
			Data4Item currentItem = (Data4Item) items.get(i);
			Element itemElement = document.createElement("item");
			Element titleElement = document.createElement("Title");
			titleElement.setTextContent(currentItem.getTitle());
			Element descriptionElement = document.createElement("Description");
			descriptionElement.setTextContent(currentItem.getDescription());
			Element picUrlElement = document.createElement("PicUrl");
			picUrlElement.setTextContent(currentItem.getPicUrl());
			Element urlElement = document.createElement("Url");
			urlElement.setTextContent(currentItem.getUrl());
			itemElement.appendChild(titleElement);
			itemElement.appendChild(descriptionElement);
			itemElement.appendChild(picUrlElement);
			itemElement.appendChild(urlElement);
			articlesElement.appendChild(itemElement);
		}

		root.appendChild(articleCountElement);
		root.appendChild(articlesElement);
		document.appendChild(root);
	}

	public void read(Document document1) {
	}

	public void addItem(Data4Item item) {
		items.add(item);
		reflushArticleCount();
	}

	public void removeItem(int index) {
		items.remove(index);
		reflushArticleCount();
	}

	private void reflushArticleCount() {
		articleCount = (new StringBuilder()).append(items.size()).toString();
	}
}
