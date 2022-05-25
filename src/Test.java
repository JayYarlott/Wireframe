package Wireframe.src;

public class Test {
    long x = 15;

    public Test(long x) {
        this.x = x;
    }

    public static void main(String[] args) {

        console.log((long) new Test(36028797018963967L).getX(), 36028797018963967L);
    }

    public double getX() {
        return x;
    }
}
