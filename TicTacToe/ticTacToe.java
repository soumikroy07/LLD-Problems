package LLDProblems.TicTacToe;

import java.util.Scanner;

public class ticTacToe {

    private Players player1,player2;
    private Board board;
    private int playerCount=0;

    public static void main(String[] args) {
        ticTacToe t = new ticTacToe();
        t.startGame();
    }

    public void startGame() {

        // Take players input

        Scanner sc = new Scanner(System.in);

        player1 = takePlayersInput(++playerCount);
        player2 = takePlayersInput(++playerCount);

        while(player1.getSymbol()==player2.getSymbol()){
            System.out.println(player2.getSymbol() + "is already chosen, take another one" );
            player2.setSymbol(sc.next().charAt(0));
        }

        // Create the board

        board = new Board(player1.getSymbol(),player2.getSymbol());

        // Run the game

        boolean player1Turn = true;
        int status=Board.INCOMPLETE;
        while (status == Board.INVALID || status == Board.INCOMPLETE){
            if(player1Turn){
                System.out.println("Player 1 - " + player1.getName()+"'s turn ");
                System.out.println("Choose your cell ");
                int x= sc.nextInt();

                int y = sc.nextInt();

                status=board.move(player1.getSymbol(),x,y);
                if(status==Board.INVALID){
                    System.out.println("Invalid turn!!S Try again ");
                    continue;
                }else{
                    player1Turn=false;
                    Board.print();
                }
            }else{
                System.out.println("Player 2 - " + player2.getName()+"'s turn ");
                System.out.println("Choose your cell ");
                int x= sc.nextInt();

                int y = sc.nextInt();

                status=board.move(player2.getSymbol(),x,y);
                if(status==Board.INVALID){
                    System.out.println("Invalid turn!! Try again ");
                    continue;
                }else{
                    player1Turn=true;
                    Board.print();
                }
            }
        }

        if (status == Board.PLAYER1WINS){
            System.out.println("Player 1 - "+player1.getName()+" wins..!!");
        }else if (status == Board.PLAYER2WINS){
            System.out.println("Player 2 - "+player2.getName()+" wins..!!");
        }else {
            System.out.println("It's a draw match..!");
        }
    }

    public Players takePlayersInput(int n){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter "+n+" player name : " );
        String name=sc.next();

        System.out.println("Enter "+n+" player symbol : ");
        char symbol = sc.next().charAt(0);

        Players p = new Players(name,symbol);

        return p;
    }

}
