package teamNotFound.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;

import teamNotFound.dao.CRUDInterface;
import teamNotFound.daoimpl.UniqueS3KeyDao;
import teamNotFound.model.UniqueS3Key;
import teamNotFound.model.Utente;

@Component
public class AmazonUploadUtil {

	@Autowired
	private TransferManager amazonS3transfer;
	@Autowired
	private UniqueS3KeyDao uniqueKeyDao;
	@Value("${app.awsServices.bucketName}")
	private String bucketName;

	@Async
	public Future<String> upload(File imageFilezed) throws IOException, AmazonServiceException, AmazonClientException, InterruptedException {
		String generatedKey = getUniqueKey();

		amazonS3transfer.upload(new PutObjectRequest(bucketName, generatedKey, imageFilezed)).waitForUploadResult();
		imageFilezed.delete();

		return new AsyncResult<String>(generatedKey);
	}

	public String generateUrl(String key) {
		GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
		return ((AmazonS3Client) amazonS3transfer.getAmazonS3Client()).generatePresignedUrl(urlRequest).toString();
	}

	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();

		FileOutputStream fos = new FileOutputStream(convFile);

		System.out.println("Not written: "+file.getName());
		fos.write(file.getBytes());
		System.out.println("Written");
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

	@Async
	public void setImageToUserAndSession(Utente utente, CRUDInterface crud, HttpSession session, Future<String> url) {
		boolean notDone = true;
		while(notDone) {
			try {
				if(url.isDone()) {
					notDone = false;
					session.setAttribute("profilePic", generateUrl(url.get()));
					
					utente.setImageGeneratedName(url.get());
					crud.update(utente);
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
