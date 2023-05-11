import java.util.*;
// Class pa6
public class pa6 {
    // Constants
    public static final int BASE = 2;
    public static final int MAX_EXPONENT = 32;
    public static final int MIN_EXPONENT = 31;
    // Non-constants
    public static int[][] matrix;
    public static int size = 0;
    private static ArrayList<Thread> arrThreads = new ArrayList<Thread>();
    public static ArrayList<ThreadClass> arrData = new ArrayList<ThreadClass>();
    // Randomly assign values to each element of matrix in the range 2^(31-N) and 2^(32-N)
    public static void InitializeMatrix() {
    // Calculate maximum range
        int max = BASE;
        int maxLimit = MAX_EXPONENT - size;
        for( int i = 1; i < maxLimit; i++ ) {
            max = max * BASE;
        }
    // Calculate minimum range
        int min = BASE;
        int minLimit = MIN_EXPONENT - size;
        for( int i = 1; i < minLimit; i++ ) {
            min = min * BASE;
        }
        System.out.println( "\nRange: ( " + min + " - " + max + " )" );
    // Initialize matrix with random numbers between min and max values.
        Random randomNumber = new Random();
        for( int i = 0; i < size; ++i ) {
            for( int j = 0; j < size; ++j ) {
                matrix[ i ][ j ] = randomNumber.nextInt(( max - min ) + 1 ) + min;
            }
        }
    }
    // Display the elements of matrix
    public static void PrintMatrix() {
        System.out.println( "\n----- Matrix -----" );
        for( int i = 0; i < size; ++i ) {
            for( int j = 0; j < size; ++j ) {
                System.out.printf( "%10d", matrix[ i ][ j ] );
            }
            System.out.print( "\n");
        }
    }
    // Main function
    public static void main( String[] args ) {
        int matrixMin = 0;
        int matrixMax = 0;
        float matrixSum = 0;
        float matrixAvg = 0;
        long startTime = 0;
        long endTime = 0;
        long timeElapsed = 0;
        try {
            if( 1 == args.length ) {
                // Parse argument to get matrix size.
                size = Integer.parseInt( args[ 0 ] );
                System.out.println( "\nMatrix size: " + size);
                // Create matrix of required size.
                matrix = new int[ size ][ size ];
                // Initialize and display matrix.
                InitializeMatrix();
                PrintMatrix();
                // Start the timer for statistics.
                startTime = System.nanoTime();
               // Create size number of threads, each thread is responsible for one row of matrix
                for( int i = 0; i < size; i++ ) {
                    Thread threadObj = new Thread( new ThreadClass( i ) );
                    threadObj.start();
                    // Add threads to arraylist.
                    arrThreads.add( threadObj );
                }
                // This for loop will not stop execution of any thread, only it will come out when all threads areexecuted.
                for( int i = 0; i < arrThreads.size(); i++ ) {
                    arrThreads.get( i ).join();
                }
                // Calculate matrix total minimum, maximum, and average using results from each thread
                matrixMin = arrData.get( 0 ).threadMin;
                matrixMax = arrData.get( 0 ).threadMax;
                matrixSum = arrData.get( 0 ).threadAvg;
                for( int i = 1; i < arrData.size(); i++ ) {
                    if( matrixMin > arrData.get( i ).threadMin ) {
                        matrixMin = arrData.get( i ).threadMin;
                }
                if( matrixMax < arrData.get( i ).threadMax ) {
                    matrixMax = arrData.get( i ).threadMax;
                }
                matrixSum = matrixSum + arrData.get( i ).threadAvg;
                }
                matrixAvg = matrixSum / size;
                // Stop the timer after calculation.
                endTime = System.nanoTime();
                timeElapsed = endTime - startTime;
                // Display statistics for each thread and then matrix total.
                System.out.println( "\n----------- Statistics -----------");
                System.out.printf( "\n%s %13s %15s %15s\n", "Thread", "Minimum", "Maximum", "Average" );
                for( int i = 0; i < arrData.size(); i++ ) {
                    System.out.printf( "\n%5d %14d %15d %20f",
                    arrData.get( i ).index,
                    arrData.get( i ).threadMin,
                    arrData.get( i ).threadMax,
                    arrData.get( i ).threadAvg );
                }
                System.out.printf( "\n\nMatrix total result: Minimum: %d\tMaximum: %d\tAverage: %f", matrixMin, matrixMax, matrixAvg );
            System.out.println( "\nTime for calculation: " + timeElapsed + " nano sec" + " = " + timeElapsed / 1000000 + "ms\n" );
            }
            else {
            // If matrix size is not provided as command line argument, exit program.
                System.out.println( "Usage : java pa6 <matrix size>" );
                System.exit( 1 );
            }
        }
        catch ( Exception e ) {
            // Catching exception
            System.out.println ( "Exception!!!!" );
        }
    }
}//end pa6

    // Class implementing the runnable interface
class ThreadClass implements Runnable {
    public int index;
    public int threadMin;
    public int threadMax;
    public float threadAvg;
    // Use the index as thread-id and index of matrix.
    ThreadClass( int tid ) {
        this.index = tid;
        this.threadMin = 0;
        this.threadMax = 0;
        this.threadAvg = 0;
    }
    //Override run() method
    public void run() {
        try {
        //System.out.println( "\nRunning Thread: " + index );
            threadMin = pa6.matrix[ index ][ 0 ];
            threadMax = pa6.matrix[ index ][ 0 ];
            int sum = 0;
            // Calculate row minimum, maximum and average.
            for( int i = 0; i < pa6.size; i++ ) {
                if( threadMin > pa6.matrix[ index ][ i ] ) {
                    threadMin = pa6.matrix[ index ][ i ];
                }
                if( threadMax < pa6.matrix[ index ][ i ] ) {
                threadMax = pa6.matrix[ index ][ i ];
                }
                sum = sum + pa6.matrix[ index ][ i ];
            }
            threadAvg = sum / pa6.size;
            // Store the data in array list.
            pa6.arrData.add( this );
            Thread.sleep( 1000 );
        }
        catch ( Exception e ) {
        // Catching exception
            System.out.println ( "Exception!!!!" );
        }       
    }
}//end runnable
