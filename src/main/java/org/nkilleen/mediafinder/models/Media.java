package org.nkilleen.mediafinder.models;

import java.util.Set;

public class Media {

	private String url;
	private String name;
	private String description;
	private String type;
	private Set<String> tags;

   public Media(String url, String name, String description, String type, Set<String> tags) {
		this.url = url;
		this.name = name;
		this.description = description;
		this.type = type;
		this.tags = tags;
   }

	public void setUrl(String url) {
   	this.url = url;
	}

	public String getUrl() {
   	return url;
	}

	public void setName(String name) {
   	this.name = name;
	}

	public String getName() {
   	return name;
	}

	public void setDescription(String description) {
   	this.description = description;
	}

	public String getDescription() {
   	return description;
	}

	public void setType(String type) {
   	this.type = type;
	}

	public String getType() {
   	return type;
	}

	public void setTags(Set<String> tags) {
   	this.tags = tags;
	}

	public Set<String> getTags() {
   	return tags;
	}

}
