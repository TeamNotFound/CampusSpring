package teamNotFound.batch.itemImplementation;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.model.DataAppello;
@Component
public class DataAppelloWriter implements ItemWriter<DataAppello>{

	@Autowired
	DataAppelloDao dad;

	@Override
	public void write(List<? extends DataAppello> items) throws Exception {
		items.forEach(data -> {
			if(data!=null) {
				System.out.println(data.getDataAppello());
				dad.remove(data);
			}
		});
	}
}
