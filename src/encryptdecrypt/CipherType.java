package encryptdecrypt;

public interface CipherType {
    String encode(String data, int key);
    String decode(String encodedData, int key);
}
