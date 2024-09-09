package Practice;

public class NextGreatestElement {
    public static void main(String[] args) {
        int nums1[] = new int[]{4,1,2};
        int res[] = new int[nums1.length];
        int nums2[] = new int[]{1,3,4,2};

        for (int i=0;i<nums1.length;i++) {
            boolean found = false;
            for(int j=0;j<nums2.length;j++) {
                if(nums1[i]==nums2[j]) {
                    found = true;
                }
                if(found && nums1[i]<nums2[j]) {
                    res[i] = nums2[j];
                    break;
                }
            }
            if(res[i]==0){
                res[i] = -1;
            }
        }
        for (int re : res) {
            System.out.println(re);
        }
    }
}
