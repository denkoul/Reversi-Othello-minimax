import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Othello {
    public static Board mainBoard;

    public static int current_color = Color.Black;

    public static void main(String[] args) throws java.lang.InterruptedException
    {
        Scanner scanner = new Scanner(System.in);
        int h;
        System.out.print("How many human player there will be? (0, 1, 2)\n>>>");
        do
        {
            h = scanner.nextInt();
        }while(h<0 && h > 2);

        switch(h)
        {
            case 0:
                OnlyAI();
                break;
            case 1:
                Normal();
                break;
            case 2:
                OnlyHuman();
                break;
        }

    }

    public static void OnlyAI()throws java.lang.InterruptedException
    {
        OthelloPlayer AI1, AI2;
        int depth = GetDepth();

        System.out.print("AI-only play can be quite fast, so insert the amount of SECONDS you want the AI to wait " +
                "after every turn (0 for none)\n>>>");
        Scanner s = new Scanner(System.in);
        float temp = s.nextFloat();
        int sec = (int)(1000 * temp);

        AI1 = new OthelloPlayer(depth, Color.White);
        AI2 = new OthelloPlayer(depth, Color.Black);

        mainBoard = new Board();
        mainBoard.printUI();

        while(!mainBoard.isFinal())
        {
            if (AI1.isMyTurn(current_color))
            {
                mainBoard = AI1.MiniMax(mainBoard);
            }
            else
            {
                mainBoard = AI2.MiniMax(mainBoard);
            }
            TimeUnit.MILLISECONDS.sleep(sec);
            mainBoard.printUI();
            System.out.println("Current score is: " + mainBoard.getScore());
            current_color = 3-current_color;
        }

    }

    public static void Normal()throws java.lang.InterruptedException
    {
        Scanner scanner = new Scanner(System.in);
        OthelloPlayer HumanPlayer, AIPlayer;

        System.out.print("Black plays first\nPress 'F' to play first\nor any other button to play second\n>>> ");
        String ans = scanner.nextLine();

        int depth = GetDepth();

        mainBoard = new Board();
        mainBoard.printUI();

        if (ans.equals("F") || ans.equals("f"))
        {
            HumanPlayer = new OthelloPlayer(depth,Color.Black);
            AIPlayer = new OthelloPlayer(depth,Color.White);
        }
        else
        {
            HumanPlayer = new OthelloPlayer(depth,Color.White);
            AIPlayer = new OthelloPlayer(depth,Color.Black);
        }

        while(!mainBoard.isFinal())
        {
            if (HumanPlayer.isMyTurn(current_color))
            {
                System.out.println("It's player's turn: ");

                ArrayList<Move> myMoves = mainBoard.avaliableMoves(current_color);

                if (myMoves.isEmpty()) System.out.println("No available move for you");
                else
                {
                    System.out.print("Select a move: ");
                    for (int i = 0; i < myMoves.size(); i++)
                    {
                        System.out.print("Option " + (i + 1) + " : (" + (myMoves.get(i).getX() + 1) + ", " + intToLetter((myMoves.get(i).getY() + 1)) + ") ");
                    }
                    System.out.print("\n>>> ");
                    mainBoard.makeMove(mainBoard, myMoves.get(scanner.nextInt() - 1), current_color);
                }
            }
            else
            {
                System.out.println("It's CPU's turn:\nExecution will be delayed for 2sec for the penny human brain to" +
                        " catch up..");

                TimeUnit.SECONDS.sleep(2);
                mainBoard = AIPlayer.MiniMax(mainBoard);
            }
            mainBoard.printUI();
            System.out.println("Current score is: " + mainBoard.getScore());
            current_color = 3-current_color;
        }
    }

    public static void OnlyHuman()
    {
        Scanner scanner = new Scanner(System.in);
        OthelloPlayer HumanPlayer1, HumanPlayer2;

        System.out.print("Player 1 enter a name. THIS PLAYER PLAYS FIRST!!!\n>>> ");
        String p1name = scanner.nextLine();
        System.out.print("Player 2 enter a name. This player plays second\n>>> ");
        String p2name = scanner.nextLine();

        mainBoard = new Board();
        mainBoard.printUI();

        HumanPlayer1 = new OthelloPlayer(0, Color.Black);
        HumanPlayer2 = new OthelloPlayer(0, Color.White);

        while(!mainBoard.isFinal())
        {
            if (HumanPlayer1.isMyTurn(current_color))
            {
                System.out.println(p1name + " plays: ");
            }
            else
            {
                System.out.println(p2name + " plays: ");
            }
            ArrayList<Move> myMoves = mainBoard.avaliableMoves(current_color);

            if (myMoves.isEmpty()) System.out.println("No available move for you");
            else
            {
                System.out.print("Select a move: ");
                for (int i = 0; i < myMoves.size(); i++)
                {
                    System.out.print("Option " + (i + 1) + " : (" + (myMoves.get(i).getX() + 1) + ", " + intToLetter((myMoves.get(i).getY() + 1)) + ") ");
                }
                System.out.print("\n>>> ");
                mainBoard.makeMove(mainBoard, myMoves.get(scanner.nextInt() - 1), current_color);
            }

            mainBoard.printUI();
            System.out.println("Current score is: " + mainBoard.getScore());
            current_color = 3-current_color;
        }
    }

    public static int GetDepth()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter depth\n>>>");
        return scanner.nextInt();
    }

    public static char intToLetter(int x) {
        char[] array = { 'A', 'B', 'C', 'D', 'E', 'F', 'G','H' };
        return array[x-1];
    }
}