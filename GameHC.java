public class GameHC extends GameAI {

    private GameController gameController;

    public GameHC(int difficultyLevel){ 
        setDifficultyLevel(difficultyLevel);
        view = new View();
        gameController = new GameController();
        setHasStarted(false);
        prepareToGame();
        playGame();
    }

    void prepareToGame() {
        setPlayer1(new PlayerHuman("first"));
        setPlayer2(new PlayerComp("Computer"));
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
            int[] coordinatesAsInt;
            
            if(getCurrentPlayer() == getPlayer2()){
                view.printOcean(getCurrentPlayer().getOcean(), getHasStarted());
                coordinatesAsInt = gameController.getCoordinates(coordinatesAsString);
                textToDisplay = shoot(coordinatesAsInt);
                view.printText(textToDisplay);
            }
            else{
                coordinatesAsInt = getComputerCoordinates();
                textToDisplay = shoot(coordinatesAsInt);
                view.printText(textToDisplay);
                view.printOcean(getCurrentPlayer().getOcean(), getHasStarted());
            }

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