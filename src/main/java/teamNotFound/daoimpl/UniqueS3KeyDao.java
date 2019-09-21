package teamNotFound.daoimpl;

import org.springframework.stereotype.Repository;

import teamNotFound.dao.CrudGenerico;
import teamNotFound.model.UniqueS3Key;

@Repository
public class UniqueS3KeyDao  extends CrudGenerico<UniqueS3Key, String>{

	public UniqueS3KeyDao() {
		this.classeT = UniqueS3Key.class;
	}

}
