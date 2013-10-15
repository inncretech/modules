package models;

public enum EventType {

    LOGIN((byte) 1), SIGNUP((byte) 2), FORGOTPWD((byte)3), UNSUBSCRIBE((byte)4);

    private byte id;

    private EventType(byte id) {
        this.id = id;
    }

    public byte getId() {
        return this.id;
    }

    public static EventType getById(byte id){
        if(id == 2)
           return  SIGNUP;
        else
            return FORGOTPWD;
    }
}
