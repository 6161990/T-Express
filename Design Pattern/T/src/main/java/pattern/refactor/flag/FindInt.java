package pattern.refactor.flag;

public class FindInt {

    /** Refactor Before */
    public static boolean findBefore(int[] data, int number) {
        boolean flag = false;

        for (int i = 0; i <data.length; i++) {
            if(data[i] == number){
                flag = true;
            }
        }
        return flag;
    }

    /** Refactor After 1 */
    public static boolean findAfter(int[] data, int number) {
        boolean found = false;

        for (int i = 0; i < data.length; i++) {
            if(data[i] == number){
                found = true;
                return found;
            }
        }
            return found;
    }

    /** Refactor After 2 */
    public static boolean findAfter2(int[] data, int number) {
        boolean found = false;

        for (int i = 0; i < data.length; i++) {
            if(data[i] == number){
                found = true;
                break;
            }
        }
        return found;
    }

    /** Refactor After 3 */
    public static boolean findAfter3(int[] data, int number) {

        for (int i = 0; i < data.length; i++) {
            if(data[i] == number) {
                return true;
            }
        }
        return false;
    }

}
