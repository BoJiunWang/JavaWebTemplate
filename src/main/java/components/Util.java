package components;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.owasp.esapi.ESAPI;

/**
 * Created by Ivan_Wang on 2017-07-01.
 */
public class Util {

  /**
   * Encrypt string by SHA-1.
   *
   * @param originalString Original string
   * @return Encrypted string
   */
  public static String hashBySha1(String originalString) {
    try {
      MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
      digest.update(originalString.getBytes((Charset.forName("UTF-8"))));
      byte[] messageDigests = digest.digest();
      StringBuilder hexString = new StringBuilder();
      for (byte messageDigest : messageDigests) {
        String shaHex = Integer.toHexString(messageDigest & 0xFF);
        if (shaHex.length() < 2) {
          hexString.append(0);
        }
        hexString.append(shaHex);
      }
      return "0x" + hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      Logger.logWarn("hashBySha1", e.getMessage());
      return null;
    }
  }

  public static String encodeForHtml(String originalString) {
    return ESAPI.encoder().encodeForHTML(originalString);
  }
}
