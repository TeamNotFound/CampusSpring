package teamNotFound.batch;




import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.HibernatePagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import teamNotFound.batch.itemImplementation.DataAppelloProcessor;
import teamNotFound.batch.itemImplementation.DataAppelloWriter;
import teamNotFound.model.DataAppello;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig{

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private EntityManagerFactory entityFactory;

	@Bean
	public ItemReader<DataAppello> readerHibernate(){
		return new HibernatePagingItemReaderBuilder<DataAppello>()
				.sessionFactory(entityFactory.unwrap(SessionFactory.class))
				.queryString("from DataAppello")
				.fetchSize(10)
				.maxItemCount(10)
				.saveState(true)
				.name("mrJob")
				.build();
	}

	@Bean
	public Job job(@Qualifier("step1") Step step1) {
		return jobs.get("job")
				.flow(step1)
				.end()
				.build();
	}

	@Bean
protected Step step1(@Qualifier("readerHibernate") ItemReader<DataAppello> reader,
			DataAppelloProcessor processor,
			DataAppelloWriter writer) {
		return steps.get("step1")
				.<DataAppello, DataAppello> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

	@Autowired
	private Job job;

	@Scheduled(cron = "0 0 0 * * ?")
	//@Scheduled(fixedRate = 5000)
	public void runJob() throws Exception{
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("jobbo_Id", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		jobLauncher.run(job, jobParameters);
	}
}

