package ancient_encryption;

public class AtbashCipher extends AbstractChiper {

    @Override
    public String encrypt(String original) {
        String result = "";
        for (char c : original.toCharArray()) {
            result += findCharIndex(c) == -1 ? c : ALPHABETH[ALPHABETH.length - 1 - findCharIndex(c)];
        }
        return result;
    }

    @Override
    public String decrypt(String encrypted) {
        String result = "";
        for (char c : encrypted.toCharArray()) {
            result += findCharIndex(c) == -1 ? c : ALPHABETH[ALPHABETH.length - 1 - findCharIndex(c)];
        }
        return result;
    }
}
