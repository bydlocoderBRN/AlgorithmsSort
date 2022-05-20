package Str;

public class KnutMorrisPratt {
    public static int findStr(String str, String subStr){
        int[] prefMas = Prefix.prefMassive(subStr);
        int strIndex=0;
        int subIndex=0;
        boolean subIndexIsNull = false;
        while (subIndex<subStr.length() && strIndex<str.length()){
            if(str.charAt(strIndex) == subStr.charAt(subIndex)){
                strIndex++;
                subIndex++;
                subIndexIsNull=false;
            }else {
                if(subIndex==0){
                    strIndex++;
                    subIndex++;
                    subIndexIsNull=true;
                }else {
                    subIndex = prefMas[subIndex - 1];
                }
            }
        }
        if(subIndexIsNull){
            return -1;
        }else return strIndex -subIndex;
    }
}
