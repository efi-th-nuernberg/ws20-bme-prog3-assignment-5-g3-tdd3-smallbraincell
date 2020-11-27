import java.util.Arrays;
public class Life implements ILife {

//Spielfeld  
public boolean raster[][] = new boolean[10][10];

  public static void main(String[] args) {
    Life l = new Life(new String[] {  "     ",
                                      "     ",
                                      " *** ",
                                      "     ",
                                      "     " });
    l = (Life) l.nextGeneration();
  }


  public Life() {
    nukeAll();
  }

  public Life(String[] setup) {
    this();
    for (int y = 0; y < setup.length; y++)
      for (int x = 0; x < setup[y].length(); x++)
        if (setup[y].charAt(x) != ' ')
          setAlive(x, y);
  }


  @Override
  public void nukeAll() {
    //Alle löschen
    raster = new boolean[10][10];
    
  }

  @Override
  public void setAlive(int x, int y) {
    raster[x][y] = true; //Zellen sind da

  }

  @Override

  public void setDead(int x, int y) {
    //Zelle an x, y ist "falsch" -> tot
    raster[x][y] = false;

  }

  @Override
  public boolean isAlive(int x, int y) {
    //Gucken ob Zelle im Raster ist
    return raster[x][y];
    
  }

  @Override
  public ILife nextGeneration() {
   
   boolean[][] future = new boolean[raster.length][raster[0].length];
   for(int i = 0;i<raster.length;i++){
    for(int j= 0; j<raster[i].length; j++){
      int neighbours = checkForNeighbours(i, j);
      if(neighbours == 3){
        future[i][j] = true;
      }else if(isAlive(i,j) && neighbours == 2){
        future[i][j] = true;
      }
    }
   }
   raster = future;
   for(boolean[] a: raster){
     System.out.println(Arrays.toString(a));
   } 
   return this;
 }
  
  
  
  public int checkForNeighbours(int x, int y) {
    int neighbours = 0;

    //oben
    if(y - 1 >= 0 && raster[x][y-1]) {
      neighbours++;
    }
    //unten
    if(y + 1 < raster[x].length && raster[x][y+1]) {
      neighbours++;
    }
    //links
    if(x - 1 >= 0 && raster[x-1][y]) {
      neighbours++;
    }
    //rechts
    if(x + 1 < raster.length && raster[x+1][y]) {
      neighbours++;
    }
    //oben rechts
    if(x + 1 < raster.length && y - 1 >= 0 && raster[x+1][y-1]) {
      neighbours++;
    }
    //unten rechts
    if(x + 1 < raster.length && y + 1 < raster[x].length && raster[x+1][y+1]) {
      neighbours++;
    }
    //unten links
    if(x - 1 >= 0 && y + 1 < raster[x].length && raster[x-1][y+1]) {
      neighbours++;
    }
    //oben links
    if(x - 1 >= 0 && y - 1 >= 0 && raster[x-1][y-1]) {
      neighbours++;
    }
  
    return neighbours;
  }
}



