package pojo;

import java.util.Date;

import pojo.SwitchStatus;

public class Switch {
	private SwitchStatus status;
	private Date logDate;
	private SwitchType type;
	private StatusCalculationType statusCalculationType;
	
	
	public SwitchStatus getStatus() {
		return this.status;
	}
	public void setStatus(SwitchStatus status) {
		this.status = status;
	}
	public Date getLogDate() {
		return this.logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public SwitchType getType() {
		return this.type;
	}
	public void setType(SwitchType type) {
		this.type = type;
	}
	public StatusCalculationType getStatusCalculationType() {
		return statusCalculationType;
	}
	public void setStatusCalculationType(StatusCalculationType statusCalculationType) {
		this.statusCalculationType = statusCalculationType;
	}
}
