package encryptdecrypt;

public class UnicodeCipher implements CipherType {
    @Override
    public String encode(String data, int key) {
        return shift(data, key, 1);
    }

    @Override
    public String decode(String encodedData, int key) {
        return shift(encodedData, key, -1);
    }

    private String shift(String data, int key, int direction) {
        StringBuilder sb = new StringBuilder();
        for (char c: data.toCharArray()) {
            c = (char) (c + key * direction);
            sb.append(c);
        }
        return sb.toString();
    }
}
