package ShoppingCartApp.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocalizationService {
    /**
     * Get localized strings for a specific locale
     */
    public static Map<String, String> getLocalizedStrings(Locale language) {
        Map<String, String> strings = new HashMap<>();

        Connection connection = DatabaseConnection.getInstance().getDBConnection();

        String query = "SELECT * FROM localization_strings WHERE language = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, language.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String key = resultSet.getString("key");
                String translation = resultSet.getString("value");
                strings.put(key, translation);
            }
        } catch (Exception e) {
            System.err.println("Failed to load localized strings from database for language: " + language);
            // Fallback to hardcoded defaults
            strings.put("itemNumberPrompt","Enter the number of items to purchase:");
            strings.put("itemPricePrompt","Enter the price of the item:");
            strings.put("itemQuantityPrompt","Enter the quantity of the item:");
            strings.put("totalCostMessage","Total cost:");
            strings.put("addItemPrompt","Add Item");
            strings.put("calcItemPrompt","Calculate Total");
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("Failed to close database connection: " + e.getMessage());
            }
        }

        return strings;
    }
}