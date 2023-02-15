public class BigBinaryTest {
    public static void main(String[] args) {
        BigBinary big1 = new BigBinary(new int[]{1,1},true);
        BigBinary big2 = new BigBinary(new int[]{1,0,1},false);
        System.out.println(BigBinary.minus(big1,big2));
    }
}
