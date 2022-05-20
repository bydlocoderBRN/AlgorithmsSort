package Str;

public class Prefix {
    private static int calcPrefix(String s){
        int len =s.length();
        int maxPref=0;
        int tempPref=0;
        boolean equal = true;
        for(int i=0, j=len-1;i<len-1 && j>0;i++, j--){
            equal = true;
//            System.out.println("lef= " + i + " right= " + j);
            for(int k=0, n =j; k<=i && n<=len-1; k++, n++){
//                System.out.println(s.charAt(k) + " vs " + s.charAt(n));
                if(Character.compare(s.charAt(k),s.charAt(n)) != 0){
                    equal=false;
//                    System.out.println("!=");
                    break;
                }
            }
            if(equal){
                tempPref=i+1;
//                System.out.println(maxPref + " " + tempPref);
                if(tempPref>maxPref){
                    maxPref = tempPref;
                }
            }

        }
        return maxPref;
    }
    public static int[] prefMassive(String s){
        int[] mas = new int[s.length()];
        for(int i=0;i<=s.length()-1;i++) {
            mas[i] = calcPrefix(s.substring(0,i+1));
        }
        return mas;
    }
}
