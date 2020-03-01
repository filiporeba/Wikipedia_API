package wiki.secondOption;

import java.net.URLEncoder;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WikipediaSearcherTwo {

    public static void main(String[] args) {
        WikiSearch();
    }

    private static void WikiSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'n' to exit");
        System.out.print("Type your sentence or correct football club name(e.g Tottenham Hotspur F.C)");

        String coding = "ISO-8859-2";

        try {
            while (true) {
                System.out.print(": ");

                String sentenceToSearch = scanner.nextLine();

                if (sentenceToSearch.equals("n") || sentenceToSearch.equals("N"))
                    break;

                sentenceToSearch += " Wikipedia";

                Document google = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(sentenceToSearch,coding)).get();

                String getWikiURL = google.getElementsByTag("cite").get(0).text();

                String replace = getWikiURL.replace(" › ", "/");
                System.out.println("https://" + replace + "_");

            }
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
        }
        scanner.close();
    }
}