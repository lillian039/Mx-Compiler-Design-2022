package LLVMIR.Value;

public class ConstString extends IRValue {
    String string;

    public ConstString(String string) {
        this.string = string;
    }
}
