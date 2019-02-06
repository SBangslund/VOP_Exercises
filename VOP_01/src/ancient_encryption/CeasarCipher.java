package ancient_encryption;

public class CeasarCipher extends AbstractChiper {

    private final int rotFactor;

    public CeasarCipher(int rotFactor) {
        this.rotFactor = rotFactor > 0 && rotFactor <= ALPHABETH.length ? rotFactor : 1;
    }

    @Override
    public String encrypt(String original) {
        String result = "";
        for (char c : original.toCharArray()) {
            result += findCharIndex(c) == -1 ? c : ALPHABETH[(findCharIndex(c) + rotFactor) % ALPHABETH.length];
        }
        return result;
    }

    @Override
    public String decrypt(String encrypted) {
        String result = "";
        for (char c : encrypted.toCharArray()) {
            result += findCharIndex(c) == -1 ? c : ALPHABETH[(findCharIndex(c) + ALPHABETH.length - rotFactor) % ALPHABETH.length];
        }
        return result;
    }

}
