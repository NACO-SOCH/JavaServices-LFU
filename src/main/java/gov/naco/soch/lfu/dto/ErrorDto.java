package gov.naco.soch.lfu.dto;

public class ErrorDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String field;
	private String description;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[field=" + field + ", description=" + description + "]";
	}

}
