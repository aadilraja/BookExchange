package application.backend.utils;

public class Pairs<key,value> {
    public key key;
    public value value;
    public Pairs(key key, value value) {
        this.key = key;
        this.value = value;
    }
    public key getKey() {
        return key;
    }
    public value getValue() {
        return value;
    }
}
