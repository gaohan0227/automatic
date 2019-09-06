package priv.bigant.aotomatic.common;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encodes {
    private static final String K = "UTF-8";
    private static final char[] ALLATORIxDEMO = CustomArrayList.ALLATORIxDEMO("*:(8.>,<\"2[IYO_M]CSAQGWEU[KYI_O]MSCQ{iyo\u007fm}csaqgweu{kyi\u007fo}mscq").toCharArray();

    public static String unescapeXml(String a) {
        return StringEscapeUtils.unescapeXml(a);
    }

    /*public static String encodeHex(byte[] a) {
        return new String(Hex.encodeHex(a));
    }
*/
    public static String escapeXml(String a) {
        return StringEscapeUtils.escapeXml10(a);
    }

    public static String urlEncode(String a) throws UnsupportedEncodingException {
        try {
            return URLEncoder.encode(a, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw var2;
        }
    }

    public static byte[] decodeBase64(String a) {
        return Base64.decodeBase64(a.getBytes());
    }

    public static String decodeBase64String(String a) {
        try {
            return new String(Base64.decodeBase64(a.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return "";
        }
    }

    public static String encodeBase64(String a) {
        try {
            return new String(Base64.encodeBase64(a.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var2) {
            return "";
        }
    }

    public Encodes() {
    }

    /*public static byte[] decodeHex(String a) {
        try {
            return Hex.decodeHex(a.toCharArray());
        } catch (DecoderException var2) {
            throw Exceptions.unchecked(var2);
        }
    }

    public static String urlDecode(String a) {
        try {
            return URLDecoder.decode(a, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            throw Exceptions.unchecked(var2);
        }
    }*/

    public static String encodeBase62(byte[] a) {
        char[] var10000 = new char[a.length];
        boolean var10002 = true;
        char[] var1 = var10000;

        int var2;
        for(int var3 = var2 = 0; var3 < a.length; var3 = var2) {
            int var10001 = var2;
            char var4 = ALLATORIxDEMO[(a[var2] & 255) % ALLATORIxDEMO.length];
            ++var2;
            var1[var10001] = var4;
        }

        return new String(var1);
    }

    public static String escapeHtml(String a) {
        return StringEscapeUtils.escapeHtml4(a);
    }

    public static String unescapeHtml(String a) {
        return StringEscapeUtils.unescapeHtml4(a);
    }

    public static String encodeBase64(byte[] a) {
        return new String(Base64.encodeBase64(a));
    }
}
