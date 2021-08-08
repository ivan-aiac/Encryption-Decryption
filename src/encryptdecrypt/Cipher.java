package encryptdecrypt;

public class Cipher {

    private CipherType cipherType;

    public void setCipherType(CipherType cipherType) {
        this.cipherType = cipherType;
    }

    public String encode(String data, int key) {
        return cipherType.encode(data, key);
    }

    public String decode(String encodedData, int key) {
        return cipherType.decode(encodedData, key);
    }
}
