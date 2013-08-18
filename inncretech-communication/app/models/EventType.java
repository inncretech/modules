package models;

public enum EventType {

    LOGIN((byte) 1), SIGNUP((byte) 2), FORGOTPWD((byte)3);

    private byte id;

    private EventType(byte id) {
        this.id = id;
    }

    public byte getId() {
        return this.id;
    }
}
