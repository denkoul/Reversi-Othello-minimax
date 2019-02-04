import java.util.ArrayList;

public class OthelloPlayer
{
    private int maxDepth;
    int player_color;

    public OthelloPlayer(int maxDepth, int player_color)
    {
        this.maxDepth = maxDepth;
        this.player_color = player_color;
    }

    public Board MiniMax(Board board)
    {
        if (player_color == Color.Black)
        {
            return max(new Board(board), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }
        else
        {
            return min(new Board(board), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    public Board max(Board board, int a, int b, int depth)
    {
        if((board.isFinal()) || (depth == maxDepth))
        {
            return board;
        }
        ArrayList<Board> children = board.getChildren(Color.Black);
        if (children.isEmpty()) return board;
        Board maxBoard = children.get(0);
        for (Board child : children)
        {
            Board oppBoard = min(child, a, b, depth + 1);
            if(oppBoard.value > maxBoard.value)
            {
                maxBoard = child;
            }
            else if(oppBoard.value == maxBoard.value && Math.random() < .5f)
            {
                maxBoard = child;
            }

            //AB Pruning
            a = Integer.max(a, oppBoard.value);
            if(b <= a)
            {
                break;
            }
        }
        return maxBoard;
    }

    public Board min(Board board, int a, int b, int depth)
    {
        if((board.isFinal()) || (depth == maxDepth))
        {
            return board;
        }
        ArrayList<Board> children = board.getChildren(Color.White);
        if (children.isEmpty()) return board;

        Board minBoard = children.get(0);
        for (Board child : children)
        {
            Board oppBoard = max(child, a, b, depth + 1);
            if(oppBoard.value < minBoard.value)
            {
                minBoard = child;
            }
            else if(oppBoard.value == minBoard.value && Math.random() < .5f)
            {
                minBoard = child;
            }

            //AB Pruning
            b = Integer.min(b, oppBoard.value);
            if(b <= a)
            {
                break;
            }
        }
        return minBoard;
    }

    public boolean isMyTurn(int c)
    {
        return player_color==c;
    }
}
