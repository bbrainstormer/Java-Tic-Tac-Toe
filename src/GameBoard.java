import java.util.Arrays;

public class GameBoard {
    private final SquareState[][] boardArray;

    public GameBoard()
    {
        boardArray = new SquareState[3][3];
        for (SquareState[] row : boardArray)
        {
            Arrays.fill(row, SquareState.EMPTY);
        }
    }
    private static void PrintPaddingRow(int length)
    {
        for (int i = 0; i < length; i++)
        {
            System.out.print("-");
            if (i != length - 1)
            {
                System.out.print("+");
            }
        }
        System.out.println();
    }

    private void PrintBoardRow(int rowIndex) {
        SquareState[] row = boardArray[rowIndex];
        int numItems = row.length;
        for (int x = 0; x < numItems; x++)
        {
            System.out.print(row[x].toString());
            if (x != numItems - 1)
            {
                System.out.print("|");
            }
        }
        System.out.println();
    }
    public void PrintBoard() {
        int numColumns = boardArray.length;
        for (int y = 0; y < numColumns; y++) {
            PrintBoardRow(y);
            if (y != numColumns - 1) {
                int rowLength = boardArray[y].length;
                PrintPaddingRow(rowLength);
            }
        }
    }

    public boolean XVictory()
    {
        // Rows
        for (int row = 0; row < 3; row++)
        {
            if (boardArray[row][0] == SquareState.X && boardArray[row][1] == SquareState.X && boardArray[row][2] == SquareState.X)
            {
                return true;
            }
        }
        // Columns
        for (int col = 0; col < 3; col++)
        {
            if (boardArray[0][col] == SquareState.X && boardArray[1][col] == SquareState.X && boardArray[2][col] == SquareState.X)
            {
                return true;
            }
        }
        // Diagonals
        if (boardArray[0][0] == SquareState.X && boardArray[1][1] == SquareState.X && boardArray[2][2] == SquareState.X)
        {
            return true;
        }
        if (boardArray[0][2] == SquareState.X && boardArray[1][1] == SquareState.X && boardArray[2][0] == SquareState.X)
        {
            return true;
        }
        return false;
    }

    public boolean OVictory()
    {
        // Rows
        for (int row = 0; row < 3; row++)
        {
            if (boardArray[row][0] == SquareState.O && boardArray[row][1] == SquareState.O && boardArray[row][2] == SquareState.O)
            {
                return true;
            }
        }
        // Columns
        for (int col = 0; col < 3; col++)
        {
            if (boardArray[0][col] == SquareState.O && boardArray[1][col] == SquareState.O && boardArray[2][col] == SquareState.O)
            {
                return true;
            }
        }
        // Diagonals
        if (boardArray[0][0] == SquareState.O && boardArray[1][1] == SquareState.O && boardArray[2][2] == SquareState.O)
        {
            return true;
        }
        if (boardArray[0][2] == SquareState.O && boardArray[1][1] == SquareState.O && boardArray[2][0] == SquareState.O)
        {
            return true;
        }
        return false;
    }

    public SquareState GetCell(BoardPosition position)
    {
        return boardArray[position.y()][position.x()];
    }

    public void SetCell(BoardPosition position, SquareState state)
    {
        boardArray[position.y()][position.x()] = state;
    }

    public boolean IsFull()
    {
        boolean full = true;
        outer: for (SquareState[] row : boardArray)
        {
            for (SquareState square : row)
            {
                if (square == SquareState.EMPTY)
                {
                    full = false;
                    break outer;
                }
            }
        }
        return full;
    }

}
