import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
    private final Scanner scanner;
    private final GameBoard board;
    private GameState gameState;

    public TicTacToeGame(Scanner scanner)
    {
        this.scanner = scanner;
        board = new GameBoard();
        gameState = GameState.X_TURN;
    }

    private BoardPosition GetPlayerMove()
    {
        System.out.print("Enter a row (1-3): ");
        int row = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Enter a column (1-3): ");
        int column = Integer.parseInt(scanner.nextLine()) - 1;
        BoardPosition position = new BoardPosition(row, column);
        boolean validPosition = (0 <= row) && (row < 3)
                && (0 <= column) && (column < 3)
                && board.GetCell(position) == SquareState.EMPTY;

        // If the position is invalid, try again
        if (!validPosition)
        {
            System.out.println("Not a valid position");
            System.out.println("Try again");
            return GetPlayerMove();
        }
        return new BoardPosition(row, column);
    }

    public void ExecuteTurn()
    {
        switch (gameState)
        {
            case X_TURN:
                System.out.println("X's turn");
                board.PrintBoard();
                BoardPosition xMove = GetPlayerMove();
                board.SetCell(xMove, SquareState.X);
                if (board.XVictory())
                {
                    gameState = GameState.X_WON;
                }
                else if (board.IsFull())
                {
                    gameState = GameState.DRAW;
                }
                else
                {
                    gameState = GameState.O_TURN;
                }
                break;
            case O_TURN:
                System.out.println("O's turn");
                board.PrintBoard();
                BoardPosition oMove = GetPlayerMove();
                board.SetCell(oMove, SquareState.O);
                if (board.OVictory())
                {
                    gameState = GameState.O_WON;
                }
                else if (board.IsFull())
                {
                    gameState = GameState.DRAW;
                }
                else
                {
                    gameState = GameState.X_TURN;
                }
                break;
            case X_WON:
                board.PrintBoard();
                System.out.println("Congratulations! Player X won! Press enter to continue ");
                gameState = GameState.GAME_FINISHED;
                scanner.nextLine();
                break;
            case O_WON:
                board.PrintBoard();
                System.out.println("Congratulations! Player O won! Press enter to continue ");
                gameState = GameState.GAME_FINISHED;
                scanner.nextLine();
                break;
            case DRAW:
                board.PrintBoard();
                System.out.println("It was a draw. Press enter to continue ");
                gameState = GameState.GAME_FINISHED;
                scanner.nextLine();
                break;
            case GAME_FINISHED:
            default:
                System.out.println("The game has finished.  There's nothing more");
                scanner.nextLine();
                break;
        }
    }

    public boolean GameFinished()
    {
        return (gameState == GameState.GAME_FINISHED);
    }
}
