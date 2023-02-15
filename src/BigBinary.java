public class BigBinary {
    private int[] bits;
    private boolean positive;

    public BigBinary(int[] bits, boolean positive) {
        this.bits = new int[bits.length];
        for (int i = 0; i < bits.length; i++){
            this.bits[i] = bits[i];
        }
        this.positive = positive;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!positive){
            sb.append("-");
        }

        boolean zero = true;
        for (int i = 0; i < bits.length; i++) {

            if (zero) {
                if (bits[i] == 1) {
                    zero = false;
                }
            }
            if (!zero) {
                sb.append(bits[i]);
            }
        }
        if (zero){
            return "0";
        }
        return sb.toString();
    }

    //fushufanma
    public static String complement(String o){
        StringBuilder mm = new StringBuilder();
        for (int l = 0;l < o.length();l++) {
            if (o.charAt(l) == '1'){
                mm.append("0");
            }else if (o.charAt(l) == '0'){
                mm.append("1");
            }
        }
        String oo = mm.toString();
        return sadd(oo,"1");
    }

    public static String delete0(String t){
        StringBuilder stringBuilder = new StringBuilder();
        boolean iszero = true;
        for (int i = 0; i < t.length(); i++) {

            if (iszero) {
                if (t.charAt(i) == '1') {
                    iszero = false;
                }
            }
            if (!iszero) {
                stringBuilder.append(t.charAt(i));
            }
        }
        if (iszero){
            return "0";
        }
        return stringBuilder.toString();
    }

    public BigBinary add(BigBinary bigbinary){
        change(add(this, bigbinary));
        return this;
    }

    public BigBinary minus(BigBinary bigbinary){
        change(minus(this,bigbinary));
        return this;
    }

    public void change(BigBinary bigbinary){
        this.bits =  bigbinary.bits.clone();
        this.positive = bigbinary.positive;
    }

    public static BigBinary add(BigBinary b1, BigBinary b2){
        String st1 = b1.toString();
        String st2 = b2.toString();
        String st11 = new String();
        String st21 = new String();
        String sum = new String();
        String minus = new String();

        //-13+(-7)
        if (st1.charAt(0) == '-' && st2.charAt(0) == '-'){
            st11 = st1.substring(1);
            st21 = st2.substring(1);
            sum = "-" + sadd(st11,st21);
            return toBigBinary(sum);
        }
        //-13+7
        if (st1.charAt(0) == '-' && st2.charAt(0) != '-'){
            st11 = st1.substring(1);
            st21 = st2;
            minus = sminus(st21,st11);
            return toBigBinary(minus);
        }
        //13+7
        if (st1.charAt(0) != '-' && st2.charAt(0) != '-'){
            sum = sadd(st1,st2);
            return toBigBinary(sum);
        }
        //13+(-7)
        if (st1.charAt(0) != '-' && st2.charAt(0) == '-'){
            st11 = st1;
            st21 = st2.substring(1);
            minus = sminus(st11,st21);
            return toBigBinary(minus);
        }else {return null;}
    }

    public static BigBinary minus(BigBinary b1, BigBinary b2){
        String st1 = b1.toString();
        String st2 = b2.toString();
        String st11 = new String();
        String st21 = new String();
        String sum = new String();
        String minus = new String();

        //13-7
        if (st1.charAt(0) != '-' && st2.charAt(0) != '-'){
            minus = sminus(st1,st2);
            return toBigBinary(minus);
        }
        //13-(-7)
        if (st1.charAt(0) != '-' && st2.charAt(0) == '-'){
            st11 = st1;
            st21 = st2.substring(1);
            sum = sadd(st11,st21);
            return toBigBinary(sum);
        }
        //(-13)-7
        if (st1.charAt(0) == '-' && st2.charAt(0) != '-'){
            st11 = st1.substring(1);
            st21 = st2;
            sum = "-" + sadd(st11,st21);
            return toBigBinary(sum);
        }
        //(-13)-(-7)
        if (st1.charAt(0) == '-' && st2.charAt(0) == '-'){
            st11 = st1.substring(1);
            st21 = st2.substring(1);
            minus = sminus(st21,st11);
            return toBigBinary(minus);
        }else {
            return null;
        }
    }

    public static BigBinary toBigBinary(String i){
        //w is positive
        boolean w = false;
        int len;
        if (i.charAt(0) == '-'){
            w = false;
            len = i.length()-1;
        }else {
            w = true;
            len = i.length();
        }
        int[] pp = new int[len];
        if (w) {
            for (int k = 0;k < len;k++) {
                pp[k] = i.charAt(k) - 48;
            }
        }
        if (!w){
            for (int k = len;k > 0;k--) {
                pp[k - 1] = i.charAt(k) - 48;
            }
        }
        BigBinary bb1 = new BigBinary(pp,w);
        return bb1;
    }

    public static String sadd(String b1, String b2) {
        int len1 = b1.length();
        int len2 = b2.length();
        String s1 = b1;
        String s2 = b2;
        StringBuilder sb1 = new StringBuilder();
        if (len1 > len2){
            for (int i = 0;i < (len1 - len2);i++){
                sb1.append(0);
            }
            sb1.append(b2);
            s1 = b1;
            s2 = sb1.toString();
        } else if (len1 < len2) {
            for(int j = 0; j < (len2 - len1);j++){
                sb1.append(0);
            }
            sb1.append(b1);
            s1 = sb1.toString();
            s2 = b2;
        }
        int flag = 0;
        StringBuilder sb2 = new StringBuilder();
        for (int z = s1.length() - 1;z >= 0; z--){
            if ((s1.charAt(z) - 48) == 0 && (s2.charAt(z) - 48) == 0){
                sb2.append(flag);
                flag = 0;
                continue;
            }
            if (((s1.charAt(z) - 48) == 0 && (s2.charAt(z) - 48) == 1 && flag == 0) || ((s1.charAt(z) - 48) == 1 && (s2.charAt(z) - 48) == 0 && flag == 0)) {
                sb2.append(1);
                flag = 0;
                continue;
            }
            if (((s1.charAt(z) - 48) == 0 && (s2.charAt(z) - 48) == 1 && flag == 1) || ((s1.charAt(z) - 48) == 1 && (s2.charAt(z) - 48) == 0 && flag == 1)){
                sb2.append(0);
                flag = 1;
                continue;
            }
            if ((s1.charAt(z) - 48) == 1 && (s2.charAt(z) - 48) == 1 && flag == 0){
                sb2.append(0);
                flag = 1;
                continue;
            }
            if ((s1.charAt(z) - 48) == 1 && (s2.charAt(z) - 48) == 1 && flag == 1){
                sb2.append(1);
                flag = 1;
            }
        }
        if (flag == 1){
            sb2.append(flag);
        }
        sb2.reverse();
        return sb2.toString();
    }


    public static String sminus(String c1, String c2) {

        boolean c1big = false;
        boolean c2big = false;
        boolean equal = false;

        StringBuilder sb11 = new StringBuilder();
        StringBuilder sb21 = new StringBuilder();

        if(c1.equals(c2)){
            return "0";
        }
        if(c1.length() == c2.length()){
            if(c1.compareTo(c2) > 0){
                c1big = true;
            }if(c2.compareTo(c1) > 0){
                c2big = true;
            }
        }
        if(c1.length() > c2.length()){
            c1big = true;
        }
        if(c1.length() < c2.length()){
            c2big = true;
        }

        //after complement and adding 0
        String c12;
        String c22;

        String result1 = new String();
        String result2 = new String();
        String result3 = new String();

        StringBuilder sb12 = new StringBuilder();
        StringBuilder sb22 = new StringBuilder();

        //13-7
        if (c1big) {
            //change 111 to 0111
            for (int i = 0; i < c1.length() - c2.length(); i++) {
                sb22.append(0);
            }
            sb22.append(c2);

            c12 = "0" + c1;
            c22 = complement("0" + sb22.toString());

            result1 = sadd(c12, c22);
            result2 = result1.substring(1);
            result3 = delete0(result2);

            return result3;
        }

        //7-13
        if (c2big) {
            for (int i = 0; i < c2.length() - c1.length(); i++) {
                sb12.append(0);
            }
            sb12.append(c1);

            c12 = complement("0" + sb12.toString());
            c22 = "0" + c2;

            result1 = sadd(c12, c22);
            result2 = result1.substring(1);
            result3 = "-" + delete0(result2);

            return result3;
        }else {return "error";}
    }

    public BigBinary multiply(BigBinary bigbinary){
        return null;
    }

    public static BigBinary multiply(BigBinary b1, BigBinary b2){
        return null;
    }

}