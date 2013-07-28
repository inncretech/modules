package models;

public enum EventType {

    LOGIN((byte) 1), SIGNUP((byte) 2);

    private byte id;

    private EventType(byte id) {
        this.id = id;
    }

    public byte getId() {
        return this.id;
    }
}
