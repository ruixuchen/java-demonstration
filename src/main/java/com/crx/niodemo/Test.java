package com.crx.niodemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class Test {

	public static void main(String[] args) throws IOException {
		File result=new File("result.pdf");
		OutputStream outputStream=new FileOutputStream(result);

		File dictionary=new File("pdf/");
		mergeFiles(dictionary);
//		File[] files=dictionary.listFiles();
//		int count=0;
//		for (File file : files) {
//			InputStream inputStream=new FileInputStream(file);
//			byte[] buffer=new byte[inputStream.available()];
//			FileUtils.writeByteArrayToFile(result, buffer, false);
////			while((count=inputStream.read(buffer))>0){
////				//outputStream.write(buffer, 0, count);
////				FileUtils.writeByteArrayToFile(result, buffer, true);
////			}
//			//transform(file.getName(), "pdf/"+file.getName().split("\\.")[0]+".pdf", inputStream);
//			inputStream.close();
//			
//		}
//		outputStream.close();
	}
	public static boolean transform(String fileName, String toPath, InputStream in) throws IOException {
        try {
            final File toFile = new File(toPath);
            if (!toFile.exists()) {
                if (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".rtf")) {
                    final com.aspose.words.Document doc = new com.aspose.words.Document(in);
                    in.close();
                    doc.save(toPath, com.aspose.words.SaveFormat.PDF);
                }
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
	
	public static void mergeFiles(File dictionary) throws IOException{
		PDFMergerUtility merger=new PDFMergerUtility();
		File[] files=dictionary.listFiles();
		for (File file : files) {
			merger.addSource(file);
		}
		merger.setDestinationFileName("result.pdf");
		merger.mergeDocuments(null);
	}

}
