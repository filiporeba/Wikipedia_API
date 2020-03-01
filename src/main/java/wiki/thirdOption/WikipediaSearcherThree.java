package wiki.thirdOption;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class WikipediaSearcherThree {

    public static void main(String[] args) {
        WikiSearch();
    }

    private static void WikiSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'n' to exit");
        System.out.print("Type your sentence or correct football club name(e.g Tottenham Hotspur F.C)");

        try {
            while (true) {
                System.out.print(": ");
                String typedWord = scanner.nextLine();

                if (typedWord.equals("n") || typedWord.equals("N"))
                    break;

                String replaced = typedWord.replace(" ", "_");

                URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=info&generator=allpages&inprop=url&gapfrom=" + replaced + "&gaplimit=1");
                InputStreamReader streamReader = new InputStreamReader(url.openStream());
                JsonObject object = new JsonParser().parse(streamReader).getAsJsonObject();

                JsonElement query = object.get("query");
                String s = query.getAsJsonObject().get("pages").toString();
                String[] tabStr = s.split("\"");
                String pageID = tabStr[1];

                JsonElement page = query.getAsJsonObject().get("pages");
                JsonElement jsonElement = page.getAsJsonObject().get(pageID);

                System.out.println(jsonElement.getAsJsonObject().get("fullurl").toString());
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong :(");
        }
        scanner.close();
    }
}
