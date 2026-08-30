import java.util.*;

class RockPaperScissors{
    static String play(String p,String c){
    if(p.equalsIgnoreCase(c)) return "Draw";
    if((p.equalsIgnoreCase("Rock")&&c.equals("Scissors"))||
       (p.equalsIgnoreCase("Paper")&&c.equals("Rock"))||
       (p.equalsIgnoreCase("Scissors")&&c.equals("Paper"))) return "Player Wins";
    return "Computer Wins";

    }

    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        String[] a={"Rock","Paper","Scissors"};
        int w=0,l=0,d=0;

        for(int i=1;i<=5;i++){
            System.out.print("Enter move: ");
            String p=s.next();
            String c=a[new Random().nextInt(3)];
            String r=play(p,c);
            System.out.println("Player: "+p+" Computer: "+c+" "+r);
            if(r.equals("Player Wins"))w++;
            else if(r.equals("Computer Wins"))l++;
            else d++;
        }

        System.out.println("Wins: "+w+" Losses: "+l+" Draws: "+d+" Win %: "+w*20.0);
    }
}