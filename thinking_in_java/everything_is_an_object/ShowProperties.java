public class ShowProperties {
  public static void main(String[] args) {
      // All properties
      System.getProperties().list(System.out);

      System.out.println(System.getProperty("user.name"));
      System.out.println(System.getProperty("java.library.path"));
  }
}
