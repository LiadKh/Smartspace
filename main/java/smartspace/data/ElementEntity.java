package smartspace.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import smartspace.dao.rdb.MapToJsonConverter;

@Entity
@Table(name = "ELEMENTS")
public class ElementEntity implements SmartspaceEntity<ElementKey> {

	@Column(nullable = false)
	private String elementSmartspace;
	@Column(nullable = false)
	private String elementId;
	private Location location;
	private String name;
	private String type;
	private Date creationTimestamp;
	private boolean expired;
	private String creatorSmartspace;
	private String creatorEmail;
	private Map<String, Object> moreAttributes;

	// default constructor
	public ElementEntity() {
	}

	public ElementEntity(String name, String type, Location location, Date creationTimestamp, String creatorEmail,
			String creatorSmartspace, boolean expiredBoolean, Map<String, Object> moreAtributes) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.creationTimestamp = creationTimestamp;
		this.creatorEmail = creatorEmail;
		this.creatorSmartspace = creatorSmartspace;
		this.expired = expiredBoolean;
		this.moreAttributes = moreAtributes;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@Transient
	public String getElementSmartspace() {
		return elementSmartspace;
	}

	public void setElementSmartspace(String elementSmartSpace) {
		this.elementSmartspace = elementSmartSpace;
	}

	@Embedded
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getCreatorSmartspace() {
		return creatorSmartspace;
	}

	public void setCreatorSmartspace(String creatorSmartspace) {
		this.creatorSmartspace = creatorSmartspace;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	@Lob
	@Convert(converter = MapToJsonConverter.class)
	public Map<String, Object> getMoreAttributes() {
		return moreAttributes;
	}

	public void setMoreAttributes(Map<String, Object> moreAttributes) {
		this.moreAttributes = moreAttributes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date date) {
		this.creationTimestamp = date;
	}

	@Override
	public String toString() {
		return "ElementEntity [elementSmartSpace=" + elementSmartspace + ", elementId=" + elementId + ", location="
				+ location + ", name=" + name + ", type=" + type + ", creationTimestamp=" + creationTimestamp
				+ ", expierd=" + expired + ", creatorSmartspace=" + creatorSmartspace + ", creatorEmail=" + creatorEmail
				+ ", moreAttributes=" + moreAttributes + "]";
	}

	/*
	 * @author liadk
	 *
	 * @see smartspace.data.SmartspaceEntity#getKey()
	 * 
	 */
	@Override
	@EmbeddedId
	@Column(name = "ID")
	public ElementKey getKey() {

		// added this because otherwise calling getKey before create causes exception.
		long id = 0;

		try {
			id = Long.parseLong(this.elementId);
		} catch (Exception e) {
			return null;
		}

		return new ElementKey(this.elementSmartspace, id);
	}

	/*
	 * @author liadk
	 *
	 * @see smartspace.data.SmartspaceEntity#setKey(java.lang.Object)
	 * 
	 */
	@Override
	public void setKey(ElementKey k) {
		this.setElementSmartspace(k.getElementSmartspace());
		this.elementId = String.valueOf(k.getId());
	}

}
