public class SumOfTwoNumberUsingStringAndChar { //
    public static void main(String[] args) {
        String s1 = "223", s2 = "789"; // "1012"
        // length is same for both the strings
        // on different lengths 0 needs to be appended 23 + 789
        StringBuilder result = new StringBuilder();
        char[] s1c = new StringBuilder(s1).reverse().toString().toCharArray();

        char[] s2c = new StringBuilder(s2).reverse().toString().toCharArray();
        int previousRemainder =0;
        for(int i=0;i< s1c.length;i++) {
            int sum = Integer.parseInt(s1c[i]+"") + Integer.parseInt(s2c[i]+"")+previousRemainder;
            if(sum>9) {
                previousRemainder = 1;
                result.append(sum%10);
            }else {
                result.append(sum);
            }
            if(i== s1c.length-1 && sum>9){
                result.append(1);
            }
        }
        System.out.print(result.reverse().toString());

    }
}

