package teamNotFound.batch.itemImplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import teamNotFound.daoimpl.DataAppelloDao;
import teamNotFound.model.DataAppello;
@Component
public class DataAppelloReader implements ItemReader <DataAppello> {
	
	private static Iterator<DataAppello> dataAppelloIterator = null;
	@Autowired
	DataAppelloDao dad;
	public DataAppello read() throws Exception, UnexpectedInputException, ParseException {
		System.out.println("Sono nel reader");
		if (getIterator().hasNext()) {
			System.out.println("sono nel hasNext");
			return getIterator().next();
		}else {
			System.out.println("sono nell'else del reader");
			dataAppelloIterator = null;
			return null;
		}
		
	}
	public Iterator<DataAppello> getIterator() {
		if(dataAppelloIterator==null) {
			dataAppelloIterator= dad.getAll().iterator();
		}
		
		return dataAppelloIterator;
	}
}

