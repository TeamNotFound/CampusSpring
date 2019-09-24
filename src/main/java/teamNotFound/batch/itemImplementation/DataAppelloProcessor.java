package teamNotFound.batch.itemImplementation;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.model.DataAppello;
@Component
public class DataAppelloProcessor implements ItemProcessor<DataAppello, DataAppello>{
	@Autowired
	DataAppelloDao dad;
	@Override
	public DataAppello process(DataAppello da) throws Exception
	{
		
		Date date = da.getDataAppello();
		 System.out.println(date);
		LocalDateTime dateLocal = Instant.ofEpochMilli( date.getTime() )
		                            .atZone( ZoneId.systemDefault() )
		                            .toLocalDateTime();
		System.out.println(LocalDateTime.now());
		System.out.println(dateLocal);
		System.out.println(dateLocal.isBefore(LocalDateTime.now()));
		if(dateLocal.isBefore(LocalDateTime.now())) {
			return da;
		}else {
			return null;
		}
		
	}
}

