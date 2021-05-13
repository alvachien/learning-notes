import javax.sound.midi.SysexMessage;

public class ArrayTest
{
    public static void main(String[] args) 
    {
        String[] books = new String[]
        {
            "Book 1",
            "Book 2",
            "Book 3",
            "Book 4"
        };

        String[] names = {
            "name1",
            "name2",
            "name3"
        };
        String[] arArr = new String[5];

        String strCreateBooks = """
        String[] books = new String[] 
        {
            "Book 1",
            "Book 2",
            "Book 3",
            "Book 4"
        };
        """;
        System.out.print(strCreateBooks);
        System.out.println("Length of books: " + books.length);
        System.out.println();

        String strCreateNames = """
        String[] names = {
            "name1",
            "name2",
            "name3"
        };
        """;
        System.out.print(strCreateNames);
        System.out.println("Length of names: " + names.length);
        System.out.println();

        String strCreateArray = "String[] arArr = new String[5];";
        System.out.print(strCreateArray);
        System.out.println("Length of arArr: " + arArr.length);
        System.out.println();
    }
}
