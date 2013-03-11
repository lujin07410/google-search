package main;

public class Element {
	private String title = null;
	private String url = null;
	private String description = null;

	public Element(String title, String url, String description) {
		this.title = title;
		this.url = url;
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public String getUrl() {
		return this.url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
