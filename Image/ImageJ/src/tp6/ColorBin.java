package tp6;

/**
 * Created by melkir on 19/04/14.
 */
public enum ColorBin {
    BLACK(0),
    WHITE(255);

    public int value;

    private ColorBin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public ColorBin other() {
        return this.equals(BLACK) ? WHITE : BLACK;
    }
}
