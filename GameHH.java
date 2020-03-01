public class GameHH extends Game {

    private GameController gameController;

    public GameHH(){ 
        setHasStarted(false);
        view = new View();
        gameController = new GameController();
        prepareToGame();
        playGame();
    }

    void prepareToGame() {
        setPlayer1(new PlayerHuman("first"));
        setPlayer2(new PlayerHuman("second"));
        setCurrentPlayer(getPlayer2());
    }

    private void playGame() {
        setHasStarted(true);
        String textToDisplay = "";
        String coordinatesAsString = "";
        boolean isGaming = true;
        while(isGaming){
            view.clearScreen();
            view.printTitle(String.format("It's %s's turn to strike!", turnOfPlayer()));
            view.printOcean(getCurrentPlayer().getOcean(), getHasStarted());

            int[] coordinatesAsInt = gameController.getCoordinates(coordinatesAsString);
            textToDisplay = shoot(coordinatesAsInt);
            view.printText(textToDisplay);

            if(getPlayer1().hasLost() || getPlayer2().hasLost()){
                isGaming = false;
            } else{
                changeCurrentPlayer();
                view.inputFromUser("Press Enter to continue");
            }
        }
        view.printText(String.format("Congratoulations %s! You Won!", turnOfPlayer()));
    }
}