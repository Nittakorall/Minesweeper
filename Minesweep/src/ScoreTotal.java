public class ScoreTotal { public ScoreTotal() {
}
    public void starter() {
        Player player = new Player("");
        player.playerName();
        int winTimes = 0;
        int lostTimes = 0;

        Menu menu = new Menu();
        menu.menu(winTimes, lostTimes);
    }
}