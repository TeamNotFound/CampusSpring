package teamNotFound.model.dto;

public class FacoltaNameOnlyDTO {
	private int id;

	private String facolta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFacolta() {
		return facolta;
	}

	public void setFacolta(String facolta) {
		this.facolta = facolta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facolta == null) ? 0 : facolta.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacoltaNameOnlyDTO other = (FacoltaNameOnlyDTO) obj;
		if (facolta == null) {
			if (other.facolta != null)
				return false;
		} else if (!facolta.equals(other.facolta))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public FacoltaNameOnlyDTO(int id, String facolta) {
		super();
		this.id = id;
		this.facolta = facolta;
	}

	public FacoltaNameOnlyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
