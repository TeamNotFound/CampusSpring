package teamNotFound.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;

import teamNotFound.daoimpl.UniqueS3KeyDao;
import teamNotFound.model.UniqueS3Key;

@Component
public class AmazonUploadUtil {

	@Autowired
	private TransferManager amazonS3transfer;
	@Autowired
	private UniqueS3KeyDao uniqueKeyDao;
	@Value("${app.awsServices.bucketName}")
	private String bucketName;

	public String upload(MultipartFile file) throws IOException, AmazonServiceException, AmazonClientException, InterruptedException {
		String generatedKey = getUniqueKey();
		File imageFilezed = convert(file);

		amazonS3transfer.upload(new PutObjectRequest("campus-bucket", generatedKey, imageFilezed)).waitForUploadResult();

		imageFilezed.delete();

		System.out.println("\n\n"+bucketName+"\n\n");
		return generatedKey;
	}

	public String generateUrl(String key) {
		GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest("campus-bucket", key);
		return ((AmazonS3Client) amazonS3transfer.getAmazonS3Client()).generatePresignedUrl(urlRequest).toString();
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());

		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String getUniqueKey() {
		String generatedKey;
		do {
			generatedKey = RandomStringUtils.random(16, true, true);
		} while (uniqueKeyDao.getById(generatedKey) != null);

		uniqueKeyDao.inserimento(new UniqueS3Key(generatedKey));

		return generatedKey;
	}
}
