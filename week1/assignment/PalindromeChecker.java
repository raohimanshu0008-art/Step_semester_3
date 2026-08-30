import java.util.*;

class PalindromeChecker {
    static boolean isPalindromeIterative(String s) {
        for(int i=0,j=s.length()-1;i<j;i++,j--)
            if(s.charAt(i)!=s.charAt(j)) return false;
        return true;
    }

    static boolean isPalindromeRecursive(String s) {
        if(s.length()<2) return true;
        if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
        return isPalindromeRecursive(s.substring(1,s.length()-1));
    }

    static boolean isPalindromeArrayReversal(String s) {
        char[] a=s.toCharArray();
        for(int i=0,j=a.length-1;i<j;i++,j--) {
            char t=a[i]; a[i]=a[j]; a[j]=t;
        }
        return s.equals(new String(a));
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println("Iterative: "+(isPalindromeIterative(s)?"Palindrome":"Not Palindrome"));
        System.out.println("Recursive: "+(isPalindromeRecursive(s)?"Palindrome":"Not Palindrome"));
        System.out.println("Array Reversal: "+(isPalindromeArrayReversal(s)?"Palindrome":"Not Palindrome"));
    }
}