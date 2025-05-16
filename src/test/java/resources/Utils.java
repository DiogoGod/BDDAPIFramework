package resources;


import io.restassured.builder.RequestSpecBuilder;



import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    private RequestSpecification req;
    private static PrintStream logStream;


    public RequestSpecification requestSpecification() throws IOException {
       File logFile = new File("target/logging.txt");
       logFile.getParentFile().mkdirs();

       PrintStream log= new PrintStream(new FileOutputStream(logFile,true),true);
       return new RequestSpecBuilder()
               .setBaseUri(getGlobalValue("baseUrl"))
               .addFilter(new RequestLoggingFilter(log))
               .addFilter(new ResponseLoggingFilter(log))
               .setContentType(ContentType.JSON)
               .build();
    }


    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }



    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }


    public static void closeLogStream() {
        if (logStream != null) {
            logStream.close();
        }
    }
}
