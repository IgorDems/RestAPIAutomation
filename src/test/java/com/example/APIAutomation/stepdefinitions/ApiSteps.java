package com.example.APIAutomation.stepdefinitions;
import com.example.APIAutomation.utils.ApiPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class ApiSteps {

    ApiPage apiPage = new ApiPage();
    Response response;

    @When("I get all objects")
    public void iGetAllObjects() {
        response = apiPage.getAllObjects();
    }

    @Then("I should see phone with lowest price")
    public void iShouldSeePhoneWithLowestPrice() {
        List<Map<String, Object>> objects = response.jsonPath().getList("$");

        Optional<Map<String, Object>> lowestPricedPhone = objects.stream()
                .filter(obj -> obj.get("name") != null && obj.get("name").toString().toLowerCase().contains("phone"))
                .map(obj -> {
                    Map<String, Object> data = (Map<String, Object>) obj.get("data");
                    if (data != null && data.get("price") != null) {
                        obj.put("price", Double.parseDouble(data.get("price").toString()));
                    } else {
                        obj.put("price", Double.MAX_VALUE); // Assign a high value to ignore this entry
                    }
                    return obj;
                })
                .min((obj1, obj2) -> ((Double) obj1.get("price")).compareTo((Double) obj2.get("price")));

        if (lowestPricedPhone.isPresent()) {
            Map<String, Object> phone = lowestPricedPhone.get();
            String phoneName = phone.get("name").toString();
            Double lowestPrice = (Double) phone.get("price");
            System.out.println("Phone with Lowest Price: " + phoneName + " - Price: " + lowestPrice);
        } else {
            Assert.fail("No valid phone price found in the response");
        }
    }



    @Then("I should see device with lowest price")
    public void iShouldSeeDeviceWithLowestPrice() {
        List<Map<String, Object>> objects = response.jsonPath().getList("$");

        Optional<Map<String, Object>> phoneWithLowestPrice = objects.stream()
                .filter(obj -> {
                    Map<String, Object> data = (Map<String, Object>) obj.get("data");
                    return data != null && data.get("price") != null;
                })
                .min((obj1, obj2) -> {
                    Map<String, Object> data1 = (Map<String, Object>) obj1.get("data");
                    Map<String, Object> data2 = (Map<String, Object>) obj2.get("data");
                    Double price1 = Double.parseDouble(data1.get("price").toString());
                    Double price2 = Double.parseDouble(data2.get("price").toString());
                    return price1.compareTo(price2);
                });

        if (phoneWithLowestPrice.isPresent()) {
            Map<String, Object> lowestPricePhone = phoneWithLowestPrice.get();
            String phoneName = (String) lowestPricePhone.get("name");
            Map<String, Object> data = (Map<String, Object>) lowestPricePhone.get("data");
            Double lowestPrice = Double.parseDouble(data.get("price").toString());
            System.out.println("Device with Lowest Price: " + phoneName + " - Price: " + lowestPrice);
        } else {
            Assert.fail("No valid price found in the response");
        }
    }


    @Then("I should see phone names starting with {string}")
    public void iShouldSeePhoneNamesStartingWith(String word) {
        List<Map<String, Object>> objects = response.jsonPath().getList("$");

        List<String> phoneNames = objects.stream()
                .filter(obj -> obj.get("name") != null && obj.get("name").toString().toLowerCase().contains("phone"))
                .map(obj -> obj.get("name").toString())
                .filter(name -> name.toLowerCase().startsWith(word.toLowerCase()))
                .collect(Collectors.toList());

        if (!phoneNames.isEmpty()) {
            phoneNames.forEach(name -> System.out.println("Phone name starting with " + word + ": " + name));
        } else {
            Assert.fail("No phone names starting with " + word);
        }
    }

    @Then("I should see all IDs are not null")
    public void iShouldSeeAllIDsAreNotNull() {
        List<String> ids = response.jsonPath().getList("id");
        for (String id : ids) {
            Assert.assertNotNull(id);
        }
    }
}
