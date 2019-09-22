package teamNotFound.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UniqueS3Key")
public class UniqueS3Key {

	@Id
	private String chiave;


	public String getKey() {
		return chiave;
	}

	public void setKey(String key) {
		this.chiave = key;
	}

	public UniqueS3Key(String key) {
		super();
		this.chiave = key;
	}

	public UniqueS3Key() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chiave == null) ? 0 : chiave.hashCode());
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
		UniqueS3Key other = (UniqueS3Key) obj;
		if (chiave == null) {
			if (other.chiave != null)
				return false;
		} else if (!chiave.equals(other.chiave))
			return false;
		return true;
	}
}
