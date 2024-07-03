package testing.example;

public class Attack {
    private int index;
    private String name;
    private String effect;
    private String type;
    private String kind;
    private int power;
    private int accuracy;
    private int pp;

    public Attack(int index, String name, String effect, String type, String kind, int power, int accuracy, int pp) {
        this.index = index;
        this.name = name;
        this.effect = effect;
        this.type = type;
        this.kind = kind;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public int index() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String effect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String type() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String kind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int power() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int accuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int pp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }
}

