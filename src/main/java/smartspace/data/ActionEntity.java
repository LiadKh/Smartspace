package smartspace.data;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="ACTIONS")
public class ActionEntity implements SmartspaceEntity<ActionKey> {

	private String actionSmartspace;
	private String actionId;
	private String elementSmartspace;
	private String elementId;
	private String playerSmartspace;
	private String playerEmail;
	private String actionType;
	private Date creationTimestamp;
	private Map<String, Object> moreAttributes;
	private ActionKey key;

	// default constructor
	public ActionEntity() {

	}

	public ActionEntity(String elementId, String elementSmartspace, String actionType, Date creationTimestamp,
			String playerEmail, String playerSmartspace, Map<String, Object> moreAttributes) {
		this.elementId = elementId;
		this.elementSmartspace = elementSmartspace;
		this.actionType = actionType;
		this.creationTimestamp = creationTimestamp;
		this.playerEmail = playerEmail;
		this.playerSmartspace = playerSmartspace;
		this.moreAttributes = moreAttributes;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@JsonIgnore
	public String getActionSmartspace() {
		return actionSmartspace;
	}

	public void setActionSmartspace(String actionSmartspace) {
		this.actionSmartspace = actionSmartspace;
	}

	public String getElementSmartspace() {
		return elementSmartspace;
	}

	public void setElementSmartspace(String elementSmartspace) {
		this.elementSmartspace = elementSmartspace;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPlayerSmartspace() {
		return playerSmartspace;
	}

	public void setPlayerSmartspace(String playerSmartspace) {
		this.playerSmartspace = playerSmartspace;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Map<String, Object> getMoreAttributes() {
		return moreAttributes;
	}

	public void setMoreAttributes(Map<String, Object> moreAttributes) {
		this.moreAttributes = moreAttributes;
	}

	@Override
	public String toString() {
		return "ActionEntity [actionSmartspace=" + actionSmartspace + ", actionId=" + actionId + ", elementSmartspace="
				+ elementSmartspace + ", elementId=" + elementId + ", playerSmartspace=" + playerSmartspace
				+ ", playerEmail=" + playerEmail + ", actionType=" + actionType + ", creationTimestamp="
				+ creationTimestamp + ", moreAttributes=" + moreAttributes + "]";
	}

	/*
	 * @author liadk
	 *
	 * @see smartspace.data.SmartspaceEntity#getKey()
	 * 
	 */
	@Override
	@Id
	public ActionKey getKey() {
		long id = 0;

        try {
            id = Long.parseLong(this.actionId);
        } catch (Exception e) {
            return null;
        }
        
        this.key = new ActionKey(this.actionSmartspace, id);
		return key;
	}

	/*
	 * @author liadk
	 *
	 * @see smartspace.data.SmartspaceEntity#setKey(java.lang.Object)
	 * 
	 */
	@Override
	public void setKey(ActionKey k) {
		if (k != null) {
			this.setActionSmartspace(k.getActionSmartspace());
			this.actionId = String.valueOf(k.getId());
		} else {
			this.setActionSmartspace(null);
			this.actionId = null;
		}
		
		this.key = new ActionKey(k.getActionSmartspace(), k.getId());
	}

}
