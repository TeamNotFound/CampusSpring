package teamNotFound.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
	
	public String upload(MultipartFile file) throws IOException {
		String generatedKey = getUniqueKey();
		File imageFilezed = convert(file);
		try {
		amazonS3transfer.upload(new PutObjectRequest("campus-bucket", generatedKey, imageFilezed));
		
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest("campus-bucket", generatedKey);
		String url = ((AmazonS3Client) amazonS3transfer.getAmazonS3Client()).generatePresignedUrl(request).toString();
		
		return url;
		
		} finally {
			imageFilezed.delete();
		}
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();

		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
	private String getUniqueKey() {
		String generatedKey;
		do {
			generatedKey = RandomStringUtils.random(8, true, true);
		} while (uniqueKeyDao.getById(generatedKey) != null);
		
		uniqueKeyDao.inserimento(new UniqueS3Key(generatedKey));
		
		return generatedKey;
	}
}
