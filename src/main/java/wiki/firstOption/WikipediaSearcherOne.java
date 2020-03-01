package wiki.firstOption;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class WikipediaSearcherOne {

    public static void main(String[] args) {
        WikipediaSearch();
    }

    private static void WikipediaSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'n' to exit");
        System.out.print("Type your football club name");

        try {
            while (true) {
                System.out.print(": ");
                String sentenceToSearch = scanner.nextLine();
                String correcrtSentence = sentenceToSearch.replace(" ", "_");

                if (sentenceToSearch.equals("n") || sentenceToSearch.equals("N"))
                    break;

                URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch=\"" + correcrtSentence + "\"&srlimit=1");
                InputStreamReader streamReader = new InputStreamReader(url.openStream());
                WikipediaApi wikiURL = new Gson().fromJson(streamReader, WikipediaApi.class);

                String titleURL = wikiURL.getQuery().getSearch().get(0).getTitle();
                String replaced = titleURL.replace(" ", "_");

                System.out.println("https://en.wikipedia.org/wiki/" + replaced);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
        }
        scanner.close();
    }
}
