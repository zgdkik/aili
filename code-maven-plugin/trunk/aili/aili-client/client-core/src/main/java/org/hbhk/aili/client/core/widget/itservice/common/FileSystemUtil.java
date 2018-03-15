package org.hbhk.aili.client.core.widget.itservice.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * 文件操作工具类
 */
public final class FileSystemUtil {
	
	private static final Log LOG = LogFactory.getLog(FileSystemUtil.class);

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private FileSystemUtil() {
	}
    /**
     * 
     * <p>文件名称生产</p> 
     */
	public static String generateFileName() {
		String dateStr = DateTimeHelper.formatDate(new Date(), "yyyy-MM-dd-");
		String uuid =UUID.randomUUID().toString();
		return dateStr.concat(uuid);
	}

	/**
	 * 把输入流写到输出流中 writeFile
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 * @return md5-checksum
	 * @since JDK1.6
	 */
	public static String writeFile(InputStream in, OutputStream out) {
		return writeFile(in, out, false);
	}
   /**
    * 
    * <p>加密文件流</p> 
    * @date 2013-3-29 下午3:14:07
    * @param in
    * @param out
    * @param isVerified
    * @return
    * @see
    */
	public static String writeFile(InputStream in, OutputStream out,
			boolean isVerified) {
		MessageDigest md5Digest = null;
		OutputStream outStream = null;
		InputStream inStream = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
			outStream = new BufferedOutputStream(out, 32 * 1024);
			inStream = new BufferedInputStream(in, 32 * 1024);
			byte[] writecache = new byte[32 * 1024];
			int size = 0;
			while ((size = inStream.read(writecache)) != -1) {
				if (isVerified) {
					md5Digest.update(writecache);
				}
				outStream.write(writecache, 0, size);
			}
			outStream.flush();
			return byteToHex(md5Digest.digest());
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} finally {
		    if(outStream != null) {
		        try {
	                outStream.close();
	            } catch (IOException e) {}
		    }
		    if(inStream != null) {
		        try {
		            inStream.close();
		        } catch (IOException e) {}
		    }
		}
		return "NO DIGEST.";
	}

	public static String getFileCheckSum(File file){
		String checkSum = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new OutputStream(){
				@Override
				public void write(int b) throws IOException {
				}};
			checkSum = writeFile(in, out, true);
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
		} finally {
		    if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {}
            }
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
		}
		
		return checkSum;
	}
	
	/**
	 * 
	 * <p>将byte转化成16进制</p> 
	 * @param bytes
	 * @return
	 * @see
	 */
	private static String byteToHex(byte bytes[]) {

		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			char c0 = hexDigits[(b & 0xf0) >> 4];
			char c1 = hexDigits[b & 0xf];
			sb.append(c0);
			sb.append(c1);
		}
		return sb.toString();
	}
}
