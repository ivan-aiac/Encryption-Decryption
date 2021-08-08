package encryptdecrypt;

public class CaesarCipher implements CipherType {

    private static final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
        for (char c : data.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                c = ALPHABET_LOWER.charAt(Math.floorMod(ALPHABET_LOWER.indexOf(c) + key * direction, 26));
            } else if (c >= 'A' && c <= 'Z') {
                c = ALPHABET_UPPER.charAt(Math.floorMod(ALPHABET_UPPER.indexOf(c) + key * direction, 26));
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
