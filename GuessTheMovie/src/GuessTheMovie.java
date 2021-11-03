//Java OOP project.
//-------------------
//Guess The Movie game.
//---------------------
//A random movie out of a list of movies will be displayed encrypted using
//" _ ". You have to guess the movie letter by letter. As soon as you guess all the
//letters you win. If you fail to do so you lose. You get 5 wrong choices



import java.io.File;
import java.util.Scanner;
import java.lang.Character;

//   class to handle the movies   //

class Movies{
    private int noOfMovies = 0;
    private final String [] movies = new String [26];
    private String randomMovie = "";
    private String encryptedMovie = "";
    boolean str = false;

//    Reading the movies  //

    public boolean strEquals(){
        return str;
    }
    public void readMovies() throws Exception{
        File file = new File("movies.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            movies[noOfMovies] = line;
            noOfMovies++;
        }
    }
    public void selectRandomMovie() {
        int ctr = (int)(Math.random()*noOfMovies)+1;
        randomMovie += movies[ctr];
    }
    public String getRandomMovie() {
        return randomMovie;
    }
    public void encryptMovie(){
        StringBuilder sb = new StringBuilder(encryptedMovie);
        for(int i = 0 ; i < randomMovie.length() ; i++){
            if(randomMovie.charAt(i) != ' '){
                sb.append('_');
            }else{
                sb.append(' ');
            }
        }
        encryptedMovie = sb.toString();
        System.out.println("Your encrypted movie is below.");
        System.out.println(encryptedMovie);
    }
    public boolean checkCharacter(char ch){
        boolean val = false;
        if(Character.isUpperCase(ch)){
            ch = Character.toLowerCase(ch);
        }
        StringBuilder string = new StringBuilder(encryptedMovie);
        for(int i=0 ; i<randomMovie.length() ; i++){
            if(randomMovie.charAt(i) == ch){
                string.setCharAt(i,ch);
                val = true;
            }
        }
        encryptedMovie = string.toString();
        if(encryptedMovie.equalsIgnoreCase(randomMovie)){
            str = true;
        }
        System.out.println(encryptedMovie);
        return val;
    }
}

//   class to start the game and end   //

class Game{
    boolean flag = true;

    Scanner sc = new Scanner(System.in);

    public int startGame(){
        int wrongGuess = -1;
        Movies movie = new Movies();
        try{
            movie.readMovies();
        }catch (Exception exception){
            System.out.println("Movie not found");
        }
        movie.selectRandomMovie();
        movie.encryptMovie();
        wrongGuess++;
        while(wrongGuess < 5){
            if(movie.strEquals()){
                return wrongGuess;
            }else{
                System.out.println("You have guessed ("+(wrongGuess)+") wrong guesses.");
                System.out.println("Enter the character: ");
                char ch = sc.next().charAt(0);
                flag = movie.checkCharacter(ch);
                if(!flag){
                    wrongGuess++;
                }else{
                    System.out.println("Nice Guess...");
                }
            }
        }
        System.out.println("You have guessed ("+(wrongGuess)+") wrong guesses.");
        System.out.println("The Movie was: "+movie.getRandomMovie());
        return wrongGuess;
    }
}

//     Main Method   //

public class GuessTheMovie {
    public static void main(String[] args){
        int ctr;
        System.out.println("""
                We have chosen a random movie for you.
                Your movie is encrypted. Guess the correct letter and reveal the letters,
                finally guess the movie.
                -------------------------------------------------------------------""");
        Game game = new Game();
        ctr = game.startGame();
        if(ctr == 5){
            System.out.println("GAME OVER YOU LOSE!!!");
        }
        else{
            System.out.println("CONGRATULATIONS YOU WIN");
        }
    }
}