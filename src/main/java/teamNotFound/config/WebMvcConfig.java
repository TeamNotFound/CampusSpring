package teamNotFound.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

@Configuration
public class WebMvcConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public AmazonS3 amazonS3client(AWSCredentialsProvider provider,
			@Value("cloud.aws.region.static") String region) {

		return AmazonS3ClientBuilder.standard()
				.withCredentials(provider)
				.withRegion(region)
				.build();
	}
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Bean
	public TransferManager manager() {
		return TransferManagerBuilder.standard()
				  .withS3Client(amazonS3)
				  .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
				  .build();
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}