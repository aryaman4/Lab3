import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.Arrays;
public class WebScraper {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    /**
     * Function which counts the number of occurrences of the given string in the webpage.
     * @param urlString Web page to search in.
     * @param searchString Word to search for.
     * @return Number of occurrences of the word.
     */
    public static int countWord(String urlString, String searchString) {
        int index = 0;
        int stringCount = 0;
        urlString = urlString.toUpperCase();
        searchString = searchString.toUpperCase();
        while (urlString.indexOf(searchString, index) != -1) {
            if (urlString.indexOf(searchString, index) != -1) {
                stringCount++;
                index = urlString.indexOf(searchString, index) + 1;
            }
        }
        return stringCount;
    }
    public static int uniqueCountWord(String urlString) {
        int prev = 0;
        int current = 0;
        int stringIndex = 0;
        String[] store = new String[urlString.length() + 10];
        while (urlString.indexOf(" ") != -1) {
                current = urlString.indexOf(" ", prev);
                if (current == -1) {
                    store[stringIndex] = urlString.substring(prev);
                    break;
                }
                store[stringIndex] = urlString.substring(prev, current);
                prev = current + 1;
                stringIndex++;
        }
        Arrays.sort(store, 0, stringIndex + 1);
        int uniqueCount = 0;
        for (int i = 0; i < stringIndex; i++) {
            if (store[i] != store[i+1]) {
                uniqueCount++;
            }
        }
        uniqueCount++;
        return  uniqueCount;
    }
    /**
     * Main function to implement program.
     * @param unused no use.
     */
    public static void main(String[] unused){
        System.out.println(uniqueCountWord(urlToString("http://erdani.com/tdpl/hamlet.txt")));
    }
}
