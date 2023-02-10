public enum Movement {
    L('L'),
    R('R'),
    M('M');

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    Movement(char asChar) {
        this.asChar = asChar;
    }
}
