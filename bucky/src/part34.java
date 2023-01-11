public class part34 {
    public static void main(String[] args) {
        int[][] firstarray={{8,9,10,11},{12,13,14,15}};
        int[][] secondarray={{30,31,32,33},{43},{4,5,6}};

        System.out.println("This is the first array");
        display(firstarray);

        System.out.println("This is the second array");
        display(secondarray);
    }
    public static void display(int[][]x){
        for (int[] row : x) {
            for (int col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }
}
