package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {

    public static String getForwardedVariables(String conf) {
        String[] trying = conf.split("\n");
        String wordToFilter = "X_FORWARDED_";
        return Arrays.stream(trying)
                .filter(x -> x.startsWith("environment="))
                .filter(x -> x.contains(wordToFilter))
                .map(x -> x.substring(x.indexOf(wordToFilter) + wordToFilter.length()))
                .map(x -> {
                    if (!x.contains(wordToFilter)) {
                        if (x.contains(",")) {
                            return x.substring(0, x.indexOf(','));
                        }
                        return x.replaceAll(",", "");
                    } else {
                        return getResult(x, wordToFilter);
                    }
                })
                .map(x -> x.replaceAll("[\\[\\]\"]", "")
                        .trim())
                .collect(Collectors.joining(","));
    }

    public static String getResult(String sentence, String word) {
        String stringBuilder = sentence.substring(0, sentence.indexOf(","));
        String stringToAdd;
        while (sentence.contains(word)) {
            stringToAdd = sentence.substring(sentence.indexOf(word) + word.length());
            stringToAdd = stringToAdd.substring(0, stringToAdd.indexOf(","));
            stringBuilder = stringBuilder + "|" + stringToAdd;
            sentence = sentence.replace(word, "");
        }
        return stringBuilder.replace("|", ",");
    }
}
//END
